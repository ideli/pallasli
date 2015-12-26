package com.pallas.knowledge.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DownLoadAction extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/x-download");// 设置为下载application/x-download
		String filedownload = "/download/test.zip";// 即将下载的文件的相对路径
		String filedisplay = request.getParameter("filename");// 下载文件时显示的文件保存名称
		try {
			String filenamedisplay = URLEncoder.encode(filedisplay, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filedisplay + filenamedisplay);

			RequestDispatcher dis = request.getRequestDispatcher(filedownload);
			if (dis != null) {
				dis.forward(request, response);
			}
			response.flushBuffer();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public HttpServletResponse download(String path,
			HttpServletResponse response) {

		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			// String ext = filename.substring(filename.lastIndexOf(".") + 1)
			// .toUpperCase();

			// // 以流的形式下载文件。
			// InputStream fis = new BufferedInputStream(new
			// FileInputStream(path));
			// byte[] buffer = new byte[fis.available()];
			// fis.read(buffer);
			// fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = response.getOutputStream();
			// response.setContentType("application/octet-stream");
			response.setContentType("application/x-download");
			// toClient.write(buffer);
			FileInputStream is = new FileInputStream(path);
			byte[] b1 = new byte[4096];
			while (true) {
				int reader = is.read(b1);
				if (reader < 0) {
					break;
				}
				toClient.write(b1, 0, reader);
			}
			is.close();
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public void downloadLocal(HttpServletResponse response)
			throws FileNotFoundException {
		// 下载本地文件
		String fileName = "Operator.doc".toString(); // 文件的默认保存名
		// 读到流中
		InputStream inStream = new FileInputStream("c:/Operator.doc");// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void downloadNet(HttpServletResponse response)
			throws MalformedURLException {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		URL url = new URL("windine.blogdriver.com/logo.gif");

		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream("c:/abc.gif");

			byte[] buffer = new byte[1204];
			// int length;
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void downLoadWithOpen(String filePath, HttpServletResponse response,
			boolean isOnLine) throws Exception {
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;

		response.reset(); // 非常重要
		if (isOnLine) { // 在线打开方式
			URL u = new URL("file:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition",
					"inline; filename=" + f.getName());
			// 文件名应该编码成UTF-8
		} else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ f.getName());
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}
}
