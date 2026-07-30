package com.maintenx.view.dialogs;

import com.maintenx.model.HistoriqueIntervention;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoriqueDialog extends JDialog {
    public HistoriqueDialog(Window owner, List<HistoriqueIntervention> list) {
        super(owner, "Historique", ModalityType.APPLICATION_MODAL);
        setSize(680, 400);
        setLocationRelativeTo(owner);

        var root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setOpaque(true);

        root.add(Ui.title("Historique"), BorderLayout.NORTH);

        var model = new DefaultTableModel(
                new String[]{"Date", "Intervention", "Action", "Ancienne", "Nouvelle", "Utilisateur"}, 0);
        for (var h : list)
            model.addRow(new Object[]{
                    h.getDateAction(), h.getInterventionId(), h.getAction(),
                    h.getAncienneValeur(), h.getNouvelleValeur(), h.getUtilisateur()
            });

        var table = Ui.styledTable(model);
        var scroll = Ui.scrollPane(table);
        root.add(scroll, BorderLayout.CENTER);

        var closeBtn = Ui.button("Fermer");
        closeBtn.addActionListener(e -> dispose());
        var btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnPanel.add(closeBtn);
        root.add(btnPanel, BorderLayout.SOUTH);

        add(root);
    }
}
