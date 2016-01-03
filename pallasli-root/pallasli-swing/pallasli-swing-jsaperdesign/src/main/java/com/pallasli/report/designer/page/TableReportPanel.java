package com.pallasli.report.designer.page;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.pallasli.report.designer.event.DragListener;

public class TableReportPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TableReportPanel() {
		setBackground(Color.WHITE);
		setLayout(null);

		ModelDemo demoModel = new ModelDemo();
		// demoModel.
		Object[][] playerInfo = {
				{ "阿呆", new Integer(66), new Integer(32), new Integer(98),
						new Boolean(false) },
				{ "阿呆", new Integer(82), new Integer(69), new Integer(128),
						new Boolean(true) }, };
		String[] Names = { "姓名", "语文", "数学", "总分", "及格" };
		JTable table = new JTable(demoModel);
		table.setPreferredScrollableViewportSize(new Dimension(550, 30));

		ModelDemo datamodel = (ModelDemo) table.getModel();
		datamodel.addColumn();
		table.setModel(datamodel);

		TableColumnModel defaultModel = table.getColumnModel();
		TableColumn cl = new TableColumn(3);
		cl.setHeaderValue("af");
		defaultModel.addColumn(cl);
		// TableColumn cl = new TableColumn();
		// cl.setWidth(200);
		// table.addColumn(cl);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(600, 120);
		scrollPane.setLocation(10, 10);
		new DragListener(scrollPane);
		add(scrollPane);
	}
}
