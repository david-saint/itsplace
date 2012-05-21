package net.itsplace.web.service;


import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.web.dao.StampDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StampService")
public class StampServiceImpl implements StampService{
	private static final Logger logger = LoggerFactory.getLogger(StampServiceImpl.class);
	
	@Autowired
	private StampDao stampDao;

	@Override
	public List<Place> getPlaceStampedList(String email) {
		return stampDao.getPlaceStampedList(email);
	}

	@Override
	public List<PlaceStamp> getStampedList(Map<String, Object> param) {
		return stampDao.getStampedList(param);
	}
}
