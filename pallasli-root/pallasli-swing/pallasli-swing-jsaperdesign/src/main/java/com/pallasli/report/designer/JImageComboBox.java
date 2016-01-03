package com.pallasli.report.designer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

public class JImageComboBox extends JComboBox {
	public JImageComboBox(Object[][] data) {
		super(data);
		ListCellRenderer renderer = new ComplexCellRenderer();

		setRenderer(renderer);

	}

	@Override
	public Object getSelectedItem() {
		Object item = super.getSelectedItem();

		Object values[] = (Object[]) item;
		int value = (Integer) values[0];
		Font font = (Font) values[1];
		Color color = (Color) values[2];
		MyIcon icon = (MyIcon) values[3];
		String text = (String) values[4];

		return new ComboTypeValue(value, font, color, icon, text);

	}
}
