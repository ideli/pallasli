package com.pallasli.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.SSLException;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpClientTest {

	// public CloseableHttpClient createHttpsClient() {
	//
	// X509TrustManager x509mgr = new X509TrustManager() {
	//
	// @Override
	// public void checkClientTrusted(X509Certificate[] xcs, String string) {
	//
	// }
	//
	// @Override
	// public void checkServerTrusted(X509Certificate[] xcs, String string) {
	//
	// }
	//
	// @Override
	// public X509Certificate[] getAcceptedIssuers() {
	//
	// return null;
	//
	// }
	//
	// };
	//
	// SSLContext sslContext = null;
	//
	// try {
	//
	// sslContext = SSLContext.getInstance("TLS");
	//
	// } catch (NoSuchAlgorithmException e1) {
	//
	// // TODO Auto-generated catch block
	//
	// e1.printStackTrace();
	//
	// }
	//
	// try {
	//
	// sslContext.init(null, new TrustManager[] { x509mgr }, null);
	//
	// } catch (KeyManagementException e) {
	//
	// // TODO Auto-generated catch block
	//
	// e.printStackTrace();
	//
	// }
	//
	// SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	//
	// sslContext,
	//
	// SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	//
	// return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	//
	// }

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) throws Exception {

		// CloseableHttpClient httpclient = HttpClients.createDefault();

		// HttpClientTest t = new HttpClientTest();

		CloseableHttpClient httpclient = HttpClients.createDefault(); // t.createHttpsClient();

		// HttpGet httpGet = new
		// HttpGet("http://www.google.com.hk/search?hl=zh&ie=utf-8&num=30&output=rss&q=java+%7C+hibernate+%7C+spring&tbm=blg");

		HttpGet httpGet = new HttpGet(
				"http://localhost:8080/designerApp/home.html");
		System.out.println(httpGet.getURI());
		URI uri = new URIBuilder().setScheme("http").setHost("localhost")
				.setPort(8080).setPath("/designerApp/home.html")
				.setParameter("q", "httpclient")
				.setParameter("btnG", "Google Search").setParameter("aq", "f")
				.setParameter("oq", "").build();
		httpGet = new HttpGet(uri);
		System.out.println(httpGet.getURI());
		// HttpGet httpGet = new HttpGet("http://itindex.net/");

		try {

			HttpResponse httpResp = httpclient.execute(httpGet);
			HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
					HttpStatus.SC_OK, "OK");
			response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
			response.addHeader("Set-Cookie",
					"c2=b; path=\"/\", c3=c; domain=\"localhost\"");
			Header h1 = response.getFirstHeader("Set-Cookie");
			System.out.println(h1);
			Header h2 = response.getLastHeader("Set-Cookie");
			System.out.println(h2);
			Header[] hs = response.getHeaders("Set-Cookie");
			HeaderIterator it = response.headerIterator("Set-Cookie");
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			HeaderElementIterator it2 = new BasicHeaderElementIterator(
					response.headerIterator("Set-Cookie"));

			while (it2.hasNext()) {
				HeaderElement elem = it2.nextElement();
				System.out.println(elem.getName() + " = " + elem.getValue());
				NameValuePair[] params = elem.getParameters();
				for (int i = 0; i < params.length; i++) {
					System.out.println(" " + params[i]);
				}
			}
			System.out.println(hs.length);
			System.out.println(response.getProtocolVersion());
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			System.out.println(response.getStatusLine().toString());
			int statusCode = httpResp.getStatusLine().getStatusCode();

			if (statusCode == 200) {

				System.out.println(EntityUtils.toString(httpResp.getEntity()));

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			httpGet.releaseConnection();

		}

	}

	// 和服务器连接
	public String getContent(String url) throws Exception {
		StringBuilder sb = new StringBuilder();
		// HttpClient client = new DefaultHttpClient();
		CloseableHttpClient client = HttpClients.createDefault(); // new
																	// HttpClientTest().createHttpsClient();
		// HttpParams httpParams = client.getParams();
		//
		// HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		// HttpConnectionParams.setSoTimeout(httpParams, 5000);
		HttpResponse response = client.execute(new HttpGet(url));
		HttpEntity entity = response.getEntity();
		// 缓存到内存对象
		// if (entity != null) {
		// entity = new BufferedHttpEntity(entity);
		// }

		if (entity != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"), 8192);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
		}
		// 关闭Http实体内容流和关闭Http响应的区别在于，前者通过消耗掉Http实体内容来保持相关的http连接，然后后者会立即关闭、丢弃http连接。
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		// HttpGet httpget = new HttpGet("http://localhost/");
		// CloseableHttpResponse response = httpclient.execute(httpget);
		// try {
		// HttpEntity entity = response.getEntity();
		// if (entity != null) {
		// InputStream instream = entity.getContent();
		// try {
		// // do something useful
		// } finally {
		// instream.close();
		// }
		// }
		// } finally {
		// response.close();
		// }
		return sb.toString();
	}

	public String getContent(HttpResponse ht) {

		try {
			if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = ht.getEntity();
				InputStream is = he.getContent();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String response = "";
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					// response = br.readLine();
					response = response + readLine;
				}
				is.close();
				br.close();

				// String str = EntityUtils.toString(he);
				System.out.println("=========" + response);
				return response;
			} else {
				return "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}

	public void doPost(String urlAddress, String username, String password) {
		try {
			// String getUrl = urlAddress +
			// "?username="+username+"&password="+password;
			HttpPost httpPost = new HttpPost(urlAddress);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			NameValuePair pair1 = new BasicNameValuePair("username", username);
			NameValuePair pair2 = new BasicNameValuePair("password", password);
			params.add(pair1);
			params.add(pair2);

			HttpEntity he;
			he = new UrlEncodedFormEntity(params, "gbk");
			httpPost.setEntity(he);

			// 创建HTTP实体内容，HttpClient提供了一些类，这些类可以通过http连接高效地输出Http实体内容。HttpClient提供的这几个类涵盖的常见的数据类型，如String，byte数组，输入流，和文件类型：StringEntity,ByteArrayEntity,InputStreamEntity,FileEntity。
			// File file = new File("somefile.txt");
			// FileEntity entity = new FileEntity(file,
			// ContentType.create("text/plain", "UTF-8"));
			//
			// HttpPost httppost = new HttpPost("http://localhost/action.do");
			// httppost.setEntity(entity);

			CloseableHttpClient hc = HttpClients.createDefault(); // new
																	// HttpClientTest().createHttpsClient();
			// HttpClient hc = new DefaultHttpClient();
			HttpResponse ht = hc.execute(httpPost);
			System.out.println(ht.getStatusLine());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void requestWithHandler() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://localhost/json");

		ResponseHandler<MyJsonObject> rh = new ResponseHandler<MyJsonObject>() {

			@Override
			public MyJsonObject handleResponse(final HttpResponse response)
					throws IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(),
							statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException(
							"Response contains no content");
				}
				Gson gson = new GsonBuilder().create();
				ContentType contentType = ContentType.getOrDefault(entity);
				Charset charset = contentType.getCharset();
				Reader reader = new InputStreamReader(entity.getContent(),
						charset);
				return gson.fromJson(reader, MyJsonObject.class);
			}
		};
		try {
			MyJsonObject myjson = httpclient.execute(httpget, rh);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void other() throws ClientProtocolException, IOException {

		/**
		 * 对于Http请求执行过程来说，HttpClient的接口有着必不可少的作用。
		 * HttpClient接口没有对Http请求的过程做特别的限制和详细的规定
		 * ，连接管理、状态管理、授权信息和重定向处理这些功能都单独实现。这样用户就可以更简单地拓展接口的功能（比如缓存响应内容）。
		 * 
		 * 一般说来，HttpClient实际上就是一系列特殊的handler或者说策略接口的实现，这些handler（测试接口）
		 * 负责着处理Http协议的某一方面，比如重定向、认证处理、有关连接持久性和keep
		 * alive持续时间的决策。这样就允许用户使用自定义的参数来代替默认配置，实现个性化的功能。
		 */
		ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {

			@Override
			public long getKeepAliveDuration(HttpResponse response,
					HttpContext context) {
				long keepAlive = super.getKeepAliveDuration(response, context);
				if (keepAlive == -1) {
					// Keep connections alive 5 seconds if a keep-alive value
					// has not be explicitly set by the server
					keepAlive = 5000;
				}
				return keepAlive;
			}

		};
		CloseableHttpClient httpclient = HttpClients.custom()
				.setKeepAliveStrategy(keepAliveStrat).build();

		/**
		 * HttpContext可以包含任意类型的对象，因此如果在多线程中共享上下文会不安全。推荐每个线程都只包含自己的http上下文。
		 * 
		 * 在Http请求执行的过程中，HttpClient会自动添加下面的属性到Http上下文中：
		 * 
		 * HttpConnection的实例，表示客户端与服务器之间的连接 HttpHost的实例，表示要连接的目标服务器
		 * HttpRoute的实例，表示全部的连接路由
		 * HttpRequest的实例，表示Http请求。在执行上下文中，最终的HttpRequest对象会代表http消息的状态
		 * 。Http/1.0和Http/1.1都默认使用相对的uri。但是如果使用了非隧道模式的代理服务器，就会使用绝对路径的uri。
		 * HttpResponse的实例，表示Http响应 java.lang.Boolean对象，表示是否请求被成功的发送给目标服务器
		 * RequestConfig对象，表示http request的配置信息
		 * java.util.List<Uri>对象，表示Http响应中的所有重定向地址
		 * 我们可以使用HttpClientContext这个适配器来简化和上下文交互的过程。
		 */

		HttpContext context = new BasicHttpContext();
		HttpClientContext clientContext = HttpClientContext.adapt(context);
		HttpHost target = clientContext.getTargetHost();
		HttpRequest request = clientContext.getRequest();
		HttpResponse response = clientContext.getResponse();
		RequestConfig config = clientContext.getRequestConfig();

		/**
		 * 同一个逻辑会话中的多个Http请求，应该使用相同的Http上下文来执行，这样就可以自动地在http请求中传递会话上下文和状态信息。
		 * 在下面的例子中，我们在开头设置的参数，会被保存在上下文中，并且会应用到后续的http请求中。
		 */
		httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(1000).setConnectTimeout(1000).build();

		HttpGet httpget1 = new HttpGet("http://localhost/1");
		httpget1.setConfig(requestConfig);
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpget1, context);
			HttpEntity entity1 = response1.getEntity();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		HttpGet httpget2 = new HttpGet("http://localhost/2");
		CloseableHttpResponse response2 = null;
		try {
			response2 = httpclient.execute(httpget2, context);
			HttpEntity entity2 = response2.getEntity();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 如果要自定义异常处理机制，我们需要实现HttpRequestRetryHandler接口。
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= 5) {
					// Do not retry if over max retry count
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// Timeout
					return false;
				}
				if (exception instanceof UnknownHostException) {
					// Unknown host
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					// Connection refused
					return false;
				}
				if (exception instanceof SSLException) {
					// SSL handshake exception
					return false;
				}
				HttpClientContext clientContext = HttpClientContext
						.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// Retry if the request is considered idempotent
					return true;
				}
				return false;
			}
		};
		httpclient = HttpClients.custom().setRetryHandler(myRetryHandler)
				.build();

		// Http协议拦截器
		httpclient = HttpClients.custom()
				.addInterceptorLast(new HttpRequestInterceptor() {

					public void process(final HttpRequest request,
							final HttpContext context) throws HttpException,
							IOException {
						AtomicInteger count = (AtomicInteger) context
								.getAttribute("count");
						request.addHeader("Count",
								Integer.toString(count.getAndIncrement()));
					}

				}).build();

		AtomicInteger count = new AtomicInteger(1);
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAttribute("count", count);

		HttpGet httpget = new HttpGet("http://localhost/");
		for (int i = 0; i < 10; i++) {
			CloseableHttpResponse response3 = null;
			try {
				response3 = httpclient.execute(httpget, localContext);
				HttpEntity entity = response3.getEntity();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					response3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 重定向处理
		// HttpClient会自动处理所有类型的重定向，除了那些Http规范明确禁止的重定向。See Other (status code
		// 303) redirects on POST and PUT requests are converted to GET requests
		// as required by the HTTP specification.
		// 我们可以使用自定义的重定向策略来放松Http规范对Post方法重定向的限制。
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		httpclient = HttpClients.custom().setRedirectStrategy(redirectStrategy)
				.build();
		// HttpClient在请求执行过程中，经常需要重写请求的消息。
		// HTTP/1.0和HTTP/1.1都默认使用相对的uri路径。同样，原始的请求可能会被一次或者多次的重定向。最终结对路径的解释可以使用最初的请求和上下文。URIUtils类的resolve方法可以用于将拦截的绝对路径构建成最终的请求。这个方法包含了最后一个分片标识符或者原始请求。
		httpclient = HttpClients.createDefault();
		HttpClientContext context2 = HttpClientContext.create();
		httpget = new HttpGet("http://localhost:8080/");
		CloseableHttpResponse response3 = httpclient.execute(httpget, context);
		try {
			HttpHost target2 = context2.getTargetHost();
			List<URI> redirectLocations = context2.getRedirectLocations();
			URI location = URIUtils.resolve(httpget.getURI(), target,
					redirectLocations);
			System.out.println("Final HTTP location: "
					+ location.toASCIIString());
			// Expected to be an absolute URI
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			response3.close();
		}

	}

	public void manager() throws Exception {
		// HTTP连接管理器
		// 如果要终止连接，可以调用ConnectionRequest的cancel()方法。这个方法会解锁被ConnectionRequest类get()方法阻塞的线程。
		/**
		 * BasicHttpClientConnectionManager是个简单的连接管理器，它一次只能管理一个连接。尽管这个类是线程安全的，
		 * 它在同一时间也只能被一个线程使用
		 * 。BasicHttpClientConnectionManager会尽量重用旧的连接来发送后续的请求，并且使用相同的路由
		 * 。如果后续请求的路由和旧连接中的路由不匹配
		 * ，BasicHttpClientConnectionManager就会关闭当前连接，使用请求中的路由重新建立连接
		 * 。如果当前的连接正在被占用，会抛出java.lang.IllegalStateException异常。
		 */
		HttpClientContext context = HttpClientContext.create();
		HttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();
		HttpRoute route = new HttpRoute(new HttpHost("localhost", 80));
		// 获取新的连接. 这里可能耗费很多时间
		ConnectionRequest connRequest = connMrg.requestConnection(route, null);
		// 10秒超时
		HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
		try {
			// 如果创建连接失败
			if (!conn.isOpen()) {
				// establish connection based on its route info
				connMrg.connect(conn, route, 1000, context);
				// and mark it as route complete
				connMrg.routeComplete(conn, route, context);
			}
			// 进行自己的操作.
		} finally {
			connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
		}
		/**
		 * 相对BasicHttpClientConnectionManager来说，
		 * PoolingHttpClientConnectionManager是个更复杂的类
		 * ，它管理着连接池，可以同时为很多线程提供http连接请求。Connections are pooled on a per route
		 * basis.当请求一个新的连接时，如果连接池有有可用的持久连接，连接管理器就会使用其中的一个，而不是再创建一个新的连接。
		 * 
		 * PoolingHttpClientConnectionManager维护的连接数在每个路由基础和总数上都有限制。默认，
		 * 每个路由基础上的连接不超过2个，总连接数不能超过20。在实际应用中，这个限制可能会太小了，尤其是当服务器也使用Http协议时。
		 * 
		 * 下面的例子演示了如果调整连接池的参数：
		 */
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);

		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm).build();

		/**
		 * 当使用了请求连接池管理器（比如PoolingClientConnectionManager）后，
		 * HttpClient就可以同时执行多个线程的请求了。
		 * 
		 * PoolingClientConnectionManager会根据它的配置来分配请求连接。如果连接池中的所有连接都被占用了，
		 * 那么后续的请求就会被阻塞 ，直到有连接被释放回连接池中。为了防止永远阻塞的情况发生，我们可以把http.conn-manager.
		 * timeout的值设置成一个整数
		 * 。如果在超时时间内，没有可用连接，就会抛出ConnectionPoolTimeoutException异常。
		 **/
		cm = new PoolingHttpClientConnectionManager();
		httpClient = HttpClients.custom().setConnectionManager(cm).build();

		// URIs to perform GETs on
		String[] urisToGet = { "http://www.domain1.com/",
				"http://www.domain2.com/", "http://www.domain3.com/",
				"http://www.domain4.com/" };

		// create a thread for each URI
		GetThread[] threads = new GetThread[urisToGet.length];
		for (int i = 0; i < threads.length; i++) {
			HttpGet httpget = new HttpGet(urisToGet[i]);
			threads[i] = new GetThread(httpClient, httpget);
		}

		// start the threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].start();
		}

		// join the threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].join();
		}
	}

	public void keeplive() {
		/**
		 * 连接存活策略 Http规范没有规定一个持久连接应该保持存活多久。有些Http服务器使用非标准的Keep-Alive头消息和客户端进行交互，
		 * 服务器端会保持数秒时间内保持连接。HttpClient也会利用这个头消息。如果服务器返回的响应中没有包含Keep-Alive头消息，
		 * HttpClient会认为这个连接可以永远保持
		 * 。然而，很多服务器都会在不通知客户端的情况下，关闭一定时间内不活动的连接，来节省服务器资源。在某些情况下默认的策略显得太乐观
		 * ，我们可能需要自定义连接存活策略。
		 **/
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

			public long getKeepAliveDuration(HttpResponse response,
					HttpContext context) {
				// Honor 'keep-alive' header
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						try {
							return Long.parseLong(value) * 1000;
						} catch (NumberFormatException ignore) {
						}
					}
				}
				HttpHost target = (HttpHost) context
						.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
				if ("www.naughty-server.com".equalsIgnoreCase(target
						.getHostName())) {
					// Keep alive for 5 seconds only
					return 5 * 1000;
				} else {
					// otherwise keep alive for 30 seconds
					return 30 * 1000;
				}
			}

		};
		CloseableHttpClient client = HttpClients.custom()
				.setKeepAliveStrategy(myStrategy).build();
	}
}

