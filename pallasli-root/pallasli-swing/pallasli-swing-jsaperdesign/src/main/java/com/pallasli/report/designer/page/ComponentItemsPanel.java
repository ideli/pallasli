package com.pallasli.report.designer.page;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.pallasli.report.designer.Main;

public class ComponentItemsPanel extends JPanel {
	public List list = null;
	public JButton selectedComp;

	/**
	 * Create the panel.
	 */
	public ComponentItemsPanel() {

		FlowLayout flowLayout_3 = (FlowLayout) getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		JToolBar toolBar_3 = new JToolBar();

		add(toolBar_3);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(e);
				System.out.println(e.getSource());
				// selectedComp=
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(Main.class.getResource("/designer/icons/tool/new.gif")));
		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		toolBar_3.add(btnNewButton_5);
	}

}
