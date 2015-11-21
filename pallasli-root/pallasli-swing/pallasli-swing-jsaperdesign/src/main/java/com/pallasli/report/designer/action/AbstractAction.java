package com.pallasli.report.designer.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import com.pallasli.report.designer.Main;

public abstract class AbstractAction {

	protected Main mainFrame = Main.getInstance();
	protected NewAction instance;
	protected AbstractButton action;

	public AbstractAction(AbstractButton action) {
		this.setAction(action);
		addListeners();
	}

	public AbstractButton getAction() {
		return action;
	}

	public void setAction(AbstractButton action) {
		this.action = action;
	}

	public AbstractAction getInstance() {
		return instance;
	}

	public void addListeners() {
		action.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				execute();
			}
		});
	}

	public abstract void execute();
}
