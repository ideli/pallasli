package com.pallasli.jdbc;

import java.util.Date;
import java.util.Random;

import com.pallasli.utils.DateUtils;

public class TestDbFactory {
	// private DbFactory dbFactory = null;

	public static void main(String[] args) throws Exception {
		String[] procName = (new DbFactory()).getAllProcedureName();
		for (int i = 0; i < procName.length; i++) {
			for (int j = i + 1; j < procName.length; j++) {
				if (procName[i].equals(procName[j])) {
					System.out.println("the same name:[" + procName[i] + "]");
				}
			}
		}
		System.out.println("check over");
	}

	public void test() {

		// out("" + Constant.getTaskType(Constant.Organ.Bank,
		// Constant.cmdYhqqgjjGrye));
		// ("Asia/Shanghai");

		System.setProperty("user.timezone", "Asia/Shanghai");
		System.out.println("Date()=" + new Date());
		System.out.println("Calendar=" + DateUtils.getCurDateTime());
		// out("" + TimeZone.getDefault() );

		for (int i = 0; i < 10; i++) {
			double d = (new Random()).nextDouble();
			long l = (long) (d * (Math.pow(10, 6)) * 100);
			double dd = l / 100.0;
			System.out.println(i + "," + d + "," + l + "," + dd);
			System.out.println((new java.util.Date()).toString());
			// Thread.sleep(2000);
		}

		// ParseXML px = new ParseXML();
		// out(px.transFormer("./config/config.xml", "./config/config.xsl"));
		System.out.println("P_dfeefef".toLowerCase().replace("p_", "tmp_")
				.replace("tx_", ""));
		StringBuffer sb = new StringBuffer("123456780");
		sb.insert(sb.length() - 1, '9');
		System.out.println(sb.toString());

	}
}
