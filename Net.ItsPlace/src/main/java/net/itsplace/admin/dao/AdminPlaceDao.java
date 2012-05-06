package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;

public interface AdminPlaceDao {

	/* 가맹점을 검색하여 리스트로 보여준다*/
	public List<Place>  getPlaceList(Map<String, Object> param) throws DataAccessException;
	public Place getPlace(int fid) throws DataAccessException;
	/* 가맹점 승인*/
	public void enablePlace(int fid) throws DataAccessException;
	/* 가맹점 승인취소*/
	public void disablePlace(int fid) throws DataAccessException;
	
	/* 가맹점 수정*/
	public void editPlace(Place place) throws DataAccessException;
	
	
}
