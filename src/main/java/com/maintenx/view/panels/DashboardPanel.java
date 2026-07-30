package com.maintenx.view.panels;

import com.maintenx.controller.DashboardController;
import com.maintenx.view.components.*;
import com.formdev.flatlaf.FlatClientProperties;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DashboardPanel extends JPanel {
    public DashboardPanel(DashboardController c) {
        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setOpaque(true);

        add(Ui.header("Tableau de bord"), BorderLayout.NORTH);

        var top = new JPanel(new GridLayout(1, 4, 12, 0));
        top.setOpaque(false);
        var urgent = new StatCard("Interventions urgentes", Ui.DARK, "\u26a1");
        urgent.setValue(c.service.countUrgent());
        var actifs = new StatCard("Techniciens actifs", Ui.GRAY_700, "\ud83d\udc65");
        actifs.setValue(c.service.countTechniciensActifs());
        var dispo = new StatCard("Techniciens disponibles", Ui.GRAY_600, "\u2713");
        dispo.setValue(c.service.countTechniciensDisponibles());
        var total = new StatCard("Total interventions", Ui.GRAY_800, "\ud83d\udcca");
        long totalCount = c.service.interventionsByStatus().values().stream().mapToLong(Long::longValue).sum();
        total.setValue(totalCount);
        top.add(total);
        top.add(urgent);
        top.add(actifs);
        top.add(dispo);

        var chartsPanel = new JPanel(new GridLayout(2, 2, 12, 12));
        chartsPanel.setOpaque(false);

        chartsPanel.add(createChartPanel(
                ChartFactory.createPieChart("R\u00e9partition par statut",
                        pie(c.service.interventionsByStatus()), true, true, false)));
        chartsPanel.add(createChartPanel(
                ChartFactory.createBarChart("Interventions par priorit\u00e9",
                        "Priorit\u00e9", "Nombre", category(c.service.interventionsByPriority()))));
        chartsPanel.add(createChartPanel(
                ChartFactory.createBarChart("Interventions par technicien",
                        "Technicien", "Nombre", category(c.service.interventionsByTechnician()))));
        chartsPanel.add(createChartPanel(
                ChartFactory.createLineChart("Cr\u00e9\u00e9es par mois",
                        "Mois", "Nombre", category(c.service.interventionsByMonth()))));

        var center = new JPanel(new BorderLayout(0, 16));
        center.setOpaque(false);
        center.add(top, BorderLayout.NORTH);
        center.add(chartsPanel, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);
    }

    private JPanel createChartPanel(JFreeChart chart) {
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(Ui.FONT_SECTION);
        chart.getTitle().setPaint(Ui.TEXT_PRIMARY);
        if (chart.getPlot() instanceof PiePlot<?> plot) {
            plot.setBackgroundPaint(Color.WHITE);
            plot.setOutlinePaint(null);
            plot.setShadowPaint(null);
        } else if (chart.getPlot() instanceof CategoryPlot plot) {
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Ui.BORDER);
            plot.setRangeGridlinePaint(Ui.BORDER);
            plot.setOutlinePaint(null);
            if (plot.getRenderer() instanceof BarRenderer r) {
                r.setShadowVisible(false);
                r.setDefaultItemLabelsVisible(false);
            }
        }

        var cp = new ChartPanel(chart);
        cp.setBorder(BorderFactory.createEmptyBorder());
        cp.setPreferredSize(new Dimension(300, 220));
        cp.setOpaque(true);
        cp.setBackground(Color.WHITE);
        cp.putClientProperty(FlatClientProperties.STYLE, "arc:12;");

        var wrap = new JPanel(new BorderLayout());
        wrap.setOpaque(true);
        wrap.setBackground(Color.WHITE);
        wrap.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        wrap.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
        wrap.add(cp, BorderLayout.CENTER);

        return wrap;
    }

    private DefaultPieDataset<String> pie(java.util.Map<String, Long> m) {
        var d = new DefaultPieDataset<String>();
        m.forEach(d::setValue);
        return d;
    }

    private DefaultCategoryDataset category(java.util.Map<String, Long> m) {
        var d = new DefaultCategoryDataset();
        m.forEach((k, v) -> d.addValue(v, "Interventions", k));
        return d;
    }
}
