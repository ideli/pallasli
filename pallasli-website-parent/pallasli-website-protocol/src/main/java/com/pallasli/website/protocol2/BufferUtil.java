package com.pallasli.website.protocol2;

/**
 * 这是一个Utility类，主要功能是从ByteBuffer中取得或者放入一个字符串String，不同的协议有不同的字符串处理方法。
 * 目前，这个类没有考虑编码方式，可以对这个类进行扩充。
 * 
 * 在这个框架中，Message.receive是最复杂的部分，下面对该过程的流程进行说明：
 * 
 * (1) 根据MessageFactory.getMessageHeaderLength()返回的消息头长度，接收消息头。
 * 
 * (2) 用MessageFactory.createMessageHeader()构造消息头，
 * 并调用MessageHeader.buildFromBuffer() 取得消息头数据。
 * 
 * (3) 根据消息头中的信息，接收消息体
 * 
 * (4) 构造消息，并调用Message.buildBodyFromBuffer()取得消息体数据。
 * 
 * (5) 调用Message.setHeader()。
 * 
 * (6) 返回构造的消息。 这其中有一个步骤出错，将抛出MessageFormatException。
 * 
 * @author lyt1987
 *
 */
class BufferUtil {
	// /**
	// * 从buffer取得一个字符串，length为长度
	// */
	// static String getString(ByteBuffer buffer, int length);
	//
	// /**
	// * 从buffer取得一个以'/0'结束的字符串，length为最大长度
	// */
	// static String getCString(ByteBuffer buffer, int length);
	//
	// /**
	// * 从buffer取得一个变长的字符串，长度用两字节的short类型表示
	// */
	// static String getVarStringShortLength(ByteBuffer buffer);
	//
	// /**
	// * 从buffer取得一个变长的字符串，长度用四字节的int类型表示
	// */
	// static String getVarStringIntLength(ByteBuffer buffer);
	//
	// /**
	// * 从buffer取得一个变长的字符串，长度用一字节的byte表示
	// */
	// static String getVarStringByteLength();
	//
	// /**
	// * 在buffer中放入一个字符串，length为长度
	// */
	// static void putString(ByteBuffer buffer, String str, int length);
	//
	// /**
	// * 在buffer中放入一个字符串，length为最大长度。如果str没有达到最大长度，那么用0填充。
	// */
	// static void putCString(ByteBuffer buffer, String str, int length);
	//
	// /**
	// * 在buffer中放入一个字符串，长度用一个short表示
	// */
	// static void putVarStringShortLength(ByteBuffer buffer);
	//
	// /**
	// * 在buffer中放入一个字符串，长度用一个int表示
	// */
	// static void putVarStringIntLength(ByteBuffer buffer);
	//
	// /**
	// * 在buffer中放入一个字符串，长度用一个byte表示
	// */
	// static void putVarStringByteLength(ByteBuffer buffer);
}
