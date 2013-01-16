package net.itsplace.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.itsplace.validation.UserPrimarykey;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;






import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity(name="PUSER")
public class User implements Serializable{
	public interface AddUser {}
	public interface EditUser {}
	
	@Id
	@Email(groups={AddUser.class})	
	@NotEmpty(message="Email을 입력하세요",groups={AddUser.class})
	@UserPrimarykey(groups={AddUser.class})
	private String email;
	@NotEmpty(message="비밀번호를 입력하세요",groups={AddUser.class,EditUser.class})
	@Size(min = 5, max = 20, message="비밀번호는 5글자 이상 입력하세요",groups={AddUser.class,EditUser.class})
	private String password;

	@NotEmpty(message="이름을 입력하세요",groups={AddUser.class,EditUser.class})
	private String name;
	
	/**
	 * Access level of the user. 
	 * ADMIN = 관리자
	 * USER = 일반 사용자(모든 사용자가 가지고 있는 공통 권한)
	 * FRANCH = 가맹점 관리자 권한
	 */
	private String role;
	
	/*프로필 이미지 URL*/
	private String profileImageUrl;
	/* 프로필 이미지 선택 */
	private String profileImageType;
	private Boolean isDelete;	
	private Boolean isEmail;	
	private String emailToken;
	private String mobile;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date saveDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date editDate;
	private String sex;
	private String passwordLink;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

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

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(Boolean isEmail) {
		this.isEmail = isEmail;
	}

	public String getEmailToken() {
		return emailToken;
	}

	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPasswordLink() {
		return passwordLink;
	}

	public void setPasswordLink(String passwordLink) {
		this.passwordLink = passwordLink;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
