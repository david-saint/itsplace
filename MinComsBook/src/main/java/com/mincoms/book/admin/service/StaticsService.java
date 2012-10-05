package com.mincoms.book.admin.service;

import com.mincoms.book.domain.DataTable;
import com.mincoms.book.domain.Paging;

public interface StaticsService {
	public DataTable findRentalStatics(Paging paging);
}
