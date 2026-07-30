package com.maintenx.view.panels;

import com.maintenx.controller.InterventionController;
import com.maintenx.service.InterventionSearchCriteria;
import com.maintenx.view.components.Ui;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RechercheAvanceePanel extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"R\u00e9f\u00e9rence", "Titre", "Cat\u00e9gorie", "Priorit\u00e9", "Statut", "Localisation", "\u00c9quipement"}, 0);

    public RechercheAvanceePanel(InterventionController c) {
        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        var refField = Ui.field(10);
        var titreField = Ui.field(12);
        var locField = Ui.field(10);
        var equipField = Ui.field(10);

        var searchBtn = Ui.button("Rechercher");
        var resetBtn = Ui.buttonOutlined("R\u00e9initialiser");

        var filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        filterBar.setOpaque(false);

        filterBar.add(createFilterItem("R\u00e9f.", refField));
        filterBar.add(createFilterItem("Titre", titreField));
        filterBar.add(createFilterItem("Localisation", locField));
        filterBar.add(createFilterItem("\u00c9quipement", equipField));
        filterBar.add(Box.createHorizontalStrut(8));
        filterBar.add(searchBtn);
        filterBar.add(resetBtn);

        add(Ui.header("Recherche avanc\u00e9e", filterBar), BorderLayout.NORTH);

        var table = Ui.styledTable(model);
        add(Ui.scrollPane(table), BorderLayout.CENTER);

        Runnable run = () -> {
            var cr = new InterventionSearchCriteria();
            cr.reference = refField.getText();
            cr.titre = titreField.getText();
            cr.localisation = locField.getText();
            cr.equipement = equipField.getText();
            model.setRowCount(0);
            for (var i : c.search(cr))
                model.addRow(new Object[]{
                        i.getReference(), i.getTitre(), i.getCategorie(),
                        i.getPriorite(), i.getStatut(), i.getLocalisation(), i.getEquipement()
                });
        };

        searchBtn.addActionListener(e -> run.run());
        resetBtn.addActionListener(e -> {
            refField.setText("");
            titreField.setText("");
            locField.setText("");
            equipField.setText("");
            run.run();
        });
        run.run();
    }

    private JPanel createFilterItem(String label, JComponent field) {
        var p = new JPanel(new BorderLayout(4, 2));
        p.setOpaque(false);
        var lbl = new JLabel(label);
        lbl.setFont(Ui.FONT_SMALL);
        lbl.setForeground(Ui.TEXT_SECONDARY);
        p.add(lbl, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }
}
