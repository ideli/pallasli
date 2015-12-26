package com.pallasli.repository;

import java.util.List;
import java.util.Map;

import com.pallasli.bean.TestBean;

public interface UserRepository {
	void save();

	public List<Map<String, Object>> findALL();

	public List<TestBean> findALLDepts();

	public int delete(int bid);
}
