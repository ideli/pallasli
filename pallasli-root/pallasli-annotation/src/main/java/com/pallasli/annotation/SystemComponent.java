package com.pallasli.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemComponent {
	/**
	 * 类注解
	 * 
	 * system:(默认)系统组件 user:用户组件
	 * 
	 * @return
	 */
	String value() default SystemComponentType.SYSTEM;
}
