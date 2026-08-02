package com.maintenx.service;

import com.maintenx.model.*;
import com.maintenx.model.enums.*;
import com.maintenx.util.PasswordHasher;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class DemoDataFactory {
    private DemoDataFactory() {}
    public static InMemoryStore createStore() {
        var s = new InMemoryStore();
        s.utilisateurs.add(new Utilisateur(s.userSeq.getAndIncrement(), "LAADIOUI", "Othmane", "admin@maintenx.local", "admin", PasswordHasher.hash("Admin123!"), Role.ADMINISTRATEUR, "0600000001", true));
        s.utilisateurs.add(new Utilisateur(s.userSeq.getAndIncrement(), "El Ghazi", "Anass", "responsable@maintenx.local", "responsable", PasswordHasher.hash("Resp123!"), Role.RESPONSABLE, "0600000002", true));
        s.utilisateurs.add(new Utilisateur(s.userSeq.getAndIncrement(), "Technicien", "Ali", "tech@maintenx.local", "tech", PasswordHasher.hash("Tech123!"), Role.TECHNICIEN, "0600000003", true));
        s.utilisateurs.add(new Utilisateur(s.userSeq.getAndIncrement(), "Demandeur", "Test", "demandeur@maintenx.local", "demandeur", PasswordHasher.hash("Dem123!"), Role.DEMANDEUR, "0600000004", true));
        s.techniciens.add(new Technicien(s.techSeq.getAndIncrement(), "TEC-001", "Benali", "Youssef", "y.benali@sirecom.local", "0611111111", Specialite.INFORMATIQUE, true, true));
        s.techniciens.add(new Technicien(s.techSeq.getAndIncrement(), "TEC-002", "Haddad", "Sara", "s.haddad@sirecom.local", "0622222222", Specialite.RESEAU, true, true));
        s.techniciens.add(new Technicien(s.techSeq.getAndIncrement(), "TEC-003", "Amrani", "Karim", "k.amrani@sirecom.local", "0633333333", Specialite.ELECTRICITE, true, true));
        String[] cats = {"Informatique", "Réseau", "Électricité", "Climatisation", "Plomberie"};
        for (int i = 1; i <= 20; i++) {
            var in = new Intervention();
            in.setId(s.intSeq.getAndIncrement());
            in.setReference("INT-2026-" + String.format("%04d", i));
            in.setTitre("Intervention de démonstration " + i);
            in.setDescription("Demande réaliste de maintenance numéro " + i);
            in.setCategorie(cats[i % cats.length]);
            in.setLocalisation("Site " + ((i % 4) + 1));
            in.setEquipement("Équipement " + i);
            in.setDateSouhaitee(LocalDate.now().plusDays(i % 8));
            in.setPriorite(Priorite.values()[i % Priorite.values().length]);
            in.setStatut(StatutIntervention.values()[i % StatutIntervention.values().length]);
            in.setDemandeur(s.utilisateurs.get(i % s.utilisateurs.size()));
            if (i % 2 == 0) in.setTechnicien(s.techniciens.get(i % s.techniciens.size()));
            in.setCoutEstime(BigDecimal.valueOf(250 + i * 15L));
            in.setCommentaire("Commentaire initial");
            if (in.getStatut() == StatutIntervention.TERMINEE) in.setSolutionAppliquee("Solution appliquée et validée.");
            s.interventions.add(in);
            s.historiques.add(new HistoriqueIntervention(s.histSeq.getAndIncrement(), in.getId(), "CREATION", "", in.getStatut().name(), "system"));
        }
        return s;
    }
}
