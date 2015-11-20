package com.pallasli.report.designer.tooloper;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReportDesignPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ReportDesignPanel() {

		textField = new JTextField();
		textField.setDragEnabled(true);
		add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		add(comboBox);

		JButton btnNewButton = new JButton("New button");
		add(btnNewButton);

	}

}
