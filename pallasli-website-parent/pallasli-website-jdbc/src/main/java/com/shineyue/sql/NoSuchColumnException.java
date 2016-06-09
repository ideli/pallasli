package com.shineyue.sql;

/**
 * 试图从Row上使用无效的索引和名字抛出该异常
 */
public class NoSuchColumnException extends Exception {

	private static final long serialVersionUID = 5872892303075461607L;

	public NoSuchColumnException(String message) {
        super(message);
    }
}
