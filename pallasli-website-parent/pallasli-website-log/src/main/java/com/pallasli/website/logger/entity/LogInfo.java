package com.pallasli.website.logger.entity;

import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("serial")
public class LogInfo implements Serializable {

	private String time;
	private String methodName;
	private String methodAgrs;
	private String beginTime;
	private String endTime;
	private String ip;
	private String userName;
	private Object[] args;

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodAgrs() {
		return methodAgrs;
	}

	public void setMethodAgrs(String methodAgrs) {
		this.methodAgrs = methodAgrs;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "LogInfo [time=" + time + ", methodName=" + methodName
				+ ", methodAgrs=" + methodAgrs + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", ip=" + ip + ", userName="
				+ userName + ", args=" + Arrays.toString(args) + "]";
	}

}
