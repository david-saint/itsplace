package net.itsplace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.itsplace.user.User;

@Entity(name="PLACEUSER")
public class PlaceUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	@ManyToOne
	@JoinColumn(name="FID")	
	private Place place;
	@ManyToOne
	@JoinColumn(name="EMAIL")	
	private User user; 
	private Boolean isDelete;
	private Date saveDate;	
	private Date editDate;
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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
	
	
	
	
}
