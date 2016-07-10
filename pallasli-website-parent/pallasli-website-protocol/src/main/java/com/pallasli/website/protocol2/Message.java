package com.pallasli.website.protocol2;

import java.nio.ByteBuffer;

/**
 * Message代表一个消息包。每个消息包有一个消息头。其定义如下：
 * 
 * @author lyt1987
 *
 */
public interface Message {
	/**
	 * 设置消息头，在MessageChannel.receive中调用
	 */
	void setHeader(MessageHeader header);

	/**
	 * 返回消息头
	 */
	MessageHeader getHeader();

	/**
	 * 从Buffer中取出消息体
	 */
	void buildBodyFromBuffer(ByteBuffer buffer);

	/**
	 * 把消息体放到Buffer中
	 */
	void appendBodyToBuffer(ByteBuffer buffer);
}
