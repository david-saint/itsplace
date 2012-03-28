package com.myplace.common;

import java.util.Date;

public class Notice {

	private String nid;
	private String title;
	private String content;
	private Date inpdate;
	private String hit;
	private String useyn;
	
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
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
	public Date getInpdate() {
		return inpdate;
	}
	public void setInpdate(Date inpdate) {
		this.inpdate = inpdate;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	
	
	
}
