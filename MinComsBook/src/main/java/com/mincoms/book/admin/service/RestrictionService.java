package com.mincoms.book.admin.service;

import java.util.List;

import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.domain.dto.DtoBookRestriction;
import com.mincoms.book.domain.vo.VoBookRestriction;

public interface RestrictionService {

	
	public void save(DtoBookRestriction dtoBookRestriction);
	public JsonResponse save(BookRestriction BookRestriction);
	public DataTable<VoBookRestriction> getRestrictionUserList(Paging page, Boolean isSolved);
	public BookRestriction findByBookRestriction(int id);
	public BookRestriction  findByUserInfoAndSolveDateIsNull(UserInfo userInfo);
	public JsonResponse isRestriction(UserInfo userInfo);
	public List<BookRestriction> findByUserInfo(int userId);
	
	
}
