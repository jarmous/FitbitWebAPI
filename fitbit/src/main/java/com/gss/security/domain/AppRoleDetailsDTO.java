package com.gss.security.domain;


import org.springframework.security.core.GrantedAuthority;

public class AppRoleDetailsDTO implements GrantedAuthority {
	
	
	private static final long serialVersionUID = 5283744955896597373L;
	private String authority;
	private String authorityDesc;
	private String extraString;
	
	public AppRoleDetailsDTO() {
		super();
	}
	public AppRoleDetailsDTO( String authority, String authorityDesc) {
		super();
		this.authorityDesc = authorityDesc;
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public String getExtraString() {
		return extraString;
	}

	public void setExtraString(String extraString) {
		this.extraString = extraString;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "AppRoleDetailsDTO [authority=" + authority + ", authorityDesc=" + authorityDesc + ", extraString="
				+ extraString + "]";
	}

}
