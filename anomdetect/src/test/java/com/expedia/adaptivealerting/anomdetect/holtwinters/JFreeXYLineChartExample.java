package com.expedia.adaptivealerting.anomdetect.holtwinters;

import org.jfree.data.xy.XYDataset;

import java.util.LinkedHashMap;

public class JFreeXYLineChartExample {

    public static void main(String[] args) {
        LinkedHashMap<String, double[]> seriesMap = buildSeriesMap();
        XYDataset xyDataset = ChartHelper.createXYDataset(seriesMap);
        ChartHelper.drawChart(xyDataset,
                "Line Chart Example",
                "Which Browser are you using?",
                "Category",
                "Score");
    }

    private static LinkedHashMap<String, double[]> buildSeriesMap() {
        LinkedHashMap<String, double[]> seriesMap = new LinkedHashMap<>();
        seriesMap.put("Firefox", new double[]{1.0, 4.0, 3.0});
        seriesMap.put("Chrome", new double[]{4.0, 5.0, 6.0});
        seriesMap.put("InternetExplorer", new double[]{4.0, 5.0, 4.0});
        return seriesMap;
    }
}
