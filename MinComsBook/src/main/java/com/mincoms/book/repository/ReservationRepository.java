package com.mincoms.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.BookReservation;

public interface ReservationRepository extends JpaRepository<BookReservation, Long>, QueryDslPredicateExecutor<BookReservation>  {
	/**
	 * <b>도서 예약/예약취소 리스트  {@link BookInfo}</b>
	 * 예약된 도서 또는 예약취소된 도서(예약은 했지만 대출이 안된 도서중에서)
	 * <pre>
	 * <b>History:</b>
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param reservationStatus  예약/예약취소 여부
	 * @return List<BookReservation> 예약리스트
	 * @throws Exception 
	 * @see 
	 */
	List<BookReservation> findByIsCanceledAndBookRentalIsNull(Boolean IsCanceled);
	
	
	List<BookReservation> findAll(Specification<BookReservation> spec);
	
	/**
	 * <b>사용자가 예약중인 도서 찾기<b>	
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn  
	 * @param userId  
	 * @return List<BookReservation> 예약리스트
	 * @throws Exception 
	 * @see 
	 */
	@Query("select R from BookReservation R  Where R.bookInfo.isbn = ?1 AND R.userInfo.userId = ?2  AND R.isCanceled = 0 AND R.bookRental is Null")
	BookReservation findByReservationBook(String isbn, int userId);
	
	/**
	 * <b>사용자가 예약한 도서 모곩<b>	
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param userId  
	 * @return List<BookReservation> 예약리스트
	 * @throws Exception 
	 * @see 
	 */
	@Query("select R from BookReservation R  Where R.userInfo.userId = ?1 AND R.isCanceled = 0 AND R.bookRental is Null")
	List<BookReservation> findByReservationBooks(int userId);
	
	BookReservation findById(int id);
}
