package com.pallasli.constant;

public class SystemConstant {
	public final static String FILE_SEP = "";
	public final static String CHARSET = "";

	public final static String CURR_PATH = SystemConstant.class
			.getClassLoader().getResource("").getPath();
	public final static String WEB_ROOT =

	CURR_PATH.indexOf("WEB-INF") > 0 ? CURR_PATH.substring(0,
			CURR_PATH.indexOf("WEB-INF"))
			: CURR_PATH.indexOf("target/classes") > 0 ? CURR_PATH.substring(0,
					CURR_PATH.indexOf("target/classes")) + "src/main/webapp/"
					: "";
	public static final String DATA_PATH = null;

}
