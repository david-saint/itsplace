package com.mincoms.book.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.mincoms.validation.IntTypeMiss;

@Entity(name="BookCategorySub")
public class BookCategorySub {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String name;
	private int dispSeq;
	private boolean isDeleted;
	

	@ManyToOne
	@JoinColumn(name="root_id")	
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
