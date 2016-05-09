package com.pallasli.ws.bpm.activiti.dao;

import java.util.List;

import com.pallasli.bpm.entity.RemarkInfo;

public interface HistoryServiceDao {

	public List<RemarkInfo> getRemarkList(String processInstanceId);

}
