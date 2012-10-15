package net.itsplace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.itsplace.user.User.AddUser;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 가맹점 댓글 클래스    <br />
 * 
 * @author 김동훈
 * @version 1.0, 2011. 8. 24.
 * @param email
 * @return  edit.jsp
 * @throws 
 * @see 
 */

public class PlaceComment {
	public interface AddPlaceComment {}
	public interface EditPlaceComment {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private String cid; /* 코멘트  */
		@NotEmpty(message="코멘트를 입력하세요",groups={AddPlaceComment.class})
		private String comment; /* 코멘트 */
		private String email; /* 사용자 이메일  */
		private String name; 
		private String fname;
		private int fid;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		private Date saveDate;
		private String prettyDate;
		private String profileImageUrl;
		private String commentCount; // 코멘트갯수 
		
		public String getPrettyDate() {
			return prettyDate;
		}
		public void setPrettyDate(String prettyDate) {
			this.prettyDate = prettyDate;
		}
		public String getCommentCount() {
			return commentCount;
		}
		public void setCommentCount(String commentCount) {
			this.commentCount = commentCount;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getProfileImageUrl() {
			return profileImageUrl;
		}
		public void setProfileImageUrl(String profileImageUrl) {
			this.profileImageUrl = profileImageUrl;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCid() {
			return cid;
		}
		public void setCid(String cid) {
			this.cid = cid;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public int getFid() {
			return fid;
		}
		public void setFid(int fid) {
			this.fid = fid;
		}
		public Date getSaveDate() {
			return saveDate;
		}
		public void setSaveDate(Date saveDate) {
			this.saveDate = saveDate;
		}
		
		@Override
		public String toString() {
			return "PlaceComment:[cid=" + cid + ", fid= " + fid + ", email= " + email + ", comment= " + comment  + "]"  ;
		}
}
