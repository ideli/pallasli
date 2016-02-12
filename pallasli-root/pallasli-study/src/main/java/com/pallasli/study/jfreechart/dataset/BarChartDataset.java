package com.pallasli.study.jfreechart.dataset;

public class BarChartDataset {
	private double value;
	private String group;
	private String item;

	public BarChartDataset(double value, String group, String item) {
		this.value = value;
		this.group = group;
		this.item = item;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
