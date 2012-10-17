package com.mincoms.book.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mincoms.book.domain.dto.DtoRentalStatics;

public interface StaticsDao {

	public List<DtoRentalStatics> getRentalStatics(Map<String, Object> param) throws DataAccessException ;
	public long getRentalStaticsCount(Map<String, Object> param) throws DataAccessException ;
}
