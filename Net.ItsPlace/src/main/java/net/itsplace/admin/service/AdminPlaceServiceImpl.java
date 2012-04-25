package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminPlaceDao;
import net.itsplace.domain.Place;
import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("AdminPlaceService")
public class AdminPlaceServiceImpl implements AdminPlaceService{
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceServiceImpl.class);
	
	@Autowired
	private AdminPlaceDao adminPlaceDao;
	
	@Override
	public List<Place> getPlaceList(Map<String, Object> param)
			throws DataAccessException {
		return adminPlaceDao.getPlaceList(param);
	}

	@Override
	public void enablePlace(int fid) throws DataAccessException {
		adminPlaceDao.enablePlace(fid);
	}

	@Override
	public void disablePlace(int fid) throws DataAccessException {
		adminPlaceDao.disablePlace(fid);
	}

	@Override
	public void editPlace(Place place) throws DataAccessException {
		adminPlaceDao.editPlace(place);
		
	}

	
}
