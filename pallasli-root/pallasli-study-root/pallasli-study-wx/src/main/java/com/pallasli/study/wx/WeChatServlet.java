package com.pallasli.study.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.rhcloud.insongr.srv.wechat.bean.WeChatReqBean;
//import com.rhcloud.insongr.srv.wechat.bean.WeChatRespBean;
//import com.rhcloud.insongr.srv.wechat.common.Constants;
//import com.rhcloud.insongr.srv.yahoo.geocode.ResultSet;
//import com.rhcloud.insongr.srv.yahoo.weather.YahooWeather;
//import com.rhcloud.insongr.srv.yahoo.weather.yweather.Forecast;
//import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

//@WebServlet(urlPatterns = "/WeChatServlet")
public class WeChatServlet extends HttpServlet {
	private static final long serialVersionUID = -2776902810130266533L;
	private static Logger log = Logger.getLogger(WeChatServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		// 此处需要检验signature对网址接入合法性进行校验。
		// 没看出来有什么用，没弄。
		// 详见：<a
		// href="http://mp.weixin.qq.com/cgi-bin/readtemplate?t=wxm-callbackapi-doc&lang=zh_CN">文档</a>
		log.info(signature + " : " + timestamp + " : " + nonce + " : " + echostr);
		PrintWriter out = resp.getWriter();
		out.write("hello");
		out.write(echostr);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Scanner scanner = new Scanner(req.getInputStream());
		resp.setContentType("application/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer(100);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			out.write("hello");

			// // 2、解析用户的信息
			// JAXBContext jc = JAXBContext.newInstance(WeChatReqBean.class);
			// Unmarshaller u = jc.createUnmarshaller();
			// WeChatReqBean reqBean = (WeChatReqBean) u.unmarshal(new
			// StringReader(sb.toString()));
			//
			// // 3、判定用户是否发的是地理位置的PO，并查询天气
			// String content = getContent(reqBean);
			//
			// // 4、创建一个回复消息
			// jc = JAXBContext.newInstance(WeChatRespBean.class);
			// Marshaller m = jc.createMarshaller();
			// m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// m.setProperty(CharacterEscapeHandler.class.getName(), new
			// CharacterEscapeHandler() {
			// @Override
			// public void escape(char[] arg0, int arg1, int arg2, boolean arg3,
			// Writer arg4) throws IOException {
			// arg4.write(arg0, arg1, arg2);
			// }
			// });
			// m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			// WeChatRespBean respBean = createRespBean(reqBean, content);
			// m.marshal(respBean, out);
			out.flush();
			// } catch (JAXBException e) {
			// log.info(e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}

	// /**
	// * @param reqBean
	// * @param content
	// * @return
	// */
	// private WeChatRespBean createRespBean(WeChatReqBean reqBean, String
	// content) {
	// WeChatRespBean respBean = new WeChatRespBean();
	// respBean.setFromUserName(reqBean.getToUserName());
	// respBean.setToUserName(reqBean.getFromUserName());
	// respBean.setMsgType("text");
	// respBean.setCreateTime(new Date().getTime());
	// respBean.setContent(content);
	// return respBean;
	// }
	//
	// /**
	// * @param reqBean
	// * @throws JAXBException
	// * @throws MalformedURLException
	// */
	// private String getContent(WeChatReqBean reqBean) throws JAXBException,
	// MalformedURLException {
	// StringBuffer content = new StringBuffer("亲，");
	// switch (reqBean.getMsgType()) {
	// case Constants.MSGTYPE_TEXT:
	// if ("Hello2BizUser".equals(reqBean.getContent())) {
	// // 这是新用户关注时默认发的一条信息。可以做一个欢迎处理。
	// content.append("这是一个测试哈。想查天气，给我发一个地理位置的PO。[愉快]");
	// } else {
	// content.append("我没功夫和你聊天，我很忙的。想查天气，给我发一个地理位置的PO。[右哼哼]");
	// }
	// break;
	// case Constants.MSGTYPE_LOCATION:
	// // 此处先调用Yahoo的PlaceFinder服务，获取用户当前所在地的woeid。
	// // 再调用Yahoo的Weather服务获取天气情况。
	// // 如果用兴趣，可以尝试做一个小黄鸡的服务。<a
	// // href="http://developer.simsimi.com/api">小黄鸡</a>
	// String placeUrl = "http://where.yahooapis.com/geocode?location=" +
	// reqBean.getLocation_X() + "+"
	// + reqBean.getLocation_Y() + "&gflags=R";
	// JAXBContext jc = JAXBContext.newInstance(ResultSet.class);
	// Unmarshaller u = jc.createUnmarshaller();
	// URL url = new URL(placeUrl);
	// ResultSet resultSet = (ResultSet) u.unmarshal(url);
	// content.append(resultSet.getResult().getCity() + "：");
	//
	// String weatherUrl =
	// String.format("http://weather.yahooapis.com/forecastrss?w=%s&u=c",
	// resultSet.getResult().getWoeid());
	// url = new URL(weatherUrl);
	// jc = JAXBContext.newInstance(YahooWeather.class);
	// u = jc.createUnmarshaller();
	// YahooWeather weather = (YahooWeather) u.unmarshal(url);
	// List<Forecast> list = weather.getChannel().getItem().getForecasts();
	// content.append("今天最低温度" + list.get(0).getLow() + "℃，最高温度" +
	// list.get(0).getHigh() + "℃；");
	// content.append("明天最低温度" + list.get(1).getLow() + "℃，最高温度" +
	// list.get(1).getHigh() + "℃。");
	// if (list.get(0).getHigh() <= 15 || list.get(1).getHigh() <= 15) {
	// content.append("天凉，注意保暖哦。");
	// } else if (list.get(0).getHigh() <= 25 || list.get(1).getHigh() <= 25) {
	// if (list.get(0).getCode() >= 26 && list.get(0).getCode() <= 32) {
	// content.append("晴空万里，出去走走吧。");
	// }
	// } else {
	// content.append("出去看看大街上的黑丝吧。");
	// }
	// break;
	// case Constants.MSGTYPE_IMAGE:
	// content.append("好漂亮的图片。[色]");
	// break;
	// default:
	// content.append("想查天气，请给我发一个地理位置的PO。");
	// break;
	// }
	// return content.toString();
	// }
}