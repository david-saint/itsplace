package com.mincoms.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="UserEtcInfo")
public class UserEtcInfo {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int userEtcId;
	
	 private String userName;
	 private String userJoinDate;
	 private String userIdNumber;
	 private boolean isDeleted;
	public int getUserEtcId() {
		return userEtcId;
	}
	public void setUserEtcId(int userEtcId) {
		this.userEtcId = userEtcId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserJoinDate() {
		return userJoinDate;
	}
	public void setUserJoinDate(String userJoinDate) {
		this.userJoinDate = userJoinDate;
	}
	public String getUserIdNumber() {
		return userIdNumber;
	}
	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	 
	 
	 
}
