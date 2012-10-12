package com.mincoms.domain;

import java.io.Serializable;


public class BookCategory  implements Serializable{
	private int id;
	private BookCategorySub bookCategorySub;
	private String name;
	private int dispSeq;
	private boolean isDeleted;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDispSeq() {
		return dispSeq;
	}
	public void setDispSeq(int dispSeq) {
		this.dispSeq = dispSeq;
	}
	
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public BookCategorySub getBookCategorySub() {
		return bookCategorySub;
	}
	public void setBookCategorySub(BookCategorySub bookCategorySub) {
		this.bookCategorySub = bookCategorySub;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	
	
	
	
	
	
}
