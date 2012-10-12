package com.mincoms.domain;

import java.io.Serializable;

public class BookCategorySub  implements Serializable{

	private int id;
	private String name;
	private int dispSeq;
	private boolean isDeleted;
	

	private BookCategoryRoot bookCategoryRoot;
	public BookCategoryRoot getBookCategoryRoot() {
		return bookCategoryRoot;
	}
	public void setBookCategoryRoot(BookCategoryRoot bookCategoryRoot) {
		this.bookCategoryRoot = bookCategoryRoot;
	}
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
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}