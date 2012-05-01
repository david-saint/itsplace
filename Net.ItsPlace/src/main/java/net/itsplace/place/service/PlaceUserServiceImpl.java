package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceUser;
import net.itsplace.place.dao.PlaceUserDao;
import net.itsplace.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("placeUserService")
public class PlaceUserServiceImpl implements PlaceUserService {

	private static final Logger logger = LoggerFactory.getLogger(PlaceUserServiceImpl.class);
	
	@Autowired
	private PlaceUserDao placeUserDao;

	@Override
	public List<PlaceUser> getPlaceUserList(Map<String, Object> param) {
		return placeUserDao.getPlaceUserList(param);
	}

	@Override
	public void savePlaceUser(PlaceUser placeUser) {
		if(placeUserDao.getPlaceUser(placeUser)==null){
			placeUserDao.savePlaceUser(placeUser);
		}
		
	}

	@Override
	public void deletePlaceUser(int uid) {
		placeUserDao.deletePlaceUser(uid);
	}

	@Override
	public List<PlaceUser> getPlaceListByEmail(String email) {
		return placeUserDao.getPlaceListByEmail(email);
	}

	@Override
	public List<Place> getFranchiserListByEmail(String email) {
		return placeUserDao.getFranchiserListByEmail(email);
	}
	
}
