package com.pallali.study.guava.cache;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class StudentRemovalListener implements RemovalListener<String, Student> {

	@Override
	public void onRemoval(RemovalNotification<String, Student> arg0) {

	}

}