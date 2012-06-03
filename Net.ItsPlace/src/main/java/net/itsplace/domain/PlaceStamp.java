package net.itsplace.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PlaceStamp {

	public interface AddPlaceStamp {}
	public interface EditPlaceStamp {}
	private int stampid; //pk
	private int sid;        //스탬프타입 fk
	private int fid;		//가맹점 fk
	private String isDelete; //삭제여
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date saveDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date editDate;
	private String stampTitle;
	private String theme;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate; //유효기간 시작
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate; // 유효기간 종료
	private StampType stampType;
	private Place place;
	private String content;
	
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getStampid() {
		return stampid;
	}
	public void setStampid(int stampid) {
		this.stampid = stampid;
	}
	public StampType getStampType() {
		return stampType;
	}
	public void setStampType(StampType stampType) {
		this.stampType = stampType;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
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

	public String getStampTitle() {
		return stampTitle;
	}
	public void setStampTitle(String stampTitle) {
		this.stampTitle = stampTitle;
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
	
	
}
