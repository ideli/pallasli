package com.pallasli.study.jfreechart.factory;

import org.jfree.chart.JFreeChart;

public interface Factory {

	public JFreeChart create3D() throws Exception;

	public JFreeChart create() throws Exception;

	public void saveChartFile() throws Exception;

	public void show() throws Exception;

	public void show3D() throws Exception;

}
