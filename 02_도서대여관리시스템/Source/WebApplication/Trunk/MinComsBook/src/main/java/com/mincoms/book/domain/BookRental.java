package com.mincoms.book.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.mincoms.book.domain.BookInfo.AddBook;

@Entity(name="BookRental")
public class BookRental {
	public interface AddRental {}
	public interface EditRental {}
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="isbn")
	private BookInfo bookInfo;
	
	@ManyToOne()
	@JoinColumn(name="userId")
	private UserInfo userInfo;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date returnDate;
	
	private String extendReason;
	
	//@Column(columnDefinition="int DEFAULT 0",nullable=false) 디폴트값 작동안함 왜 안되지
	private Integer extendcount;	
	public Integer getExtendcount() {
		return extendcount;
	}
	public void setExtendcount(Integer extendcount) {
		this.extendcount = extendcount;
	}
	public String getExtendReason() {
		return extendReason;
	}
	public void setExtendReason(String extendReason) {
		this.extendReason = extendReason;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
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
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
	
}
