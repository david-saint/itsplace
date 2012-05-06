package net.itsplace.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import net.itsplace.admin.dao.AdminMediaDao;
import net.itsplace.admin.dao.AdminPlaceDao;
import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;

@Service("AdminMediaService")
public class AdminMediaSeviceImpl implements AdminMediaService{
	private static final Logger logger = LoggerFactory.getLogger(AdminMediaSeviceImpl.class);
	
	@Autowired
	private AdminMediaDao adminMediaDao;

	@Override
	public void savePlaceMedia(Pmedia media) {
		adminMediaDao.savePlaceMedia(media);
	}

	@Override
	public void updatePlaceImage(Place place) throws DataAccessException {
		adminMediaDao.updatePlaceImage(place);
	}

	@Override
	public void updatePlaceMedia(Pmedia media) throws DataAccessException {
		adminMediaDao.updatePlaceMedia(media);
	}
}
