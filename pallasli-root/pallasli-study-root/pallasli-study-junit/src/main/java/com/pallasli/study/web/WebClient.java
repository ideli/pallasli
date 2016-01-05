package com.pallasli.study.web;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
	public String getContent(URL url) {
		StringBuffer content = new StringBuffer();
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			InputStream is = conn.getInputStream();
			byte[] b = new byte[2048];

			int count;
			while (-1 != (count = is.read(b))) {
				content.append(new String(b, 0, count));
			}
		} catch (Exception e) {

		}
		return content.toString();
	}
}
