package jstl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
//使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库。
//tld 防止WEB-INF目录下  如果打包则防止MATA-INFO

/**
 * JSP引擎遇到自定义标签时，首先创建标签处理器类的实例对象，然后按照JSP规范定义的通信规则依次调用它的方法。
 * 
 * 1、public void setPageContext(PageContext pc)，
 * JSP引擎实例化标签处理器后，将调用setPageContext方法将JSP页面的pageContext对象传递给标签处理器
 * ，标签处理器以后可以通过这个pageContext对象与JSP页面进行通信。
 * 
 * 2、public void setParent(Tag
 * t)，setPageContext方法执行完后，WEB容器接着调用的setParent方法将当前标签的父标签传递给当前标签处理器
 * ，如果当前标签没有父标签，则传递给setParent方法的参数值为null。
 * 
 * 3、public int
 * doStartTag()，调用了setPageContext方法和setParent方法之后，WEB容器执行到自定义标签的开始标记时
 * ，就会调用标签处理器的doStartTag方法。
 * 
 * 4、public int doEndTag()，WEB容器执行完自定义标签的标签体后，就会接着去执行自定义标签的结束标记，此时，
 * WEB容器会去调用标签处理器的doEndTag方法。
 * 
 * 5、public void release()，通常WEB容器执行完自定义标签后，标签处理器会驻留在内存中，为其它请求服务器，直至停止web应用时，
 * web容器才会调用release方法。
 * 
 * @author lyt1987
 *
 */
public class ViewIPTag implements Tag {

	// 接收传递进来的PageContext对象
	private PageContext pageContext;

	@Override
	public int doEndTag() throws JspException {
		System.out.println("调用doEndTag()方法");
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
		System.out.println("调用doStartTag()方法");
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		JspWriter out = pageContext.getOut();
		String ip = request.getRemoteAddr();
		try {
			// 这里输出的时候会抛出IOException异常
			out.write(ip);
		} catch (IOException e) {
			// 捕获IOException异常后继续抛出
			throw new RuntimeException(e);
		}
		return 0;
	}

	@Override
	public Tag getParent() {
		return null;
	}

	@Override
	public void release() {
		System.out.println("调用release()方法");
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		System.out.println("setPageContext(PageContext pageContext)");
		this.pageContext = pageContext;
	}

	@Override
	public void setParent(Tag arg0) {

	}

}
