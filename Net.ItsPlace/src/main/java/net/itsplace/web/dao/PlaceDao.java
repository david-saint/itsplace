package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;

import org.springframework.dao.DataAccessException;


public interface PlaceDao {
	public Place getPlace(int fid) throws DataAccessException;
	public List<PlaceComment> getPlaceCommentList(Map<String, Object> param) throws DataAccessException;
	
}
