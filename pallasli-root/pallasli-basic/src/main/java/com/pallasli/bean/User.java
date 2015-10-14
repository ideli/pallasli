package com.pallasli.bean;

import java.util.Date;

public class User {
	private long id;
	private String f_name;
	private String f_password;
	private String f_finger_code;
	private String f_caption;
	private String f_state;
	private String f_latest_ip;
	private Date f_latest_time;
	private boolean f_force;
	private int version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_password() {
		return f_password;
	}

	public void setF_password(String f_password) {
		this.f_password = f_password;
	}

	public String getF_finger_code() {
		return f_finger_code;
	}

	public void setF_finger_code(String f_finger_code) {
		this.f_finger_code = f_finger_code;
	}

	public String getF_caption() {
		return f_caption;
	}

	public void setF_caption(String f_caption) {
		this.f_caption = f_caption;
	}

	public String getF_state() {
		return f_state;
	}

	public void setF_state(String f_state) {
		this.f_state = f_state;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getF_latest_ip() {
		return f_latest_ip;
	}

	public void setF_latest_ip(String f_latest_ip) {
		this.f_latest_ip = f_latest_ip;
	}

	public Date getF_latest_time() {
		return f_latest_time;
	}

	public void setF_latest_time(Date f_latest_time) {
		this.f_latest_time = f_latest_time;
	}

	public boolean isF_force() {
		return f_force;
	}

	public void setF_force(boolean f_force) {
		this.f_force = f_force;
	}

}
