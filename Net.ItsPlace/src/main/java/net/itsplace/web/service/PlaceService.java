package net.itsplace.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;

public interface PlaceService {
	public Place getPlace(int fid);
	public List<PlaceComment> getPlaceCommentList(Map<String, Object> param) ;

}
