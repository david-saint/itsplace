package com.mincoms.book.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity(name="UserInfo")
public class UserInfo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	private String userRname;
	//private int deptCode;
	private String email;
	private String telephone;
	private int authlevel;
	private Boolean isDeleted;	
	private String password;
	private String gcmId;

	@OneToOne
	@JoinColumn(name="deptcode",columnDefinition="")
	private DeptInfo deptInfo;		
	
	public UserInfo(){
	}
	
	public UserInfo(int userId) {
		super();
		this.userId = userId;
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


	public String toString() { 
        return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE); 
	}

	public String getGcmId() {
		return gcmId;
	}

	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	
	
	
}
