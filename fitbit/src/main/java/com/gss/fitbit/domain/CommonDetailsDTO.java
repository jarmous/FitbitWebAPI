package com.gss.fitbit.domain;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonDetailsDTO implements Serializable {
	
	private static final long serialVersionUID = -45258597925325208L;
	
	public static final String VERSION = "version";
	public static final String CREATED_AUTHOR = "strCreatedAuthor";
	public static final String CREATED_DATE = "createdDate";
	public static final String UPDATED_AUTHOR = "strUpdatedAuthor";
	public static final String UPDATED_DATE = "updatedDate";
	
	private int version;
	private String strCreatedAuthor;
	private Date createdDate;
	private String strUpdatedAuthor;
	private Date updatedDate;
	
	
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getStrCreatedAuthor() {
		return strCreatedAuthor;
	}
	public void setStrCreatedAuthor(String strCreatedAuthor) {
		this.strCreatedAuthor = strCreatedAuthor;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getStrUpdatedAuthor() {
		return strUpdatedAuthor;
	}
	public void setStrUpdatedAuthor(String strUpdatedAuthor) {
		this.strUpdatedAuthor = strUpdatedAuthor;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
