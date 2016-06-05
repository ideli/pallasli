package com.pallasli.study.dwr;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

/**
 * 通知消息调用java对象
 */
public class ChatWithSamePage {
	/**
	 * @param text
	 *            The new message text to add
	 */
	public void sendWebMessage(String text) {
		WebContext wctx = WebContextFactory.get();
		String remoteAdd = wctx.getHttpServletRequest().getRemoteAddr();
		text = "来自" + remoteAdd + "的朋友说：" + text;
		// Make sure we have a list of the list 10 messages
		if (text != null && text.trim().length() > 0) {
			messages.addFirst(new WebMessage(text));
			while (messages.size() > 10) {
				messages.removeLast();
			}
		}
		// 将队列中的消息通知到所有客户端
		notifyAllClient(wctx, messages);
	}

	public void notifyAllClient(WebContext wctx, List msgQueue) {
		String currentPage = wctx.getCurrentPage();
		// Clear the input box in the browser that kicked off this page only
		Util utilThis = new Util(wctx.getScriptSession());
		utilThis.setValue("text", "");
		// For all the browsers on the current page:
		Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage);
		Util utilAll = new Util(sessions);
		// Clear the list and add in the new set of messages
		utilAll.removeAllOptions("chatlog");
		utilAll.addOptions("chatlog", messages, "text");
	}

	/**
	 * The current set of messages
	 */
	private LinkedList messages = new LinkedList();
}
