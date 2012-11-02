package net.itsplace.domain;

import java.util.Date;

import javax.validation.Valid;

import net.itsplace.user.User;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class Place {
	public interface AddPlace {}
	public interface EditPlace {}
	
	private int fid; //프랜차이저 고유키
	@NotEmpty
	private String fname; //가맹점명
	@NotEmpty
	private String name; //신청자명
	
	/**
	 * 1:개인 자영업 0:프랜차이즈
	 */
	@NotEmpty
	private String placeType; //1: 프랜ㄴ차이저, 2: 일반가맹점 
	@NotEmpty
	private String mobile; //신청자휴대전화번호
		
	@NotEmpty
	private String phone1; //가맹전화번호1
	private String phone2; //가맹전화번호2
	private String remark; //신청 메세지
	private String isAuth; //신청승인 여부
	private String nldno; //주소FK
	private String latitude; //위도
	private String longitude; //경도
	private Address address;
	private String fullAddress; //가맹점 주소
	private String fileName; //파일명
	private String searchType; //
	private String category; // 업종명
	private CommonsMultipartFile fileData; //업로드파일
	
	
	private StampType stampType;
	private User user; 
	private PlaceComment placeComment; 
	private PlaceStamp placeStamp;
	private String imageHost; 
	private String serviceType; // 서비스종류

	private String email; // 가맹주 아이디

	private String closeDay; // 휴무일
	private String openDay; // 영업시간
	private String website; // 영웹사이트
	private String info; // 가맹점 상세설명
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate; //
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date saveDate; // 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date editDate; //
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate; // 
	private String qrcode; 
	private String qrAuthCode;
	private String authCode;
	private String mcode;
	private String sido;
	private String gugun;
	private String dong;
	private String newAddress;
	private String payInfo;
	private String bldInfo;
	private String parkInfo;
	private int placeOn;
	
	private int pageBlock = 10;
	private int pageSize  = 5;
	private int currentPage = 1;
	private int pageNum = 1;
	
	private int start = 0;
	
	
	public int getPlaceOn() {
		return placeOn;
	}
	public void setPlaceOn(int placeOn) {
		this.placeOn = placeOn;
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
	public String getImageHost() {
		return imageHost;
	}
	public void setImageHost(String imageHost) {
		this.imageHost = imageHost;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getCloseDay() {
		return closeDay;
	}
	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}
	public String getOpenDay() {
		return openDay;
	}
	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getPlaceType() {
		return placeType;
	}
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PlaceComment getPlaceComment() {
		return placeComment;
	}
	public void setPlaceComment(PlaceComment placeComment) {
		this.placeComment = placeComment;
	}

	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Place(){
	
	}
	public Place(int fid, String fname, String nldno,
			String latitude, String longitude) {
		
		this.fid = fid;
		this.fname = fname;
		this.nldno = nldno;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	public String getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
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

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
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


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	

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
	public String getNldno() {
		return nldno;
	}
	public void setNldno(String nldno) {
		this.nldno = nldno;
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
	
	public StampType getStampType() {
		return stampType;
	}
	public void setStampType(StampType stampType) {
		this.stampType = stampType;
	}
	public PlaceStamp getPlaceStamp() {
		return placeStamp;
	}
	public void setPlaceStamp(PlaceStamp placeStamp) {
		this.placeStamp = placeStamp;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		if(pageBlock == 0) pageBlock = 10;
		this.pageBlock = pageBlock;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize == 0) pageSize = 5;
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		if(currentPage == 0) currentPage = 1;
		this.currentPage = currentPage;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		if(pageNum == 0) pageNum = 1;
		this.pageNum = pageNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
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
	@Override
	public String toString() {
		return "Place:[fid=" + fid + ", fname= " + fname + ",mobile= " + mobile+ ", nldno= " + nldno + ", latitude= " + latitude +  ", longitude= " + longitude +  ",  ]"  ;
	}
}
