package net.itsplace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="PSTAMP")
public class Stamp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid ;//적립 스탬프 키
	
	@ManyToOne
	@JoinColumn(name="STAMPID")	
	private PlaceStamp placeStamp;	
	@ManyToOne
	@JoinColumn(name="EMAIL")	
	private User user;
	private String status;
	private Date saveDate;
	private String mobile;
	private Date burnDate;
	private String remark;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public PlaceStamp getPlaceStamp() {
		return placeStamp;
	}
	public void setPlaceStamp(PlaceStamp placeStamp) {
		this.placeStamp = placeStamp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getBurnDate() {
		return burnDate;
	}
	public void setBurnDate(Date burnDate) {
		this.burnDate = burnDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
//	private int stampedTotal;
//	private Date stampedLastDate;
//	private String attribute; // 스탬프 속성
	
	
	
	
	
}
