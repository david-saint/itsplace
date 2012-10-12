package com.mincoms.domain;

import java.io.Serializable;
import java.util.Date;


public class BookRental  implements Serializable{
	private long id;
	
	private BookInfo bookInfo;
	
	private UserInfo userInfo;
	
	private Date startDate;
	private Date endDate;
	private Date returnDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public UserInfo getUser() {
		return userInfo;
	}
	public void setUser(UserInfo user) {
		this.userInfo = user;
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
	
	
	
}
