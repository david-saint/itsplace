package com.mincoms.book.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mincoms.book.domain.BookInfo;

public class DtoBookInfo {
	  private int rownumber;
	  private String thumbnail;
	  private String bookCategoryRoot;
	  private String bookCategory;
	  private String isbn;
	  private String title;
	  private String authors;
	  private int count;
	  private String publisher;
	  private String publishedDate;
	  private long reservationCount;
	  private long rentalCount;
	  private Date regDate;
	
	
	/*
	public DtoBookInfo(String thumbnail, String bookCategoryRoot,
			String bookCategory, String isbn, String title, String authors,
			int count, String publisher, String publishedDate,
			long reservationCount,long rentalCount) {
		super();
		this.thumbnail = thumbnail;
		this.bookCategoryRoot = bookCategoryRoot;
		this.bookCategory = bookCategory;
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.count = count;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.reservationCount = reservationCount;
		this.rentalCount = rentalCount;
	}

*/



	public int getRownumber() {
		return rownumber;
	}


	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	public String getBookCategoryRoot() {
		return bookCategoryRoot;
	}


	public void setBookCategoryRoot(String bookCategoryRoot) {
		this.bookCategoryRoot = bookCategoryRoot;
	}


	public String getBookCategory() {
		return bookCategory;
	}


	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthors() {
		return authors;
	}


	public void setAuthors(String authors) {
		this.authors = authors;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getPublishedDate() {
		return publishedDate;
	}


	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}


	public long getReservationCount() {
		return reservationCount;
	}


	public void setReservationCount(long reservationCount) {
		this.reservationCount = reservationCount;
	}


	public long getRentalCount() {
		return rentalCount;
	}


	public void setRentalCount(long rentalCount) {
		this.rentalCount = rentalCount;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
