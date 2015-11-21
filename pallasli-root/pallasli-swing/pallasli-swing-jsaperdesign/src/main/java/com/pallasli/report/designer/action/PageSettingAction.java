package com.pallasli.report.designer.action;

import javax.swing.AbstractButton;

import com.pallasli.report.designer.page.PageSettingDialog;

public class PageSettingAction extends AbstractAction {
	public PageSettingAction(AbstractButton action) {
		super(action);
	}

	public void execute() {
		PageSettingDialog dialog = new PageSettingDialog();
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setVisible(true);
	}
}
