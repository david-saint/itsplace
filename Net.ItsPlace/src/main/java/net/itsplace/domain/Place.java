package net.itsplace.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity(name="PLACE")
public class Place {
	public interface AddPlace {}
	public interface EditPlace {}
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(columnDefinition="COMMENT 'PK'")
	private int fid; 
	
	@NotEmpty
	@Column(columnDefinition="VARCHAR(255) COMMENT '가맹점명'")
	private String fname; 
	
	@Column(columnDefinition="VARCHAR(255) COMMENT '가맹점명 영어'")
	private String ename;
	@NotEmpty
	@Column(columnDefinition="VARCHAR(255) COMMENT '신청자명'")
	private String name; 
	
	private String latitude; //위도
	private String longitude; //경도
	
	
	@NotEmpty
	@ManyToOne
	@JoinColumn(name="PLACETYPE")	
	private PlaceType placeType;  
	
	@NotEmpty
	@Column(columnDefinition="VARCHAR(14) COMMENT '가맹점주 휴대전화번호'")
	private String mobile; 
	@Column(columnDefinition="VARCHAR(255) COMMENT '가맹점주 이메일'")
	private String email;
	@NotEmpty
	@Column(columnDefinition="VARCHAR(14) COMMENT '가맹전화번호1'")
	private String phone1; 
	private String phone2; //가맹전화번호2
	
	@NotEmpty
	private Boolean isAuth; //신청승인 여부	
	private String remark; //신청 메세지
	
	@ManyToOne
	@JoinColumn(name="CATEGORY")	
	private Category category; 
	
	private String transferyn; //배달가능여부
	private String openDay; // 영업시간
	private String closeDay; // 휴무일
	private String website; // 영웹사이트
	private String info; // 가맹점 상세설명
	private String serviceType; // place 서비스 종류  N:일반 , P:프리미어, S:스탬프


	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate; //서비스시작일자
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate;//서비스종료일자

	
	@NotEmpty
	private String fileName; //가맹점 대표 이미지
	
	private String payInfo; //결재정보
	private String bldInfo; // 건물정보 지리정보
	private String parkInfo; // 주차정보

	private String searchType; //





 // 
	private String qrcode; 
	private String authCode;
	private String qrAuthCode;
	private String mcode;
	
	private String fullAddress;	
	private String sido;
	private String gugun;
	private String dong;
	private String newAddress;
	
	private int placeOn;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date saveDate; // 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date editDate;
	
	
	
	
	public int getFid() {
		return fid;
	}




	public void setFid(int fid) {
		this.fid = fid;
	}




	public String getFname() {
		return fname;
	}




	public void setFname(String fname) {
		this.fname = fname;
	}




	public String getEname() {
		return ename;
	}




	public void setEname(String ename) {
		this.ename = ename;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getLatitude() {
		return latitude;
	}




	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}




	public String getLongitude() {
		return longitude;
	}




	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}




	public PlaceType getPlaceType() {
		return placeType;
	}




	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}




	public String getMobile() {
		return mobile;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPhone1() {
		return phone1;
	}




	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}




	public String getPhone2() {
		return phone2;
	}




	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}




	public Boolean getIsAuth() {
		return isAuth;
	}




	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}




	public String getTransferyn() {
		return transferyn;
	}




	public void setTransferyn(String transferyn) {
		this.transferyn = transferyn;
	}




	public String getOpenDay() {
		return openDay;
	}




	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}




	public String getCloseDay() {
		return closeDay;
	}




	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}




	public String getWebsite() {
		return website;
	}




	public void setWebsite(String website) {
		this.website = website;
	}




	public String getInfo() {
		return info;
	}




	public void setInfo(String info) {
		this.info = info;
	}




	public String getServiceType() {
		return serviceType;
	}




	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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




	public String getFileName() {
		return fileName;
	}




	public void setFileName(String fileName) {
		this.fileName = fileName;
	}




	public String getPayInfo() {
		return payInfo;
	}




	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}




	public String getBldInfo() {
		return bldInfo;
	}




	public void setBldInfo(String bldInfo) {
		this.bldInfo = bldInfo;
	}




	public String getParkInfo() {
		return parkInfo;
	}




	public void setParkInfo(String parkInfo) {
		this.parkInfo = parkInfo;
	}




	public String getSearchType() {
		return searchType;
	}




	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}




	public String getQrcode() {
		return qrcode;
	}




	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}




	public String getAuthCode() {
		return authCode;
	}




	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}




	public String getQrAuthCode() {
		return qrAuthCode;
	}




	public void setQrAuthCode(String qrAuthCode) {
		this.qrAuthCode = qrAuthCode;
	}




	public String getMcode() {
		return mcode;
	}




	public void setMcode(String mcode) {
		this.mcode = mcode;
	}




	public String getFullAddress() {
		return fullAddress;
	}




	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}




	public String getSido() {
		return sido;
	}




	public void setSido(String sido) {
		this.sido = sido;
	}




	public String getGugun() {
		return gugun;
	}




	public void setGugun(String gugun) {
		this.gugun = gugun;
	}




	public String getDong() {
		return dong;
	}




	public void setDong(String dong) {
		this.dong = dong;
	}




	public String getNewAddress() {
		return newAddress;
	}




	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}




	public int getPlaceOn() {
		return placeOn;
	}




	public void setPlaceOn(int placeOn) {
		this.placeOn = placeOn;
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




	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
