package com.pallasli.study.jfreechart;
import org.junit.Test;

import com.pallasli.study.jfreechart.factory.BarChartFactory;

public class BarChart {
	@Test
	public void test() throws Exception {
		new BarChartFactory().saveChartFile();
		new BarChartFactory().show();
		new BarChartFactory().show3D();
	}
}