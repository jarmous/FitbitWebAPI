package com.gss.security.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetailsDTO implements UserDetails {

	private static final long serialVersionUID = -521752153744419625L;

	private String emailId;
	private String password;
	private long runnerId;
	private boolean emailVerified;
	private boolean userActive;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private Date lastLoginTS;
	private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	private String mobileNum;
	private Date dateOfBirth;
	private String displayName;
	private String gender;
	private boolean mobileVerified;
	private boolean picUploaded;
	private boolean verifMailSent;
	private boolean reportsDisabled;
	private int picVersion;
	private String mobileCode;
	private String userName;



	public long getRunnerId() {
		return runnerId;
	}
	public void setRunnerId(long runnerId) {
		this.runnerId = runnerId;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public Date getLastLoginTS() {
		return lastLoginTS;
	}

	public void setLastLoginTS(Date lastLoginTS) {
		this.lastLoginTS = lastLoginTS;
	}

	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public boolean isMobileVerified() {
		return mobileVerified;
	}
	public void setMobileVerified(boolean mobileVerified) {
		this.mobileVerified = mobileVerified;
	}

	public String getMobileCode() {
		return mobileCode;
	}
	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}
	public boolean isVerifMailSent() {
		return verifMailSent;
	}
	public void setVerifMailSent(boolean verifMailSent) {
		this.verifMailSent = verifMailSent;
	}
	public boolean isReportsDisabled() {
		return reportsDisabled;
	}
	public void setReportsDisabled(boolean reportsDisabled) {
		this.reportsDisabled = reportsDisabled;
	}
	public boolean isPicUploaded() {
		return picUploaded;
	}
	public void setPicUploaded(boolean picUploaded) {
		this.picUploaded = picUploaded;
	}
	public int getPicVersion() {
		return picVersion;
	}
	public void setPicVersion(int picVersion) {
		this.picVersion = picVersion;
	}

	@Transient
	public List<String> getRoleList(){
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}
		return roles;
	}
	
	@Transient
	public String getUsername() {
		return getUserName();
	}

	
	@Transient
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	
	@Transient
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired();
	}

	
	@Transient
	public boolean isEnabled() {
		return isUserActive();
	}
	@Override
	public String toString() {
		return "AppUserDetailsDTO [emailId=" + emailId + ", password=" + password + ", runnerId=" + runnerId
				+ ", emailVerified=" + emailVerified + ", userActive=" + userActive + ", accountExpired="
				+ accountExpired + ", accountLocked=" + accountLocked + ", credentialsExpired=" + credentialsExpired
				+ ", lastLoginTS=" + lastLoginTS + ", authorities=" + authorities + ", mobileNum=" + mobileNum
				+ ", dateOfBirth=" + dateOfBirth + ", displayName=" + displayName + ", gender=" + gender
				+ ", mobileVerified=" + mobileVerified + ", picUploaded=" + picUploaded + ", verifMailSent="
				+ verifMailSent + ", reportsDisabled=" + reportsDisabled + ", picVersion=" + picVersion
				+ ", mobileCode=" + mobileCode + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
