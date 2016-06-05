package com.pallasli.study.dwr;

public class WebMessage {
	/**
	 * @param newtext
	 *            the new message text
	 */
	public WebMessage(String newtext) {
		text = newtext;
		if (text.length() > 256) {
			text = text.substring(0, 256) + ". . .";
		}
	}

	/**
	 * @return the message id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the message itself
	 */
	public String getText() {
		return text;
	}

	/**
	 * When the message was created
	 */
	private long id = System.currentTimeMillis();
	/**
	 * The text of the message
	 */
	private String text;
}
