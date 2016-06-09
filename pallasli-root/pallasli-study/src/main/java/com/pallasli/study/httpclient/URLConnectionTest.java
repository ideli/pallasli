package com.pallasli.study.httpclient;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionTest {
	public static void main(String[] args) throws Exception {
		// 针对JDK中的URLConnection连接Servlet的问题,网上有虽然有所涉及,但是只是说明了某一个或几个问题，是以FAQ的方式来解决的，而且比较零散，现在对这个类的使用就本人在项目中的使用经验做如下总结：
		// 1:> URL请求的类别:
		// 分为二类,GET与POST请求。二者的区别在于：
		// a:) get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
		// b:) post与get的不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
		// 2:> URLConnection的对象问题:
		// URLConnection的对象,如下代码示例:

		// 下面的index.jsp由<servlet-mapping>映射到
		// 一个Servlet(com.quantanetwork.getClientDataServlet)
		// 该Servlet的注意点下边会提到
		URL url = new URL("http://localhost:8080/TestHttpURLConnectionPro/index.jsp");

		URLConnection rulConnection = url.openConnection();// 此处的urlConnection对象实际上是根据URL的
		// 请求协议(此处是http)生成的URLConnection类
		// 的子类HttpURLConnection,故此处最好将其转化
		// 为HttpURLConnection类型的对象,以便用到
		// HttpURLConnection更多的API.如下:

		HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;

		// 3:> HttpURLConnection对象参数问题
		// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true, 默认情况下是false;
		httpUrlConnection.setDoOutput(true);

		// 设置是否从httpUrlConnection读入，默认情况下是true;
		httpUrlConnection.setDoInput(true);

		// Post 请求不能使用缓存
		httpUrlConnection.setUseCaches(false);

		// 设定传送的内容类型是可序列化的java对象
		// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
		httpUrlConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");

		// 设定请求的方法为"POST"，默认是GET
		httpUrlConnection.setRequestMethod("POST");

		// 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
		httpUrlConnection.connect();

		// 4:> HttpURLConnection连接问题：

		// 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
		// 所以在开发中不调用上述的connect()也可以)。
		OutputStream outStrm = httpUrlConnection.getOutputStream();

		// 5:> HttpURLConnection写数据与发送数据问题：
		// 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。
		ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);

		// 向对象输出流写出数据，这些数据将存到内存缓冲区中
		objOutputStrm.writeObject(new String("我是测试数据"));

		// 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）
		objOutputStrm.flush();

		// 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
		// 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
		objOutputStrm.close();

		// 调用HttpURLConnection连接对象的getInputStream()函数,
		// 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
		InputStream inStrm = httpUrlConnection.getInputStream(); // <===注意，实际发送请求的代码段就在这里

		// 上边的httpConn.getInputStream()方法已调用,本次HTTP请求已结束,下边向对象输出流的输出已无意义，
		// 既使对象输出流没有调用close()方法，下边的操作也不会向对象输出流写入任何数据.
		// 因此，要重新发送数据时需要重新创建连接、重新设参数、重新创建流对象、重新写数据、
		// 重新发送数据(至于是否不用重新这些操作需要再研究)
		objOutputStrm.writeObject(new String(""));
		httpUrlConnection.getInputStream();

		// 总结：a:)
		// HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。
		// 无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去。
		// b:) 在用POST方式发送URL请求时，URL请求参数的设定顺序是重中之重，
		// 对connection对象的一切配置（那一堆set函数）
		// 都必须要在connect()函数执行之前完成。而对outputStream的写操作，又必须要在inputStream的读操作之前。
		// 这些顺序实际上是由http请求的格式决定的。
		// 如果inputStream读操作在outputStream的写操作之前，会抛出例外：
		// java.net.ProtocolException: Cannot write output after reading
		// input.......
		//
		// c:) http请求实际上由两部分组成，
		// 一个是http头，所有关于此次http请求的配置都在http头里面定义，
		// 一个是正文content。
		// connect()函数会根据HttpURLConnection对象的配置值生成http头部信息，因此在调用connect函数之前，
		// 就必须把所有的配置准备好。
		// d:) 在http头后面紧跟着的是http请求的正文，正文的内容是通过outputStream流写入的，
		// 实际上outputStream不是一个网络流，充其量是个字符串流，往里面写入的东西不会立即发送到网络，
		// 而是存在于内存缓冲区中，待outputStream流关闭时，根据输入的内容生成http正文。
		// 至此，http请求的东西已经全部准备就绪。在getInputStream()函数调用的时候，就会把准备好的http请求
		// 正式发送到服务器了，然后返回一个输入流，用于读取服务器对于此次http请求的返回信息。由于http
		// 请求在getInputStream的时候已经发送出去了（包括http头和正文），因此在getInputStream()函数
		// 之后对connection对象进行设置（对http头的信息进行修改）或者写入outputStream（对正文进行修改）
		// 都是没有意义的了，执行这些操作会导致异常的发生。
		//
		// 6:> Servlet端的开发注意点：
		// a:) 对于客户端发送的POST类型的HTTP请求，Servlet必须实现doPost方法，而不能用doGet方法。
		// b:) 用HttpServletRequest的getInputStream()方法取得InputStream的对象，比如：
		// InputStream inStream = httpRequest.getInputStream();
		// 现在调用inStream.available()（该方法用于“返回此输入流下一个方法调用可以不受阻塞地
		// 从此输入流读取（或跳过）的估计字节数”）时，永远都反回0。试图使用此方法的返回值分配缓冲区，
		// 以保存此流所有数据的做法是不正确的。那么，现在的解决办法是
		// Servlet这一端用如下实现：
		InputStream inStream = httpUrlConnection.getInputStream();
		ObjectInputStream objInStream = new ObjectInputStream(inStream);
		Object obj = objInStream.readObject();
		// 做后续的处理
		// 。。。。。。
		// 。。。 。。。
		// 而客户端，无论是否发送实际数据都要写入一个对象（那怕这个对象不用），如：
		objOutputStrm = new ObjectOutputStream(outStrm);
		objOutputStrm.writeObject(new String("")); // 这里发送一个空数据
		// 甚至可以发一个null对象，服务端取到后再做判断处理。
		objOutputStrm.writeObject(null);
		objOutputStrm.flush();
		objOutputStrm.close();

		// 注意:上述在创建对象输出流ObjectOutputStream时,如果将从HttpServletRequest取得的输入流
		// (即:new
		// ObjectOutputStream(outStrm)中的outStrm)包装在BufferedOutputStream流里面,
		// 则必须有objOutputStrm.flush();这一句,以便将流信息刷入缓冲输出流.如下:
		objOutputStrm = new ObjectOutputStream(new BufferedOutputStream(outStrm));
		objOutputStrm.writeObject(null);
		objOutputStrm.flush(); // <======此处必须要有.
		objOutputStrm.close();

		// HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。可以通过以下两个语句来设置相应的超时：
		// System.setProperty("sun.net.client.defaultConnectTimeout", 超时毫秒数字符串);
		// System.setProperty("sun.net.client.defaultReadTimeout", 超时毫秒数字符串);

		// 其中： sun.net.client.defaultConnectTimeout：连接主机的超时时间（单位：毫秒）
		// sun.net.client.defaultReadTimeout：从主机读取数据的超时时间（单位：毫秒）

		// 例如：
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");

		// Java中可以使用HttpURLConnection来请求WEB资源。
		// HttpURLConnection对象不能直接构造，需要通过URL.openConnection()来获得HttpURLConnection对象，示例代码如下：

		String szUrl = "http://www.ee2ee.com/";
		url = new URL(szUrl);
		HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();

		// HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。可以通过以下两个语句来设置相应的超时：
		// System.setProperty("sun.net.client.defaultConnectTimeout", 超时毫秒数字符串);
		// System.setProperty("sun.net.client.defaultReadTimeout", 超时毫秒数字符串);

		// 其中： sun.net.client.defaultConnectTimeout：连接主机的超时时间（单位：毫秒）
		// sun.net.client.defaultReadTimeout：从主机读取数据的超时时间（单位：毫秒）

		// 例如：
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");

		// JDK
		// 1.5以前的版本，只能通过设置这两个系统属性来控制网络超时。在1.5中，还可以使用HttpURLConnection的父类URLConnection的以下两个方法：
		// setConnectTimeout：设置连接主机超时（单位：毫秒）
		// setReadTimeout：设置从主机读取数据超时（单位：毫秒）

		// 例如：

		urlCon = (HttpURLConnection) url.openConnection();
		urlCon.setConnectTimeout(30000);
		urlCon.setReadTimeout(30000);

		// 需要注意的是，笔者在JDK1.4.2环境下，发现在设置了defaultReadTimeout的情况下，如果发生网络超时，HttpURLConnection会自动重新提交一次请求，出现一次请求调用，请求服务器两次的问题（Trouble）。我认为这是JDK1.4.2的一个bug。在JDK1.5.0中，此问题已得到解决，不存在自动重发现象。out",
		// "30000");

	}
}
