package com.lyt.pallas.basic.poi.excel;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelRead2 {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		try {

			String filePath = Thread.currentThread().getClass()
					.getResource("/").getPath()
					+ "workbook.xls";

			filePath = filePath.replaceAll("%20", " ");
			InputStream myxls = new FileInputStream(filePath);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);

			HSSFSheet sheet = wb.getSheetAt(0);

			java.util.Iterator rowIt = sheet.rowIterator();

			while (rowIt.hasNext()) {
				HSSFRow row = (HSSFRow) rowIt.next();
				java.util.Iterator cellIt = row.cellIterator();
				while (cellIt.hasNext()) {
					HSSFCell cell = (HSSFCell) cellIt.next();
					System.out.println(getData(cell));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Object getData(HSSFCell cell) {
		if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return cell.getNumericCellValue();
		} else {
			return null;
		}
	}
}
