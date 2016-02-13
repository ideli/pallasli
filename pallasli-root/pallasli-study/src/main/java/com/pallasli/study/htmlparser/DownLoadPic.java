package com.pallasli.study.htmlparser;

import java.io.File;
import java.io.IOException;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
/**
 * 使用jsoup进行简单的爬虫操作爬取图片
 * 
 * @author lyt1987
 *
 */
public class DownLoadPic {

	public void getDoc() throws IOException {

		File f = new File("E://imgs");
		if (!f.exists()) {

			f.mkdirs();

		}
		// // 以网易为例子
		//
		// Document doc = Jsoup.connect("http://www.163.com/").get();
		// // 获取后缀为png和jpg的图片的元素集合
		// Elements pngs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
		// // 遍历元素
		// for (Element e : pngs) {
		// String src = e.attr("src");// 获取img中的src路径
		// // 获取后缀名
		// String imageName = src.substring(src.lastIndexOf("/") + 1,
		// src.length());
		// // 连接url
		// URL url = new URL(src);
		// URLConnection uri = url.openConnection();
		// // 获取数据流
		// InputStream is = uri.getInputStream();
		// // 写入数据流
		// OutputStream os = new FileOutputStream(new File("E://imgs",
		// imageName));
		//
		// byte[] buf = new byte[1024];
		//
		// int l = 0;
		//
		// while ((l = is.read(buf)) != -1) {
		// os.write(buf, 0, l);
		// }
		// }
	}

	public static void main(String[] args) throws IOException {

		new DownLoadPic().getDoc(); // 调用方法

	}
}