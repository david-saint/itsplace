package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.UserInfo;

public interface RentalRepository extends JpaRepository<BookRental, Long> {
	//Rental findById(int Id);
	List<BookRental> findByReturnDateIsNull();
	
	@Query("SELECT R FROM BookRental R WHERE R.id = ?1")
	BookRental findById(long id);
	
	@Query("select R from BookRental R join R.bookInfo B Where R.returnDate is Null And B.isbn = ?1")
	List<BookRental> findByIsbn(String isbn);
	
	List<BookRental> findByBookInfoAndReturnDateIsNull(BookInfo book);
	
	List<BookRental> findByUserInfoAndReturnDateIsNull(UserInfo userInfo);
	
}
