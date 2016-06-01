package com.pallasli.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class DownloadClient {
	Logger logger = LoggerFactory.getLogger(DownloadClient.class);

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/downOdex.do")
	public ResponseEntity<String> downFile(@RequestParam(value = "odexName") String odexName,
			HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream out = null;
		try {
			File file = new File("\\" + odexName);
			int fSize = Integer.parseInt(String.valueOf(file.length()));
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/x-download");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-Length", String.valueOf(fSize));
			response.setHeader("Content-Disposition", "attachment;fileName=" + odexName);
			inputStream = new FileInputStream("\\" + odexName);
			long pos = 0;
			if (null != request.getHeader("Range")) {
				// 断点续传
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				try {
					pos = Long.parseLong(request.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
				} catch (NumberFormatException e) {
					pos = 0;
				}
			}
			out = response.getOutputStream();
			String contentRange = new StringBuffer("bytes ").append(pos + "").append("-").append((fSize - 1) + "")
					.append("/").append(fSize + "").toString();
			response.setHeader("Content-Range", contentRange);
			inputStream.skip(pos);
			byte[] buffer = new byte[1024 * 10];
			int length = 0;
			while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, length);
				// <span style="font-family:
				// Consolas;">Thread.sleep(</span><code class="java
				// value">100</code><code class="java plain">);</code>
			}
		} catch (Exception e) {
			logger.error("ODEX软件下载异常：" + e);
		} finally {
			try {
				if (null != out)
					out.flush();
				if (null != out)
					out.close();
				if (null != inputStream)
					inputStream.close();
			} catch (IOException e) {
			}
		}
		return new ResponseEntity(null, HttpStatus.OK);
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		String url = "http://dl_dir.qq.com/music/clntupate/QQMusic_Setup_85_850.exe";
		String downFile = "d:\\QQMusic.exe";
		Long netFileLenght = getNetFileSize(url);
		Long localFileLenght = getLocalFileSize(downFile);

		if (localFileLenght >= netFileLenght) {
			System.out.println("已下载完成");
			return;
		}

		System.out.println("netFileLenght : " + netFileLenght + " localFileLenght : " + localFileLenght);
		final HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setIntParameter("http.socket.timeout", 5000);

		final HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Range", "bytes=" + localFileLenght + "-");

		final HttpResponse response = httpClient.execute(httpGet);
		final int code = response.getStatusLine().getStatusCode();
		final HttpEntity entity = response.getEntity();
		System.out.println(code);

		if (entity != null && code < 400) {

			File file = new File(downFile);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			randomAccessFile.seek(localFileLenght);

			InputStream inputStream = entity.getContent();
			int b = 0;
			final byte buffer[] = new byte[1024];
			while ((b = inputStream.read(buffer)) != -1) {
				randomAccessFile.write(buffer, 0, b);
			}

			randomAccessFile.close();
			inputStream.close();
			httpClient.getConnectionManager().shutdown();
			System.out.println("下载完成");
		}
	}

	public static Long getLocalFileSize(String fileName) {
		File file = new File(fileName);
		return file.length();
	}

	public static Long getNetFileSize(String url) {
		Long count = -1L;
		final HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setIntParameter("http.socket.timeout", 5000);

		final HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			final int code = response.getStatusLine().getStatusCode();
			final HttpEntity entity = response.getEntity();
			if (entity != null && code == 200) {
				count = entity.getContentLength();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return count;
	}
}
