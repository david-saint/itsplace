package net.itsplace.web.service;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.repository.PlacePredicate;
import net.itsplace.repository.PlaceRepo;
import net.itsplace.user.UserServiceImpl;
import net.itsplace.web.dao.IndexDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("IndexService")
public class IndexServiceImpl implements IndexService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IndexDao indexDao;
	@Autowired
	PlaceRepo placeRepo;
	
	@Override
	public List<Place> getRecentPlaceList(int limit) {
		
		return indexDao.getRecentPlaceList(limit);
	}

	@Override
	public List<PlaceEvent> getRecentEventList(int limit) {
		return indexDao.getRecentEventList(limit);
	}
}
