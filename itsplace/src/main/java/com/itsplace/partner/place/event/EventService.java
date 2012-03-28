package com.itsplace.partner.place.event;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface EventService {
	public List<Event> getEventList(Map<String, Object> param);
	public Event getEvent(String eid);
	public int saveEvent(Event event);
	public void updateEvent(Event event);
}
