package com.pallasli.springboot;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "users")
public class User {
	// @Id
	private String username;
	// Comment:if set@JsonIgnore, We can't use curl to send and get data
	// @JsonIgnore
	private String password;
	// @JsonIgnore
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}