package com.mincoms.book.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.dto.DtoBookInfo;

public interface BookDao {
	public List<DtoBookInfo> getBookInfoReservationList(Map<String, Object> param) throws DataAccessException ;
	public long getBookInfoReservationListCount(Map<String, Object> param) throws DataAccessException ;
}
