package net.itsplace.service;


import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.repository.StampRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StampService")
public class StampServiceImpl implements StampService{
	private static final Logger logger = LoggerFactory.getLogger(StampServiceImpl.class);
	
	@Autowired
	StampRepository stampRepo;
	@Override
	public List<Place> getPlaceStampedList(String email) {
		return null;//stampDao.getPlaceStampedList(email);
	}

	@Override
	public List<PlaceStamp> getStampedList(Map<String, Object> param) {
		return null;//stampDao.getStampedList(param);
	}

	@Override
	public List<PlaceStamp> getPlaceStampListByEmail(String email) {
		return null;//stampDao.getPlaceStampListByEmail(email);
	}
}
