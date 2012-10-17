package com.mincoms.book.domain;

import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.hibernate.validator.constraints.NotEmpty;

import com.mincoms.validation.UniqueIsbn;
/**
 * <b>도서 도메인</b> <br />
 * 
 * <pre>
 * <b>History:</b>
 *     version 0.1, 2012.8.24 
 * </pre>
 * @author 김동훈
 * @version 1.0, 2011. 8. 31.
 * @since 2012. 8. 24
 * @see <a href="http://docs.jboss.org/hibernate/validator/4.3/reference/en-US/html/">하이버네이트 유효성  검사 문서</a> 
 */
@Entity(name="BookInfo")
public class BookInfo {
	/*
	 * 도서등록 Validation 그룹
	 */
	public interface AddBook {}
	/*
	 * 도서tnwjd Validation 그룹
	 */
	public interface EditBook {}
	
	@NotEmpty(groups={AddBook.class})
	@UniqueIsbn(groups={AddBook.class})
	@Id	
	private String isbn;
	
	@ManyToOne
	@JoinColumn(name="category")		
	private BookCategory bookCategory;
	
	@NotEmpty(groups={AddBook.class}, message="도서제목을 입력하세요")
	private String title;
	@NotEmpty(groups={AddBook.class}, message="저자를 입력하세요")
	private String authors;
	
	private String publisher;
	
	private String publishedDate;
	
	private String thumbnail;
	
	//@Column(columnDefinition="VARCHAR(255) COMMENT '저자'")
			//@Column(columnDefinition="VARCHAR(255) COMMENT '출판사'")
		//@Column(columnDefinition="VARCHAR(255) COMMENT '출판일자'")
	//@Pattern(regexp="^[0-9]*$",message="miss match",groups={AddBook.class})
	/*@IntTypeMiss(groups={AddBook.class})
	@NotNull(message="dddddd",groups={AddBook.class})*/
//	@IntTypeMiss(groups={AddBook.class})
//	@Min(value=0,groups={AddBook.class})
	@NotNull( groups={AddBook.class},message="도서 총 수량을 입력하세요")
//	@Column(columnDefinition="INT(2) COMMENT '총수량'")
	private Integer count;
	
	private boolean isDeleted;
	
	
	private Date regDate;
	

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="isbn")		
	private List<BookReservation> bookReservation;
	//spring data에서 모든 도메인을 가져오기 때문에 Json 변환되면서 오류가 생김 따라서 get메소드를 주석처리함
	/*public List<BookReservation> getBookReservation() {
		return bookReservation;
	}*/
	public void setBookReservation(List<BookReservation> bookReservation) {
		this.bookReservation = bookReservation;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}	

	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	public BookCategory getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
