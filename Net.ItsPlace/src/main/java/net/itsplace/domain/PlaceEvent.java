package net.itsplace.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PlaceEvent {
	public interface AddPlaceEvent {}
	public interface EditPlaceEvent {}
	
	private int eid;
	private String title;
	private String content;
	private Place place;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date saveDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date editDate;
	private String isAuth;
	private String isDelete;
	
	private String imageHost;
	private String filePath;
	
	private int readCount;
	public PlaceEvent(){
		place = new Place();
	}
	
	
	
	public String getImageHost() {
		return imageHost;
	}



	public void setImageHost(String imageHost) {
		this.imageHost = imageHost;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	
}
