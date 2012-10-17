package com.mincoms.book.domain.vo;

import com.mincoms.book.domain.BookInfo;

public class VoBookInfo {

	private BookInfo bookInfo;
	private String categoryRoot;
	private String category;
	private long reservationCount;
	
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public long getReservationCount() {
		return reservationCount;
	}
	public void setReservationCount(long reservationCount) {
		this.reservationCount = reservationCount;
	}
	public String getCategoryRoot() {
		return categoryRoot;
	}
	public void setCategoryRoot(String categoryRoot) {
		this.categoryRoot = categoryRoot;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
