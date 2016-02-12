package com.pallasli.study.poi.excel;

import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Test;

import com.pallasli.study.poi.excel.ExcelOperation;

public class ExcelOperationTest {
	@Test
	public void createWorkBook() {
		ExcelOperation.createWorkBook();
		XSSFSheet sheet = ExcelOperation.addSheet("sheet001");
		Map<String, Object[]> dataMap = new TreeMap<String, Object[]>();
		dataMap.put("1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
		dataMap.put("2", new Object[] { "tp01", "Gopal", "Technical Manager" });
		dataMap.put("3", new Object[] { "tp02", "Manisha", "Proof Reader" });
		dataMap.put("4", new Object[] { "tp03", "Masthan", "Technical Writer" });
		dataMap.put("5", new Object[] { "tp04", "Satish", "Technical Writer" });
		dataMap.put("6", new Object[] { "tp05", "Krishna", "Technical Writer" });
		ExcelOperation.addData(sheet, dataMap);
		ExcelOperation.saveWorkbook("createworkbook.xlsx");
	}

	@Test
	public void openWorkBook() {
		ExcelOperation.openWorkBook("createworkbook.xlsx");
		ExcelOperation.readSheet("sheet001");
	}
}
