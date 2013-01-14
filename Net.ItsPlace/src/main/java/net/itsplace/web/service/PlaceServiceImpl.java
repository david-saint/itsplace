package net.itsplace.web.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;
import net.itsplace.domain.Stamp;
import net.itsplace.service.IBaseService;
import net.itsplace.service.BaseService;
import net.itsplace.util.DurationFromNow;
import net.itsplace.web.dao.PlaceDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("PlaceService")
public class PlaceServiceImpl  implements PlaceService{
	private static final Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);
	
	@Autowired
	private PlaceDao placeDao;

	@Override
	public Place getPlace(int fid) {
		return placeDao.getPlace(fid);
	}

	@Override
	public List<PlaceComment> getPlaceCommentList(Map<String, Object> param) {
		List<PlaceComment> placeCommentList = placeDao.getPlaceCommentList(param);
		for(int i=0;i<placeCommentList.size();i++){
			PlaceComment placeComment = placeCommentList.get(i);
			
			placeComment.setPrettyDate(DurationFromNow.getTimeDiffLabel(placeComment.getSaveDate()));
			
			placeCommentList.set(i, placeComment);
		}
		return placeCommentList;
	}

	@Override
	public List<PlaceEvent> getPlaceEventListByPlace(int fid)
			throws DataAccessException {
		return placeDao.getPlaceEventListByPlace(fid);
	}

	@Override
	public List<Stamp> getPlaceStampListByPlace(int fid)
			throws DataAccessException {
		return placeDao.getPlaceStampListByPlace(fid);
	}

	@Override
	public List<PlaceMenu> getPlaceMenuList(int fid) throws DataAccessException {
		return placeDao.getPlaceMenuList(fid);
	}

	@Override
	public List<PlaceReview> getPlaceReviewList(int fid)
			throws DataAccessException {
		return placeDao.getPlaceReviewList(fid);
	}

	@Override
	public List<PlaceMedia> getPlaceMediaListt(int fid)
			throws DataAccessException {
		return placeDao.getPlaceMediaListt(fid);
	}



}
