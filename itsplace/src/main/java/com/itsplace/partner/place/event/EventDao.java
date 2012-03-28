package com.itsplace.partner.place.event;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myplace.common.Notice;

public interface EventDao {
	public List<Event> getEventList(Map<String, Object> param) throws DataAccessException;
	public Event getEvent(String eid) throws DataAccessException;
	public int saveEvent(Event event) throws DataAccessException;
	public void updateEvent(Event event) throws DataAccessException;
}
