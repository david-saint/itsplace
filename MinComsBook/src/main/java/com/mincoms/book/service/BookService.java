package com.mincoms.book.service;

import org.springframework.dao.DataAccessException;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.UserInfo;

public interface BookService {
	
	/**
	 * <b>도서 정보 저장 {@link BookInfo}</b>
	 * <pre>
	 * <b>History:</b>
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param BookInfo 도서 도메인
	 * @return boolean 저장 여부 
	 * @throws Exception 
	 * @see 
	 */
	BookInfo save(BookInfo book) throws DataAccessException;

	DataTable findBookList(Paging page);
	
	BookInfo findByIsbn(String isbn);
	
	/**
	 * <b>도서 대출 가능 여부  {@link BookInfo}</b><br>
	 * 대출가능 True, 불가 False<br>
	 * 도사 총수량과 대출도서수량 + 예약중인 도서수량이 도서 총수량보다 작아야 한다
	 * <pre>
	 * <b>History:</b>
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn  ISBN 코드
	 * @return boolean 대서 대출 가능 여부 
	 * @throws Exception 
	 * @see 
	 */
	public boolean isRental(String isbn);	
	
	/**
	 * 도서 목록 (예약정보 포함)
	 * QueryDSL 적용한 메소드
	 */
	@Deprecated
	public DataTable findReservationGroupByBooks(Paging page);
	/**
	 * 도서 목록 (예약정보 포함)
	 * Ibatis
	 */
	public DataTable getReservationGroupByBooks(Paging page);
}
