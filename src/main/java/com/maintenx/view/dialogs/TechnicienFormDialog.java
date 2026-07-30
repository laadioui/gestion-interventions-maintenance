package com.maintenx.view.dialogs;

import com.maintenx.model.Technicien;
import com.maintenx.model.enums.Specialite;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import java.awt.*;

public class TechnicienFormDialog extends JDialog {
    private boolean saved;
    private final Technicien t;
    private final JTextField matricule = Ui.field(18);
    private final JTextField nom = Ui.field(18);
    private final JTextField prenom = Ui.field(18);
    private final JTextField email = Ui.field(18);
    private final JTextField tel = Ui.field(18);
    private final JComboBox<Specialite> spec = Ui.comboBox(Specialite.values());
    private final JCheckBox dispo = Ui.checkBox("Disponible");
    private final JCheckBox actif = Ui.checkBox("Actif");

    public TechnicienFormDialog(Window owner, Technicien source) {
        super(owner, source == null ? "Nouveau technicien" : "Modifier le technicien",
                ModalityType.APPLICATION_MODAL);
        t = source == null ? new Technicien() : source;
        setSize(480, 460);
        setLocationRelativeTo(owner);

        if (source != null) {
            matricule.setText(t.getMatricule());
            nom.setText(t.getNom());
            prenom.setText(t.getPrenom());
            email.setText(t.getEmail());
            tel.setText(t.getTelephone());
            spec.setSelectedItem(t.getSpecialite());
            dispo.setSelected(t.isDisponible());
            actif.setSelected(t.isActif());
        } else {
            dispo.setSelected(true);
            actif.setSelected(true);
        }

        var root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setOpaque(true);

        var titleLabel = Ui.title(source == null ? "Nouveau technicien" : "Modifier le technicien");
        root.add(titleLabel, BorderLayout.NORTH);

        var form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        var gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;

        addField(form, gbc, "Matricule", matricule);
        addField(form, gbc, "Nom", nom);
        addField(form, gbc, "Pr\u00e9nom", prenom);
        addField(form, gbc, "Email", email);
        addField(form, gbc, "T\u00e9l\u00e9phone", tel);
        addField(form, gbc, "Sp\u00e9cialit\u00e9", spec);
        form.add(dispo, gbc);
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
        t.setMatricule(matricule.getText());
        t.setNom(nom.getText());
        t.setPrenom(prenom.getText());
        t.setEmail(email.getText());
        t.setTelephone(tel.getText());
        t.setSpecialite((Specialite) spec.getSelectedItem());
        t.setDisponible(dispo.isSelected());
        t.setActif(actif.isSelected());
    }

    public boolean isSaved() { return saved; }

    public Technicien getTechnicien() { return t; }
}
