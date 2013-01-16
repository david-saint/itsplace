package net.itsplace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="PLACESTAMP")
public class PlaceStamp {

	public interface AddPlaceStamp {}
	public interface EditPlaceStamp {}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stampid; //pk
	private int sid;        //스탬프타입 fk
	@ManyToOne
	@JoinColumn(name="FID")	
	private Place place;
	@ManyToOne
	@JoinColumn(name="SID")	
	private StampType stampType;
	private String stampTitle;
	private String theme;
	
	private Boolean isDelete; //삭제여
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date saveDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date editDate;
	

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate; //유효기간 시작
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate; // 유효기간 종료
	private String content;
	public int getStampid() {
		return stampid;
	}
	public void setStampid(int stampid) {
		this.stampid = stampid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public StampType getStampType() {
		return stampType;
	}
	public void setStampType(StampType stampType) {
		this.stampType = stampType;
	}
	public String getStampTitle() {
		return stampTitle;
	}
	public void setStampTitle(String stampTitle) {
		this.stampTitle = stampTitle;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

	
}
