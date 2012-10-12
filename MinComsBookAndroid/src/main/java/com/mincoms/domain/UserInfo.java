package com.mincoms.domain;

import java.io.Serializable;


public class UserInfo  implements Serializable{
	
	private int userId;
	private String userName;
	private String userRname;
	private String email;
	private String telephone;
	private int authlevel;
	private Boolean isDeleted;	
	private String password;
	private DeptInfo deptInfo;		
	private String gcmId;
   
	
	
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	public DeptInfo getDeptInfo() {
		return deptInfo;
	}
	public void setDeptInfo(DeptInfo deptInfo) {
		this.deptInfo = deptInfo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRname() {
		return userRname;
	}
	public void setUserRname(String userRname) {
		this.userRname = userRname;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getAuthlevel() {
		return authlevel;
	}
	public void setAuthlevel(int authlevel) {
		this.authlevel = authlevel;
	}


	
}
