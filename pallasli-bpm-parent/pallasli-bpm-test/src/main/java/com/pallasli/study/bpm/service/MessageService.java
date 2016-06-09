package com.pallasli.study.bpm.service;


public interface MessageService {

	/**
	 * 短信数据插入
	 * 
	 * @param telNum
	 * @param content
	 * @param appKey
	 * @param processName
	 */
	public void insertSms(String telNum, String content, String appKey,
			String processName);

	/**
	 * 根据手机号码或个人编号 查询手机号码 和 邮箱
	 * 
	 * @param telNum
	 * @return
	 */
	public void searchTelNumberEmail(String grbh);
}
