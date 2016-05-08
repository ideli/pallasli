/**
 * 
 */
package com.pallasli.website.report;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.junit.Assert;
import org.junit.Before;

import com.pallasli.website.report.AbstDataSource;
import com.pallasli.website.report.Report;

/**
 * 运行前，请在本机建立report的工作目录RP_DIR，并将images文件夹和jrxml文件复制过去 list.jrxml可以做为新建报表的模板。
 * 
 * @author weigq
 * 
 */
public class ReportTest {

	private Report report;
	static final String RP_DIR = "d:/test/report/";

	/**
	 * 运行前，请在本机建立report的工作目录RP_DIR，并将images文件夹和jrxml文件复制过去
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		report = new Report();
		report.setExportDir(RP_DIR);
		report.setReportDir(RP_DIR);
	}

	/**
	 * Test method for
	 * {@link com.pallasli.website.report.Report#pdfReportList(java.lang.String, java.util.List)}
	 * .
	 */
	// @Test
	public void testPdfReportListStringListOfQextendsObject() {
		String reportName = "list2";
		List<TLine> data = new ArrayList<TLine>();
		for (int i = 0; i < 5; i++) {
			TLine e = new TLine();
			e.setId(i);
			e.setName("name_姓" + i);
			data.add(e);
		}
		File file = null;
		try {
			file = report.pdfReportList(reportName, data);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Assert.state(false, "JRException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Assert.assertState(false, "IOException");
		}
		Assert.assertNotNull(file);

	}

	/**
	 * Test method for
	 * {@link com.pallasli.website.report.Report#pdfReportList(java.lang.String, java.util.Map, java.util.List)}
	 * .
	 */
	// @Test
	public void testPdfReportListStringMapOfStringObjectListOfQextendsObject() {
		String reportName = "list";
		List<TLine> data = new ArrayList<TLine>();
		for (int i = 0; i < 5; i++) {
			TLine e = new TLine();
			e.setId(i);
			e.setName("name_姓" + i);
			data.add(e);
		}
		File file = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("pTitle", "b测试参数a");
			file = report.pdfReportList(reportName, parameters, data);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Assert.state(false, "JRException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Assert.state(false, "IOException");
		}
		Assert.assertNotNull(file);
	}

	/**
	 * Test method for
	 * {@link com.pallasli.website.report.Report#pdfReport(java.lang.String, java.util.Map)}
	 * .
	 */
	// @Test
	public void testPdfReportStringMapOfStringObject() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("disp", "test1中文");
		try {
			File file = report.pdfReport("onlyParam", parameters);
			Assert.assertNotNull(file);
		} catch (JRException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception" + e);
		}
	}

	/**
	 * Test method for
	 * {@link com.pallasli.website.report.Report#pdfReport(java.lang.String, java.util.Map, com.pallasli.website.report.AbstDataSource)}
	 * .
	 */
	// @Test
	public void testPdfReportStringMapOfStringObjectAbstDataSource() {
		String reportName = "list";
		List<TLine> list = new ArrayList<TLine>();
		for (int i = 0; i < 5; i++) {
			TLine e = new TLine();
			e.setId(i);
			e.setName("name_姓" + i);
			list.add(e);
		}
		File file = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("pTitle", "b测试参数a");
			AbstDataSource data = Report.list2DataSource(list);
			file = report.pdfReport(reportName, parameters, data);
			// throw new JRException("ss");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Assert.state(false, "JRException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Assert.state(false, "IOException");
		}
		Assert.assertNotNull(file);
	}

	/**
	 * Test method for
	 * {@link com.pallasli.website.report.Report#list2DataSource(java.util.List)}.
	 */
	// @Test
	public void testList2DataSource() {
		List<TLine> data = new ArrayList<TLine>();
		for (int i = 0; i < 5; i++) {
			TLine e = new TLine();
			e.setId(i);
			e.setName("name_姓" + i);
			data.add(e);
		}
		AbstDataSource ds = Report.list2DataSource(data);
		Assert.assertNotNull(ds);
	}

}
