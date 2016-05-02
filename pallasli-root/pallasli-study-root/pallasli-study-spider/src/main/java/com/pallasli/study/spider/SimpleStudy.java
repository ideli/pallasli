package com.pallasli.study.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;

public class SimpleStudy {
	public static void main(String[] args) {
		// useURL();
		// useHttpClient();

		try {
			oalog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void oalog() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("u", "20233"));
		formparams.add(new BasicNameValuePair("p", "8"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				Consts.UTF_8);
		HttpPost httppost = new HttpPost(
				"http://oa.atwasoft.net:7001/portal/login.do");
		httppost.setEntity(entity);

		HttpHost target = new HttpHost("oa.atwasoft.net", 7001, "http");
		HttpClientContext context = HttpClientContext.create();

		CloseableHttpResponse response = httpclient.execute(httppost, context);
		System.out.println(response.getStatusLine().getStatusCode());

		// 获取cookie
		CookieStore cookieStore = context.getCookieStore();

		HeaderIterator it2 = response.headerIterator("Set-Cookie");

		while (it2.hasNext()) {
			System.out.println(it2.next());
		}
		HeaderElementIterator it = new BasicHeaderElementIterator(
				response.headerIterator("Set-Cookie"));
		Map<String, String> map = new HashMap<String, String>();
		while (it.hasNext()) {
			HeaderElement elem = it.nextElement();
			System.out.println(elem.getName() + " = " + elem.getValue());
			NameValuePair[] params = elem.getParameters();
			for (int i = 0; i < params.length; i++) {
				System.out.println(" " + params[i]);
				map.put(params[i].getName(), params[i].getValue());
			}
		}
		URI uri = new URIBuilder().setScheme("http").setHost("www.google.com")
				.setPath("/search").setParameter("q", "httpclient")
				.setParameter("btnG", "Google Search").setParameter("aq", "f")
				.setParameter("oq", "").build();
		HttpGet httpget = new HttpGet(uri);
		System.out.println(httpget.getURI());
		// Set the store
		List<NameValuePair> formparams2 = new ArrayList<NameValuePair>();
		// action: "OaAppDirect"
		// data: [0, 0, "", "", {logrq: "2016-04-18", nfs: -1}]
		// method: "oa_log_cx"
		// tid: 99
		// type: "rpc"
		formparams2.add(new BasicNameValuePair("action", "OaAppDirect"));
		formparams2.add(new BasicNameValuePair("data", "[]"));
		formparams2.add(new BasicNameValuePair("method", "oa_log_cx"));
		formparams2.add(new BasicNameValuePair("tid", "99"));
		formparams2.add(new BasicNameValuePair("type", "rpc"));
		UrlEncodedFormEntity entity2 = new UrlEncodedFormEntity(formparams2,
				Consts.UTF_8);
		HttpPost httppost2 = new HttpPost(
				"http://oa.atwasoft.net:7001/OA/direct");
		httppost2.setEntity(entity2);

		HttpHost target2 = new HttpHost("oa.atwasoft.net", 7001, "http");

		CloseableHttpClient httpclient2 = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response2 = httpclient
				.execute(httppost2, context);

		System.out.println(response2);
		if (response2.getEntity() != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response2.getEntity().getContent(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.indexOf("success") < 0)
					System.out.println(line);
			}
		}
		// Remote Address:222.222.216.162:7001
		// Request URL:http://oa.atwasoft.net:7001/OA/direct
		// Request Method:POST
		// Status Code:200 OK
		// Response Headers
		// view source
		// Content-Length:1915
		// Content-Type:text/html;charset=UTF-8
		// Date:Mon, 18 Apr 2016 06:29:51 GMT
		// Server:Apache-Coyote/1.1
		// Request Headers
		// view source
		// Accept:*/*
		// Accept-Encoding:gzip, deflate
		// Accept-Language:zh-CN,zh;q=0.8
		// Connection:keep-alive
		// Content-Length:118
		// Content-Type:application/json
		// Cookie:JSESSIONID=4A2EA4D8EBAB2A699D1F58D6B75734EE.jvm1
		// Host:oa.atwasoft.net:7001
		// Origin:http://oa.atwasoft.net:7001
		// Referer:http://oa.atwasoft.net:7001/portal/home.do
		// User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36
		// (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36
		// X-Requested-With:XMLHttpRequest
		// Request Payload
		// view source
		// {action: "OaAppDirect", method: "oa_log_cx", data: [0, 0, "", "",
		// {logrq: "2016-04-18", nfs: -1}],…}
		// action: "OaAppDirect"
		// data: [0, 0, "", "", {logrq: "2016-04-18", nfs: -1}]
		// method: "oa_log_cx"
		// tid: 99
		// type: "rpc"

		// as an HTML form and save the result to the file
		// login
		// Remote Address:222.222.216.162:7001
		// Request URL:http://oa.atwasoft.net:7001/portal/login.do
		// Request Method:POST
		// Status Code:302 Found
		// Response Headers
		// view source
		// Access-Control-Allow-Credentials:true
		// Access-Control-Allow-Headers:X-Requested-With
		// Access-Control-Allow-Origin:http://oa.atwasoft.net:7001
		// Content-Language:zh-CN
		// Content-Length:0
		// Date:Mon, 18 Apr 2016 05:54:51 GMT
		// Location:http://oa.atwasoft.net:7001/portal/home.do
		// Server:Apache-Coyote/1.1
		// Request Headers
		// view source
		// Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		// Accept-Encoding:gzip, deflate
		// Accept-Language:zh-CN,zh;q=0.8
		// Cache-Control:max-age=0
		// Connection:keep-alive
		// Content-Length:28
		// Content-Type:application/x-www-form-urlencoded
		// Cookie:JSESSIONID=C762316E03500E03B91A76733CC4FA8E.jvm1
		// Host:oa.atwasoft.net:7001
		// Origin:http://oa.atwasoft.net:7001
		// Referer:http://oa.atwasoft.net:7001/portal/login.do
		// User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36
		// (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36
		// Form Data
		// view source
		// view URL encoded
		// u:20233
		// p:860830
		// msg_signed:

		// home
		// Remote Address:222.222.216.162:7001
		// Request URL:http://oa.atwasoft.net:7001/portal/home.do
		// Request Method:GET
		// Status Code:200 OK
		// Response Headers
		// view source
		// Access-Control-Allow-Credentials:true
		// Access-Control-Allow-Headers:X-Requested-With
		// Content-Language:zh-CN
		// Content-Type:text/html;charset=utf-8
		// Date:Mon, 18 Apr 2016 05:54:51 GMT
		// Server:Apache-Coyote/1.1
		// Transfer-Encoding:chunked
		// Request Headers
		// view source
		// Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		// Accept-Encoding:gzip, deflate, sdch
		// Accept-Language:zh-CN,zh;q=0.8
		// Cache-Control:max-age=0
		// Connection:keep-alive
		// Cookie:JSESSIONID=C762316E03500E03B91A76733CC4FA8E.jvm1
		// Host:oa.atwasoft.net:7001
		// Referer:http://oa.atwasoft.net:7001/portal/login.do
		// User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36
		// (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36
		//
	}

