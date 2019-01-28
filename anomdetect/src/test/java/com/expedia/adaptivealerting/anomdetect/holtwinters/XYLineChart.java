package com.expedia.adaptivealerting.anomdetect.holtwinters;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XYLineChart extends JFrame {

    public XYLineChart(String windowTitle, XYDataset dataset, String chartTitle, String xAxisLabel, String yAxisLabel) {
        super(windowTitle);
        JFreeChart chart = createChart(dataset, chartTitle, xAxisLabel, yAxisLabel);
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        saveChartImage(chart, panel, chartTitle);
    }

    private JFreeChart createChart(XYDataset dataset, String chartTitle, String xAxisLabel, String yAxisLabel) {
        return ChartFactory.createXYLineChart(
                chartTitle,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private void saveChartImage(JFreeChart chart, ChartPanel panel, String chartTitle) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            OutputStream out = new FileOutputStream(String.format("%s_%s.png", timestamp, chartTitle));
            ChartUtilities.writeChartAsPNG(out,
                    chart,
                    panel.getMaximumDrawWidth(),
                    panel.getMaximumDrawHeight());

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}