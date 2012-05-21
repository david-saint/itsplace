package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.PlaceComment;

import org.springframework.dao.DataAccessException;

public interface PlaceCommentDao {
	public List<PlaceComment> getPlaceCommentList(Map<String, Object> param) throws DataAccessException;

	public void savePlaceComment(PlaceComment placeComment) throws DataAccessException;
	public PlaceComment getPlaceComment(int cid) throws DataAccessException;
	public void deletePlaceComment(int cid) throws DataAccessException;
}
