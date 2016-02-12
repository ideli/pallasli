package com.pallasli.study.annotation.use;

import com.pallasli.study.annotation.ClassAnnotation;
import com.pallasli.study.annotation.MethodAnnotation;

@ClassAnnotation(description = "这是一个类")
public class AnnotationUse {
	@MethodAnnotation(description = "这是一个方法", methodType = "")
	public String work() {
		return "work over!";
	}
}
