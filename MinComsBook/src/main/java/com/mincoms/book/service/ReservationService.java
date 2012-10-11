package com.mincoms.book.service;

import java.util.List;

import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;

public interface ReservationService {

	public DataTable findReservationBooks(Paging page);
	public List<BookReservation> findByReservationBooks(int userId);
	
	
	/**
	 * <b>도서예약 </b> <br />
	 * 도서가 대출불가능 할 겨우에만(!!!) 예약이 가능하며 대출정지자는 예약을 못한다.
	 * 예약을 하게되면 타회원은 대툴이 불가능하다 <br>
	 * 예약된 도서가 대출 가능하게 된 경우 1일 경과후 대출이 발생하지 않았을겨우 예약은 자동 취소된다
	 * 
	 * <pre>
	 * <b>History:</b>
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param ISBN 
	 * @param Day  대출 일수
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	public JsonResponse reservation(String isbn);
	public JsonResponse reservationCancel(int id);
	
	/**
	 * <b>현재 예약중인 도서목록 </b>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn 
	 * @return List<BookReservation>
	 * @see 
	 */
	public BookReservation findByReservationBook(String isbn,int userId);
	/**
	 * <b>현재 예약중인 도서목록 </b>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn 
	 * @return List<BookReservation>
	 * @see 
	 */
	public List<BookReservation> findByReservationBook(String isbn);
}
