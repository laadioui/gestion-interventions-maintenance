package com.maintenx.view.panels;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.maintenx.controller.ParametreController;
import com.maintenx.util.AppPreferences;
import com.maintenx.view.components.Ui;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class ParametresPanel extends JPanel {
    public ParametresPanel(ParametreController c) {
        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        add(Ui.header("Param\u00e8tres"), BorderLayout.NORTH);

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

        var versionTitle = Ui.sectionTitle("Informations sur l'application");
        card.add(versionTitle, gbc);

        var versionRow = new JPanel(new BorderLayout(8, 0));
        versionRow.setOpaque(false);
        var vLbl = new JLabel("Version");
        vLbl.setFont(Ui.FONT_SMALL);
        vLbl.setForeground(Ui.TEXT_SECONDARY);
        vLbl.setPreferredSize(new Dimension(140, 20));
        versionRow.add(vLbl, BorderLayout.WEST);
        var vVal = new JLabel(c.version());
        vVal.setFont(Ui.FONT_REGULAR);
        vVal.setForeground(Ui.TEXT_PRIMARY);
        versionRow.add(vVal, BorderLayout.CENTER);
        card.add(versionRow, gbc);

        gbc.insets = new Insets(16, 0, 4, 0);
        var themeTitle = Ui.sectionTitle("Apparence");
        card.add(themeTitle, gbc);

        var dark = new JCheckBox("Th\u00e8me sombre");
        dark.setFont(Ui.FONT_REGULAR);
        dark.setBackground(Color.WHITE);
        gbc.insets = new Insets(4, 0, 4, 0);
        card.add(dark, gbc);

        dark.addActionListener(e -> {
            try {
                if (dark.isSelected())
                    FlatDarkLaf.setup();
                else
                    FlatLightLaf.setup();
                AppPreferences.saveTheme(dark.isSelected());
                SwingUtilities.updateComponentTreeUI(SwingUtilities.getWindowAncestor(this));
            } catch (Exception ignored) {
            }
        });

        var footer = new JLabel("Stage : Othmane LAADIOUI - Sirecom");
        footer.setFont(Ui.FONT_SMALL);
        footer.setForeground(Ui.TEXT_SECONDARY);
        gbc.insets = new Insets(24, 0, 0, 0);
        card.add(footer, gbc);

        add(card, BorderLayout.CENTER);
    }
}
