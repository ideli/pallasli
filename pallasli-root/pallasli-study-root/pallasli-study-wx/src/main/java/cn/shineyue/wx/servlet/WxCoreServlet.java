package cn.shineyue.wx.servlet;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.shineyue.wx.business.BusinessMessage;
import cn.shineyue.wx.handler.WxCoreHandlerFactory;
import cn.shineyue.wx.context.WebContextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *  核心Servlet
 */
public class WxCoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected WxMpService wxService;
//	protected WxCoreInMemoryConfigStorage wxCoreInMemoryConfigStorage;
	protected WxMpConfigStorage wxConfig;
	protected WxMpMessageRouter wxMessageRouter;
	protected final Logger log = LoggerFactory.getLogger(WxCoreServlet.class);

	@Override
	public void init() throws ServletException {
		
		super.init();
		
		log.debug("WxCoreServlet init Start");
		
		//在servlet init中用这种方式获取spring的工厂。servlet启动在dispatcher后
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		WebContextUtil.setWac(wac);
		
		//原有模式是 从xml里读入微信客户端配置，现在用spring持久化
//		InputStream is1 = WxCoreServlet.class.getClassLoader().getResourceAsStream("weixin/weixin-config.xml");
//		log.debug("WxCoreServlet init is1:" + is1);
//      WxCoreInMemoryConfigStorage config = WxCoreInMemoryConfigStorage
//        		.fromXml(is1);
//
//      wxCoreInMemoryConfigStorage = config;
//      wxMpService = new WxMpServiceImpl();
//      wxMpService.setWxMpConfigStorage(config);
		
		//wxConfig Spring注入了，见 applicationContext.xml
		wxConfig = (WxMpConfigStorage)WebContextUtil.getBean("wxConfig");
		//wxService Spring注入了，见 applicationContext.xml
		wxService = (WxMpService)WebContextUtil.getBean("wxService");
        //先删除菜单 再创建 --zhangchanglong
        try {
        	wxService.menuDelete();
        	wxService.menuCreate(BusinessMessage
        			.getMenu());
        } catch (WxErrorException e) {
        	e.printStackTrace();
        }

        wxMessageRouter = new WxMpMessageRouter(wxService);
        wxMessageRouter
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)	//订阅事件
		.event(WxConsts.EVT_SUBSCRIBE)
		.handler(
				WxCoreHandlerFactory.getInstance()
						.getWelcomeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//1
		.content("1")
		.handler(
				WxCoreHandlerFactory.getInstance().getFwlcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//2
		.content("2")
		.handler(
				WxCoreHandlerFactory.getInstance().getZcxzHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//3
		.content("3")
		.handler(
				WxCoreHandlerFactory.getInstance().getGzYeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//4
		.content("4")
		.handler(
				WxCoreHandlerFactory.getInstance().getCalcDkjsHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//11
		.content("11")
		.handler(
				WxCoreHandlerFactory.getInstance().getJcdjLcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//12
		.content("12")
		.handler(
				WxCoreHandlerFactory.getInstance().getDwhjLcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//13
		.content("13")
		.handler(
				WxCoreHandlerFactory.getInstance().getZhzyLcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//14
		.content("14")
		.handler(
				WxCoreHandlerFactory.getInstance().getJstzLcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//15
		.content("15")
		.handler(
				WxCoreHandlerFactory.getInstance().getTqywLcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//16
		.content("16")
		.handler(
				WxCoreHandlerFactory.getInstance().getDkywLcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//21
		.content("21")
		.handler(
				WxCoreHandlerFactory.getInstance().getHjywZcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//22
		.content("22")
		.handler(
				WxCoreHandlerFactory.getInstance().getTqywZcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//23
		.content("23")
		.handler(
				WxCoreHandlerFactory.getInstance().getDkywZcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//24
		.content("24")
		.handler(
				WxCoreHandlerFactory.getInstance().getHkywZcHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//11 menu
		.eventKey("11")
		.handler(
				WxCoreHandlerFactory.getInstance().getSearchInfoHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//12 menu
		.eventKey("12")
		.handler(
				WxCoreHandlerFactory.getInstance().getGzYeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//13 menu
		.eventKey("13")
		.handler(
				WxCoreHandlerFactory.getInstance().getGzMxHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//14 menu
		.eventKey("14")
		.handler(
				WxCoreHandlerFactory.getInstance().getGdMxHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//15 menu
		.eventKey("15")
		.handler(
				WxCoreHandlerFactory.getInstance().getGdJhHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//21 menu
		.eventKey("21")
		.handler(
				WxCoreHandlerFactory.getInstance().getRegisterHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//22 menu
		.eventKey("22")
		.handler(
				WxCoreHandlerFactory.getInstance().getHkzzMenuHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//23 menu
		.eventKey("23")
		.handler(
				WxCoreHandlerFactory.getInstance().getTqsqHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_CLICK)		//24 menu
		.eventKey("24")
		.handler(
				WxCoreHandlerFactory.getInstance().getDksqHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_EVENT)
		.event(WxConsts.EVT_UNSUBSCRIBE)		//unsubscribe
		.handler(
				WxCoreHandlerFactory.getInstance().getUnSubscribeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//0
		.content("0")
		.handler(
				WxCoreHandlerFactory.getInstance().getWelcomeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//？
		.content("?")
		.handler(
				WxCoreHandlerFactory.getInstance().getWelcomeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//h
		.content("h")
		.handler(
				WxCoreHandlerFactory.getInstance().getWelcomeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//help
		.content("help")
		.handler(
				WxCoreHandlerFactory.getInstance().getWelcomeHandler())
		.end()
		.rule()
		.async(false)
		.msgType(WxConsts.XML_MSG_TEXT)		//帮助
		.content("帮助")
		.handler(
				WxCoreHandlerFactory.getInstance().getWelcomeHandler())
		.end()
		.rule()
		.async(false)	//unknown
		.handler(WxCoreHandlerFactory.getInstance().getUnKnownHandler())
		.end()					
		;
        log.debug("WxCoreServlet init End");
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
	    response.setStatus(HttpServletResponse.SC_OK);

	    String signature = request.getParameter("signature");
	    String nonce = request.getParameter("nonce");
	    String timestamp = request.getParameter("timestamp");

	    if (!wxService.checkSignature(timestamp, nonce, signature)) {
	      // 消息签名不正确，说明不是公众平台发过来的消息
	      response.getWriter().println("非法请求");
	      return;
	    }

	    String echostr = request.getParameter("echostr");
	    if (StringUtils.isNotBlank(echostr)) {
	      // 说明是一个仅仅用来验证的请求，回显echostr
	      response.getWriter().println(echostr);
	      return;
	    }

	    String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
	        "raw" :
	        request.getParameter("encrypt_type");

	    if ("raw".equals(encryptType)) {
	      // 明文传输的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
	      WxMpXmlOutMessage outMessage = wxMessageRouter.route(inMessage);
	      if (outMessage != null) {
	        response.getWriter().write(outMessage.toXml());
	      }
	      return;
	    }

	    if ("aes".equals(encryptType)) {
	      // 是aes加密的消息
	      String msgSignature = request.getParameter("msg_signature");
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxConfig, timestamp, nonce, msgSignature);
	      WxMpXmlOutMessage outMessage = wxMessageRouter.route(inMessage);
	      response.getWriter().write(outMessage.toEncryptedXml(wxConfig));
	      return;
	    }

	    response.getWriter().println("不可识别的加密类型");
	    return;

	}

}

