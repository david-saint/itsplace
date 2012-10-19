package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.UserInfo;

public interface RentalRepository extends JpaRepository<BookRental, Long> {
	
	@Query("SELECT R FROM BookRental R WHERE R.id = ?1")
	BookRental findById(long id);
	
	@Query("SELECT R FROM BookRental R WHERE R.userInfo.userId = ?1 And R.bookInfo.isbn = ?2 And R.returnDate is Null" )
	List<BookRental> findByRentalId(int userId, String isbn);
	
	
	@Query("select R from BookRental R Where R.bookInfo.isbn = ?1 And R.returnDate is Null")
	List<BookRental> findByIsbn(String isbn);
	
	List<BookRental> findByBookInfoAndReturnDateIsNull(BookInfo book);
	
	List<BookRental> findByUserInfoAndReturnDateIsNull(UserInfo userInfo);
	
	List<BookRental> findByReturnDateIsNull();
	
	List<BookRental> findByUserInfoAndReturnDateIsNotNull(UserInfo userInfo);

}
