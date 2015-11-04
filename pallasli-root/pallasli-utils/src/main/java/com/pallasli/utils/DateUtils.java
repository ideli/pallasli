package com.pallasli.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import org.joda.time.DateTime;
//import org.joda.time.Days;
//import org.joda.time.Hours;
//import org.joda.time.Minutes;
//import org.joda.time.Seconds;

public class DateUtils {

	private DateUtils() {
	
	}

	/**
	 * 将字符串根据格式转换为日期,如convertStringToDate("yyyy-MM-dd", "2012-02-03")
	 * 
	 * @param format
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String format, String strDate)
			throws ParseException {
		Date date;
		SimpleDateFormat df = new SimpleDateFormat(format);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return date;
	}

	// public static String diffTime(Date start, Date end) {
	//
	// DateTime dt1 = new DateTime(start.getTime());
	// DateTime dt2 = new DateTime(end.getTime());
	// int days = Days.daysBetween(dt1, dt2).getDays();
	// int hourse = Hours.hoursBetween(dt1, dt2).getHours() % 24;
	// int minutes = Minutes.minutesBetween(dt1, dt2).getMinutes() % 60;
	// int seconds = Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;
	//
	// StringBuilder sb = new StringBuilder();
	//
	// if (days > 0) {
	// sb.append(days + "天");
	// }
	//
	// if (hourse > 0 || sb.length() > 0) {
	// sb.append(hourse + "小时");
	// }
	//
	// if (minutes > 0 || sb.length() > 0) {
	// sb.append(minutes + "分钟");
	// }
	//
	// sb.append(seconds + "秒");
	//
	// return sb.toString();
	//
	// }

	
	public static void main(String[] args) {
		try {
			System.out.println(convertStringToDate("yyyy-MM-dd", "2012-02-03"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
