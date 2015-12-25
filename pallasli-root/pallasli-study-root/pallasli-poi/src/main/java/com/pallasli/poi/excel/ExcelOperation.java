package com.pallasli.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {
	private static XSSFWorkbook workbook;

	public static void createWorkBook() {
		workbook = new XSSFWorkbook();

	}

	public static XSSFSheet addSheet(String sheetName) {
		return workbook.createSheet(sheetName);
	}

	public static void saveWorkbook(String path) {
		// Create file system using specific name
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("createworkbook.xlsx written successfully");
	}

	public static void openWorkBook(String path) {
		File file = new File(path);
		FileInputStream fIP;
		try {
			fIP = new FileInputStream(file);
			// Get the workbook instance for XLSX file
			workbook = new XSSFWorkbook(fIP);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.isFile() && file.exists()) {
			System.out.println("openworkbook.xlsx file open successfully.");
		} else {
			System.out.println("Error to open openworkbook.xlsx file.");
		}
	}

	public static void readSheet(String string) {
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			XSSFRow row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + " \t\t ");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + " \t\t ");
					break;
				}
			}
			System.out.println();
		}
	}

	public static void addData(XSSFSheet spreadsheet,
			Map<String, Object[]> dataMap) {
		Set<String> keyid = dataMap.keySet();
		int rowid = 0;
		for (String key : keyid) {
			XSSFRow row = spreadsheet.createRow(rowid++);
			Object[] objectArr = dataMap.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		System.out.println("Writesheet.xlsx written successfully");
	}
}
