package net.itsplace.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PlaceMenu {
	public interface AddPlaceMenu{}
	public interface EditPlaceMenu {}
	private int mnid;
	private int fid;
	private String title;
	private String content;
	private int price;
	private String isSale;
	private int salePrice;
	private int sort;
	private String host;
	private String filePath;
	private String mType;
	private CommonsMultipartFile file;
	public int getMnid() {
		return mnid;
	}
	public void setMnid(int mnid) {
		this.mnid = mnid;
	}
	public int getFid() {
		return fid;
	}
	
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public void setFid(int fid) {
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getIsSale() {
		return isSale;
	}
	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	
	
}
