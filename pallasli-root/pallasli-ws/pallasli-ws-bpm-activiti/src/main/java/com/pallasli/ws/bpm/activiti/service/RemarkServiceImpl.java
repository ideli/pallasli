package com.pallasli.ws.bpm.activiti.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.pallasli.bpm.api.bean.RemarkInfo;
import com.pallasli.bpm.api.service.RemarkService;

public class RemarkServiceImpl implements RemarkService {

	/**
	 * 获取流程流转记录列表
	 * 
	 * @param processKey
	 *            流程定义唯一标识
	 * @param instanceId
	 *            流程实例唯一标识
	 * @return
	 */
	@Override
	@WebMethod
	public List<RemarkInfo> getRemarkList(
			@WebParam(name = "processKey") String processKey,
			@WebParam(name = "instanceId") String instanceId) {
		return null;
	}
}
