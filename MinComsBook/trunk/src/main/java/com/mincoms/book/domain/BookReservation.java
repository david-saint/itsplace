package com.mincoms.book.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="BookReservation")
public class BookReservation {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date reservationDate;
	
	private boolean isCanceled;
	
	@ManyToOne
	@JoinColumn(name="bookRental_id")
	private BookRental bookRental;
	@ManyToOne
	@JoinColumn(name="isbn")
	private BookInfo bookInfo;
	@ManyToOne
	@JoinColumn(name="userId")
	private UserInfo userInfo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public BookRental getBookRental() {
		return bookRental;
	}
	public void setBookRental(BookRental bookRental) {
		this.bookRental = bookRental;
	}
	public boolean getTsCanceled() {
		return isCanceled;
	}
	public void setIsCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}
	

	
}
