package com.pallasli.report.designer;

import java.awt.Color;
import java.awt.Font;

public class ComboTypeValue {
	private Font font;
	private Color color;
	private MyIcon icon;
	private String text;
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public MyIcon getIcon() {
		return icon;
	}

	public void setIcon(MyIcon icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ComboTypeValue(int value, Font font, Color color, MyIcon icon,
			String text) {
		super();
		this.value = value;
		this.font = font;
		this.color = color;
		this.icon = icon;
		this.text = text;
	}

}
