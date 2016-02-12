package com.pallasli.study.jfreechart;
import org.junit.Test;

import com.pallasli.study.jfreechart.factory.XYLineChartFactory;

public class XYLineChart {
	@Test
	public void test() throws Exception {
		new XYLineChartFactory().show();
	}
}