package com.maintenx.view.dialogs;

import com.maintenx.model.Intervention;
import com.maintenx.model.enums.Priorite;
import com.maintenx.view.components.Ui;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class InterventionFormDialog extends JDialog {
    private boolean saved;
    private final Intervention i;
    private final JTextField titre = Ui.field(20);
    private final JTextField categorie = Ui.field(20);
    private final JTextField localisation = Ui.field(20);
    private final JTextField equipement = Ui.field(20);
    private final JTextField cout = Ui.field(8);
    private final JTextArea desc = Ui.textArea(4, 20);
    private final JTextArea commentaire = Ui.textArea(3, 20);
    private final JComboBox<Priorite> priorite = Ui.comboBox(Priorite.values());

    public InterventionFormDialog(Window owner, Intervention source) {
        super(owner, source == null ? "Nouvelle intervention" : "Modifier l'intervention",
                ModalityType.APPLICATION_MODAL);
        i = source == null ? new Intervention() : source;
        setSize(580, 580);
        setLocationRelativeTo(owner);

        if (source != null) {
            titre.setText(i.getTitre());
            categorie.setText(i.getCategorie());
            localisation.setText(i.getLocalisation());
            equipement.setText(i.getEquipement());
            desc.setText(i.getDescription());
            commentaire.setText(i.getCommentaire());
            priorite.setSelectedItem(i.getPriorite());
            cout.setText(String.valueOf(i.getCoutEstime()));
        }

        var root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setOpaque(true);

        var titleLabel = Ui.title(source == null ? "Nouvelle intervention" : "Modifier l'intervention");
        root.add(titleLabel, BorderLayout.NORTH);

        var form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        var gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;

        addField(form, gbc, "Titre", titre);
        addField(form, gbc, "Cat\u00e9gorie", categorie);
        addField(form, gbc, "Localisation", localisation);
        addField(form, gbc, "\u00c9quipement", equipement);
        addField(form, gbc, "Priorit\u00e9", priorite);
        addField(form, gbc, "Co\u00fbt estim\u00e9", cout);
        addField(form, gbc, "Description", new JScrollPane(desc));
        addField(form, gbc, "Commentaire", new JScrollPane(commentaire));

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
        i.setTitre(titre.getText());
        i.setCategorie(categorie.getText());
        i.setLocalisation(localisation.getText());
        i.setEquipement(equipement.getText());
        i.setDescription(desc.getText());
        i.setCommentaire(commentaire.getText());
        i.setPriorite((Priorite) priorite.getSelectedItem());
        try {
            i.setCoutEstime(new BigDecimal(cout.getText().isBlank() ? "0" : cout.getText()));
        } catch (NumberFormatException ignored) {
            i.setCoutEstime(BigDecimal.ZERO);
        }
    }

    public boolean isSaved() { return saved; }

    public Intervention getIntervention() { return i; }
}
