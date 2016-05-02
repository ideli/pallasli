package com.pallasli.study.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {
	private URI uri;
	private String charset;
	private HttpClient _httpClient;

	public void post(List<NameValuePair> payload) throws Exception {
		HttpPost post = new HttpPost(uri);
		HttpEntity result = null;
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(payload,
					charset);
			post.setEntity(entity);
			// if (LOG.isDebugEnabled()) {
			// LOG.debug("sending:" + payload);
			// }

			HttpResponse response = _httpClient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				result = response.getEntity();
				StringBuilder msg = new StringBuilder();
				msg.append("http response with code "
						+ statusLine.getStatusCode());
				msg.append("\n");
				msg.append("post request: " + post.getURI());
				msg.append("\n");
				msg.append(statusLine.getReasonPhrase());
				if (result != null) {
					msg.append("\n\n");
					msg.append(EntityUtils.toString(result, "UTF-8"));
					msg.append("\n\n");
				}
				// throw new UmcException(msg.toString());
			}
			if (response.getEntity() != null) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf("success") < 0)
						System.out.println(line);
				}
			}
		} finally {
			if (result != null)
				try {
					EntityUtils.consume(result);
				} catch (IOException e) {
				}
			post.abort();
		}
	}
}
