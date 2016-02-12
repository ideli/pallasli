package com.pallasli.study.jfreechart.factory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class ChartWindow extends ApplicationFrame {
	ChartPanel chartPanel;

	public ChartWindow(String applicationTitle, String chartTitle,
			JFreeChart barChart) {
		super(applicationTitle);

		chartPanel = new ChartPanel(barChart);
	}

}
