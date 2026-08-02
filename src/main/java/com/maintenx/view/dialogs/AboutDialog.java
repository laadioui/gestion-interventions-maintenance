package com.maintenx.view.dialogs;

import com.maintenx.view.components.Ui;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog(Window owner) {
        super(owner, "\u00c0 propos", ModalityType.APPLICATION_MODAL);
        setSize(440, 360);
        setLocationRelativeTo(owner);

        var root = new JPanel(new BorderLayout(0, 12));
        root.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        root.setOpaque(true);
        root.putClientProperty(FlatClientProperties.STYLE, "arc:12;");

        var top = new JPanel(new BorderLayout(0, 8));
        top.setOpaque(false);

        var logo = new JLabel(Ui.logoIcon(72));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        top.add(logo, BorderLayout.NORTH);

        var title = new JLabel("MaintenX");
        title.setFont(Ui.FONT_TITLE.deriveFont(Font.BOLD, 28f));
        title.setForeground(Ui.DARK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        top.add(title, BorderLayout.SOUTH);

        root.add(top, BorderLayout.NORTH);

        var body = new JPanel(new GridLayout(0, 1, 0, 6));
        body.setOpaque(false);

        var sub = new JLabel("Application Java Swing de gestion des interventions de maintenance");
        sub.setFont(Ui.FONT_REGULAR);
        sub.setForeground(Ui.TEXT_SECONDARY);
        sub.setHorizontalAlignment(SwingConstants.CENTER);
        body.add(sub);

        body.add(Box.createVerticalStrut(8));

        var line1 = new JLabel("Stagiaire : Othmane LAADIOUI");
        line1.setFont(Ui.FONT_BOLD);
        line1.setForeground(Ui.TEXT_PRIMARY);
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        body.add(line1);

        var line2 = new JLabel("Entreprise : Sirecom");
        line2.setFont(Ui.FONT_REGULAR);
        line2.setForeground(Ui.TEXT_SECONDARY);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        body.add(line2);

        var line3 = new JLabel("Version 1.0.0");
        line3.setFont(Ui.FONT_SMALL);
        line3.setForeground(Ui.TEXT_SECONDARY);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        body.add(line3);

        root.add(body, BorderLayout.CENTER);

        var closeBtn = Ui.button("Fermer");
        closeBtn.addActionListener(e -> dispose());
        var btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnPanel.add(closeBtn);
        root.add(btnPanel, BorderLayout.SOUTH);

        add(root);
    }
}
