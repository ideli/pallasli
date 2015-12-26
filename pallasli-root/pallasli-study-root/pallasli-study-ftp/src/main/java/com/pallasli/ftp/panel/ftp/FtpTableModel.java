/**
 * 
 */
package com.pallasli.ftp.panel.ftp;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
class FtpTableModel extends DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class };
	boolean[] canEdit = new boolean[] { false, false, false };

	FtpTableModel() {
		super(new Object[][] {}, new String[] { "文件名", "大小", "日期" });
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}