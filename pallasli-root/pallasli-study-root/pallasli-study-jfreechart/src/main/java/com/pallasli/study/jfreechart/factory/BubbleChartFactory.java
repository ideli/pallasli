package com.pallasli.study.jfreechart.factory;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.ui.RefineryUtilities;

public class BubbleChartFactory implements Factory {

	@Override
	public JFreeChart create3D() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JFreeChart create() throws Exception {
		DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();
		double ad[] = { 30, 40, 50, 60, 70, 80 };
		double ad1[] = { 10, 20, 30, 40, 50, 60 };
		double ad2[] = { 4, 5, 10, 8, 9, 6 };
		double ad3[][] = { ad, ad1, ad2 };
		defaultxyzdataset.addSeries("Series 1", ad3);

		JFreeChart jfreechart = ChartFactory.createBubbleChart(
				"AGE vs WEIGHT vs WORK", "Weight", "AGE", defaultxyzdataset,
				PlotOrientation.HORIZONTAL, true, true, false);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setForegroundAlpha(0.65F);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setSeriesPaint(0, Color.blue);
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setLowerMargin(0.2);
		numberaxis.setUpperMargin(0.5);
		NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
		numberaxis1.setLowerMargin(0.8);
		numberaxis1.setUpperMargin(0.9);
		return jfreechart;
	}

	@Override
	public void saveChartFile() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() throws Exception {
		JFreeChart barChart;
		try {
			barChart = create();
			ChartWindow chart = new ChartWindow("sd", "df", barChart);
			chart.chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
			chart.setContentPane(chart.chartPanel);
			chart.chartPanel.setDomainZoomable(true);
			chart.chartPanel.setRangeZoomable(true);
			chart.pack();
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show3D() throws Exception {
		// TODO Auto-generated method stub

	}

}
