package com.mincoms.book.admin.service;

import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.dto.DtoBookRestriction;
import com.mincoms.book.domain.vo.VoBookRestriction;

public interface RestrictionService {

	public void save(DtoBookRestriction dtoBookRestriction);
	public JsonResponse save(BookRestriction BookRestriction);
	public DataTable<VoBookRestriction> getRestrictionUserList(Paging page, Boolean isSolved);
	public BookRestriction findByBookRestriction(int id);
	
}
