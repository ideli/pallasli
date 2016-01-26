package com.lyt.service.handler;

import java.util.Iterator;
import java.util.List;

import javax.xml.soap.SOAPHeader;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.JAXRPCHandler;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.NodeImpl;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.message.Text;

/**
 * <b>function:</b>读取Header信息的Handler
 * 
 * @author hoojo
 * @createDate Dec 19, 2010 11:34:56 AM
 * @file ReadHeaderHandler.java
 * @package com.hoo.service.handler
 * @project AxisWebService
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class ReadHeaderHandler extends JAXRPCHandler {

	private static final long serialVersionUID = -546314372478633248L;

	@SuppressWarnings("unchecked")
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
		try {
			// 头对象
			SOAPHeader header = msgContext.getCurrentMessage().getSOAPHeader();
			Iterator<SOAPHeaderElement> iter = header.getChildElements();
			while (iter.hasNext()) {
				// 子元素
				SOAPHeaderElement el = iter.next();
				// 继续子元素
				List<NodeImpl> list = el.getChildren();
				for (NodeImpl node : list) {
					if (node.hasChildNodes()) {
						MessageElement msg = (MessageElement) node;
						if (msg.hasChildNodes()) {
							List<NodeImpl> msgNode = msg.getChildren();
							for (NodeImpl n : msgNode) {
								if (!n.hasChildNodes()) {
									Text text = (Text) n;
									System.out.println("父节点：" + el.getName()
											+ ", 节点：" + msg.getName() + ", 值："
											+ text.getValue());
								} else {
									MessageElement child = (MessageElement) n;
									if (child.hasChildNodes()) {
										List<NodeImpl> childNode = child
												.getChildren();
										for (NodeImpl n2 : childNode) {
											if (!n2.hasChildNodes()) {
												Text text = (Text) n2;
												System.out.print("父父节点："
														+ el.getName()
														+ ", 父节点："
														+ msg.getName());
												System.out.println(", 节点："
														+ child.getName()
														+ "##"
														+ child.getNodeName()
														+ ", 值："
														+ text.getValue()
														+ "##"
														+ child.getValue());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
