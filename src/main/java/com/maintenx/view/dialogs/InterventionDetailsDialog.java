package com.maintenx.view.dialogs;

import com.maintenx.model.Intervention;
import com.maintenx.view.components.Ui;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class InterventionDetailsDialog extends JDialog {
    public InterventionDetailsDialog(Window owner, Intervention i) {
        super(owner, "D\u00e9tails - " + i.getReference(), ModalityType.APPLICATION_MODAL);
        setSize(580, 500);
        setLocationRelativeTo(owner);

        var root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setOpaque(true);

        var header = new JPanel(new BorderLayout(8, 0));
        header.setOpaque(false);
        header.add(Ui.title(i.getReference()), BorderLayout.WEST);

        var statusBadge = Ui.statusBadge(i.getStatut().name());
        header.add(statusBadge, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        var content = new JPanel(new GridBagLayout());
        content.setOpaque(true);
        content.setBackground(Color.WHITE);
        content.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
        content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        var gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 0, 3, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;

        addDetailRow(content, gbc, "Titre", i.getTitre());
        addDetailRow(content, gbc, "Description", n(i.getDescription()));
        addDetailRow(content, gbc, "Cat\u00e9gorie", i.getCategorie());
        addDetailRow(content, gbc, "Localisation", i.getLocalisation());
        addDetailRow(content, gbc, "\u00c9quipement", n(i.getEquipement()));
        addDetailRow(content, gbc, "Priorit\u00e9", i.getPriorite().name());
        addDetailRow(content, gbc, "Statut", i.getStatut().name());
        addDetailRow(content, gbc, "Demandeur", i.getDemandeur() == null ? "" : i.getDemandeur().nomComplet());
        addDetailRow(content, gbc, "Technicien", i.getTechnicien() == null ? "Non affect\u00e9" : i.getTechnicien().nomComplet());
        addDetailRow(content, gbc, "Diagnostic", n(i.getDiagnostic()));
        addDetailRow(content, gbc, "Solution", n(i.getSolutionAppliquee()));
        addDetailRow(content, gbc, "Co\u00fbt estim\u00e9", String.valueOf(i.getCoutEstime()));
        addDetailRow(content, gbc, "Co\u00fbt r\u00e9el", n(i.getCoutReel() != null ? String.valueOf(i.getCoutReel()) : ""));

        var scroll = new JScrollPane(content);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        root.add(scroll, BorderLayout.CENTER);

        var closeBtn = Ui.button("Fermer");
        closeBtn.addActionListener(e -> dispose());
        var btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnPanel.add(closeBtn);
        root.add(btnPanel, BorderLayout.SOUTH);

        add(root);
    }

    private void addDetailRow(JPanel panel, GridBagConstraints gbc, String label, String value) {
        var lbl = new JLabel(label);
        lbl.setFont(Ui.FONT_SMALL);
        lbl.setForeground(Ui.TEXT_SECONDARY);
        panel.add(lbl, gbc);
        var val = new JLabel(value.isBlank() ? "-" : value);
        val.setFont(Ui.FONT_REGULAR);
        val.setForeground(Ui.TEXT_PRIMARY);
        panel.add(val, gbc);
    }

    private String n(String s) { return s == null ? "" : s; }
}
