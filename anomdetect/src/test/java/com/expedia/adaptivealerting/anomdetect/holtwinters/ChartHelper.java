package com.expedia.adaptivealerting.anomdetect.holtwinters;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Map;

public class ChartHelper {

    public static XYDataset createXYDataset(Map<String, double[]> seriesMap) {

        final XYSeriesCollection dataset = new XYSeriesCollection();

        seriesMap.forEach((seriesKey, seriesValue) -> {
            XYSeries series = new XYSeries(seriesKey);
            for (int i = 0; i < seriesValue.length; i++) series.add(i, seriesValue[i]);
            dataset.addSeries(series);
        });
        return dataset;
    }

    public static void drawChart(XYDataset dataset, String windowTitle, String chartTitle, String xAxisLabel, String yAxisLabel) {
        SwingUtilities.invokeLater(() -> {
            XYLineChart example = new XYLineChart(windowTitle, dataset, chartTitle, xAxisLabel, yAxisLabel);
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(1280, 1024);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
