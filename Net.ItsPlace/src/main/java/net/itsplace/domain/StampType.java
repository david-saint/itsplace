package net.itsplace.domain;

import java.util.Date;

public class StampType {

	private int sid;
	private String title;
	private int stampcount;
	private int eventday;
	private String remark;
	private int dispseq;
	private String isDelete;
	private Date saveDate;
	private Date editDate;
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
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStampcount() {
		return stampcount;
	}
	public void setStampcount(int stampcount) {
		this.stampcount = stampcount;
	}
	public int getEventday() {
		return eventday;
	}
	public void setEventday(int eventday) {
		this.eventday = eventday;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDispseq() {
		return dispseq;
	}
	public void setDispseq(int dispseq) {
		this.dispseq = dispseq;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
