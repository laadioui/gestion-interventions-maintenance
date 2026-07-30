package com.maintenx.view.panels;

import com.maintenx.controller.UtilisateurController;
import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Role;
import com.maintenx.util.ErrorHandler;
import com.maintenx.view.components.Ui;
import com.maintenx.view.renderers.StatusCellRenderer;
import com.maintenx.view.dialogs.UtilisateurFormDialog;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UtilisateurPanel extends JPanel {
    private final UtilisateurController controller;
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Nom", "Pr\u00e9nom", "Email", "Login", "R\u00f4le", "Actif"}, 0);
    private final JTable table;
    private final JTextField searchField = Ui.field(15);

    public UtilisateurPanel(UtilisateurController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(0, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        var addBtn = Ui.button("+ Ajouter");
        var editBtn = Ui.buttonOutlined("Modifier");
        var toggleBtn = Ui.buttonOutlined("Activer/D\u00e9sactiver");
        var resetBtn = Ui.buttonGhost("R\u00e9initialiser mot de passe");

        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, "search");
        var searchBar = Ui.searchBar(searchField, null, addBtn, editBtn, toggleBtn, resetBtn);
        add(Ui.header("Utilisateurs", searchBar), BorderLayout.NORTH);

        table = Ui.styledTable(model);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        var scroll = Ui.scrollPane(table);
        add(scroll, BorderLayout.CENTER);

        searchField.addActionListener(e -> refresh());
        addBtn.addActionListener(e -> open(null));
        editBtn.addActionListener(e -> selected(u -> open(u)));
        toggleBtn.addActionListener(e -> selected(u -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Confirmer l'activation/d\u00e9sactivation de " + u.nomComplet() + " ?",
                    "Confirmation", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                if (u.isActif()) controller.deactivate(u.getId());
                else controller.activate(u.getId());
                refresh();
            }
        }));
        resetBtn.addActionListener(e -> selected(u -> {
            String pwd = JOptionPane.showInputDialog(this,
                    "Nouveau mot de passe pour " + u.nomComplet());
            if (pwd != null && !pwd.isBlank()) {
                controller.resetPassword(u.getId(), pwd);
                JOptionPane.showMessageDialog(this, "Mot de passe r\u00e9initialis\u00e9.",
                        "Succ\u00e8s", JOptionPane.INFORMATION_MESSAGE);
            }
        }));

        refresh();
    }

    private void open(Utilisateur u) {
        try {
            var d = new UtilisateurFormDialog(SwingUtilities.getWindowAncestor(this), u);
            d.setVisible(true);
            if (d.isSaved()) {
                if (u == null) controller.create(d.getUtilisateur(), d.getPassword());
                else controller.update(d.getUtilisateur());
                refresh();
            }
        } catch (Exception ex) {
            ErrorHandler.show(this, ex.getMessage(), ex);
        }
    }

    private void selected(java.util.function.Consumer<Utilisateur> action) {
        int r = table.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Veuillez s\u00e9lectionner un utilisateur.",
                    "S\u00e9lection requise", JOptionPane.WARNING_MESSAGE);
            return;
        }
        long id = (long) model.getValueAt(table.convertRowIndexToModel(r), 0);
        controller.search("", null, null).stream()
                .filter(u -> u.getId() == id).findFirst().ifPresent(action);
    }

    private void refresh() {
        model.setRowCount(0);
        String search = searchField.getText().trim();
        for (var u : controller.search(search, (Role) null, null))
            model.addRow(new Object[]{
                    u.getId(), u.getNom(), u.getPrenom(), u.getEmail(),
                    u.getNomUtilisateur(), u.getRole(), u.isActif() ? "Actif" : "Inactif"
            });
    }
}
