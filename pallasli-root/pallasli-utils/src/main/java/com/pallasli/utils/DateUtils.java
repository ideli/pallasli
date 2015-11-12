package com.pallasli.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

//import org.joda.time.DateTime;
//import org.joda.time.Days;
//import org.joda.time.Hours;
//import org.joda.time.Minutes;
//import org.joda.time.Seconds;

public class DateUtils {

	private DateUtils() {

	}

	public static boolean isLeapYear(String ddate) {

		Date d = str2Date(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	public static String getWeek(String sdate, String num) {
		Date dd = str2Date(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1"))
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2"))
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3"))
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4"))
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5"))
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6"))
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0"))
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
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

	public static java.util.Date str2Time(String timestring) {
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date date = null;
		try {
			if (timestring.indexOf("CST") > 0) {
				sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",
						Locale.US);
				date = (Date) sdf.parse(timestring);
				// formatStr = new
				// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			} else {
				if (timestring.length() > 10) {
					date = format2.parse(timestring);
				} else {
					str2Date(timestring);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date str2Date(String year, String mon, String day) {
		return str2Date(year, mon, day, "0", "0", "0");
	}

	public static Date str2Date(String year, String mon, String day,
			String hour, String minute, String second) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(mon) - 1,
				Integer.parseInt(day), Integer.parseInt(hour),
				Integer.parseInt(minute), Integer.parseInt(second));
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
		// return new Date(Integer.parseInt(year) - 1900, Integer.parseInt(mon)
		// - 1, Integer.parseInt(day));
	}

	public static Date str2Date(String str) {
		if (str != null && !str.trim().equals("")) {
			if (str.indexOf("-") != -1) {
				String[] arrDate = str.split("-");
				return str2Date(arrDate[0], arrDate[1], arrDate[2]);
			} else {
				return str2Date(str.substring(0, 4), str.substring(4, 6),
						str.substring(6));
			}
		} else {
			return new Date();
		}
	}

	public static Date Str2DateTime(String str) {
		if (str != null && !str.trim().equals("")) {
			try {
				String[] dt = str.split(" ");
				String[] d = dt[0].split("-");
				String[] t = dt[1].split(":");
				return str2Date(d[0], d[1], d[2], t[0], t[1], t[2]);
			} catch (Exception e) {
				System.err.println("ת��ʱ����?" + e.getMessage());
				return new Date();
			}
		} else {
			return new Date();
		}
	}

	public static Date GoMonth(Date date, int mon) {
		// Timestamp st = new Timestamp(date.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, mon);
		return cal.getTime();
	}

	public static Date GoDate(Date date, int n) {
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, n); // ����һ��
			// cd.add(Calendar.MONTH, n);//����һ����

			return cd.getTime();

		} catch (Exception e) {
			return null;
		}
	}

	public static String getCurDate() {
		Calendar c = Calendar.getInstance();
		Timestamp ts = new Timestamp(c.getTime().getTime());
		String curDate = String.valueOf(ts);
		curDate = curDate.substring(0, curDate.indexOf(" "));

		return curDate;
	}

	public static String getCurTime() {
		Calendar c = Calendar.getInstance();

		Timestamp ts = new Timestamp(c.getTime().getTime());
		String curTime = String.valueOf(ts);
		curTime = curTime.substring(11, curTime.indexOf("."));

		return curTime;
	}

	public static String getCurDateTime() {
		Calendar c = Calendar.getInstance();
		Timestamp ts = new Timestamp(c.getTime().getTime());
		String curDateTime = String.valueOf(ts);
		curDateTime = curDateTime.substring(0, curDateTime.indexOf("."));
		return curDateTime;
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String formatDate(String date, String pattern)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// Date dt = sdf.parse(date);
		return sdf.format(date);
	}

	/*
	 * public static String formatDate(String date) { String
	 * cdate=date.substring(0,4)+date.substring(5,7)+date.substring(8,10);
	 * return cdate; } public static String formatDate(Date date) { Timestamp ts
	 * = new Timestamp(date.getTime()); String DateTime = String.valueOf(ts);
	 * DateTime = DateTime.substring(0,DateTime.indexOf("."));
	 * 
	 * return formatDate(DateTime); }
	 */

	public static String formatTime(String time) {
		String ctime = time.substring(0, 2) + time.substring(3, 5)
				+ time.substring(6, 8);
		return ctime;
	}

	public static int getYear() {
		String curDate = getCurDate();
		int year = Integer.parseInt(curDate.substring(0, 4));
		return year;
	}

	public static int getMon() {
		String curDate = getCurDate();
		int mon = Integer.parseInt(curDate.substring(5, 7));

		return mon;
	}

	/**
	 * ��ȡϵͳ����
	 * 
	 * @return ϵͳ����,�����ʽ�磺20
	 */
	public static int getDd() {
		String curDate = getCurDate();
		int dd = Integer.parseInt(curDate.substring(8, 10));

		return dd;
	}

	public static String getDateTimeDiff(Date dt1, Date dt2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(dt1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dt2);

		long tickCount = cal2.getTimeInMillis() - cal1.getTimeInMillis();

		return getDateTime(tickCount);
	}

	public static String getDateTime(long tickCount) {
		StringBuffer sb = new StringBuffer();
		int day = (int) (tickCount / (1000 * 60 * 60 * 24));
		tickCount %= (1000 * 60 * 60 * 24);// ��ȥ���ʣ�µ�ms��
		int hour = (int) (tickCount / (1000 * 60 * 60));
		tickCount %= (1000 * 60 * 60);
		int min = (int) (tickCount / (1000 * 60));
		tickCount %= (1000 * 60);
		int sec = (int) (tickCount / 1000);

		if (day > 0)
			sb.append(day > 9 ? day : "0" + day).append("��");
		if (hour > 0)
			sb.append(hour > 9 ? hour : "0" + hour).append("Сʱ");
		if (min > 0)
			sb.append(min > 9 ? min : "0" + min).append("����");
		if (sec >= 0)
			sb.append(sec > 9 ? sec : "0" + sec).append("��");
		return sb.toString();
	}

	public static String getBeginDateOfMonth(Date dt, String pattern)
			throws Exception {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(dt);
		c.set(GregorianCalendar.DATE, 1);
		// return c.get(GregorianCalendar.YEAR) + "-" + (mon<10 ? ("0" +
		// String.valueOf(mon)) : String.valueOf(mon)) + "-" + "01";
		return formatDate(c.getTime(), pattern);
	}

	public static String getEndDateOfMonth(Date dt, String pattern)
			throws Exception {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(dt);
		GregorianCalendar end = new GregorianCalendar(
				c.get(GregorianCalendar.YEAR),
				c.get(GregorianCalendar.MONTH) + 1, 0);
		return formatDate(end.getTime(), pattern);
	}

	// Static format objects
	private static SimpleDateFormat dateFormat = new SimpleDateFormat();

	/**
	 * �����ַ��Ƿ�����ָ����ʽ��ʾ
	 *
	 * @param dateString
	 *            ������ʾ������/ʱ��
	 * @param dateFormatPattern
	 *            ���������ַ����õ�ģʽ ģʽ��java.text.SimpleDateFormat�����ģʽ�ַ��ʾ
	 * @return ��Ч����true�����򷵻�false
	 */
	public static boolean isValidDate(String dateString,
			String dateFormatPattern) {
		Date validDate = null;
		synchronized (dateFormat) {
			try {
				dateFormat.applyPattern(dateFormatPattern);
				dateFormat.setLenient(false);
				validDate = dateFormat.parse(dateString);
			} catch (ParseException e) {
				// Ignore and return null
			}
		}
		return validDate != null;
	}

	public static boolean isValidEmailAddr(String emailAddrString) {
		boolean isValid = false;
		if (emailAddrString != null && emailAddrString.indexOf("@") != -1
				&& emailAddrString.indexOf(".") != -1) {
			isValid = true;
		}
		return isValid;
	}

	public static boolean isValidString(String value, String[] validStrings,
			boolean ignoreCase) {
		boolean isValid = false;
		for (int i = 0; validStrings != null && i < validStrings.length; i++) {
			if (ignoreCase) {
				if (validStrings[i].equalsIgnoreCase(value)) {
					isValid = true;
					break;
				}
			} else {
				if (validStrings[i].equals(value)) {
					isValid = true;
					break;
				}
			}
		}
		return isValid;
	}

	public static String toHTMLString(String in) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; in != null && i < in.length(); i++) {
			char c = in.charAt(i);
			if (c == '\'') {
				out.append("&#39;");
			} else if (c == '\"') {
				out.append("&#34;");
			} else if (c == '<') {
				out.append("&lt;");
			} else if (c == '>') {
				out.append("&gt;");
			} else if (c == '&') {
				out.append("&amp;");
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}

	/**
	 * ʹ��ģʽת���ַ�Ϊ���ڣ�java.text.SimpleDateFormat��Ϊģʽ����
	 *
	 * @param dateString
	 *            Ҫת�����ַ�
	 * @param dateFormatPattern
	 *            ģʽ
	 * @return ���Ӧ������
	 * @exception ParseException
	 *                , �ַ�ƥ��ģʽ
	 */
	public static Date toDate(String dateString, String dateFormatPattern)
			throws ParseException {
		Date date = null;
		if (dateFormatPattern == null) {
			dateFormatPattern = "yyyy-MM-dd";
		}
		synchronized (dateFormat) {
			dateFormat.applyPattern(dateFormatPattern);
			dateFormat.setLenient(false);
			date = dateFormat.parse(dateString);
		}
		return date;
	}

	/**
	 * ��������ʱ���ַ��е����ڲ���
	 * 
	 * @param strDateTime
	 * @return �磺2004-6-6 00:00:00.0 ���� 2004-6-6
	 */
	public static String getDate(String strDateTime) {
		return strDateTime.substring(0, strDateTime.indexOf(" "));
	}

	public static void main(String[] args) {
		try {
			System.out.println(convertStringToDate("yyyy-MM-dd", "2012-02-03"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
