package com.pallasli.authority.orgnization;

public abstract class AbstractUser {

	private long id;
	private String f_name;
	private String f_caption;
	private String f_password;

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

	public String getF_caption() {
		return f_caption;
	}

	public void setF_caption(String f_caption) {
		this.f_caption = f_caption;
	}

	public String getF_password() {
		return f_password;
	}

	public void setF_password(String f_password) {
		this.f_password = f_password;
	}

}
