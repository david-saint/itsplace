package com.mincoms.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="BookCategory")
public class BookCategory {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotEmpty
	private int id;
	
	@ManyToOne
	@JoinColumn(name="rootid")	
	private BookCategoryRoot bookCategoryRoot;
	     
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
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public BookCategoryRoot getBookCategoryRoot() {
		return bookCategoryRoot;
	}
	public void setBookCategoryRoot(BookCategoryRoot bookCategoryRoot) {
		this.bookCategoryRoot = bookCategoryRoot;
	}
	
	
	
	
}
