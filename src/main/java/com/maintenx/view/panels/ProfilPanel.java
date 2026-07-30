package com.maintenx.view.panels;

import com.maintenx.controller.ProfilController;
import com.maintenx.view.components.Ui;
import com.maintenx.view.dialogs.ChangerMotDePasseDialog;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class ProfilPanel extends JPanel {
    public ProfilPanel(ProfilController c) {
        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        add(Ui.header("Profil"), BorderLayout.NORTH);

        var u = c.current();

        var card = new JPanel(new GridBagLayout());
        card.setOpaque(true);
        card.setBackground(Color.WHITE);
        card.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
        card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        var gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;

        var avatar = new JLabel("\ud83d\udc64");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        avatar.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(avatar, gbc);

        var name = new JLabel(u.nomComplet());
        name.setFont(Ui.FONT_TITLE);
        name.setForeground(Ui.TEXT_PRIMARY);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(8, 0, 4, 0);
        card.add(name, gbc);

        var role = new JLabel(u.getRole().name());
        role.setFont(Ui.FONT_BOLD);
        role.setForeground(Ui.WHITE);
        role.setHorizontalAlignment(SwingConstants.CENTER);
        role.setOpaque(true);
        role.setBackground(Ui.DARK);
        role.setBorder(BorderFactory.createEmptyBorder(4, 16, 4, 16));
        var roleWrap = new JPanel(new FlowLayout(FlowLayout.CENTER));
        roleWrap.setOpaque(false);
        roleWrap.add(role);
        gbc.insets = new Insets(0, 0, 16, 0);
        card.add(roleWrap, gbc);

        gbc.insets = new Insets(4, 0, 4, 0);
        addInfoRow(card, gbc, "Email", u.getEmail());
        addInfoRow(card, gbc, "T\u00e9l\u00e9phone", u.getTelephone() != null ? u.getTelephone() : "Non renseign\u00e9");
        addInfoRow(card, gbc, "Nom d'utilisateur", u.getNomUtilisateur());
        addInfoRow(card, gbc, "Statut", u.isActif() ? "Actif" : "Inactif");

        var changePwdBtn = Ui.button("Changer le mot de passe");
        var btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setOpaque(false);
        btnPanel.add(changePwdBtn);
        gbc.insets = new Insets(16, 0, 0, 0);
        card.add(btnPanel, gbc);

        changePwdBtn.addActionListener(e ->
                new ChangerMotDePasseDialog(SwingUtilities.getWindowAncestor(this), c).setVisible(true));

        add(card, BorderLayout.CENTER);
    }

    private void addInfoRow(JPanel panel, GridBagConstraints gbc, String label, String value) {
        var row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);

        var lbl = new JLabel(label);
        lbl.setFont(Ui.FONT_SMALL);
        lbl.setForeground(Ui.TEXT_SECONDARY);
        lbl.setPreferredSize(new Dimension(140, 20));
        row.add(lbl, BorderLayout.WEST);

        var val = new JLabel(value);
        val.setFont(Ui.FONT_REGULAR);
        val.setForeground(Ui.TEXT_PRIMARY);
        row.add(val, BorderLayout.CENTER);

        panel.add(row, gbc);
    }
}
