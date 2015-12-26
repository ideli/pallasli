package com.pallasli.opensource.io;

import java.io.IOException;

public class ApacheCommonsExampleMain {
	public static void main(String[] args) {
		try {
			UtilityExample.runExample();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileMonitorExample.runExample();

		FiltersExample.runExample();

		InputExample.runExample();

		OutputExample.runExample();

		ComparatorExample.runExample();
	}
}
