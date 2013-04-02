package net.itsplace.domain.dto;

import java.util.Date;

public class PlaceUserMedia {

	private int mkey;
	private String mtype; // R:리뷰 C:코멘트
	private String title;
	private String content;
	private String url;
	private String siteUrl;
	private Date createDate;
	public int getMkey() {
		return mkey;
	}
	public void setMkey(int mkey) {
		this.mkey = mkey;
	}

	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
