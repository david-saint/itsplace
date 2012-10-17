package com.mincoms.book.domain.dto;

import java.util.Date;

public class DtoRentalStatics {
	private int rownumber;
	private int id;
	private String isbn;
	private String thumbnail;
	private String categoryRoot;
	private String categotySub;
	private String category;
	private String title;				
	private String deptName;
	private String userRname;
	private Date startDate;
	private Date endDate;
	private Date returnDate;
	private String userName;
	public int getRownumber() {
		return rownumber;
	}
	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getCategoryRoot() {
		return categoryRoot;
	}
	public void setCategoryRoot(String categoryRoot) {
		this.categoryRoot = categoryRoot;
	}
	public String getCategotySub() {
		return categotySub;
	}
	public void setCategotySub(String categotySub) {
		this.categotySub = categotySub;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getUserRname() {
		return userRname;
	}
	public void setUserRname(String userRname) {
		this.userRname = userRname;
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
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
