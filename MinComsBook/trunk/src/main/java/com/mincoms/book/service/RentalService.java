package com.mincoms.book.service;

import java.util.List;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.UserInfo;
@Validated
public interface RentalService {

	/**
	 * <b>도서 대출</b>
	 * 대출가능한 도서가 있는지 확인후 대출
	 * 대출하는 도서가 예약상태였다면 예약정보에 대출 도서 PK를 업데이트 한다.(예약완료를 의미함)
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param {@link UserInfo} 유저  도메인
	 * @return List BookRental 
	 * @throws Exception 
	 * @see 
	 */
	public JsonResponse saveRental(String isbn, int day,UserInfo userInfo);
	/**
	 * <b>사용자가 대출중인 도서대출정보 목록 </b>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param {@link UserInfo} 유저  도메인
	 * @return List BookRental 
	 * @throws Exception 
	 * @see 
	 */
	public List<BookRental> findByUserInfoAndReturnDateIsNull(UserInfo userInfo);
	/**
	 * <b>사용자 도서대출목록(History) </b>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param {@link UserInfo} 유저  도메인
	 * @return List BookRental 
	 * @throws Exception 
	 * @see 
	 */
	public List<BookRental> findByUserInfoAndReturnDateIsNotNull(UserInfo userInfo);
	/**
	 * <b>전체 도서대출목록(History) </b>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @return List BookRental 
	 * @throws Exception 
	 * @see 
	 */
	public List<BookRental> findByReturnDateIsNotNull();
	
	public BookRental findById(long id);
	
	/**
	 * <b>도서 반납 </b>
	 * 도서반납시 해당 도서가 예약이 되어있다면 24시간후에도 예약중이면 예약을 취소한다
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 도서대툴 id
	 * @param day 연기 일수
	 * @return JsonResponse
	 * @see 
	 */
	public JsonResponse returnBook(long id);
	/**
	 * <b>도서 대출 연기 </b>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 도서대툴 id
	 * @param day 연기 일수
	 * @return JsonResponse
	 * @see 
	 */
	public JsonResponse extendBook(long id, int day, String extendReason);
	
	/**
	 * <b>도서 대출 리스트 </b>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param isbn 
	 * @return List<BookRental>
	 * @see 
	 */
	public List<BookRental> findByIsbn(String isbn);
	
	public List<BookRental> findByRentalId(int userid, String isbn);
	
	
}
