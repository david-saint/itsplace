package net.itsplace.web.service;

import java.util.List;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.admin.service.AdminBaseService;
import net.itsplace.admin.service.AdminBaseServiceImpl;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.web.dao.PlaceDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<PlaceComment> getPlaceCommentList(int fid) {
		return placeDao.getPlaceCommentList(fid);
	}
	

}
