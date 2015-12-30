package com.pallasli.study.jfreechart.factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

public class TimeSeriesChartFactory implements Factory {

	@Override
	public JFreeChart create3D() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JFreeChart create() throws Exception {
		final TimeSeries series = new TimeSeries("Random Data");
		Second current = new Second();
		double value = 100.0;
		for (int i = 0; i < 4000; i++) {
			try {
				value = value + Math.random() - 0.5;
				series.add(current, new Double(value));
				current = (Second) current.next();
			} catch (SeriesException e) {
				System.err.println("Error adding to series");
			}
		}
		final XYDataset dataset = new TimeSeriesCollection(series);
		JFreeChart timechart = ChartFactory.createTimeSeriesChart(
				"Computing Test", "Seconds", "Value", dataset, false, false,
				false);
		return timechart;
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
