package com.maintenx.view;

import com.maintenx.controller.*;
import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Role;
import com.maintenx.service.*;
import com.maintenx.util.AppPreferences;
import com.maintenx.view.components.Ui;
import com.maintenx.view.dialogs.AboutDialog;
import com.maintenx.view.panels.*;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final CardLayout cards = new CardLayout();
    private final JPanel content = new JPanel(cards);
    private final Map<String, JButton> navButtons = new LinkedHashMap<>();
    private final Color SIDEBAR_BG = Ui.SIDEBAR;
    private final Color SIDEBAR_HOVER = Ui.SIDEBAR_HOVER;
    private JPanel currentActive;

    public MainFrame(Utilisateur user, UtilisateurService utilisateurs, TechnicienService techniciens,
                     InterventionService interventions, DashboardService dashboard,
                     HistoriqueService historique, JournalActiviteService journal,
                     ExportService export, ConfigurationService configuration) {
        super("MaintenX - " + user.nomComplet());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(AppPreferences.windowSize());
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AppPreferences.saveWindowSize(getSize());
            }
        });

        var root = new JPanel(new BorderLayout());
        root.setBackground(Ui.CARD_BG);

        var sidebar = createSidebar(user);
        root.add(sidebar, BorderLayout.WEST);

        content.setOpaque(true);
        content.setBackground(Ui.CARD_BG);
        root.add(content, BorderLayout.CENTER);

        addPage("Tableau de bord", new DashboardPanel(new DashboardController(dashboard)));
        if (user.getRole() == Role.ADMINISTRATEUR)
            addPage("Utilisateurs", new UtilisateurPanel(new UtilisateurController(utilisateurs, user)));
        if (user.getRole() != Role.TECHNICIEN)
            addPage("Techniciens", new TechnicienPanel(new TechnicienController(techniciens)));
        addPage("Interventions", new InterventionPanel(new InterventionController(interventions, user),
                new TechnicienController(techniciens), user));
        addPage("Recherche avancée", new RechercheAvanceePanel(new InterventionController(interventions, user)));
        addPage("Rapports", new RapportPanel(new InterventionController(interventions, user), dashboard, user));
        addPage("Historique", new HistoriquePanel(new HistoriqueController(historique)));
        if (user.getRole() == Role.ADMINISTRATEUR)
            addPage("Journal", new JournalActivitePanel(journal));
        addPage("Profil", new ProfilPanel(new ProfilController(utilisateurs, user)));
        addPage("Paramètres", new ParametresPanel(new ParametreController(configuration)));

        add(root, BorderLayout.CENTER);

        showPage("Tableau de bord");
    }

    private JPanel createSidebar(Utilisateur user) {
        var sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        var topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(SIDEBAR_BG);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 16, 16, 16));

        var brand = new JLabel("MaintenX");
        brand.setFont(Ui.FONT_TITLE.deriveFont(Font.BOLD, 22f));
        brand.setForeground(Color.WHITE);
        topPanel.add(brand, BorderLayout.NORTH);

        var roleLabel = new JLabel(user.getRole().name());
        roleLabel.setFont(Ui.FONT_SMALL);
        roleLabel.setForeground(Ui.GRAY_400);
        roleLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        var userInfo = new JLabel(user.nomComplet());
        userInfo.setFont(Ui.FONT_SMALL);
        userInfo.setForeground(Ui.GRAY_500);

        var infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);
        infoPanel.add(roleLabel, BorderLayout.NORTH);
        infoPanel.add(userInfo, BorderLayout.SOUTH);
        topPanel.add(infoPanel, BorderLayout.CENTER);

        sidebar.add(topPanel, BorderLayout.NORTH);

        var navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(SIDEBAR_BG);
        navPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        for (var entry : navButtons.entrySet()) {
            var btn = createNavButton(entry.getKey());
            navButtons.put(entry.getKey(), btn);
            navPanel.add(btn);
            navPanel.add(Box.createVerticalStrut(2));
        }

        var scrollNav = new JScrollPane(navPanel);
        scrollNav.setBorder(BorderFactory.createEmptyBorder());
        scrollNav.setBackground(SIDEBAR_BG);
        scrollNav.getViewport().setBackground(SIDEBAR_BG);
        scrollNav.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollNav.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        sidebar.add(scrollNav, BorderLayout.CENTER);

        var bottomPanel = new JPanel(new GridLayout(2, 1, 0, 4));
        bottomPanel.setBackground(SIDEBAR_BG);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 12, 8));

        var aboutBtn = createNavButton("\u2139  \u00c0 propos");
        aboutBtn.addActionListener(e -> new AboutDialog(this).setVisible(true));
        bottomPanel.add(aboutBtn);

        var logoutBtn = createNavButton("\u2b9c  D\u00e9connexion");
        logoutBtn.addActionListener(e -> {
            AppPreferences.saveWindowSize(getSize());
            JOptionPane.showMessageDialog(this,
                    "Session termin\u00e9e. Relancez l'application pour ouvrir une nouvelle session.",
                    "D\u00e9connexion", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        bottomPanel.add(logoutBtn);

        sidebar.add(bottomPanel, BorderLayout.SOUTH);

        return sidebar;
    }

    private JButton createNavButton(String text) {
        var b = new JButton(text);
        b.setFont(Ui.FONT_REGULAR);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setForeground(new Color(203, 213, 225));
        b.setBackground(SIDEBAR_BG);
        b.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:8;");
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (b.getBackground() != Ui.SIDEBAR_ACTIVE)
                    b.setBackground(SIDEBAR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (b.getBackground() != Ui.SIDEBAR_ACTIVE)
                    b.setBackground(SIDEBAR_BG);
            }
        });
        return b;
    }

    private void addPage(String name, JPanel panel) {
        content.add(panel, name);

        var btn = createNavButton(name);
        btn.addActionListener(e -> showPage(name));
        navButtons.put(name, btn);
    }

    private void showPage(String name) {
        cards.show(content, name);
        for (var entry : navButtons.entrySet()) {
            boolean active = entry.getKey().equals(name);
            var btn = entry.getValue();
            btn.setBackground(active ? Ui.SIDEBAR_ACTIVE : SIDEBAR_BG);
            btn.setForeground(active ? Color.WHITE : new Color(203, 213, 225));
            btn.setFont(active ? Ui.FONT_BOLD : Ui.FONT_REGULAR);
        }
    }
}
