package com.pallasli.study.jfreechart;
import org.junit.Test;

import com.pallasli.study.jfreechart.factory.PieChartFactory;

public class PieChart {
	@Test
	public void test() throws Exception {
		new PieChartFactory().saveChartFile();
		new PieChartFactory().show();
		new PieChartFactory().show3D();
	}
}