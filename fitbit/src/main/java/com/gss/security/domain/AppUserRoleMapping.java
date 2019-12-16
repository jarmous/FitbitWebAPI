package com.gss.security.domain;


import java.io.Serializable;

public class AppUserRoleMapping implements Serializable {
	
	private static final long serialVersionUID = 164196948950489307L;
	
	private String username;
	private String role;	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
