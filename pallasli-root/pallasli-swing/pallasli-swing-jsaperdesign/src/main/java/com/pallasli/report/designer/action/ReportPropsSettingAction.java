package com.pallasli.report.designer.action;

import javax.swing.AbstractButton;

import com.pallasli.report.designer.page.ReportPropsSettingDialog;

public class ReportPropsSettingAction extends AbstractAction {
	public ReportPropsSettingAction(AbstractButton action) {
		super(action);
	}

	public void execute() {
		ReportPropsSettingDialog dialog = new ReportPropsSettingDialog();
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setVisible(true);
	}
}
