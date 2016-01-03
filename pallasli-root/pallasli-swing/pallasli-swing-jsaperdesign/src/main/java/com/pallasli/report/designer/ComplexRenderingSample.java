package com.pallasli.report.designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ListCellRenderer;

public class ComplexRenderingSample {
	public static void main(String args[]) {
		Object elements[][] = {
				{ new Font("Helvetica", Font.PLAIN, 20), Color.RED,
						new MyIcon("/designer/icons/tool/movedown.gif"), "A" },
				{ new Font("TimesRoman", Font.BOLD, 14), Color.BLUE,
						new MyIcon("/designer/icons/tool/moveup.gif"), "A" } };

		JFrame frame = new JFrame("Complex Renderer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ListCellRenderer renderer = new ComplexCellRenderer();
		JComboBox comboBox = new JComboBox(elements);
		comboBox.setRenderer(renderer);
		frame.add(comboBox, BorderLayout.NORTH);

		frame.setSize(300, 200);
		frame.setVisible(true);
	}

}
