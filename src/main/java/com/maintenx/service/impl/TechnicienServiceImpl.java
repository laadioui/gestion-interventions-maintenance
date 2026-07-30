package com.maintenx.service.impl;
import com.maintenx.exception.*;
import com.maintenx.model.Technicien;
import com.maintenx.model.enums.Specialite;
import com.maintenx.model.enums.StatutIntervention;
import com.maintenx.service.*;
import com.maintenx.validation.InputValidator;
import java.util.Comparator;
import java.util.List;
public class TechnicienServiceImpl implements TechnicienService {
    private final InMemoryStore store; private final JournalActiviteService journal;
    public TechnicienServiceImpl(InMemoryStore store, JournalActiviteService journal) { this.store = store; this.journal = journal; }
    public List<Technicien> findAll() { refreshWorkload(); return store.techniciens.stream().sorted(Comparator.comparing(Technicien::getId)).toList(); }
    public List<Technicien> search(String text, Specialite specialite, Boolean disponible) {
        String q = text == null ? "" : text.toLowerCase();
        return findAll().stream()
                .filter(t -> q.isBlank() || (t.getMatricule()+" "+t.getNom()+" "+t.getPrenom()+" "+t.getSpecialite()).toLowerCase().contains(q))
                .filter(t -> specialite == null || t.getSpecialite() == specialite)
                .filter(t -> disponible == null || t.isDisponible() == disponible)
                .toList();
    }
    public Technicien create(Technicien t) {
        validate(t);
        if (store.techniciens.stream().anyMatch(x -> x.getMatricule().equalsIgnoreCase(t.getMatricule()))) throw new DuplicateResourceException("Matricule déjà utilisé.");
        t.setId(store.techSeq.getAndIncrement()); store.techniciens.add(t); journal.log("system", "TECHNICIEN_CREATE", t.getMatricule()); return t;
    }
    public void update(Technicien t) {
        validate(t); var e = byId(t.getId());
        e.setMatricule(t.getMatricule()); e.setNom(t.getNom()); e.setPrenom(t.getPrenom()); e.setEmail(t.getEmail()); e.setTelephone(t.getTelephone());
        e.setSpecialite(t.getSpecialite()); e.setDisponible(t.isDisponible()); e.setActif(t.isActif());
    }
    public void deactivate(long id) { byId(id).setActif(false); byId(id).setDisponible(false); }
    public Technicien requireAssignable(long id) { var t = byId(id); if (!t.isActif()) throw new BusinessException("Un technicien inactif ne peut pas recevoir une intervention."); return t; }
    public void refreshWorkload() {
        for (var t : store.techniciens) {
            long count = store.interventions.stream().filter(i -> i.getTechnicien() != null && i.getTechnicien().getId() == t.getId())
                    .filter(i -> i.getStatut() != StatutIntervention.TERMINEE && i.getStatut() != StatutIntervention.ANNULEE).count();
            t.setInterventionsEnCours((int) count); t.setDisponible(t.isActif() && count < 5);
        }
    }
    private Technicien byId(long id) { return store.techniciens.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("Technicien introuvable.")); }
    private void validate(Technicien t) { InputValidator.required(t.getMatricule(), "matricule"); InputValidator.required(t.getNom(), "nom"); InputValidator.required(t.getPrenom(), "prénom"); InputValidator.email(t.getEmail()); InputValidator.phone(t.getTelephone()); if (t.getSpecialite() == null) throw new ValidationException("La spécialité est obligatoire."); }
}
