package com.pallasli.ws.bpm.activiti.dao;

import java.util.List;

import com.pallasli.bpm.api.bean.RemarkInfo;

public interface HistoryServiceDao {

	public List<RemarkInfo> getRemarkList(String processInstanceId);

}
