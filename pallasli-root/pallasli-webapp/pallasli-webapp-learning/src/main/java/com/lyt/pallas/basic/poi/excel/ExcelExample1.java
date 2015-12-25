package com.lyt.pallas.basic.poi.excel;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelExample1 {

	// Apache的Jakata项目的子项目POI，POI可以到www.apache.org下载到

	/*
	 * 首先，理解一下一个Excel的文件的组织形式，一个Excel文件对应于一个workbook(HSSFWorkbook)，一个workbook可以有多个sheet
	 * （HSSFSheet）组成，一个sheet是由多个row（HSSFRow）组成，一个row是由多个cell（HSSFCell）组成
	 */

	// POI
	// 处理excel目前比较成熟的接口是HSSF，给用户使用的对象在org.apache.poi.hssf.usermodel包中,主要部分包括Excel对象，样式和格式，还有辅助操作
	// POI中的常用api
	// HSSFWorkbook excel的文档对象
	// HSSFSheet excel的表单
	// HSSFRow excel的行
	// HSSFCell excel的格子单元
	// HSSFFont excel字体
	// HSSFDataFormat 日期格式
	// 从一个简单的例子进入poi教程
	public static void main(String args[]) {
		try {

			// 创建一个excel文件
			HSSFWorkbook wb = new HSSFWorkbook();

			FileOutputStream fileOut = new FileOutputStream("D:\\workbook.xls");

			wb.write(fileOut);
			fileOut.close();
			System.out.println("创建完成！！！！");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
