package com.pallasli.report.designer.action;

import javax.swing.AbstractButton;

public class ExitAction extends AbstractAction {
	public ExitAction(AbstractButton action) {
		super(action);
	}

	public void execute() {
		System.exit(0);
	}
}
