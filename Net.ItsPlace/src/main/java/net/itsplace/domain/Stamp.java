package net.itsplace.domain;

import java.util.Date;

import net.itsplace.user.User;

public class Stamp {

	private int pid ;//적립 스탬프 키
	private PlaceStamp placeStamp;
	private User user;
	private String mobile;
	private String status;
	private Date saveDate;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
	
}