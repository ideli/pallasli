package com.palllasli.utils;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.pallasli.utils.DateUtils;

public class DateUtilsTest {

	@Test
	public void convertStringToDate() throws ParseException {
		String format = "yyyy-mm-dd";
		String strDate = "2012-10-10";
		Date date = DateUtils.convertStringToDate(format, strDate);
		assertTrue(date.before(new Date()));

	}

}
