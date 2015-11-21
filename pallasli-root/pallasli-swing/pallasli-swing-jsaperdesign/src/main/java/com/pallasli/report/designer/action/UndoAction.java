package com.pallasli.report.designer.action;

import javax.swing.AbstractButton;

import com.pallasli.report.designer.page.NewFileDialog;

public class UndoAction extends AbstractAction {
	public UndoAction(AbstractButton action) {
		super(action);
	}

	public void execute() {
		NewFileDialog dialog = new NewFileDialog();
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setVisible(true);
	}

}
