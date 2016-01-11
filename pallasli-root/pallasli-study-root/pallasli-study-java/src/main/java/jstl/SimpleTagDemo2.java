package jstl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author gacl SimpleTagSupport类实现了SimpleTag接口，
 *         SampleTagDemo2类继承SimpleTagSupport
 */
public class SimpleTagDemo2 extends SimpleTagSupport {

	/*
	 * 简单标签使用这个方法就可以完成所有的业务逻辑
	 * 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag() 重写doTag方法，控制标签执行5次
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// 得到代表jsp标签体的JspFragment
		JspFragment jspFragment = this.getJspBody();
		for (int i = 0; i < 5; i++) {
			// 将标签体的内容输出到浏览器
			jspFragment.invoke(null);
		}
	}

	/*
	 * 简单标签使用这个方法就可以完成所有的业务逻辑
	 * 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 * 重写doTag方法，修改标签体里面的内容，将标签体的内容转换成大写
	 */
	public void doTag2() throws JspException, IOException {
		// 得到代表jsp标签体的JspFragment
		JspFragment jspFragment = this.getJspBody();
		StringWriter sw = new StringWriter();
		// 将标签体的内容写入到sw流中
		jspFragment.invoke(sw);
		// 获取sw流缓冲区的内容
		String content = sw.getBuffer().toString();
		content = content.toUpperCase();
		PageContext pageContext = (PageContext) this.getJspContext();
		// 将修改后的content输出到浏览器中
		pageContext.getOut().write(content);
	}

	/*
	 * 简单标签使用这个方法就可以完成所有的业务逻辑
	 * 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 * 重写doTag方法，控制标签余下的Jsp不执行
	 */
	public void doTag3() throws JspException, IOException {
		// 抛出一个SkipPageException异常就可以控制标签余下的Jsp不执行
		throw new SkipPageException();
	}

	/**
	 * 存储集合
	 */
	private List items;

	/**
	 * 迭代集合时使用的变量
	 */
	private String var;

	public void foreach() throws JspException, IOException {
		PageContext pageContext = (PageContext) this.getJspContext();
		Iterator it = items.iterator();
		while (it.hasNext()) {
			// 得到一个迭代出来的对象
			Object object = it.next();
			// 将迭代出来的对象存放到pageContext对象中
			pageContext.setAttribute(var, object);
			// 输出标签体中的内容
			this.getJspBody().invoke(null);
		}
	}

	private boolean test;

	public void iftag() throws JspException, IOException {
		if (test) {
			this.getJspBody().invoke(null);
		}
	}

	private boolean isExecute;

	public void choose() throws JspException, IOException {
		// 输出标签体中的内容
		this.getJspBody().invoke(null);
	}
	// 定义父标签
	// <c:choose>
	// <c:when test="${user==null}">
	// when标签标签体输出的内容：
	// <h3>用户为空</h3>
	// </c:when>
	// <c:otherwise>
	// 用户不为空
	// </c:otherwise>
	// </c:choose>
	// public void when() throws JspException, IOException {
	// //获取标签的父标签
	// ChooseTag parentTag = (ChooseTag) this.getParent();
	// if (test == true && parentTag.isExecute() == false) {
	// //输出标签体中的内容
	// this.getJspBody().invoke(null);
	// //将父标签的isExecute属性设置为true，告诉父标签，我(when标签)已经执行过了
	// parentTag.setExecute(true);
	// }
	// }
	// public void other() throws JspException, IOException {
	// // 获取标签的父标签
	// ChooseTag parentTag = (ChooseTag) this.getParent();
	// // 如果父标签下的when标签没有执行过
	// if (parentTag.isExecute() == false) {
	// // 输出标签体中的内容
	// this.getJspBody().invoke(null);
	// // 设置父标签的isExecute属性为true，告诉父标签，我(otherwise标签)已经执行过了
	// parentTag.setExecute(true);
	// }
	// }
}