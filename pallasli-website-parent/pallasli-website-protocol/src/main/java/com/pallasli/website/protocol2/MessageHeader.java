package com.pallasli.website.protocol2;

import java.nio.ByteBuffer;

/**
 * 在这样一套网络协议中，总是有一个固定长度的消息头，不同的协议有不同的消息头，但是几乎所有的消息头都定义了本消息的长度和本消息的类型。
 * 类型用于识别不同的消息包。类型相同的包，格式都是一样的，可以用同一个Java的class来表达。类型不同的包，格式可能相同也可能不同，依赖于协议。
 * 接口定义如下：
 * 
 * @author lyt1987
 *
 */
public interface MessageHeader {
	/**
	 * 返回消息类型
	 */
	int getMessageType();

	/**
	 * 返回消息长度
	 */
	int getMessageLength();

	/**
	 * 从Buffer中提取消息头
	 */
	void buildFromBuffer(ByteBuffer buffer);

	/**
	 * 把消息头放到Buffer中
	 */
	void appendToBuffer(ByteBuffer buffer);

}
