package com.pallasli.report.designer.page;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ReportDesignPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ReportDesignPanel() {
		setBackground(Color.WHITE);
		setLayout(null);

	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}
