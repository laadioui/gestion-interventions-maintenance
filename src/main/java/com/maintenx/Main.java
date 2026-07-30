package com.maintenx;

import com.formdev.flatlaf.FlatLightLaf;
import com.maintenx.controller.LoginController;
import com.maintenx.service.DemoDataFactory;
import com.maintenx.service.impl.*;
import com.maintenx.util.AppPreferences;
import com.maintenx.util.ErrorHandler;
import com.maintenx.view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FlatLightLaf.setup();
                AppPreferences.applySavedTheme();
                var store = DemoDataFactory.createStore();
                var journal = new JournalActiviteServiceImpl(store);
                var auth = new AuthenticationServiceImpl(store, journal);
                var utilisateurs = new UtilisateurServiceImpl(store, journal);
                var techniciens = new TechnicienServiceImpl(store, journal);
                var historiques = new HistoriqueServiceImpl(store);
                var interventions = new InterventionServiceImpl(store, techniciens, historiques, journal);
                var dashboard = new DashboardServiceImpl(store);
                var export = new ExportServiceImpl();
                var configuration = new ConfigurationServiceImpl();
                new LoginFrame(new LoginController(auth, utilisateurs, techniciens, interventions, dashboard, historiques, journal, export, configuration)).setVisible(true);
            } catch (Exception ex) {
                ErrorHandler.showFatal(null, "Impossible de démarrer MaintenX.", ex);
            }
        });
    }
}
