package com.mincoms.book.domain.vo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.mincoms.book.domain.BaseCode;
import com.mincoms.book.domain.UserInfo;

public class VoBookRestriction {
	private long id;	
	private String userRname;
	private String deptName;
	private Date regDate;
	private Date solveDate;
	private String reason;
	private String solveReason;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSolveReason() {
		return solveReason;
	}
	public void setSolveReason(String solveReason) {
		this.solveReason = solveReason;
	}
	public String getUserRname() {
		return userRname;
	}
	public void setUserRname(String userRname) {
		this.userRname = userRname;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getSolveDate() {
		return solveDate;
	}
	public void setSolveDate(Date solveDate) {
		this.solveDate = solveDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
