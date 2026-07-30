package com.maintenx.view.renderers;

import com.maintenx.view.components.Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StatusCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean selected, boolean focus, int row, int col) {
        var c = super.getTableCellRendererComponent(table, value, selected, focus, row, col);
        if (!selected && value != null) {
            String v = value.toString().toUpperCase();
            c.setFont(Ui.FONT_SMALL);
            setHorizontalAlignment(SwingConstants.CENTER);

            Color bg;
            Color fg = Color.WHITE;

            switch (v) {
                case "OUVERTE":
                    bg = Ui.GRAY_600;
                    break;
                case "AFFECTEE":
                    bg = Ui.GRAY_500;
                    break;
                case "EN_COURS":
                    bg = Ui.GRAY_700;
                    break;
                case "EN_ATTENTE":
                    bg = Ui.GRAY_400;
                    fg = Color.BLACK;
                    break;
                case "TERMINEE":
                    bg = Ui.DARK;
                    break;
                case "ANNULEE":
                    bg = Ui.BLACK;
                    break;
                case "URGENTE":
                    bg = Ui.BLACK;
                    break;
                case "HAUTE":
                    bg = Ui.GRAY_800;
                    break;
                case "MOYENNE":
                    bg = Ui.GRAY_500;
                    break;
                case "BASSE":
                    bg = Ui.GRAY_400;
                    fg = Color.BLACK;
                    break;
                default:
                    bg = Ui.GRAY_500;
                    break;
            }
            c.setBackground(bg);
            c.setForeground(fg);
            ((JLabel) c).setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        } else if (selected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        }
        return c;
    }
}
