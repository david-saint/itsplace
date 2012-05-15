package net.itsplace.admin.service;

import java.util.List;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.admin.dao.AdminEventDao;
import net.itsplace.domain.PlaceEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminEventService")
public class AdminEventServiceImpl implements AdminEventService{
	private static final Logger logger = LoggerFactory.getLogger(AdminEventServiceImpl.class);

	@Autowired
	private AdminEventDao adminEventeDao;
	
	@Override
	public List<PlaceEvent> getPlaceEventList(int fid) {
		return adminEventeDao.getPlaceEventList(fid);
	}

	@Override
	public void savePlaceEvent(PlaceEvent placeEvent) {
		adminEventeDao.savePlaceEvent(placeEvent);
	}

	@Override
	public void editPlaceEvent(PlaceEvent placeEvent) {
		adminEventeDao.editPlaceEvent(placeEvent);
	}
	

}
