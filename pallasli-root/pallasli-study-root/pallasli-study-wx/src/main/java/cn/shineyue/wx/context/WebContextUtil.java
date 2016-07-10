package cn.shineyue.wx.context;

import org.springframework.web.context.WebApplicationContext;

/**
 * @Description webContext 工具
 * @author zhangchanglong
 * @created zhangchanglong 2015年8月6日
 * @version 1
 */
public class WebContextUtil {
	private static WebApplicationContext wac;

	private WebContextUtil() {
	}

	public static void setWac(WebApplicationContext wac) {
		WebContextUtil.wac = wac;
	}

	public static WebApplicationContext getWac() {
		return wac;
	}

	public static Object getBean(String name) {
		return wac.getBean(name);
	}
}
