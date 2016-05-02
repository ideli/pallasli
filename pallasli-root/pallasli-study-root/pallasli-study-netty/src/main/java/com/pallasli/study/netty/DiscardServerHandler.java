package com.pallasli.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel. 一个最简单的服务就是什么也不知，抛弃信息
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		// Discard the received data silently.
		((ByteBuf) msg).release(); // (3)
		// 接收信息处理
		// ByteBuf in = (ByteBuf) msg;
		// try {
		// while (in.isReadable()) { // (1)
		// System.out.print((char) in.readByte());
		// System.out.flush();
		// 回显消息
		// ctx.write(msg); // (1)
		// ctx.flush(); // (2)
		// }
		// } finally {
		// ReferenceCountUtil.release(msg); // (2)
		// }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
