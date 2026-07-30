package com.maintenx.service.impl;
import com.maintenx.exception.*;
import com.maintenx.model.*;
import com.maintenx.model.enums.*;
import com.maintenx.service.*;
import com.maintenx.validation.InputValidator;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
public class InterventionServiceImpl implements InterventionService {
    private final InMemoryStore store; private final TechnicienService techniciens; private final HistoriqueService historiques; private final JournalActiviteService journal;
    public InterventionServiceImpl(InMemoryStore store, TechnicienService techniciens, HistoriqueService historiques, JournalActiviteService journal) { this.store = store; this.techniciens = techniciens; this.historiques = historiques; this.journal = journal; }
    public List<Intervention> findAuthorized(Utilisateur user) {
        var all = store.interventions.stream().sorted(Comparator.comparing(Intervention::getDateCreation).reversed());
        if (user.getRole() == Role.TECHNICIEN) return all.filter(i -> i.getTechnicien() != null && i.getTechnicien().getEmail().equalsIgnoreCase(user.getEmail())).toList();
        return all.toList();
    }
    public List<Intervention> search(InterventionSearchCriteria c, Utilisateur user) {
        return findAuthorized(user).stream()
                .filter(i -> blank(c.reference) || i.getReference().toLowerCase().contains(c.reference.toLowerCase()))
                .filter(i -> blank(c.titre) || i.getTitre().toLowerCase().contains(c.titre.toLowerCase()))
                .filter(i -> blank(c.categorie) || i.getCategorie().equalsIgnoreCase(c.categorie))
                .filter(i -> c.priorite == null || i.getPriorite() == c.priorite)
                .filter(i -> c.statut == null || i.getStatut() == c.statut)
                .filter(i -> blank(c.localisation) || i.getLocalisation().toLowerCase().contains(c.localisation.toLowerCase()))
                .filter(i -> blank(c.equipement) || i.getEquipement().toLowerCase().contains(c.equipement.toLowerCase()))
                .filter(i -> c.coutMin == null || i.getCoutReel().compareTo(c.coutMin) >= 0)
                .filter(i -> c.coutMax == null || i.getCoutReel().compareTo(c.coutMax) <= 0)
                .toList();
    }
    public Intervention create(Intervention i, Utilisateur actor) {
        validate(i); i.setId(store.intSeq.getAndIncrement()); i.setReference("INT-2026-" + String.format("%04d", i.getId())); i.setStatut(StatutIntervention.OUVERTE); i.setDemandeur(actor);
        store.interventions.add(i); historiques.add(i.getId(), "CREATION", "", i.getStatut().name(), actor.getNomUtilisateur()); journal.log(actor.getNomUtilisateur(), "INTERVENTION_CREATE", i.getReference()); return i;
    }
    public void update(Intervention i, Utilisateur actor) { validate(i); i.setDateModification(LocalDateTime.now()); historiques.add(i.getId(), "MODIFICATION", "", i.getTitre(), actor.getNomUtilisateur()); }
    public void assign(long interventionId, long technicienId, Utilisateur actor) {
        var i = byId(interventionId); var old = i.getTechnicien() == null ? "" : i.getTechnicien().toString(); var t = techniciens.requireAssignable(technicienId);
        i.setTechnicien(t); i.setStatut(StatutIntervention.AFFECTEE); historiques.add(i.getId(), "AFFECTATION", old, t.toString(), actor.getNomUtilisateur()); journal.log(actor.getNomUtilisateur(), "INTERVENTION_ASSIGN", i.getReference());
    }
    public void changeStatus(long interventionId, StatutIntervention status, String solution, Utilisateur actor) {
        var i = byId(interventionId); var old = i.getStatut();
        if (old == StatutIntervention.ANNULEE && status == StatutIntervention.EN_COURS) throw new BusinessException("Une intervention annulée ne peut pas repasser directement en cours.");
        if (status == StatutIntervention.TERMINEE) {
            InputValidator.required(solution, "solution appliquée");
            i.setSolutionAppliquee(solution); if (i.getDateFin() == null) i.setDateFin(LocalDateTime.now());
        }
        i.setStatut(status); if (status == StatutIntervention.EN_COURS && i.getDateDebut() == null) i.setDateDebut(LocalDateTime.now());
        historiques.add(i.getId(), "CHANGEMENT_STATUT", old.name(), status.name(), actor.getNomUtilisateur());
    }
    public void cancel(long interventionId, Utilisateur actor) { changeStatus(interventionId, StatutIntervention.ANNULEE, "", actor); }
    public File exportCsv(List<Intervention> interventions, File file) { return new ExportServiceImpl().exportInterventions(interventions, file); }
    private Intervention byId(long id) { return store.interventions.stream().filter(i -> i.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("Intervention introuvable.")); }
    private void validate(Intervention i) {
        InputValidator.required(i.getTitre(), "titre"); InputValidator.required(i.getDescription(), "description"); InputValidator.required(i.getCategorie(), "catégorie"); InputValidator.required(i.getLocalisation(), "localisation");
        InputValidator.nonNegative(i.getCoutEstime(), "coût estimé"); InputValidator.nonNegative(i.getCoutReel(), "coût réel"); InputValidator.chronological(i.getDateDebut(), i.getDateFin());
    }
    private boolean blank(String s) { return s == null || s.isBlank(); }
}
