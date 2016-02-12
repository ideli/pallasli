package com.pallasli.study.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class AnalysisAnnotation {
	/**
	 * 在运行时分析处理annotation类型的信息
	 * 
	 * 
	 */
	@Test
	public void testRuntimeAnnotation()

	{
		try {

			// 通过运行时反射API获得annotation信息
			Class rt_class = Class.forName("com.pallasli.study.annotation.use.AnnotationUse");
			Method[] methods = rt_class.getMethods();
			boolean flag = rt_class.isAnnotationPresent(ClassAnnotation.class);

			if (flag) {
				ClassAnnotation description = (ClassAnnotation) rt_class.getAnnotation(ClassAnnotation.class);
				System.out.println(" Description--->" + description.description());
				for (Method method : methods) {
					if (method.isAnnotationPresent(MethodAnnotation.class)) {
						MethodAnnotation methodAnno = method.getAnnotation(MethodAnnotation.class);
						System.out.println(" description--->" + methodAnno.description() + " methodType "
								+ methodAnno.methodType());
						System.out.println(method.getName());
						method.invoke(rt_class.newInstance());
					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
