package com.itsplace.partner.place.event;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("EventService")
public class EventserviceImpl implements EventService{


	private static final Logger logger = LoggerFactory.getLogger(EventserviceImpl.class);
	
	

	@Autowired
	private EventDao eventDao;
	
	@Override
	public Event getEvent(String eid) {
		return eventDao.getEvent(eid);
	}

	@Override
	public int saveEvent(Event event) {
		return eventDao.saveEvent(event);
	}

	@Override
	public void updateEvent(Event event) {
		eventDao.updateEvent(event);
		
	}
	@Override
	public List<Event> getEventList(Map<String, Object> param) {
		return eventDao.getEventList(param);
	}

}
