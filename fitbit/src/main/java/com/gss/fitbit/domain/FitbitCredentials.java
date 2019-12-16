package com.gss.fitbit.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="fitbit_credentials")
public class FitbitCredentials implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long rowId;
	private long runnerId;
	private String createdAuthor;
	private String updatedAuthor;
	private Date createdDate;
	private Date updatedDate;
	private int version;
	private boolean deleted;
	private String accessToken;
	private String refreshToken;
	private Date extraDate1;
	private Date extraDate2;
	private long extraNum1;
	private long extraNum2;
	private String extraStr1;
	private String extraStr2;
	private boolean extraBol1;
	private boolean extraBol2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROW_ID")
	public long getRowId() {
		return rowId;
	}
	@Column(name="RUNNER_ID",nullable=false)
	public long getRunnerId() {
		return runnerId;
	}
	@Column(name="CREATED_AUTHOR",nullable=false,columnDefinition="varchar(100) default 'system'")
	public String getCreatedAuthor() {
		return createdAuthor;
	}
	@Column(name="UPDATED_AUTHOR",length=100,nullable=true)
	public String getUpdatedAuthor() {
		return updatedAuthor;
	}
	@Column(name="CREATED_TS",nullable=false , columnDefinition="timestamp default '2018-02-20 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return createdDate;
	}
	@Column(name="UPDATED_TS",nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	@Column(name="VERSION",nullable=false,columnDefinition="integer default 1")
	public int getVersion() {
		return version;
	}
	@Column(name="DELETED",nullable=false,columnDefinition="smallint default 0")
	public boolean isDeleted() {
		return deleted;
	}
	@Column(name="ACCESS_TOKEN",nullable=false)
	public String getAccessToken() {
		return accessToken;
	}
	@Column(name="REFRESH_TOKEN",nullable=false)
	public String getRefreshToken() {
		return refreshToken;
	}
	@Column(name="EXTRA_DATE1",nullable=true)
	public Date getExtraDate1() {
		return extraDate1;
	}
	@Column(name="EXTRA_DATE2",nullable=true)
	public Date getExtraDate2() {
		return extraDate2;
	}
	@Column(name="EXTRANUM_COL1",nullable=true,columnDefinition="bigint default 0")
	public long getExtraNum1() {
		return extraNum1;
	}
	@Column(name="EXTRANUM_COL2",nullable=true,columnDefinition="bigint default 0")
	public long getExtraNum2() {
		return extraNum2;
	}
	@Column(name="EXTRA_COL1",length=250,nullable=true)
	public String getExtraStr1() {
		return extraStr1;
	}
	@Column(name="EXTRA_COL2",length=250,nullable=true)
	public String getExtraStr2() {
		return extraStr2;
	}
	@Column(name="EXTRABOL_COL1",nullable=true,columnDefinition="smallint default 0")
	public boolean isExtraBol1() {
		return extraBol1;
	}
	@Column(name="EXTRABOL_COL2",nullable=true,columnDefinition="smallint default 0")
	public boolean isExtraBol2() {
		return extraBol2;
	}
	
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}
	public void setRunnerId(long runnerId) {
		this.runnerId = runnerId;
	}
	public void setCreatedAuthor(String createdAuthor) {
		this.createdAuthor = createdAuthor;
	}
	public void setUpdatedAuthor(String updatedAuthor) {
		this.updatedAuthor = updatedAuthor;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public void setExtraDate1(Date extraDate1) {
		this.extraDate1 = extraDate1;
	}
	public void setExtraDate2(Date extraDate2) {
		this.extraDate2 = extraDate2;
	}
	public void setExtraNum1(long extraNum1) {
		this.extraNum1 = extraNum1;
	}
	public void setExtraNum2(long extraNum2) {
		this.extraNum2 = extraNum2;
	}
	public void setExtraStr1(String extraStr1) {
		this.extraStr1 = extraStr1;
	}
	public void setExtraStr2(String extraStr2) {
		this.extraStr2 = extraStr2;
	}
	public void setExtraBol1(boolean extraBol1) {
		this.extraBol1 = extraBol1;
	}
	public void setExtraBol2(boolean extraBol2) {
		this.extraBol2 = extraBol2;
	}
	
	@Override
	public String toString() {
		return "GarminCredentials [rowId=" + rowId + ", runnerId=" + runnerId + ", createdAuthor=" + createdAuthor
				+ ", updatedAuthor=" + updatedAuthor + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", version=" + version + ", deleted=" + deleted + ", accessToken=" + accessToken
				+ ", refreshToken=" + refreshToken + ", extraDate1=" + extraDate1 + ", extraDate2="
				+ extraDate2 + ", extraNum1=" + extraNum1 + ", extraNum2=" + extraNum2 + ", extraStr1=" + extraStr1
				+ ", extraStr2=" + extraStr2 + ", extraBol1=" + extraBol1 + ", extraBol2=" + extraBol2 + "]";
	}

}
