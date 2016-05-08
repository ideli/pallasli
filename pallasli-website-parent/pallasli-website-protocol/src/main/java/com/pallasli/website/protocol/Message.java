package com.pallasli.website.protocol;

public class Message {

	private String context;

	private byte[] message = new byte[1024];

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public byte[] getBytes() {
		byte[] temp = string2bytes(context, 1024);
		System.arraycopy(temp, 0, message, 0, 1024);

		return message;
	}

	public static byte[] string2bytes(String s, int length) {
		while (s.getBytes().length < length)
			s = "0" + s;
		return s.getBytes();
	}
}
