package com.pallasli.study.jfreechart.factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class LineChartFactory implements Factory {

	@Override
	public JFreeChart create3D() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JFreeChart create() throws Exception {

		DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
		line_chart_dataset.addValue(15, "schools", "1970");
		line_chart_dataset.addValue(30, "schools", "1980");
		line_chart_dataset.addValue(60, "schools", "1990");
		line_chart_dataset.addValue(120, "schools", "2000");
		line_chart_dataset.addValue(240, "schools", "2010");
		line_chart_dataset.addValue(300, "schools", "2014");

		JFreeChart lineChartObject = ChartFactory
				.createLineChart("Schools Vs Years", "Year", "Schools Count",
						line_chart_dataset, PlotOrientation.VERTICAL, true,
						true, false);
		return lineChartObject;
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
