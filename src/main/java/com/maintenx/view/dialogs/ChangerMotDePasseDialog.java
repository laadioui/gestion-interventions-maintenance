package com.maintenx.view.dialogs;

import com.maintenx.controller.ProfilController;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import java.awt.*;

public class ChangerMotDePasseDialog extends JDialog {
    public ChangerMotDePasseDialog(Window owner, ProfilController c) {
        super(owner, "Changer le mot de passe", ModalityType.APPLICATION_MODAL);
        setSize(380, 200);
        setLocationRelativeTo(owner);

        var root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setOpaque(true);

        root.add(Ui.title("Changer le mot de passe"), BorderLayout.NORTH);

        var p = Ui.passwordField(16);
        var lbl = new JLabel("Nouveau mot de passe");
        lbl.setFont(Ui.FONT_SMALL);
        lbl.setForeground(Ui.TEXT_SECONDARY);

        var fieldPanel = new JPanel(new BorderLayout(0, 4));
        fieldPanel.setOpaque(false);
        fieldPanel.add(lbl, BorderLayout.NORTH);
        fieldPanel.add(p, BorderLayout.CENTER);
        root.add(fieldPanel, BorderLayout.CENTER);

        var btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.setOpaque(false);
        var cancelBtn = Ui.buttonOutlined("Annuler");
        var okBtn = Ui.button("Enregistrer");

        cancelBtn.addActionListener(e -> dispose());
        okBtn.addActionListener(e -> {
            String newPwd = new String(p.getPassword());
            if (newPwd.isBlank()) {
                JOptionPane.showMessageDialog(this, "Le mot de passe ne peut pas \u00eatre vide.",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            c.changePassword(newPwd);
            JOptionPane.showMessageDialog(this, "Mot de passe modifi\u00e9 avec succ\u00e8s.",
                    "Succ\u00e8s", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        btnPanel.add(cancelBtn);
        btnPanel.add(okBtn);
        root.add(btnPanel, BorderLayout.SOUTH);

        add(root);
    }
}
