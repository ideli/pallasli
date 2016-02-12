package com.pallasli.poi.excel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.JsonArray;

public class ExcelUtilsTest {

	@Test
	public void writeExcel() {
		String filePath = "";
		List<JsonArray> dataArrayList = new ArrayList<JsonArray>();
		List<String> sheets = new ArrayList<String>();
		List<String> autoColumns = new ArrayList<String>();

		boolean flag = ExcelUtils.writeExcel(filePath, sheets, dataArrayList, autoColumns);
		assertTrue(flag);
	}

	@Test
	public void readExcel() {
		String filePath = "";
		String sheet = "";
		JsonArray dataArray = ExcelUtils.readExcel(filePath, sheet);
		assertNotNull(dataArray);
	}

}
