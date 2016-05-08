/**
 * 河北省神玥软件科技有限公司 版权所有
 * @file 文件:LoginUser.java
 * @date 创建时间:2015-12-4
 * @author 创建人:魏广强
 * @Description TODO
 */
package com.pallasli.website.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * 
 * 
 */
public class Report {

	private static final Logger log = LoggerFactory.getLogger(Report.class);

	private String reportDir;
	private String exportDir;

	/**
	 * 用list来做数据源产生新的PDF报表文件。无任何的parameter
	 * 
	 * @param reportName
	 * @param data
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	public File pdfReportList(String reportName, List<? extends Object> data) throws JRException, IOException {
		AbstDataSource dataSource = new WebappDataSource(data);

		return this.pdfReport(reportName, null, dataSource);
	}

	/**
	 * 用list来做数据源产生新的PDF报表文件。
	 * 
	 * @param reportName
	 * @param parameters
	 * @param data
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	public File pdfReportList(String reportName, Map<String, Object> parameters, List<? extends Object> data)
			throws JRException, IOException {
		AbstDataSource dataSource = new WebappDataSource(data);
		return this.pdfReport(reportName, parameters, dataSource);
	}

	/**
	 * 只有参数无数据的报表的输出。注意：jrxml中必须要有whenNoDataType="AllSectionsNoDetail"否则不输出任何内容
	 * 
	 * @param reportName
	 * @param parameters
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	public File pdfReport(String reportName, Map<String, Object> parameters) throws JRException, IOException {
		return this.pdfReport(reportName, parameters, null);
	}

	/**
	 * 产生新的PDF报表文件。
	 * 
	 * @param reportName
	 *            报表名，只有主文件名，不带扩展名
	 * @param parameters
	 *            参数集
	 * @param dataSource
	 *            数据集
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	public File pdfReport(String reportName, Map<String, Object> parameters, AbstDataSource dataSource)
			throws JRException, IOException {
		if (parameters == null)
			parameters = new HashMap<String, Object>(1);
		File pdfFile = null;
		parameters.put("reportDir", reportDir);
		// 判断是不是已编译过 ：jasper文件是否已存在且时间比jrxml文件晚
		File jrxmlFile = new File(this.reportDir + reportName + ".jrxml");
		log.debug("jrxmlFile:{}", jrxmlFile.getAbsolutePath());
		if (jrxmlFile.exists()) {
			File jasperFile = new File(this.reportDir + reportName + ".jasper");
			if (jasperFile.exists() && jasperFile.lastModified() > jrxmlFile.lastModified()) {

			} else {
				this.compile(jrxmlFile.getAbsolutePath());
			}

			// fillData
			if (jasperFile.exists()) {
				JasperPrint jp = this.fillData(jasperFile.getAbsolutePath(), parameters, dataSource);
				if (jp != null) {
					// export
					List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>(1);
					jasperPrintList.add(jp);
					pdfFile = this.export(jasperPrintList);
				} else {
					log.error("JasperPrint不存在，fillData出错了！jrxmlFile:{}", jrxmlFile.getAbsolutePath());
				}
			} else {
				log.error("jasperFile不存在，compile出错了！jrxmlFile:{}", jrxmlFile.getAbsolutePath());
			}
		} else {
			log.error("jrxmlFile不存在！jrxmlFile:{}", jrxmlFile.getAbsolutePath());
		}
		return pdfFile;

	}

	/**
	 * 由行bean的List生成DataSource的对象
	 * 
	 * @param list
	 * @return
	 */
	public static AbstDataSource list2DataSource(List<? extends Object> list) {
		AbstDataSource dataSource = new WebappDataSource(list);
		return dataSource;
	}

	/**
	 * compile jrxml文件
	 * 
	 * @param reportFilePath
	 * @throws JRException
	 */
	private void compile(String reportFilePath) throws JRException {
		log.debug("compile start. reportFilePath:{}", reportFilePath);
		JasperCompileManager.compileReportToFile(reportFilePath);
	}

	private JasperPrint fillData(String jasperFileName, Map<String, Object> parameters, AbstDataSource dataSource)
			throws JRException {
		log.debug("fillData. jasperFileName:{}", jasperFileName);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFileName, parameters, dataSource);
		return jasperPrint;
	}

	private File export(List<JasperPrint> jasperPrintList) throws IOException, JRException {
		log.debug("export pdf start");
		File destFile = null;
		FileBufferedOutputStream fbos = new FileBufferedOutputStream();
		JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fbos));
		try {
			exporter.exportReport();
			fbos.close();
			if (fbos.size() > 0) {
				destFile = getDestFile(jasperPrintList);
				OutputStream outputStream = new FileOutputStream(destFile);
				try {
					fbos.writeData(outputStream);
					fbos.dispose();
					outputStream.flush();
				} finally {
					if (outputStream != null) {
						try {
							outputStream.close();
						} catch (IOException ex) {
							throw ex;
						}
					}
				}
			}
		} catch (JRException e) {
			throw e;
		} finally {
			fbos.close();
			fbos.dispose();
		}
		log.debug("export pdf end");
		return destFile;
	}

	private File getDestFile(List<JasperPrint> jasperPrintList) throws IOException {
		String pdfFileName = String.valueOf(System.currentTimeMillis());
		if (jasperPrintList != null && !jasperPrintList.isEmpty()) {
			pdfFileName = jasperPrintList.get(0).getName() + pdfFileName;
		}
		File destFile = new File(exportDir + pdfFileName + ".pdf");
		while (destFile.exists()) {
			pdfFileName = pdfFileName + "_0";
			destFile = new File(exportDir + pdfFileName + ".pdf");
		}
		destFile.createNewFile();

		return destFile;
	}

	public String getReportDir() {
		return reportDir;
	}

	public void setReportDir(String reportDir) {
		this.reportDir = reportDir;
	}

	public String getExportDir() {
		return exportDir;
	}

	public void setExportDir(String exportDir) {
		this.exportDir = exportDir;
	}
}
