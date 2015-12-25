package com.scheduler;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyIterator implements ScheduleIterator {
	
	@SuppressWarnings("unused")
	private final int hourOfDay, minute, second;
	private final Calendar calendar = Calendar.getInstance();

	public DailyIterator(int hourOfDay, int minute, int second) {
		this(hourOfDay, minute, second, new Date());
	}

	public DailyIterator(int hourOfDay, int minute, int second, Date date) {
		this.hourOfDay = hourOfDay;
		this.minute = minute;
		this.second = second;
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		if (!calendar.getTime().before(date)) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	public Date next() {
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		DailyIterator ss = new DailyIterator(9, 5, 0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Wake up! " + dateFormat.format(ss.next()) );
	}

}
