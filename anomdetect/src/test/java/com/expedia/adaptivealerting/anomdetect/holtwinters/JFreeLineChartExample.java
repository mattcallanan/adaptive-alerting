package com.expedia.adaptivealerting.anomdetect.holtwinters;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JFreeLineChartExample extends JFrame {

    private static final long serialVersionUID = 1L;

    public JFreeLineChartExample(String windowTitle) {
        super(windowTitle);
        // Create dataset
        CategoryDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Site Traffic", // Chart title
                "Date", // X-Axis Label
                "Number of Visitor", // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        saveChartImage(chart, panel);
    }

    private void saveChartImage(JFreeChart chart, ChartPanel panel) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            OutputStream out = new FileOutputStream(String.format("%s_chart.png", timestamp));
            ChartUtilities.writeChartAsPNG(out,
                    chart,
                    panel.getMaximumDrawWidth(),
                    panel.getMaximumDrawHeight());

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private CategoryDataset createDataset() {

        String series1 = "Vistor";
        String series2 = "Unique Visitor";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(200, series1, "2016-12-19");
        dataset.addValue(150, series1, "2016-12-20");
        dataset.addValue(100, series1, "2016-12-21");
        dataset.addValue(210, series1, "2016-12-22");
        dataset.addValue(240, series1, "2016-12-23");
        dataset.addValue(195, series1, "2016-12-24");
        dataset.addValue(245, series1, "2016-12-25");

        dataset.addValue(150, series2, "2016-12-19");
        dataset.addValue(130, series2, "2016-12-20");
        dataset.addValue(95, series2, "2016-12-21");
        dataset.addValue(195, series2, "2016-12-22");
        dataset.addValue(200, series2, "2016-12-23");
        dataset.addValue(180, series2, "2016-12-24");
        dataset.addValue(230, series2, "2016-12-25");

        return dataset;
    }

    public static void main(String[] args) {
        drawChart();
    }

    private static void drawChart() {
        SwingUtilities.invokeLater(() -> {
            JFreeLineChartExample example = new JFreeLineChartExample("Line Chart Example");
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(600, 400);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}