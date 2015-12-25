package com.lyt.pallas.basic.poi.excel;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportServlet2Help {

	public static void export() {
		try {
			// 创建一个excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream("D:\\workbook.xls");
			wb.write(fileOut);
			fileOut.close();
			System.out.println("������ɣ�������");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
