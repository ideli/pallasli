package com.atwasoft.framework.container;


public class ContainerEnv 
{		
	public static final String FILE_SEP 		= System.getProperty("file.separator");
	private static final String UserDir			= System.getProperty("user.dir");
	public static final String DATA_PATH		= UserDir + FILE_SEP + "data";
	
	//在linux环境下仍然出现乱码的情况可以在操作系统上设置export LANG=zh_CN.GBK
	public static final String CHARSET			= "GBK";

	//监控端颜色常量
	public static final String COLOR_RECEIVE 	= "blue";
	public static final String COLOR_SEND 		= "maroon";
	public static final String COLOR_INIT 		= "gray";
	public static final String COLOR_ERROR 		= "red";
	public static final String COLOR_RUNNING	= "limegreen";

	public static final int DELAYTIME 			= 60 * (1000 * 60);//定时任务时间触发间隔

	private  int webServicePort	= 8081;//WebService数据服务端口
	private  boolean dbTrace	= false;//日志监控控制开关
	private boolean monitor		=false;////是否启用监控服务，该服务提供浏览器方式下的交易监控

	private String containerConfigFile;

	private String dbType;

	private int logCounter;

	private int appPoolSize;
	private int systemPoolSize;

	public int getWebServicePort() {
		return webServicePort;
	}

	public void setWebServicePort(int webServicePort) {
		this.webServicePort = webServicePort;
	}

	public boolean isDbTrace() {
		return dbTrace;
	}
	public void setDbTrace(boolean dbTrace) {
		this.dbTrace = dbTrace;
	}
	public int getAppPoolSize() {
		return appPoolSize;
	}

	public void setAppPoolSize(int poolSize) {
		this.appPoolSize = poolSize;
	}
	public int getLogCounter() {
		return logCounter;
	}

	public void setLogCounter(int logCounter) {
		this.logCounter = logCounter;
	}

	public boolean isMonitor() {
		return monitor;
	}

	public void setMonitor(boolean monitor) {
		this.monitor = monitor;
	}

	private static final ContainerEnv singleton=new ContainerEnv();

	private ContainerEnv(){}

	public static ContainerEnv getInstance(){
		return singleton;
	}
	public int getSystemPoolSize() {
		return systemPoolSize;
	}
	public void setSystemPoolSize(int systemPoolSize) {
		this.systemPoolSize = systemPoolSize;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getContainerConfigFile() {
	    return containerConfigFile;
    }

	public void setContainerConfigFile(String containerConfigFile) {
	    this.containerConfigFile = containerConfigFile;
    }

}

