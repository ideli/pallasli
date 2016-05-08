/**
 * 河北省神玥软件科技有限公司 版权所有
 * @file 文件:LoginUser.java
 * @date 创建时间:2015-12-4
 * @author 创建人:魏广强
 * @Description TODO
 */
package com.pallasli.website.report.sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pallasli.website.report.AbstDataSource;
import com.pallasli.website.report.Report;
import com.pallasli.website.report.WebappDataSource;
import com.pallasli.website.report.sample.ds.HkMx;
import com.pallasli.website.report.sample.ds.TLine;
import com.pallasli.website.report.sample.ds.TLine2;
import com.pallasli.website.report.sample.ds.TLineStudentScore;
import com.pallasli.website.report.sample.ds.TestDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MainTest {
	static final String RP_DIR = "d:/test/report/";

	public static void main(String[] args) throws JRException, IOException {

		/**
		 * 运行前，请在本机建立report的工作目录RP_DIR，并将images文件夹和jrxml文件复制过去
		 * list.jrxml可以做为新建报表的模板。
		 */
		// test_list();
		// test_dkhkmx();
		// test_bingtu();
		// test_zhutu();
		// test_xiantu();
		// test_table();
		// test_table_duobiao();
		// test_list2();
		// test_onlyParams();
		// test_subreport();//subreport
		// test_spiderChart();
		// test_erweima();
		test_pagelist();
	}

	public static void test_erweima() throws JRException, IOException {

		System.out.println("二维码!");
		Report t = new Report();
		t.setReportDir(RP_DIR);
		t.setExportDir(RP_DIR);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Parameter1", "abcdefghijklmnopqrstuvwxyz");

		File f = t.pdfReport("2weima", parameters);
		System.out.println("生成的pdf文件：" + f.getAbsolutePath());

	}

	/**
	 * spiderChart 蜘蛛图，可以显示个体的多个方面综合发展的情况。例：显示学生的各科偏科情况<br>
	 * 注意：难点在设计时的sc:spiderDataset内容如何设置。
	 * <categorySeries> <seriesExpression><![CDATA[$F{name}]]>
	 * </seriesExpression>
	 * <categoryExpression><![CDATA["shuxue"]]></categoryExpression>
	 * <valueExpression><![CDATA[$F{shuxue}]]></valueExpression>
	 * <labelExpression><![CDATA["shuxue"]]></labelExpression> </categorySeries>
	 * 一个categorySeries是一个线，一般最少3条线，否则太难看，不如用柱图来表示了。
	 * categoryExpression是这个线的id：用来区分不同的线的 labelExpression是这个线的标签
	 * seriesExpression 1条记录的id，学生的id valueExpression 1条记录在此项上的值。学生这一科的成绩
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public static void test_spiderChart() throws JRException, IOException {

		System.out.println("Hello World!");
		Report t = new Report();
		t.setReportDir(RP_DIR);
		t.setExportDir(RP_DIR);
		String reportName = "spiderChart";
		// Map<String, Object> parameters = new HashMap<String, Object>();
		// parameters.put("pTitle", "test1中文");

		List<TLineStudentScore> list = new ArrayList<TLineStudentScore>();
		// for (int i = 0; i < 3; i++) {
		TLineStudentScore e = new TLineStudentScore();
		e.setName("小明");
		e.setHuaxue(80);
		e.setShuxue(70);
		e.setWuli(90);
		e.setYingyu(85);
		e.setYuwen(80);
		list.add(e);

		e = new TLineStudentScore();
		e.setName("小位");
		e.setHuaxue(90);
		e.setShuxue(97);
		e.setWuli(93);
		e.setYingyu(65);
		e.setYuwen(70);
		list.add(e);

		e = new TLineStudentScore();
		e.setName("小红");
		e.setHuaxue(70);
		e.setShuxue(67);
		e.setWuli(73);
		e.setYingyu(95);
		e.setYuwen(89);
		list.add(e);

		// }
		File f = t.pdfReportList(reportName, list);
		System.out.println("生成的pdf文件：" + f.getAbsolutePath());

	}

	public static void test_onlyParams() throws JRException, IOException {

		System.out.println("Hello World!");
		Report t = new Report();
		t.setReportDir(RP_DIR);
		t.setExportDir(RP_DIR);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("disp", "test1中文");

		File f = t.pdfReport("onlyParam", parameters);
		System.out.println("生成的pdf文件：" + f.getAbsolutePath());

	}

	public static void test_list2() throws JRException, IOException {

		System.out.println("Hello World!");
		Report t = new Report();
		t.setReportDir(RP_DIR);
		t.setExportDir(RP_DIR);

		List<TLine> data = new ArrayList<TLine>();
		for (int i = 0; i < 5; i++) {
			TLine e = new TLine();
			e.setId(i);
			e.setName("name_姓" + i);
			data.add(e);
		}
		File f = t.pdfReportList("list2", data);
		// File f =t.pdfReportList("list2", null );
		System.out.println("生成的pdf文件：" + f.getAbsolutePath());

	}

	public static void test_pagelist() throws JRException, IOException {

		System.out.println("pagelist!");
		Report t = new Report();
		t.setReportDir(RP_DIR);
		t.setExportDir(RP_DIR);

		List<TLine> data = new ArrayList<TLine>();
		for (int i = 0; i < 50; i++) {
			TLine e = new TLine();
			e.setId(i);
			e.setName("name_姓" + i);
			data.add(e);
		}
		File f = t.pdfReportList("pageList", data);
		// File f =t.pdfReportList("list2", null );
		System.out.println("生成的pdf文件：" + f.getAbsolutePath());

	}

	/**
	 * 基本的逐行打印数据
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public static void test_list() throws JRException, IOException {

		System.out.println("Hello World!");
		Report t = new Report();
		t.setReportDir(RP_DIR);
		t.setExportDir(RP_DIR);
		String reportName = "list";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pTitle", "test1中文");

		AbstDataSource dataSource = new TestDataSource();
		File f = t.pdfReport(reportName, parameters, dataSource);
		System.out.println("生成的pdf文件：" + f.getAbsolutePath());

	}

	/**
	 * 线图
	 */
	public static void test_xiantu() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);

		AbstDataSource dataSource = new AbstDataSource() {
			private int i = -1;
			private int g = 0;
			private Map<String, Object> line = new HashMap<String, Object>();

			@Override
			public boolean next() throws JRException {
				line.clear();
				i++;
				if (i % 3 == 0) {//// xml文件中定义了3个颜色，所以这边每3个算一个组//其实定义的所有颜色是循环使用的
					g++;
				}
				// 多个股票价格走势图，多人总成绩走势图， 某人的多科成绩变化趋势，
				if (i < 12) {
					line.put("wdisp", "xx" + (i % 3));// 注意这里，其实绘图时，是以这个来判断是不是同一个颜色和图标并连成一个线的，应为有限几个。股票走势图这里就是股票名。
					// 如果多人成绩对比，也应在这里体现：李小明，王小朋等人名。
					// 科目名：数学、语文、化学成绩
					line.put("wgroup", g);// 这里应该是学期，时间点的值，观察时间越长，X坐标就越长
					line.put("wvalue", Double.valueOf((i + 1) + ""));// 成绩
																		// 分数。越大Y就越高。
					return true;
				}
				return false;
			}

			@Override
			public Object getFieldValue(JRField jrField) throws JRException {
				String fieldName = jrField.getName();
				return line.get(fieldName);
			}

		};
		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			pdfFile = report.pdfReport("xiantu", parameters, dataSource);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 柱图2,故意犯错测试一个功能
	 */
	public static void test_zhutu2() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);

		AbstDataSource dataSource = new AbstDataSource() {
			private int i = -1;
			private int g = 0;
			private Map<String, Object> line = new HashMap<String, Object>();

			@Override
			public boolean next() throws JRException {
				line.clear();
				i++;
				if (i % 3 == 0) {//// xml文件中定义了3个颜色，所以这边每3个算一个组//其实定义的所有颜色是循环使用的
					g++;
				}
				if (i < 12) {
					line.put("wdisp", "xx" + i);
					line.put("wgroup", g);
					line.put("wvalue", Double.valueOf((i + 1) + ""));
					return true;
				}
				return false;
			}

			@Override
			public Object getFieldValue(JRField jrField) throws JRException {
				String fieldName = jrField.getName();
				return line.get(fieldName);
			}

		};
		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			pdfFile = report.pdfReport("zhutu", parameters, dataSource);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 柱图
	 */
	public static void test_zhutu() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);

		AbstDataSource dataSource = new AbstDataSource() {
			private int i = -1;
			private int g = 0;
			private Map<String, Object> line = new HashMap<String, Object>();

			@Override
			public boolean next() throws JRException {
				line.clear();
				i++;
				if (i % 3 == 0) {//// xml文件中定义了3个颜色，所以这边每3个或2个算一个组//如果组定义为4个，颜色不够用时就会有重复，其实定义的所有颜色不够时是循环使用的
					g++;
				}
				if (i < 12) {
					line.put("wdisp", "xx" + (i % 3));// 注意这里，其实绘图时，是以这个来判断是不是同一个颜色和图标的。应为有限几个。在test_zhutu2中设为不同的了，下方的标识就好多了，应该是不符合人的平常想法的
					line.put("wgroup", g);
					line.put("wvalue", Double.valueOf((i + 1) + ""));
					return true;
				}
				return false;
			}

			@Override
			public Object getFieldValue(JRField jrField) throws JRException {
				String fieldName = jrField.getName();
				return line.get(fieldName);
			}

		};
		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			pdfFile = report.pdfReport("zhutu", parameters, dataSource);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 饼图，比例图
	 */
	public static void test_bingtu() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);

		AbstDataSource dataSource = new AbstDataSource() {
			private int i = -1;
			private Map<String, Object> line = new HashMap<String, Object>();

			@Override
			public boolean next() throws JRException {
				line.clear();
				i++;
				if (i < 4) {// xml文件中只定义了4个颜色，如果这边多了的话会循环使用
					line.put("wdisp", "xx" + i);
					line.put("wvalue", Double.valueOf((i + 1) + ""));
					return true;
				}
				return false;
			}

			@Override
			public Object getFieldValue(JRField jrField) throws JRException {
				String fieldName = jrField.getName();
				return line.get(fieldName);
			}

		};
		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			pdfFile = report.pdfReport("bingtu", parameters, dataSource);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 表格
	 */
	public static void test_table() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);

		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			List<TLine> list = new ArrayList<TLine>();
			for (int i = 0; i < 55; i++) {
				TLine line = new TLine();
				line.setId(i);
				line.setName("name" + i);
				list.add(line);
			}
			AbstDataSource dataSource = new WebappDataSource(list);

			parameters.put("testdt", dataSource);
			pdfFile = report.pdfReport("table", parameters);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 表格 1个报表上有2个表格 不使用子报表。（1个报表中使用2个数据源，用参数形式，需要用到subDataSet）
	 */
	public static void test_table_duobiao() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);

		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			List<TLine> list = new ArrayList<TLine>();
			for (int i = 0; i < 55; i++) {
				TLine line = new TLine();
				line.setId(i);
				line.setName("name" + i);
				list.add(line);
			}
			AbstDataSource dataSource = new WebappDataSource(list);

			List<TLine2> list2 = new ArrayList<TLine2>();
			for (int i = 0; i < 30; i++) {
				TLine2 line = new TLine2();
				line.setId(i);
				line.setName("name" + i);
				line.setAge(i + 30);
				list2.add(line);
			}
			AbstDataSource dataSource2 = new WebappDataSource(list2);

			parameters.put("testdt", dataSource);
			parameters.put("testdt2", dataSource2);
			pdfFile = report.pdfReport("table_duobiao", parameters);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 子报表 。其中有两个子报表，左边这个是list报表，有图片有pTitle这个参数的，右边是我们的柱图，只有一个柱图所以只需要一个数据源
	 * 实际效果：两边的报表都是按原大小输出，左边的因为宽度太大一直打印到最右边了成背景了也没显示全；右边那个子报表更显得显示不全
	 * 注意设计时子报表的中的参数都得在主报表中传递，数据源以参数来传递，类似于一个报表中多个数据源的做法
	 */
	public static void test_subreport() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);

		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();

			// AbstDataSource dataSource1 = new AbstDataSource() {
			// private int i = -1;
			// private Map<String, Object> line = new HashMap<String, Object>();
			//
			// @Override
			// public boolean next() throws JRException {
			// line.clear();
			// i++;
			// if (i < 4) {// xml文件中只定义了4个颜色，如果这边多了的话会循环使用
			// line.put("wdisp", "xx" + i);
			// line.put("wvalue", Double.valueOf((i + 1) + ""));
			// return true;
			// }
			// return false;
			// }
			//
			// @Override
			// public Object getFieldValue(JRField jrField) throws JRException {
			// String fieldName = jrField.getName();
			// return line.get(fieldName);
			// }
			//
			// };
			AbstDataSource dataSource1 = new TestDataSource();
			parameters.put("erweima", "12345");
			parameters.put("pTitle", "test1中文");
			parameters.put("ds1", dataSource1);
			// org.krysalis.barcode4j.impl.AbstractBarcodeBean t;
			// org.apache.batik.bridge.UserAgent t;
			// org.apache.batik.dom.svg.SVGDocumentFactory t;
			AbstDataSource dataSource2 = new AbstDataSource() {
				private int i = -1;
				private int g = 0;
				private Map<String, Object> line = new HashMap<String, Object>();

				@Override
				public boolean next() throws JRException {
					line.clear();
					i++;
					if (i % 3 == 0) {//// xml文件中定义了3个颜色，所以这边每3个或2个算一个组//如果组定义为4个，颜色不够用时就会有重复，其实定义的所有颜色不够时是循环使用的
						g++;
					}
					if (i < 12) {
						line.put("wdisp", "xx" + (i % 3));// 注意这里，其实绘图时，是以这个来判断是不是同一个颜色和图标的。应为有限几个。在test_zhutu2中设为不同的了，下方的标识就好多了，应该是不符合人的平常想法的
						line.put("wgroup", g);
						line.put("wvalue", Double.valueOf((i + 1) + ""));
						return true;
					}
					return false;
				}

				@Override
				public Object getFieldValue(JRField jrField) throws JRException {
					String fieldName = jrField.getName();
					return line.get(fieldName);
				}

			};

			parameters.put("ds2", dataSource2);

			pdfFile = report.pdfReport("subreport", parameters);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 网厅中的贷款还款明细查询
	 */
	public static void test_dkhkmx() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);
		final List<HkMx> list = new ArrayList<HkMx>();
		HkMx e = new HkMx();
		e.setF005("setF005");
		e.setF002("f002");
		e.setF026("setF026");
		e.setF006("setF006");
		e.setF007("setF007");
		e.setF011("setF011");
		e.setF008("setF008");
		e.setE023("setE023");
		e.setE032("setE032");
		list.add(e);

		AbstDataSource dataSource = new AbstDataSource() {
			private int i = -1;
			private Map<String, String> line = new HashMap<String, String>();

			@Override
			public boolean next() throws JRException {
				line.clear();
				i++;
				if (i < list.size()) {
					HkMx h = list.get(i);
					line.put("f005", h.getF005());
					line.put("f002", h.getF002());
					line.put("f026", h.getF026());
					line.put("f006", h.getF006());
					line.put("f007", h.getF007());
					line.put("f011", h.getF011());
					line.put("f008", h.getF008());
					line.put("e023", h.getE023());
					line.put("e032", h.getE032());
					line.put("e033", h.getE033());
					line.put("e034", h.getE034());
					line.put("f041", h.getF041());
					line.put("f042", h.getF042());
					line.put("f043", h.getF043());
					return true;
				}
				return false;
			}

			@Override
			public Object getFieldValue(JRField jrField) throws JRException {
				String fieldName = jrField.getName();
				return line.get(fieldName);
			}

		};
		File pdfFile = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			pdfFile = report.pdfReport("dkhkmx", parameters, dataSource);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 网厅中的贷款还款明细查询
	 */
	public static void test_dkhkmx2() {

		Report report = new Report();
		report.setReportDir(RP_DIR);
		report.setExportDir(RP_DIR);
		final List<HkMx> list = new ArrayList<HkMx>();
		HkMx e = new HkMx();
		e.setF005("setF005");
		e.setF002("f002");
		e.setF026("setF026");
		e.setF006("setF006");
		e.setF007("setF007");
		e.setF011("setF011");
		e.setF008("setF008");
		e.setE023("setE023");
		e.setE032("setE032");
		list.add(e);

		File pdfFile = null;
		try {
			pdfFile = report.pdfReportList("dkhkmx", list);
			System.out.println("ok.." + pdfFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
