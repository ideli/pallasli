package com.pallasli.study.jfreechart.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import com.pallasli.study.jfreechart.dataset.BarChartDataset;

public class BarChartFactory implements Factory {

	@Override
	public JFreeChart create3D() throws Exception {
		final String fait = "FAIT";
		final String audi = "AUDI";
		final String ford = "FORD";
		final String speed = "Speed";
		final String popular = "Popular";
		final String mailage = "Mailage";
		final String userrating = "User Rating";
		final String safty = "safty";

		List<BarChartDataset> list = new ArrayList<BarChartDataset>();
		list.add(new BarChartDataset(1.0, fait, speed));
		list.add(new BarChartDataset(4.0, fait, popular));
		list.add(new BarChartDataset(3.0, fait, userrating));
		list.add(new BarChartDataset(5.0, fait, mailage));
		list.add(new BarChartDataset(5.0, fait, safty));
		list.add(new BarChartDataset(5.0, audi, speed));
		list.add(new BarChartDataset(7.0, audi, popular));
		list.add(new BarChartDataset(6.0, audi, userrating));
		list.add(new BarChartDataset(10.0, audi, mailage));
		list.add(new BarChartDataset(4.0, audi, safty));
		list.add(new BarChartDataset(4.0, ford, speed));
		list.add(new BarChartDataset(3.0, ford, popular));
		list.add(new BarChartDataset(2.0, ford, userrating));
		list.add(new BarChartDataset(3.0, ford, mailage));
		list.add(new BarChartDataset(6.0, ford, safty));

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (BarChartDataset set : list) {
			dataset.addValue(set.getValue(), set.getGroup(), set.getItem());
		}
		JFreeChart barChart = ChartFactory.createBarChart3D(
				"Car Usage Statistics", "Category", "Score", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		return barChart;
	}

	@Override
	public JFreeChart create() throws Exception {

		final String fiat = "FIAT";
		final String audi = "AUDI";
		final String ford = "FORD";
		final String speed = "Speed";
		final String millage = "Millage";
		final String userrating = "User Rating";
		final String safety = "safety";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(1.0, fiat, speed);
		dataset.addValue(3.0, fiat, userrating);
		dataset.addValue(5.0, fiat, millage);
		dataset.addValue(5.0, fiat, safety);

		dataset.addValue(5.0, audi, speed);
		dataset.addValue(6.0, audi, userrating);
		dataset.addValue(10.0, audi, millage);
		dataset.addValue(4.0, audi, safety);

		dataset.addValue(4.0, ford, speed);
		dataset.addValue(2.0, ford, userrating);
		dataset.addValue(3.0, ford, millage);
		dataset.addValue(6.0, ford, safety);

		JFreeChart barChart = ChartFactory.createBarChart(
				"CAR USAGE STATIStICS", "Category", "Score", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		return barChart;
	}

	@Override
	public void saveChartFile() throws Exception {
		try {
			JFreeChart barChart = create();
			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			File barChartFile = new File("BarChart.jpeg");
			ChartUtilities.saveChartAsJPEG(barChartFile, barChart, width,
					height);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		JFreeChart barChart;
		try {
			barChart = create3D();
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
}
