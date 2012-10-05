package com.mincoms.book.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
public class BookRestriction {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name="userId")
	private UserInfo userInfo;
	
	private Date regDate;
	private Date solveDate;
	
	@OneToOne
	@JoinColumn(name="reasonId")
	private BaseCode basecode;
	
	private String solveReason;
	
	public BaseCode getBasecode() {
		return basecode;
	}
	public void setBasecode(BaseCode basecode) {
		this.basecode = basecode;
	}
	public String getSolveReason() {
		return solveReason;
	}
	public void setSolveReason(String solveReason) {
		this.solveReason = solveReason;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
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
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
