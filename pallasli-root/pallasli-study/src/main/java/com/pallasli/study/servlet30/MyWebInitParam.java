package com.pallasli.study.servlet30;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: WebInitParam
 * @Description: 定义Servlet的初始化参数注解
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyWebInitParam {
	// 参数名
	String paramName() default "";

	// 参数的值
	String paramValue() default "";
}