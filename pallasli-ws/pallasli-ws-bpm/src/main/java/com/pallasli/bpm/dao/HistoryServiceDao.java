package com.pallasli.bpm.dao;

import java.util.List;

import com.pallasli.bpm.bean.RemarkInfo;

public interface HistoryServiceDao {

	public List<RemarkInfo> getRemarkList(String processInstanceId);

}
