package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;
import net.itsplace.domain.Stamp;

import org.springframework.dao.DataAccessException;


public interface PlaceDao {
	public Place getPlace(int fid) throws DataAccessException;
	public List<PlaceComment> getPlaceCommentList(Map<String, Object> param) throws DataAccessException;
	
	public List<PlaceEvent> getPlaceEventListByPlace(int fid) throws DataAccessException;
	public List<Stamp> getPlaceStampListByPlace(int fid) throws DataAccessException;
	public List<PlaceMenu> getPlaceMenuList(int fid) throws DataAccessException;
	public List<PlaceReview> getPlaceReviewList(int fid) throws DataAccessException;
	public List<PlaceMedia> getPlaceMediaListt(int fid) throws DataAccessException;
	
	
}
