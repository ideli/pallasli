package com.pallasli.study.bpm.service;

public interface OrgnizationService {

	public void getUserInfoList(String keyword, int firstNumber, int pageSize);

	public void getGroupInfoList(String keyword, int firstNumber, int pageSize);

	public void getDepartmentInfoList(String keyword, int firstNumber,
			int pageSize);

	public void getPositionInfoList(String keyword, int firstNumber,
			int pageSize);

}
