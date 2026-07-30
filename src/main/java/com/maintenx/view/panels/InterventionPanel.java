package com.maintenx.view.panels;

import com.maintenx.controller.*;
import com.maintenx.model.*;
import com.maintenx.model.enums.*;
import com.maintenx.service.InterventionSearchCriteria;
import com.maintenx.util.ErrorHandler;
import com.maintenx.view.components.Ui;
import com.maintenx.view.dialogs.*;
import com.maintenx.view.renderers.StatusCellRenderer;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class InterventionPanel extends JPanel {
    private final InterventionController controller;
    private final TechnicienController techController;
    private final Utilisateur user;
    private List<Intervention> data;
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "R\u00e9f\u00e9rence", "Titre", "Cat\u00e9gorie", "Priorit\u00e9", "Statut", "Localisation", "Technicien"}, 0);
    private final JTable table;
    private final JTextField searchField = Ui.field(15);

    public InterventionPanel(InterventionController c, TechnicienController t, Utilisateur user) {
        this.controller = c;
        this.techController = t;
        this.user = user;

        setLayout(new BorderLayout(0, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        var addBtn = Ui.button("+ Nouvelle intervention");
        var editBtn = Ui.buttonOutlined("Modifier");
        var assignBtn = Ui.buttonOutlined("Affecter");
        var statusBtn = Ui.buttonOutlined("Changer statut");
        var detailsBtn = Ui.buttonOutlined("D\u00e9tails");
        var exportBtn = Ui.buttonGhost("Exporter CSV");
        var refreshBtn = Ui.buttonGhost("Actualiser");

        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, "search");
        var searchBar = Ui.searchBar(searchField, null, addBtn, editBtn, assignBtn, statusBtn, detailsBtn, exportBtn, refreshBtn);
        add(Ui.header("Interventions", searchBar), BorderLayout.NORTH);

        table = Ui.styledTable(model);
        table.setDefaultRenderer(Object.class, new StatusCellRenderer());
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        var scroll = Ui.scrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        add(scroll, BorderLayout.CENTER);

        addBtn.addActionListener(e -> open(null));
        editBtn.addActionListener(e -> selected(this::open));
        assignBtn.addActionListener(e -> selected(i -> {
            var d = new AffectationDialog(SwingUtilities.getWindowAncestor(this), techController.all());
            d.setVisible(true);
            if (d.getSelected() != null) {
                controller.assign(i.getId(), d.getSelected().getId());
                refresh();
            }
        }));
        statusBtn.addActionListener(e -> selected(i -> {
            var st = (StatutIntervention) JOptionPane.showInputDialog(this,
                    "Nouveau statut", "Changer le statut", JOptionPane.QUESTION_MESSAGE,
                    null, StatutIntervention.values(), i.getStatut());
            if (st != null) {
                String sol = st == StatutIntervention.TERMINEE
                        ? JOptionPane.showInputDialog(this, "Solution appliqu\u00e9e :")
                        : "";
                controller.status(i.getId(), st, sol);
                refresh();
            }
        }));
        detailsBtn.addActionListener(e ->
                selected(i -> new InterventionDetailsDialog(SwingUtilities.getWindowAncestor(this), i).setVisible(true)));
        exportBtn.addActionListener(e -> {
            try {
                var chooser = new JFileChooser();
                chooser.setSelectedFile(new File("interventions.csv"));
                if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    controller.export(data, chooser.getSelectedFile());
                    JOptionPane.showMessageDialog(this, "Export termin\u00e9.", "Succ\u00e8s",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                ErrorHandler.show(this, ex.getMessage(), ex);
            }
        });
        refreshBtn.addActionListener(e -> refresh());
        searchField.addActionListener(e -> refresh());

        refresh();
    }

    private void open(Intervention i) {
        try {
            var d = new InterventionFormDialog(SwingUtilities.getWindowAncestor(this), i);
            d.setVisible(true);
            if (d.isSaved()) {
                if (i == null)
                    controller.create(d.getIntervention());
                else
                    controller.update(d.getIntervention());
                refresh();
            }
        } catch (Exception ex) {
            ErrorHandler.show(this, ex.getMessage(), ex);
        }
    }

    private void selected(java.util.function.Consumer<Intervention> a) {
        int r = table.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Veuillez s\u00e9lectionner une intervention.",
                    "S\u00e9lection requise", JOptionPane.WARNING_MESSAGE);
            return;
        }
        long id = (long) model.getValueAt(table.convertRowIndexToModel(r), 0);
        data.stream().filter(i -> i.getId() == id).findFirst().ifPresent(a);
    }

    private void refresh() {
        String search = searchField.getText().trim();
        var all = controller.all();
        if (!search.isEmpty()) {
            var sc = new InterventionSearchCriteria();
            sc.titre = search;
            sc.reference = search;
            all = controller.search(sc);
        }
        data = all;
        model.setRowCount(0);
        for (var i : data)
            model.addRow(new Object[]{
                    i.getId(), i.getReference(), i.getTitre(), i.getCategorie(),
                    i.getPriorite(), i.getStatut(), i.getLocalisation(),
                    i.getTechnicien() == null ? "" : i.getTechnicien().nomComplet()
            });
    }
}
