package net.itsplace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.itsplace.domain.User.AddUser;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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

@Entity(name="PLACECOMMENT")
public class PlaceComment {
	public interface AddPlaceComment {}
	public interface EditPlaceComment {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private int cid; /* 코멘트  */
	@ManyToOne
	@JoinColumn(name="FID")	
		private Place place;
	@ManyToOne
	@JoinColumn(name="EMAIL")	
		private User user; /* 사용자 이메일  */
		
		@NotEmpty(message="코멘트를 입력하세요",groups={AddPlaceComment.class})
		private String comment; /* 코멘트 */
		
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		private Date saveDate;
		private Boolean isDelete;
	//	private String prettyDate;
		//private String profileImageUrl;
		//private String commentCount; // 코멘트갯수 
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		public Place getPlace() {
			return place;
		}
		public void setPlace(Place place) {
			this.place = place;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public Date getSaveDate() {
			return saveDate;
		}
		public void setSaveDate(Date saveDate) {
			this.saveDate = saveDate;
		}
		public Boolean getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Boolean isDelete) {
			this.isDelete = isDelete;
		}
		
		public String toString(){
			return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
		}
}
