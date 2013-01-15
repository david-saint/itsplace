package net.itsplace.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity(name="PLACEMENU")
public class PlaceMenu {
	public interface AddPlaceMenu{}
	public interface EditPlaceMenu {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mnid;
	
	@ManyToOne
	@JoinColumn(name="FID")	
	private Place place;
	
	private String title;
	private String content;
	private int price;
	private Boolean isSale;
	private int salePrice;
	private int sort;
	private String host;
	private String filePath;
	private String mType;
	private Boolean isDelete;
	public int getMnid() {
		return mnid;
	}
	public void setMnid(int mnid) {
		this.mnid = mnid;
	}
	
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
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
	public Boolean getIsSale() {
		return isSale;
	}
	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	
	
}
