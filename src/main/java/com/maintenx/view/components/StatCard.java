package com.maintenx.view.components;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatCard extends JPanel {
    private final JLabel valueLabel;

    public StatCard(String title, Color accent, String icon) {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                new EmptyBorder(16, 18, 16, 18)));
        setOpaque(true);
        putClientProperty(FlatClientProperties.STYLE, "arc:0;");
        setBackground(Color.WHITE);

        var topRow = new JPanel(new BorderLayout(8, 0));
        topRow.setOpaque(false);

        var iconLabel = new JLabel(icon);
        iconLabel.setFont(Ui.FONT_SECTION);
        iconLabel.setForeground(accent);
        iconLabel.setOpaque(true);
        iconLabel.setBackground(new Color(245, 245, 245));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(32, 28));
        topRow.add(iconLabel, BorderLayout.WEST);

        var titleLabel = new JLabel(title);
        titleLabel.setFont(Ui.FONT_SMALL);
        titleLabel.setForeground(Ui.TEXT_SECONDARY);
        topRow.add(titleLabel, BorderLayout.CENTER);

        add(topRow, BorderLayout.NORTH);

        valueLabel = new JLabel("0");
        valueLabel.setFont(Ui.FONT_HUGE);
        valueLabel.setForeground(accent);
        add(valueLabel, BorderLayout.CENTER);
    }

    public StatCard(String title) {
        this(title, Ui.DARK, "\u2606");
    }

    public void setValue(Object v) {
        valueLabel.setText(String.valueOf(v));
    }
}
