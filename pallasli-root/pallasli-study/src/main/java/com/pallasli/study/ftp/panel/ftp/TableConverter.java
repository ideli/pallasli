package com.pallasli.study.ftp.panel.ftp;

import javax.swing.table.TableModel;
import javax.swing.table.TableStringConverter;

import com.pallasli.study.ftp.extClass.FileInterface;

public class TableConverter extends TableStringConverter {

	@Override
	public String toString(TableModel model, int row, int column) {
		Object value = model.getValueAt(row, column);
		if (value instanceof FileInterface) {
			FileInterface file = (FileInterface) value;
			if (file.isDirectory())
				return "!" + file.toString();
			else
				return "Z" + file.toString();
		}
		if (value.equals(".") || value.equals(".."))
			return "!!";
		return value.toString();
	}

}
