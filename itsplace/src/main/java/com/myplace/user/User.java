package com.myplace.user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.*;

import com.itsplace.validation.UserMobileUnique;
import com.itsplace.validation.UserPrimarykey;

import javax.validation.constraints.Size;

@XmlRootElement(name = "user")
public class User {
	
	@Email	
	@NotEmpty(message="Email을 입력하세요")
	@UserPrimarykey
	private String email;
	
	private String gender;
	private String birthDay;
	private String emailToken;
	private String emailYn;
	@UserMobileUnique
	private String mobile;
	
	@NotEmpty(message="비밀번호를 입력하세요")
	@Size(min = 5, max = 20, message="비밀번호는 5글자 이상 입력하세요")
	private String password;
	
	@NotEmpty(message="이름을 입력하세요")
	private String name;
	/**
	 * Access level of the user. 
	 * ADMIN = 관리자
	 * USER = 일반 사용자(모든 사용자가 가지고 있는 공통 권한)
	 * FRANCH = 가맹점 관리자 권한
	 */
	private String role;
	
	/*주소fk*/
	private String nldno;
	
	private String useyn;
	

	
	/*프로필 이미지 URL*/
	private String profileImageUrl;
	
	/* 프로필 이미지 선택 */
	private String profileImageType;
	
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public String getProfileImageType() {
		return profileImageType;
	}
	public void setProfileImageType(String profileImageType) {
		this.profileImageType = profileImageType;
	}
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getNldno() {
		return nldno;
	}
	public void setNldno(String nldno) {
		this.nldno = nldno;
	}
	public String getRole() {
		return role;
	}
	@XmlElement
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getEmailToken() {
		return emailToken;
	}
	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
	}
	
	public String getEmailYn() {
		return emailYn;
	}
	public void setEmailYn(String emailYn) {
		this.emailYn = emailYn;
	}
	@Override
	public String toString() {
		return "User:[name=" + getName() + ", email= " + getEmail() + ", password= " + getPassword() + ", role= " + getRole() + "]"  ;
	}
}
