package net.itsplace.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;
import net.itsplace.domain.Stamp;

public interface PlaceServicew {
	public Place getPlace(int fid);

	public List<PlaceEvent> getPlaceEventListByPlace(int fid) throws DataAccessException;
	public List<Stamp> getPlaceStampListByPlace(int fid) throws DataAccessException;
	public List<PlaceMenu> getPlaceMenuList(int fid) throws DataAccessException;
	public List<PlaceReview> getPlaceReviewList(int fid) throws DataAccessException;
	public List<PlaceMedia> getPlaceMediaListt(int fid) throws DataAccessException;
}
