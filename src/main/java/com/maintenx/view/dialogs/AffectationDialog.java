package com.maintenx.view.dialogs;

import com.maintenx.model.Technicien;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AffectationDialog extends JDialog {
    private Technicien selected;

    public AffectationDialog(Window owner, List<Technicien> techniciens) {
        super(owner, "Affecter un technicien", ModalityType.APPLICATION_MODAL);
        setSize(400, 180);
        setLocationRelativeTo(owner);

        var root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setOpaque(true);

        root.add(Ui.title("Affecter un technicien"), BorderLayout.NORTH);

        var combo = Ui.comboBox(techniciens.stream()
                .filter(t -> t.isActif()).toArray(Technicien[]::new));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean hasFocus) {
                var c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
                if (value instanceof Technicien t) {
                    c.setText(t.nomComplet() + " (" + t.getSpecialite() + ")");
                }
                return c;
            }
        });
        root.add(combo, BorderLayout.CENTER);

        var btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.setOpaque(false);
        var cancelBtn = Ui.buttonOutlined("Annuler");
        var okBtn = Ui.button("Affecter");

        cancelBtn.addActionListener(e -> dispose());
        okBtn.addActionListener(e -> {
            selected = (Technicien) combo.getSelectedItem();
            dispose();
        });

        btnPanel.add(cancelBtn);
        btnPanel.add(okBtn);
        root.add(btnPanel, BorderLayout.SOUTH);

        add(root);
    }

    public Technicien getSelected() { return selected; }
}
