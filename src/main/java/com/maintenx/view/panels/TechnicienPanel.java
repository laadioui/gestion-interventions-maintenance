package com.maintenx.view.panels;

import com.maintenx.controller.TechnicienController;
import com.maintenx.model.Technicien;
import com.maintenx.util.ErrorHandler;
import com.maintenx.view.components.Ui;
import com.maintenx.view.dialogs.TechnicienFormDialog;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TechnicienPanel extends JPanel {
    private final TechnicienController controller;
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Matricule", "Nom", "Pr\u00e9nom", "Email",
                    "Sp\u00e9cialit\u00e9", "Disponible", "Actif", "Ouvertes"}, 0);
    private final JTable table;
    private final JTextField searchField = Ui.field(15);

    public TechnicienPanel(TechnicienController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(0, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        var addBtn = Ui.button("+ Ajouter");
        var editBtn = Ui.buttonOutlined("Modifier");
        var deactivateBtn = Ui.buttonOutlined("D\u00e9sactiver");

        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, "search");
        var searchBar = Ui.searchBar(searchField, null, addBtn, editBtn, deactivateBtn);
        add(Ui.header("Techniciens", searchBar), BorderLayout.NORTH);

        table = Ui.styledTable(model);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        var scroll = Ui.scrollPane(table);
        add(scroll, BorderLayout.CENTER);

        searchField.addActionListener(e -> refresh());
        addBtn.addActionListener(e -> open(null));
        editBtn.addActionListener(e -> selected(t -> open(t)));
        deactivateBtn.addActionListener(e -> selected(t -> {
            if (JOptionPane.showConfirmDialog(this,
                    "D\u00e9sactiver le technicien " + t.nomComplet() + " ?",
                    "Confirmation", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                controller.deactivate(t.getId());
                refresh();
            }
        }));

        refresh();
    }

    private void open(Technicien t) {
        try {
            var d = new TechnicienFormDialog(SwingUtilities.getWindowAncestor(this), t);
            d.setVisible(true);
            if (d.isSaved()) {
                if (t == null) controller.create(d.getTechnicien());
                else controller.update(d.getTechnicien());
                refresh();
            }
        } catch (Exception ex) {
            ErrorHandler.show(this, ex.getMessage(), ex);
        }
    }

    private void selected(java.util.function.Consumer<Technicien> a) {
        int r = table.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Veuillez s\u00e9lectionner un technicien.",
                    "S\u00e9lection requise", JOptionPane.WARNING_MESSAGE);
            return;
        }
        long id = (long) model.getValueAt(table.convertRowIndexToModel(r), 0);
        controller.all().stream().filter(t -> t.getId() == id).findFirst().ifPresent(a);
    }

    private void refresh() {
        model.setRowCount(0);
        String search = searchField.getText().trim();
        for (var t : controller.search(search, null, null))
            model.addRow(new Object[]{
                    t.getId(), t.getMatricule(), t.getNom(), t.getPrenom(),
                    t.getEmail(), t.getSpecialite(), t.isDisponible() ? "Oui" : "Non",
                    t.isActif() ? "Actif" : "Inactif", t.getInterventionsEnCours()
            });
    }
}
