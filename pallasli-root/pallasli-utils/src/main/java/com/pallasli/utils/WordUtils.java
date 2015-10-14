package com.pallasli.utils;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class WordUtils {

	public static void main(String[] args) {
		String destFile = "D:\\11.doc";
		// #####################根据自定义内容导出Word文档#################################################
		StringBuffer fileCon = new StringBuffer();
		fileCon.append("               张大炮            男              317258963215223\n"
				+ "2011     09        2013     07       3\n"
				+ "    二炮研究              成人\n"
				+ "2013000001                             2013     07     08");
		fileCon.append("\n\r\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		new WordUtils().exportDoc(destFile, fileCon.toString());

		// ##################根据Word模板导出单个Word文档###################################################
		// Map<String, String> map = new HashMap<String, String>();
		//
		// map.put("name", "Zues");
		// map.put("sex", "男");
		// map.put("idCard", "200010");
		// map.put("year1", "2000");
		// map.put("month1", "07");
		// map.put("year2", "2008");
		// map.put("month2", "07");
		// map.put("gap", "2");
		// map.put("zhuanye", "计算机科学与技术");
		// map.put("type", "研究生");
		// map.put("bianhao", "2011020301");
		// map.put("nowy", "2011");
		// map.put("nowm", "01");
		// map.put("nowd", "20220301");
		// // 注意biyezheng_moban.doc文档位置,此例中为应用根目录
		// HWPFDocument document = new WordUtils().replaceDoc(
		// "biyezheng_moban.doc", map);
		// ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		// try {
		// document.write(ostream);
		// // 输出word文件
		// OutputStream outs = new FileOutputStream(destFile);
		// outs.write(ostream.toByteArray());
		// outs.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	/**
	 * 
	 * @param destFile
	 * @param fileCon
	 */
	public static void exportDoc(String destFile, String fileCon) {
		try {
			System.out.println(fileCon);
			// doc content
			ByteArrayInputStream bais = new ByteArrayInputStream(
					fileCon.getBytes());
			POIFSFileSystem fs = new POIFSFileSystem();
			DirectoryEntry directory = fs.getRoot();
			directory.createDocument("WordDocument", bais);
			FileOutputStream ostream = new FileOutputStream(destFile);
			fs.writeFilesystem(ostream);
			bais.close();
			ostream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean writeHtmlToWordFile(String path, String htmlStr) {
		boolean w = false;
		try {
			if (!"".equals(path)) {
				// 生成临时文件名称
				String content = "<html><div style=\"text-align: center\"><span style=\"font-size: 28px\"><span style=\"font-family: 黑体\">"
						+ "制度发布通知<br /> <br /> </span></span></div></html>";
				byte b[] = content.getBytes();
				ByteArrayInputStream bais = new ByteArrayInputStream(b);
				POIFSFileSystem poifs = new POIFSFileSystem();
				DirectoryEntry directory = poifs.getRoot();
				DocumentEntry documentEntry = directory.createDocument(
						"WordDocument", bais);
				FileOutputStream ostream = new FileOutputStream(path);
				poifs.writeFilesystem(ostream);
				bais.close();
				ostream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w;
	}
	// /**
	// * 读取word模板并替换变量
	// *
	// * @param srcPath
	// * @param map
	// * @return
	// */
	// public HWPFDocument replaceDoc(String srcPath, Map<String, String> map) {
	// try {
	// // 读取word模板
	// FileInputStream fis = new FileInputStream(new File(srcPath));
	// HWPFDocument doc = new HWPFDocument(fis);
	// // 读取word文本内容
	// Range bodyRange = doc.getRange();
	// // 替换文本内容
	// for (Map.Entry<String, String> entry : map.entrySet()) {
	// bodyRange.replaceText("${" + entry.getKey() + "}",
	// entry.getValue());
	// }
	// return doc;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

}
