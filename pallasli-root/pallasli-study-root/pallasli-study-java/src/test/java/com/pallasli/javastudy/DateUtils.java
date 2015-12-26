package com.pallasli.javastudy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import org.junit.Test;

public class DateUtils {
	/**
	 * MMMM: 英文月份
	 * 
	 * ss：秒
	 * 
	 * MM:
	 * 
	 * HH:
	 * 
	 * h:24小时制
	 * 
	 */
	@Test
	public void format() {
		Date date = new Date();
		String strDateFormat = "HH:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		System.out.println(sdf.format(date));
	}

	@Test
	public void formatYMD() {
		Formatter fmt = new Formatter();
		Calendar cal = Calendar.getInstance();
		fmt.format("%tB %tb %tm", cal, cal, cal);
		System.out.println(fmt);
	}

	@Test
	public void formatHM() {
		Formatter fmt = new Formatter();
		Calendar cal = Calendar.getInstance();
		fmt.format("%tl:%tM", cal, cal);
		System.out.println(fmt);

	}

}
