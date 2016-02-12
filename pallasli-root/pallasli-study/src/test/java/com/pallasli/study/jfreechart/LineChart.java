package com.pallasli.study.jfreechart;
import org.junit.Test;

import com.pallasli.study.jfreechart.factory.LineChartFactory;

public class LineChart {
	@Test
	public void test() throws Exception {
		new LineChartFactory().show();
	}
}