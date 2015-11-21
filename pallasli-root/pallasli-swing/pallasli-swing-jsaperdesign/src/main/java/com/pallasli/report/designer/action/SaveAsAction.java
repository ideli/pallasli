package com.pallasli.report.designer.action;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;

public class SaveAsAction extends AbstractAction {
	public SaveAsAction(AbstractButton action) {
		super(action);
	}

	public void execute() {
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(mainFrame);
	}

}
