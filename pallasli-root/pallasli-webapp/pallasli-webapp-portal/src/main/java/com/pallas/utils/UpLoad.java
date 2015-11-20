package com.pallas.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UpLoad {

	public UpLoad() {

	}

	public static String file_sep = System.getProperty("file.separator");

	/**
	 * 将InputStream存储到服务器磁盘指定路径
	 * 
	 * @param is
	 * @param filepath
	 * @return
	 */
	public boolean saveStreamToDisk(InputStream is, String filepath) {
		/*
		 * boolean result = false; FileOutputStream fos = null; try {
		 * ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		 * byte[] buffer = new byte[1024]; int rc = 0; while ((rc =
		 * is.read(buffer, 0, 1024)) > 0) { swapStream.write(buffer, 0, rc); }
		 * byte[] data = swapStream.toString("UTF-8").getBytes();
		 * //.toByteArray(); swapStream.close(); is.close();
		 * 
		 * 
		 * fos = new FileOutputStream(new File(filepath)); fos.write(data);
		 * 
		 * fos.flush(); result = true; } catch (Exception e) {
		 * e.printStackTrace(); } finally { try { fos.close(); } catch
		 * (Exception e1) { } } return result;
		 */
		boolean result = false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
			int bytesRead;
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			while ((bytesRead = is.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.flush();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e1) {
			}
		}
		return result;
	}

	/**
	 * 解析txt文档
	 * 
	 * @param filepath
	 * @param
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public String JxtxtDrhm(String filepath, HttpServletRequest request)
			throws IOException {
		BufferedReader br = null;
		String str = "";

		br = new BufferedReader(new FileReader(filepath));

		String osname = System.getProperties().getProperty("os.name");
		System.out.println("操作系统的名称：" + osname.substring(0, 3));
		// 在Linux下读取文本文件时中文会变成乱码，还未找到解决办法
		// 暂时通过判断操作系统类型来解决

		if (!osname.substring(0, 3).equals("Win")) {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					filepath), "GBK");
			br = new BufferedReader(isr);
		}

		String temp = null;
		while ((temp = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(temp, "\n");
			if (st.hasMoreElements()) {// 返回的就是分隔符数量
				str += st.nextToken().replace("\t", " ,") + " ,'";
				// System.out.println("str:::::::::str:::::::::00000000000000::::::"+str);
			}
		}
		return str;

		/*
		 * try{ InputStream input = new FileInputStream(filepath); byte by[] =
		 * new byte[(int) (new File(filepath)).length()]; input.read(by);
		 * System.out.println("****************************\n字节从文件输出：");
		 * System.out.println(new String(by,"UTF-8") + "\n"); input.close(); str
		 * = new String(by,"UTF-8"); str = str.replace("\n","'"); str =
		 * str.replace("\t"," ,"); System.out.print(str); return new
		 * String(str); } catch (FileNotFoundException e) {
		 * System.err.println("file not found, " + filepath); return ""; } catch
		 * (UnsupportedEncodingException e) {
		 * System.err.println("UnsupportedEncodingException, " + filepath);
		 * return ""; } catch (IOException e) { System.err.println(e); return
		 * ""; }
		 */
	}

	/**
	 * 
	 * 描述:解析excel（2003、2007版本）<br>
	 * 日期:Dec 14, 2012<br>
	 * 姜帅
	 * 
	 * @param
	 * @return
	 * @throws BaseException
	 */
	public String JxexcelDrhm(String filepath) {
		Workbook workbook = null;
		StringBuffer str = new StringBuffer("标题'");
		try {
			String ext = filepath.substring(filepath.lastIndexOf("."),
					filepath.length()).toLowerCase();
			if (ext.equals(".xls")) {
				workbook = new HSSFWorkbook(new FileInputStream(filepath));
			} else {
				workbook = new XSSFWorkbook(new FileInputStream(filepath));
			}
			Sheet sheet = workbook.getSheetAt(0);
			int len = sheet.getLastRowNum();
			if (len > 65535 && ext.equals("xls")) {
				len = 65535;
			}
			System.out.println("**********");
			System.out.println("**********");
			System.out.println("**********");
			System.out.println("**********");
			System.out.println("**********");
			System.out.println("**********");
			System.out.println("**********");
			System.out.println("**********");
			System.out.println("**********");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 1; i <= len; i++) {
				Row row = sheet.getRow(i);
				Cell cell = null;
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					int type = cell.getCellType();
					switch (type) {
					case 0:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date date = HSSFDateUtil.getJavaDate(cell
									.getNumericCellValue());
							str = str.append(df.format(date) + ",");
						} else {
							str = str.append(Double.toString(cell
									.getNumericCellValue()) + ",");
						}
						break;
					case 1:
						str = str.append(cell.getRichStringCellValue()
								.toString() + ",");
						break;
					case 2:
						str = str.append(String.valueOf(new BigDecimal(cell
								.getNumericCellValue()).setScale(2,
								RoundingMode.HALF_UP))
								+ ",");
						break;
					case 3:
						try {
							str = str.append(df.format(cell.getDateCellValue())
									+ ",");
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
				str = str.append("'");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return str.toString();
	}

	// 测试
	public static void main(String[] args) {
		new UpLoad().JxexcelDrhm("c:\\123.xls");
	}
}
