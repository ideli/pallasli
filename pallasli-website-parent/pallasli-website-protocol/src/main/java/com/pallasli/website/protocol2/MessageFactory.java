package com.pallasli.website.protocol2;

/**
 * 这个接口封装了所有真正的用于表达消息的类的创建，在MessageChannel的receive中调用。这里用了抽象工厂模式。其定义如下：
 * 
 * @author lyt1987
 *
 */
public interface MessageFactory {
	/**
	 * 返回消息头的字节数
	 */
	int getMessageHeaderLength();

	/**
	 * 创建一个消息头对象
	 */
	MessageHeader createMessageHeader();

	/**
	 * 创建一个消息对象
	 * 
	 * @param type
	 *            消息类型，从消息头中取得
	 */
	Message createMessage(int type);
}
