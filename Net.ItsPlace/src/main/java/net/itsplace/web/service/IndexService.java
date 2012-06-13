package net.itsplace.web.service;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;

import org.springframework.dao.DataAccessException;

public interface IndexService {
	public List<Place> getRecentPlaceList(int limit);
	public List<PlaceEvent> getRecentEventList(int limit);
}
