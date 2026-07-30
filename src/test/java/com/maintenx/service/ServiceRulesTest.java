package com.maintenx.service;
import com.maintenx.exception.*;
import com.maintenx.model.*;
import com.maintenx.model.enums.*;
import com.maintenx.service.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ServiceRulesTest {
    InMemoryStore store; JournalActiviteService journal; UtilisateurService users; TechnicienService techs; HistoriqueService hist; InterventionService ints; Utilisateur admin;
    @BeforeEach void setup() { store = DemoDataFactory.createStore(); journal = new JournalActiviteServiceImpl(store); users = new UtilisateurServiceImpl(store, journal); techs = new TechnicienServiceImpl(store, journal); hist = new HistoriqueServiceImpl(store); ints = new InterventionServiceImpl(store, techs, hist, journal); admin = store.utilisateurs.get(0); }
    @Test void creationUtilisateurValide() { var u = new Utilisateur(0,"Test","User","test@local.ma","testuser","",Role.RESPONSABLE,"0600000000",true); assertTrue(users.create(u,"Password1!").getId() > 0); }
    @Test void refusUtilisateurDuplique() { var u = new Utilisateur(0,"Dup","User","admin@maintenx.local","dup","",Role.RESPONSABLE,"0600000000",true); assertThrows(DuplicateResourceException.class, () -> users.create(u,"Password1!")); }
    @Test void creationIntervention() { var i = intervention(); var saved = ints.create(i, admin); assertEquals(StatutIntervention.OUVERTE, saved.getStatut()); assertNotNull(saved.getReference()); }
    @Test void affectationTechnicien() { var i = ints.create(intervention(), admin); ints.assign(i.getId(), store.techniciens.get(0).getId(), admin); assertEquals(StatutIntervention.AFFECTEE, i.getStatut()); assertNotNull(i.getTechnicien()); }
    @Test void refusTechnicienInactif() { var i = ints.create(intervention(), admin); store.techniciens.get(0).setActif(false); assertThrows(BusinessException.class, () -> ints.assign(i.getId(), store.techniciens.get(0).getId(), admin)); }
    @Test void passageTermineAvecSolution() { var i = ints.create(intervention(), admin); ints.changeStatus(i.getId(), StatutIntervention.TERMINEE, "Remplacement terminé", admin); assertEquals(StatutIntervention.TERMINEE, i.getStatut()); assertNotNull(i.getDateFin()); }
    @Test void refusClotureSansSolution() { var i = ints.create(intervention(), admin); assertThrows(ValidationException.class, () -> ints.changeStatus(i.getId(), StatutIntervention.TERMINEE, "", admin)); }
    @Test void historiqueCree() { var i = ints.create(intervention(), admin); assertFalse(hist.findByIntervention(i.getId()).isEmpty()); }
    @Test void administrateurNeSeDesactivePas() { assertThrows(BusinessException.class, () -> users.deactivate(admin.getId(), admin.getId())); }
    private Intervention intervention() { var i = new Intervention(); i.setTitre("Test intervention"); i.setDescription("Description complète"); i.setCategorie("Informatique"); i.setLocalisation("Siège"); i.setEquipement("PC"); i.setPriorite(Priorite.HAUTE); return i; }
}
