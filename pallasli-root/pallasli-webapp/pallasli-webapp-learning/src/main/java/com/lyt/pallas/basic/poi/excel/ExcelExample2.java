package com.lyt.pallas.basic.poi.excel;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelExample2 {

	public static void main(String[] args) {

		try {

			// ������HSSFWorkbook����
			HSSFWorkbook wb = new HSSFWorkbook();

			// �����µ�sheet����
			HSSFSheet sheet = wb.createSheet("new sheet");

			// ��sheet�ﴴ��һ�У�����Ϊ�кţ���һ�У��˴�����������飩
			HSSFRow row = sheet.createRow((short) 0);

			// ��row�ｨ����cell����Ԫ�񣩣�����Ϊ�кţ���һ�У�
			HSSFCell cell = row.createCell((short) 0);

			// ����cell���������͵�ֵ
			cell.setCellValue(001);

			// ����cell�������͵�ֵ
			row.createCell((short) 1).setCellValue(1.2);

			// ����cell�ַ����͵�ֵ
			row.createCell((short) 2).setCellValue("test");

			// ����cell�������͵�ֵ
			row.createCell((short) 3).setCellValue(true);

			// �����µ�cell��ʽ
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("m/d/yy h:mm"));
			// ����cell��ʽΪ���Ƶ����ڸ�ʽ
			HSSFCell dCell = row.createCell((short) 4);
			// ����cellΪ�������͵�ֵ
			dCell.setCellValue(new Date());
			// ���ø�cell���ڵ���ʾ��ʽ
			dCell.setCellStyle(cellStyle);

			HSSFCell csCell = row.createCell((short) 5);
			// ����cell���������ĸ�λ�ֽڽض�
			// csCell.setEncoding(HSSFCell.ENCODING_UTF_16);
			csCell.setCellValue("���Ĳ���_Chinese Words Test"); // ���������Ľ���ַ�

			FileOutputStream fileOut = new FileOutputStream("D:/workbook.xls");
			wb.write(fileOut);
			fileOut.close();

			System.out.println("������ɣ�����");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
