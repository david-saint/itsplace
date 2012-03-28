package com.itsplace.partner.place.event;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.myplace.common.BaseEntity;

public class Event  {

	private int eid;
	private String fid;
	private String title;
	private String content;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate ;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate;
	
	private String useyn;
	
	
	
	
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
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
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	
	
	public String ToString() {
		return "Event:[eid=" + eid + ", fid= " + fid + ", title= " + title + ", content= " + content +  ", startDate= " + startDate.toString() +  ", endDate= " + endDate.toString() + "]"  ;
	}
	
}
