package com.pallasli.report.designer.action;

import javax.swing.AbstractButton;

import com.pallasli.report.designer.page.AboutDesignerDialog;

public class AboutDesignerAction extends AbstractAction {
	public AboutDesignerAction(AbstractButton action) {
		super(action);
	}

	public void execute() {
		AboutDesignerDialog dialog = new AboutDesignerDialog();
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setVisible(true);
	}
}
