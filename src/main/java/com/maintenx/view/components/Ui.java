package com.maintenx.view.components;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

public final class Ui {
    private Ui() {}

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color DARK = new Color(26, 26, 26);
    public static final Color GRAY_900 = new Color(30, 30, 30);
    public static final Color GRAY_800 = new Color(51, 51, 51);
    public static final Color GRAY_700 = new Color(77, 77, 77);
    public static final Color GRAY_600 = new Color(102, 102, 102);
    public static final Color GRAY_500 = new Color(128, 128, 128);
    public static final Color GRAY_400 = new Color(153, 153, 153);
    public static final Color GRAY_300 = new Color(191, 191, 191);
    public static final Color GRAY_200 = new Color(217, 217, 217);
    public static final Color GRAY_100 = new Color(230, 230, 230);
    public static final Color GRAY_50 = new Color(245, 245, 245);
    public static final Color WHITE = new Color(255, 255, 255);

    public static final Color SIDEBAR = DARK;
    public static final Color SIDEBAR_HOVER = GRAY_900;
    public static final Color SIDEBAR_ACTIVE = GRAY_800;
    public static final Color CARD_BG = WHITE;
    public static final Color TEXT_PRIMARY = DARK;
    public static final Color TEXT_SECONDARY = GRAY_600;
    public static final Color BORDER = GRAY_200;

    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_SECTION = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_HUGE = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 16);

    public static final String LOGO_PATH = "/img/maintenx_logo.png";

    public static Image appIcon() {
        var url = Ui.class.getResource(LOGO_PATH);
        return url == null ? null : new ImageIcon(url).getImage();
    }

    public static ImageIcon logoIcon(int size) {
        var img = appIcon();
        return img == null ? null : new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
    }

    public static JButton button(String text) {
        var b = new JButton(text);
        b.setFont(FONT_BOLD);
        b.setFocusPainted(false);
        b.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:0;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:#1a1a1a;"
                + "foreground:#ffffff;"
                + "hoverBackground:#333333;"
                + "pressedBackground:#000000;");
        return b;
    }

    public static JButton buttonOutlined(String text) {
        var b = new JButton(text);
        b.setFont(FONT_BOLD);
        b.setFocusPainted(false);
        b.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:0;"
                + "borderWidth:1;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:#00000000;"
                + "foreground:#1a1a1a;"
                + "borderColor:#1a1a1a;");
        return b;
    }

    public static JButton buttonGhost(String text) {
        var b = new JButton(text);
        b.setFont(FONT_REGULAR);
        b.setFocusPainted(false);
        b.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:0;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:#00000000;"
                + "foreground:#666666;"
                + "hoverBackground:#f0f0f0;");
        return b;
    }

    public static JLabel title(String text) {
        var l = new JLabel(text);
        l.setFont(FONT_TITLE);
        l.setForeground(TEXT_PRIMARY);
        return l;
    }

    public static JLabel sectionTitle(String text) {
        var l = new JLabel(text);
        l.setFont(FONT_SECTION);
        l.setForeground(TEXT_PRIMARY);
        return l;
    }

    public static JLabel hugeNumber(String text) {
        var l = new JLabel(text);
        l.setFont(FONT_HUGE);
        l.setForeground(DARK);
        return l;
    }

    public static JPanel padded(LayoutManager lm) {
        var p = new JPanel(lm);
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        return p;
    }

    public static JPanel card() {
        var p = new JPanel();
        p.setOpaque(true);
        p.putClientProperty(FlatClientProperties.STYLE, "arc:0;");
        p.setBackground(CARD_BG);
        return p;
    }

    public static JTextField field(int columns) {
        var f = new JTextField(columns);
        f.setFont(FONT_REGULAR);
        f.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:0;"
                + "margin:4,10,4,10;");
        return f;
    }

    public static JPasswordField passwordField(int columns) {
        var f = new JPasswordField(columns);
        f.setFont(FONT_REGULAR);
        f.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:0;"
                + "margin:4,10,4,10;");
        return f;
    }

    @SuppressWarnings("unchecked")
    public static <T> JComboBox<T> comboBox(T[] items) {
        var c = new JComboBox<>(items);
        c.setFont(FONT_REGULAR);
        c.putClientProperty(FlatClientProperties.STYLE, "arc:0;");
        return c;
    }

    public static JTextArea textArea(int rows, int cols) {
        var ta = new JTextArea(rows, cols);
        ta.setFont(FONT_REGULAR);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.putClientProperty(FlatClientProperties.STYLE, "arc:0;margin:6,10,6,10;");
        return ta;
    }

    public static JCheckBox checkBox(String text) {
        var c = new JCheckBox(text);
        c.setFont(FONT_REGULAR);
        return c;
    }

    public static JScrollPane scrollPane(Component view) {
        var sp = new JScrollPane(view);
        sp.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:0;"
                + "showButtons:false;");
        sp.setBorder(BorderFactory.createEmptyBorder());
        return sp;
    }

    public static JTable styledTable(javax.swing.table.TableModel model) {
        var t = new JTable(model);
        t.setFont(FONT_REGULAR);
        t.setRowHeight(36);
        t.setAutoCreateRowSorter(true);
        t.setShowHorizontalLines(true);
        t.setShowVerticalLines(false);
        t.setGridColor(GRAY_200);
        t.setSelectionBackground(GRAY_100);
        t.setSelectionForeground(TEXT_PRIMARY);
        t.setIntercellSpacing(new Dimension(0, 0));
        t.getTableHeader().setFont(FONT_BOLD);
        t.getTableHeader().setForeground(TEXT_SECONDARY);
        t.getTableHeader().setBackground(GRAY_50);
        ((JComponent) t.getTableHeader()).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        return t;
    }

    public static JPanel toolbar(Component... components) {
        var p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        p.setOpaque(false);
        for (var c : components) p.add(c);
        return p;
    }

    public static JPanel header(String title, Component... actions) {
        var p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        p.add(Ui.title(title), BorderLayout.WEST);
        if (actions.length > 0) {
            var right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
            right.setOpaque(false);
            for (var a : actions) right.add(a);
            p.add(right, BorderLayout.EAST);
        }
        return p;
    }

    public static JPanel searchBar(JTextField field, JButton searchBtn, JButton... extra) {
        var p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        p.setOpaque(false);
        field.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, "search");
        field.setPreferredSize(new Dimension(200, 32));
        p.add(field);
        if (searchBtn != null) p.add(searchBtn);
        for (var e : extra) p.add(e);
        return p;
    }

    public static JPanel badge(String text, Color bg, Color fg) {
        var l = new JLabel(text);
        l.setFont(FONT_SMALL);
        l.setForeground(fg);
        l.setOpaque(true);
        l.setBackground(bg);
        l.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        l.setHorizontalAlignment(SwingConstants.CENTER);
        var p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.add(l);
        return p;
    }

    public static JLabel statusBadge(String text) {
        var l = new JLabel(text);
        l.setFont(FONT_SMALL);
        l.setOpaque(true);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        l.setBackground(GRAY_800);
        l.setForeground(WHITE);
        return l;
    }

    public static JLabel priorityBadge(String text) {
        var l = new JLabel(text);
        l.setFont(FONT_SMALL);
        l.setOpaque(true);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        l.setBackground(GRAY_800);
        l.setForeground(WHITE);
        return l;
    }

    public static JPanel separator() {
        var p = new JPanel();
        p.setPreferredSize(new Dimension(0, 1));
        p.setBackground(BORDER);
        p.setOpaque(true);
        return p;
    }

    public static void onHover(JComponent c, Color normal, Color hover) {
        c.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { c.setBackground(hover); }
            @Override public void mouseExited(java.awt.event.MouseEvent e) { c.setBackground(normal); }
        });
    }

    private static String toHex(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    private static Color brighten(Color c, float factor) {
        return new Color(Math.min(255, (int)(c.getRed() * (1 + factor))),
                Math.min(255, (int)(c.getGreen() * (1 + factor))),
                Math.min(255, (int)(c.getBlue() * (1 + factor))));
    }

    private static Color darken(Color c, float factor) {
        return new Color(Math.max(0, (int)(c.getRed() * (1 - factor))),
                Math.max(0, (int)(c.getGreen() * (1 - factor))),
                Math.max(0, (int)(c.getBlue() * (1 - factor))));
    }
}
