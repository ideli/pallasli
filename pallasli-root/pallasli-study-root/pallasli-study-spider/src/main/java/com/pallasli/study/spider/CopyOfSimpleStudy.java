package com.pallasli.study.spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HeaderIterator;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class CopyOfSimpleStudy {
	public static void main(String[] args) {
		try {
			oalog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void oalog() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("u", "20233"));
		formparams.add(new BasicNameValuePair("p", "860830"));
		formparams.add(new BasicNameValuePair("f", "force"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				Consts.UTF_8);
		HttpPost httppost = new HttpPost(
				"http://oa.atwasoft.net:7001/portal/login.do");
		httppost.setEntity(entity);
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response = httpclient.execute(httppost, context);
		// 获取cookie
		CookieStore cookieStore = context.getCookieStore();
		System.out.println("登录后cookie");
		System.out.println(cookieStore);

		HttpGet httpget = new HttpGet(
				"http://oa.atwasoft.net:7001/OA/namespace.do?1460965947022&uname=20233");
		CloseableHttpClient httpclient3 = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response3 = httpclient3.execute(httpget, context);
		System.out.println("namespace后cookie");
		System.out.println(cookieStore);
		// System.out.println(response3);
		// if (response3.getEntity() != null) {
		// BufferedReader reader = new BufferedReader(new InputStreamReader(
		// response3.getEntity().getContent(), "UTF-8"));
		// String line = null;
		// while ((line = reader.readLine()) != null) {
		// System.out.println(line);
		// if (line.indexOf("success") < 0)
		// System.out.println(line);
		// }
		// }
		// Set the store
		HttpPost httppost2 = new HttpPost(
				"http://oa.atwasoft.net:7001/OA/direct");
		String formStatusData = "[{\"action\": \"OaAppDirect\",\"method\": \"oa_log_cx\",\"tid\": 40,\"type\": \"rpc\",\"data\": ["
				+ "0, 0, \"\", \"\", {\"logrq\": \"2016-04-15\", \"nfs\": -1}]}]";
		StringEntity params = new StringEntity(formStatusData, "UTF-8");
		System.out.println(formStatusData);
		// params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
		// "application/json"));
		// params.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING,
		// HTTP.UTF_8));
		params.setContentType("application/json;charset=UTF-8");
		System.out.println(params);
		httppost2.setHeader("accept", "application/json");
		httppost2.setHeader("Content-Type", "application/json;charset=UTF-8");
		httppost2.addHeader("content-type", "application/json");
		httppost2.addHeader("Referer",
				"http://oa.atwasoft.net:7001/portal/home.do");
		httppost2.addHeader("Origin", "//oa.atwasoft.net:7001");
		httppost2.addHeader("X-Requested-With", "XMLHttpRequest");
		httppost2
				.addHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		httppost2.setEntity(params);

		System.out.println("direct使用cookie");
		System.out.println(cookieStore);
		CloseableHttpClient httpclient2 = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response2 = httpclient2.execute(httppost2);
		HeaderIterator it2 = response2.headerIterator("Set-Cookie");
		response2.setHeader("content-type", "applicaton/json");
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}
		// System.out.println(response2);
		if (response2.getEntity() != null) {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response2.getEntity().getContent(), "UTF-8"));
			System.out.println(response2.getEntity().getContentType());
			System.out.println(response2.getEntity().getContentLength());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					response2.getEntity().getContent()));

			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			// while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			// if (line.indexOf("success") < 0)
			// System.out.println(line);
			// }
		}

		System.out.println("direct后cookie");
		System.out.println(cookieStore);
		// Server:Apache-Coyote/1.1
		// Request Headers
		// view source
		// Accept:*/*
		// Accept-Encoding:gzip, deflate
		// Accept-Language:zh-CN,zh;q=0.8
		// Connection:keep-alive
		// User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36
		// (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36
		// X-Requested-With:XMLHttpRequest

	}
}
