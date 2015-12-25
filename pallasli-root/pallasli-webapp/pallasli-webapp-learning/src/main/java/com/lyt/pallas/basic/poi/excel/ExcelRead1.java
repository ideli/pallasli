package com.lyt.pallas.basic.poi.excel;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelRead1 {

	public static void main(String[] args) {

		try {
			//得到excel的存放路径
			String filePath = Thread.currentThread().getClass()
					.getResource("/").getPath()
					+ "workbook.xls";
			//将路径中的%20替换成空格,在unicode码中%20代表空格
			filePath = filePath.replaceAll("%20", " ");
			//输入流将文件读入系统
			InputStream myxls = new FileInputStream(filePath);
			
			HSSFWorkbook wb = new HSSFWorkbook(myxls);

			// 第一个工作表
			HSSFSheet sheet = wb.getSheetAt(0); 

			// 第一行
			HSSFRow row = sheet.getRow(0); 
			
			 // 第四个单元格
			HSSFCell cell = row.getCell((short) 3);

			//判断单元格中的数据类型
			if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
				System.out.println(cell.getBooleanCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				System.out.println(cell.getStringCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				System.out.println(cell.getNumericCellValue());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
