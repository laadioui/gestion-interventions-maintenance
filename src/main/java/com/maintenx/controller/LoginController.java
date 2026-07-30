package com.maintenx.controller;

import com.maintenx.model.Utilisateur;
import com.maintenx.service.*;
import com.maintenx.view.MainFrame;
import javax.swing.*;

public class LoginController {
    private final AuthenticationService auth;
    private final UtilisateurService utilisateurs;
    private final TechnicienService techniciens;
    private final InterventionService interventions;
    private final DashboardService dashboard;
    private final HistoriqueService historique;
    private final JournalActiviteService journal;
    private final ExportService export;
    private final ConfigurationService configuration;
    public LoginController(AuthenticationService auth, UtilisateurService utilisateurs, TechnicienService techniciens, InterventionService interventions, DashboardService dashboard, HistoriqueService historique, JournalActiviteService journal, ExportService export, ConfigurationService configuration) {
        this.auth = auth; this.utilisateurs = utilisateurs; this.techniciens = techniciens; this.interventions = interventions; this.dashboard = dashboard; this.historique = historique; this.journal = journal; this.export = export; this.configuration = configuration;
    }
    public void login(JFrame loginFrame, String username, String password) {
        Utilisateur user = auth.login(username, password);
        loginFrame.dispose();
        new MainFrame(user, utilisateurs, techniciens, interventions, dashboard, historique, journal, export, configuration).setVisible(true);
    }
}
