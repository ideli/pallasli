package com.pallasli.study.struts2.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class BirthdayConvert extends DefaultTypeConverter {
	// 双向转换,回显时没进转换器不知道原因
	@Override
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		System.out.println(toType);
		if (toType == Date.class) {
			// 传入参数转换为接收对象
			String[] birthdays = (String[]) value;
			String birthday = birthdays[0].replaceAll("birthday-", "");
			try {
				return df.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (toType == String.class) {
			// 内部对象回显到页面
			Date date = (Date) value;
			return "birthday-" + df.format(date);
		}
		return super.convertValue(value, toType);
	}

}
