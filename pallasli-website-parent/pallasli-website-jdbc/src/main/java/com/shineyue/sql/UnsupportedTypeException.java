package com.shineyue.sql;

/**
 * 试图创建由结果集不支持的列类型行实例时抛出该异常
 */
public class UnsupportedTypeException extends Exception {

	private static final long serialVersionUID = -5489858231399784525L;

	public UnsupportedTypeException(String message) {
        super(message);
    }
}
