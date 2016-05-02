package com.pallasli.study.springdatajpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class User {
	public static void main(String[] args) {
		long start = Calendar.getInstance().getTimeInMillis();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 200000000; i++) {
			list.add("拉开拉拉风开发是");
		}
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println(end - start);
		System.out.println(list.size());
	}
}
