package com.maintenx.view.panels;

import com.maintenx.controller.HistoriqueController;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoriquePanel extends JPanel {
    public HistoriquePanel(HistoriqueController c) {
        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        add(Ui.header("Historique des interventions"), BorderLayout.NORTH);

        var model = new DefaultTableModel(
                new String[]{"Date", "Intervention", "Action", "Ancienne valeur", "Nouvelle valeur", "Utilisateur"}, 0);

        for (var h : c.all())
            model.addRow(new Object[]{
                    h.getDateAction(), h.getInterventionId(), h.getAction(),
                    h.getAncienneValeur(), h.getNouvelleValeur(), h.getUtilisateur()
            });

        var table = Ui.styledTable(model);
        add(Ui.scrollPane(table), BorderLayout.CENTER);
    }
}
