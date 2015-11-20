package com.pallasli.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ExcelUtils {

	public static boolean writeExcel(String filePath, List<String> sheets,
			List<JsonArray> dataArrayList, List<String> autoColumns) {
		HSSFWorkbook wb1 = new HSSFWorkbook();
		HSSFSheet sheet1 = wb1.createSheet("new sheet");
		HSSFRow row1 = sheet1.createRow(0);
		row1.createCell(0).setCellType(0);
		CellStyle cs = row1.getCell(0).getCellStyle();

		cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		row1.getCell(0).setCellStyle(cs);
		row1.getCell(0).setCellValue(1111111111111.11);
		row1.createCell(1).setCellValue(new Date());

		cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("d-mmm-yy"));
		row1.getCell(1).setCellStyle(cs);
		row1.createCell(2).setCellValue("框架阿拉是法拉利框架阿拉是法拉利");
		row1.createCell(3).setCellValue(true);
		row1.createCell(4).setCellType(HSSFCell.CELL_TYPE_ERROR);

		sheet1.autoSizeColumn(0);
		sheet1.autoSizeColumn(1);
		sheet1.autoSizeColumn(2);
		sheet1.autoSizeColumn(3);
		sheet1.autoSizeColumn(4);
		// Write the output to a file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("workbook.xls");
			wb1.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static JsonArray readExcel(String filePath, String sheetName) {
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		JsonArray result = new JsonArray();
		try {
			fs = new POIFSFileSystem(new FileInputStream("workbook.xls"));
			wb = new HSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int k = 0; k < wb.getNumberOfSheets(); k++) {
			JsonArray rowData = new JsonArray();
			HSSFSheet sheet = wb.getSheetAt(k);
			int rows = sheet.getPhysicalNumberOfRows();

			for (int r = 0; r < rows; r++) {
				JsonObject data = new JsonObject();
				HSSFRow row = sheet.getRow(r);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();

					for (int c = 0; c < cells; c++) {
						HSSFCell cell = row.getCell(c);
						if (cell != null) {
							String value = null;

							switch (cell.getCellType()) {

							case HSSFCell.CELL_TYPE_FORMULA:
								value = "FORMULA ";
								break;

							case HSSFCell.CELL_TYPE_NUMERIC:
								value = "NUMERIC value="
										+ cell.getNumericCellValue();
								break;

							case HSSFCell.CELL_TYPE_STRING:
								value = "STRING value="
										+ cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								value = "STRING value="
										+ cell.getBooleanCellValue();
								break;
							case HSSFCell.CELL_TYPE_ERROR:
								value = "STRING value="
										+ cell.getErrorCellValue();
								break;

							default:
								value = " value=" + cell.getStringCellValue();
							}
							data.addProperty("value", value);
						}
					}
				}
				rowData.add(data);
			}
			result.add(rowData);
		}
		return result;
	}

}
