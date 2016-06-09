package com.shineyue.sql;

/**
 * 在调用返回类型不支持的数据类型的子类实例上的get方法抛出该异常
 */
public class UnsupportedConversionException extends Exception {

	private static final long serialVersionUID = -2685395764025188618L;

	public UnsupportedConversionException(String message) {
        super(message);
    }
}
