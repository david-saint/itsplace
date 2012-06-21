package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.PlaceReview;

import org.springframework.dao.DataAccessException;

public interface PlaceReviewDao {
	public void savePlaceReview(PlaceReview PlaceReview) throws DataAccessException;
	public void editPlaceReview(PlaceReview PlaceReview) throws DataAccessException;
	public void deletePlaceReview(int rid)  throws DataAccessException;
	public void recoveryPlaceReview(int rid)  throws DataAccessException;
	public PlaceReview getPlaceReview(int rid)  throws DataAccessException;
	public List<PlaceReview> getPlaceReviewList(Map<String, Object> param)  throws DataAccessException;
	public List<PlaceReview> getPlaceReviewListAll(int fid)  throws DataAccessException;
}
