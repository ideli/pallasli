package com.ibeifeng.ibatis.manytomany;

import java.util.List;

public class Role {
	private int id;
	private String rname;
	
	private List<Staff> staffList;
	
	public List<Staff> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	

}
