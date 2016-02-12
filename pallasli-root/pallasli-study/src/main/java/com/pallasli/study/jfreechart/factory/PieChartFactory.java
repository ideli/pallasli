package com.pallasli.study.jfreechart.factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

public class PieChartFactory implements Factory {

	@Override
	public JFreeChart create3D() throws Exception {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("IPhone 5s", new Double(20));
		dataset.setValue("SamSung Grand", new Double(20));
		dataset.setValue("MotoG", new Double(40));
		dataset.setValue("Nokia Lumia", new Double(10));

		JFreeChart chart = ChartFactory.createPieChart3D("Mobile Sales", // chart
																			// title
				dataset, // data
				true, // include legend
				true, false);

		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(270);
		plot.setForegroundAlpha(0.60f);
		plot.setInteriorGap(0.02);
		return chart;
	}

	@Override
	public JFreeChart create() throws Exception {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("IPhone 5s", new Double(20));
		dataset.setValue("SamSung Grand", new Double(20));
		dataset.setValue("MotoG", new Double(40));
		dataset.setValue("Nokia Lumia", new Double(10));
		JFreeChart chart = ChartFactory.createPieChart("Mobile Sales", // chart
																		// title
				dataset, // data
				true, // include legend
				true, false);
		return chart;
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
			chart.setContentPane(chart.chartPanel);
			chart.setSize(560, 367);
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show3D() throws Exception {
		JFreeChart barChart;
		try {
			barChart = create3D();
			ChartWindow chart = new ChartWindow("sd", "df", barChart);
			chart.setContentPane(chart.chartPanel);
			chart.setSize(560, 367);
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
