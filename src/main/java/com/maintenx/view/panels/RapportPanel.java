package com.maintenx.view.panels;

import com.maintenx.controller.InterventionController;
import com.maintenx.model.Intervention;
import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Priorite;
import com.maintenx.model.enums.StatutIntervention;
import com.maintenx.service.DashboardService;
import com.maintenx.util.ErrorHandler;
import com.maintenx.view.components.Ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RapportPanel extends JPanel {
    private final InterventionController interventionController;
    private final DashboardService dashboard;
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"R\u00e9f\u00e9rence", "Titre", "Priorit\u00e9", "Statut", "Technicien", "Co\u00fbt estim\u00e9", "Co\u00fbt r\u00e9el"}, 0);
    private final JTable table;
    private final JLabel totalLabel = new JLabel("0");
    private final JLabel parStatutLabel = new JLabel("-");
    private final JLabel coutTotalLabel = new JLabel("0 DH");
    private final JLabel coutMoyenLabel = new JLabel("0 DH");
    private List<Intervention> data;

    public RapportPanel(InterventionController c, DashboardService d, Utilisateur user) {
        this.interventionController = c;
        this.dashboard = d;

        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        var exportBtn = Ui.button("Exporter CSV");
        var refreshBtn = Ui.buttonGhost("Actualiser");

        var headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(Ui.title("Rapport des interventions"), BorderLayout.WEST);
        var headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        headerRight.setOpaque(false);
        headerRight.add(exportBtn);
        headerRight.add(refreshBtn);
        headerPanel.add(headerRight, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        var statsPanel = new JPanel(new GridLayout(2, 4, 12, 12));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        var totalCard = createStatCard("Total interventions", totalLabel);
        var statutCard = createStatCard("R\u00e9partition", parStatutLabel);
        var coutCard = createStatCard("Co\u00fbt total estim\u00e9", coutTotalLabel);
        var moyenCard = createStatCard("Co\u00fbt moyen estim\u00e9", coutMoyenLabel);

        statsPanel.add(totalCard);
        statsPanel.add(statutCard);
        statsPanel.add(coutCard);
        statsPanel.add(moyenCard);
        add(statsPanel, BorderLayout.CENTER);

        table = Ui.styledTable(model);
        var scroll = Ui.scrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        add(scroll, BorderLayout.SOUTH);

        exportBtn.addActionListener(e -> exportCsv());
        refreshBtn.addActionListener(e -> refresh());

        refresh();
    }

    private JPanel createStatCard(String title, JLabel value) {
        var card = new JPanel(new BorderLayout(0, 6));
        card.setOpaque(true);
        card.setBackground(Ui.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                new EmptyBorder(14, 16, 14, 16)));

        var titleLabel = new JLabel(title);
        titleLabel.setFont(Ui.FONT_SMALL);
        titleLabel.setForeground(Ui.TEXT_SECONDARY);
        card.add(titleLabel, BorderLayout.NORTH);

        value.setFont(Ui.FONT_HUGE);
        value.setForeground(Ui.DARK);
        card.add(value, BorderLayout.CENTER);
        return card;
    }

    private void refresh() {
        data = interventionController.all();

        var byStatut = data.stream().collect(Collectors.groupingBy(Intervention::getStatut, Collectors.counting()));
        var statutStr = byStatut.entrySet().stream()
                .map(e -> e.getKey().name() + ": " + e.getValue())
                .collect(Collectors.joining(", "));

        totalLabel.setText(String.valueOf(data.size()));
        parStatutLabel.setText(statutStr);

        var coutTotal = data.stream()
                .map(i -> i.getCoutEstime() != null ? i.getCoutEstime() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var coutMoyen = data.isEmpty() ? BigDecimal.ZERO : coutTotal.divide(BigDecimal.valueOf(data.size()), BigDecimal.ROUND_HALF_UP);

        coutTotalLabel.setText(coutTotal + " DH");
        coutMoyenLabel.setText(coutMoyen + " DH");

        model.setRowCount(0);
        for (var i : data)
            model.addRow(new Object[]{
                    i.getReference(), i.getTitre(),
                    i.getPriorite(), i.getStatut(),
                    i.getTechnicien() == null ? "-" : i.getTechnicien().nomComplet(),
                    i.getCoutEstime(), i.getCoutReel()
            });
    }

    private void exportCsv() {
        try {
            var chooser = new JFileChooser();
            chooser.setSelectedFile(new File("rapport_interventions.csv"));
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                interventionController.export(data, chooser.getSelectedFile());
                JOptionPane.showMessageDialog(this, "Rapport export\u00e9 avec succ\u00e8s.", "Succ\u00e8s",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ErrorHandler.show(this, ex.getMessage(), ex);
        }
    }
}