/**
 * 连接回收策略 经典阻塞I/O模型的一个主要缺点就是只有当组侧I/O时，socket才能对I/O事件做出反应。当连接被管理器收回后，这个连接仍然存活
 * ，但是却无法监控socket的状态
 * ，也无法对I/O事件做出反馈。如果连接被服务器端关闭了，客户端监测不到连接的状态变化（也就无法根据连接状态的变化，关闭本地的socket）。
 * 
 * HttpClient为了缓解这一问题造成的影响，会在使用某个连接前，监测这个连接是否已经过时，如果服务器端关闭了连接，那么连接就会失效。
 * 这种过时检查并不是100%有效，并且会给每个请求增加10到30毫秒额外开销。唯一一个可行的，且does not involve a one thread
 * per socket model for idle connections的解决办法，是建立一个监控线程，来专门回收由于长时间不活动而被判定为失效的连接
 * 。这个监控线程可以周期性的调用ClientConnectionManager类的closeExpiredConnections
 * ()方法来关闭过期的连接，回收连接池中被关闭的连接。
 * 它也可以选择性的调用ClientConnectionManager类的closeIdleConnections ()方法来关闭一段时间内不活动的连接。
 **/
class IdleConnectionMonitorThread extends Thread {

	private final HttpClientConnectionManager connMgr;
	private volatile boolean shutdown;

	public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
		super();
		this.connMgr = connMgr;
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(5000);
					// Close expired connections
					connMgr.closeExpiredConnections();
					// Optionally, close connections
					// that have been idle longer than 30 sec
					connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
				}
			}
		} catch (InterruptedException ex) {
			// terminate
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}

}

class GetThread extends Thread {

	private final CloseableHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpget;

	public GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
		this.httpget = httpget;
	}

	@Override
	public void run() {
		try {
			CloseableHttpResponse response = httpClient.execute(httpget,
					context);
			try {
				HttpEntity entity = response.getEntity();
			} finally {
				response.close();
			}
		} catch (ClientProtocolException ex) {
			// Handle protocol errors
		} catch (IOException ex) {
			// Handle I/O errors
		}
	}

}