	private static void useHttpClient() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://www.baidu.com/");
		// URI uri = new URIBuilder()
		// .setScheme("http")
		// .setHost("www.google.com")
		// .setPath("/search")
		// .setParameter("q", "httpclient")
		// .build();
		// HttpGet httpget = new HttpGet(uri);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			System.out.println(httpget.getURI());
			// HttpResponse response = new
			// BasicHttpResponse(HttpVersion.HTTP_1_1,
			// HttpStatus.SC_OK, "OK");
			response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
			response.addHeader("Set-Cookie",
					"c2=b; path=\"/\", c3=c; domain=\"localhost\"");
			Header h1 = response.getFirstHeader("Set-Cookie");
			System.out.println(h1);
			Header h2 = response.getLastHeader("Set-Cookie");
			System.out.println(h2);
			Header[] hs = response.getHeaders("Set-Cookie");
			System.out.println(hs.length);
			System.out.println(response.getProtocolVersion());
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			System.out.println(response.getStatusLine().toString());

			response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
			response.addHeader("Set-Cookie",
					"c2=b; path=\"/\", c3=c; domain=\"localhost\"");

			HeaderIterator it2 = response.headerIterator("Set-Cookie");

			while (it2.hasNext()) {
				System.out.println(it2.next());
			}
			HeaderElementIterator it = new BasicHeaderElementIterator(
					response.headerIterator("Set-Cookie"));
			while (it.hasNext()) {
				HeaderElement elem = it.nextElement();
				System.out.println(elem.getName() + " = " + elem.getValue());
				NameValuePair[] params = elem.getParameters();
				for (int i = 0; i < params.length; i++) {
					System.out.println(" " + params[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void useURL() {
		URL url;
		try {
			url = new URL("http://www.baidu.com");
			InputStream ins = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(ins));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
