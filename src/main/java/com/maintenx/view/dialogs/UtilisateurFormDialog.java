package com.maintenx.view.dialogs;

import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Role;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import java.awt.*;

public class UtilisateurFormDialog extends JDialog {
    private boolean saved;
    private final Utilisateur utilisateur;
    private final JPasswordField password = Ui.passwordField(16);
    private final JTextField nom = Ui.field(18);
    private final JTextField prenom = Ui.field(18);
    private final JTextField email = Ui.field(18);
    private final JTextField login = Ui.field(18);
    private final JTextField tel = Ui.field(18);
    private final JComboBox<Role> role = Ui.comboBox(Role.values());
    private final JCheckBox actif = Ui.checkBox("Actif");

    public UtilisateurFormDialog(Window owner, Utilisateur source) {
        super(owner, source == null ? "Nouvel utilisateur" : "Modifier l'utilisateur",
                ModalityType.APPLICATION_MODAL);
        utilisateur = source == null ? new Utilisateur() : source;
        setSize(480, 440);
        setLocationRelativeTo(owner);

        if (source != null) {
            nom.setText(source.getNom());
            prenom.setText(source.getPrenom());
            email.setText(source.getEmail());
            login.setText(source.getNomUtilisateur());
            tel.setText(source.getTelephone());
            role.setSelectedItem(source.getRole());
            actif.setSelected(source.isActif());
        }
        actif.setSelected(source == null || source.isActif());

        var root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setOpaque(true);

        var titleLabel = Ui.title(source == null ? "Nouvel utilisateur" : "Modifier l'utilisateur");
        root.add(titleLabel, BorderLayout.NORTH);

        var form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        var gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;

        addField(form, gbc, "Nom", nom);
        addField(form, gbc, "Pr\u00e9nom", prenom);
        addField(form, gbc, "Email", email);
        addField(form, gbc, "Nom d'utilisateur", login);
        if (source == null) addField(form, gbc, "Mot de passe", password);
        addField(form, gbc, "R\u00f4le", role);
        addField(form, gbc, "T\u00e9l\u00e9phone", tel);
        form.add(actif, gbc);

        root.add(form, BorderLayout.CENTER);

        var btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.setOpaque(false);

        var cancelBtn = Ui.buttonOutlined("Annuler");
        var saveBtn = Ui.button("Enregistrer");

        cancelBtn.addActionListener(e -> dispose());
        saveBtn.addActionListener(e -> {
            fill();
            saved = true;
            dispose();
        });

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);
        root.add(btnPanel, BorderLayout.SOUTH);

        add(root);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field) {
        var lbl = new JLabel(label);
        lbl.setFont(Ui.FONT_SMALL);
        lbl.setForeground(Ui.TEXT_SECONDARY);
        panel.add(lbl, gbc);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 30));
        panel.add(field, gbc);
    }

    private void fill() {
        utilisateur.setNom(nom.getText());
        utilisateur.setPrenom(prenom.getText());
        utilisateur.setEmail(email.getText());
        utilisateur.setNomUtilisateur(login.getText());
        utilisateur.setRole((Role) role.getSelectedItem());
        utilisateur.setTelephone(tel.getText());
        utilisateur.setActif(actif.isSelected());
    }

    public boolean isSaved() { return saved; }

    public Utilisateur getUtilisateur() { return utilisateur; }

    public String getPassword() { return new String(password.getPassword()); }
}
