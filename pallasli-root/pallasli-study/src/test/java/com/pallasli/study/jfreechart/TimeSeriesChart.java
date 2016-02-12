package com.pallasli.study.jfreechart;
import org.junit.Test;

import com.pallasli.study.jfreechart.factory.TimeSeriesChartFactory;

public class TimeSeriesChart {
	@Test
	public void test() throws Exception {
		new TimeSeriesChartFactory().show();
	}
}