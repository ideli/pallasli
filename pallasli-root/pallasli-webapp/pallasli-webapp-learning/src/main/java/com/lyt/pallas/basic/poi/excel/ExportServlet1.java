package com.lyt.pallas.basic.poi.excel;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@SuppressWarnings("serial")
public class ExportServlet1 extends HttpServlet {

	public ExportServlet1() {
		super();
	}

	
	public void destroy() {
		super.destroy();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取得输出流
		OutputStream os = response.getOutputStream();
		// 清空输出流
		response.reset();
		response.setHeader("Content-disposition",
				"attachment; filename=workbook.xls");
		response.setContentType("application/msexcel");

		// 创建一个excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		wb.write(os);
		os.flush();
		os.close();

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

	
	public void init() throws ServletException {

	}

}
