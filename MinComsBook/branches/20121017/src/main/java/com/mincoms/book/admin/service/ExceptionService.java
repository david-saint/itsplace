package com.mincoms.book.admin.service;

import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.Paging;

public interface ExceptionService {

	public DataTable<AppException> getExceptionList(Paging page, AppException exception); 
		
	
}
