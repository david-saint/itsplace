package net.itsplace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


@Entity(name="PMEDIA")
public class PlaceMedia {
	public interface AddPlaceMedia {}
	public interface EditPlaceMedia {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mid;
	@ManyToOne
	@JoinColumn(name="FID")		
	private Place place;
	
	private String mTitle;
	private String mUrl;
	private String mType;
	private Boolean isDelete;
	private String size;
	private Date saveDate;
	private Date editDate;
	@ManyToOne
	@JoinColumn(name="EMAIL")		
	private User user;
	private int dispseq;
	private Boolean isProfile;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getmUrl() {
		return mUrl;
	}
	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getDispseq() {
		return dispseq;
	}
	public void setDispseq(int dispseq) {
		this.dispseq = dispseq;
	}
	public Boolean getIsProfile() {
		return isProfile;
	}
	public void setIsProfile(Boolean isProfile) {
		this.isProfile = isProfile;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
