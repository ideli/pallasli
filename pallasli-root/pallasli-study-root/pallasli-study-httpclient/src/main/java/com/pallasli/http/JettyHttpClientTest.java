package com.pallasli.http;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class JettyHttpClientTest {
	public static void main(String[] args) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient(
				new PoolingClientConnectionManager());

		URI uri = new URIBuilder().setScheme("http").setHost("localhost")
				.setPort(8080).setPath("/login").build();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("u", "20233"));
		params.add(new BasicNameValuePair("p", "860830"));
		params.add(new BasicNameValuePair("f", "force"));
		params.add(new BasicNameValuePair("msg_signed", ""));
		HttpPost httpGet = new HttpPost(uri);
		httpGet.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

		HttpResponse httpResp = httpclient.execute(httpGet);
		int statusCode = httpResp.getStatusLine().getStatusCode();
		System.out.println(statusCode);

		CookieStore cookieStore = httpclient.getCookieStore();

		uri = new URIBuilder().setScheme("http").setHost("localhost")
				.setPort(8080).setPath("/hello").build();

		httpGet = new HttpPost(uri);
		httpGet.setHeader("Content-type", "application/json; charset=utf-8");
		String json = "{action: \"OaAppDirect\",data: [0, 0, \"\", \"\", {logrq:\"2016-01-05\", nfs: 0}],method: \"oa_log_cx\",tid: 66,type: \"rpc\"}";
		StringEntity se = new StringEntity(json, HTTP.UTF_8);
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));
		httpGet.setEntity(se);
		byte[] b = new byte[1024];
		se.getContent().read(b);
		System.out.println(new String(b));

		System.out.println(cookieStore);
		httpclient.setCookieStore(cookieStore);
		httpResp = httpclient.execute(httpGet);

		statusCode = httpResp.getStatusLine().getStatusCode();
		System.out.println(httpResp.getStatusLine());
		if (statusCode == 200) {

			System.out.println(EntityUtils.toString(httpResp.getEntity()));

		}

		httpGet.releaseConnection();

	}

	static CookieStore cookieStore = null;

	public static void setCookieStore(HttpResponse httpResponse) {
		System.out.println("----setCookieStore");
		cookieStore = new BasicCookieStore();
		// JSESSIONID
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		System.out.println(setCookie);
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
				setCookie.indexOf(";"));
		System.out.println("JSESSIONID:" + JSESSIONID);
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
				JSESSIONID);
		cookie.setVersion(0);
		cookie.setDomain("127.0.0.1");
		cookie.setPath("/CwlProClient");
		// cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
		// cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
		// cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
		// cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
		cookieStore.addCookie(cookie);
	}
}
