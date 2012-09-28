package com.mincoms.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.validation.IntTypeMiss;

@Entity(name="BookCategory")
public class BookCategory {
	public interface AddBookCategory {}
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

	@ManyToOne
	@JoinColumn(name="sub_id")	
	private BookCategorySub bookCategorySub;
	
	@NotEmpty(groups={AddBookCategory.class}, message="도서제목을 입력하세요")    
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
