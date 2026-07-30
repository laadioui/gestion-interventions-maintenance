package com.maintenx.view.panels;

import com.maintenx.service.JournalActiviteService;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JournalActivitePanel extends JPanel {
    public JournalActivitePanel(JournalActiviteService service) {
        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        add(Ui.header("Journal d'activit\u00e9"), BorderLayout.NORTH);

        var model = new DefaultTableModel(
                new String[]{"Date", "Utilisateur", "Action", "D\u00e9tails"}, 0);

        for (var j : service.findAll())
            model.addRow(new Object[]{
                    j.getDateAction(), j.getUtilisateur(), j.getAction(), j.getDetails()
            });

        var table = Ui.styledTable(model);
        add(Ui.scrollPane(table), BorderLayout.CENTER);
    }
}
