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
	
	public BookRental findById(long id);
	
	/**
	 *  <b>도서반납 </b>
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
	
}
