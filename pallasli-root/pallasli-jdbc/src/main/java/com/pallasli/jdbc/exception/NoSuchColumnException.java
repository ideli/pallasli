package com.pallasli.jdbc.exception;

public class NoSuchColumnException extends Exception {

	private static final long serialVersionUID = 5872892303075461607L;

	public NoSuchColumnException(String message) {
		super(message);
	}
}
