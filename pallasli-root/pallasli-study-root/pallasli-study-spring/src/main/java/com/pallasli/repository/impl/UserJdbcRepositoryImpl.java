package com.pallasli.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pallasli.bean.TestBean;
import com.pallasli.repository.UserRepository;

@Repository("userJdbcRepository")
public class UserJdbcRepositoryImpl implements UserRepository {

	@Autowired(required = false)
	private TestBean testBean;

	@Override
	public void save() {
		System.out.println("UserRepository Save...");
		System.out.println(testBean);
	}

	private JdbcTemplate jdbcT;

	public void setJdbcT(JdbcTemplate jdbcT) {
		this.jdbcT = jdbcT;
	}

	@Override
	public List<Map<String, Object>> findALL() {
		String sql = "create  table Dept(id int primary key,name varchar(20))";
		jdbcT.execute(sql);
		sql = "select * from dept";
		List<Map<String, Object>> list = jdbcT.queryForList(sql);
		System.out.println(list.size());
		return list;
	}

	@Override
	public List<TestBean> findALLDepts() {
		List<TestBean> depts = new ArrayList<TestBean>();

		String

		sql = "select * from Dept";
		List<Map<String, Object>> list = jdbcT.queryForList(sql);
		Iterator<Map<String, Object>> iterator = list.iterator();
		TestBean dept = null;
		while (iterator.hasNext()) {
			Map<String, Object> map4dept = iterator.next();
			dept = new TestBean();
			map4dept.containsKey("");
			// dept.setDeptNo(((BigDecimal) map4dept.get("DEPTNO")).intValue());
			// dept.setDName((String) map4dept.get("DNAME"));
			// dept.setLoc((String) map4dept.get("LOC"));
			depts.add(dept);
		}
		return depts;
	}

	@Override
	public int delete(int bid) {
		String sql = "delete from DeptInfo where bid =?";
		return jdbcT.update(sql, new Object[] { bid });
	}

}