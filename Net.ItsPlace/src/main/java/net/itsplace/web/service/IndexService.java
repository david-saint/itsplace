package net.itsplace.web.service;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;

import org.springframework.dao.DataAccessException;

public interface IndexService {
	/**
	 * 최근등록된 place list
	 * @param limit 갯수
	 * @return
	 */
	public List<Place> getRecentPlaceList(int limit);
	public List<PlaceEvent> getRecentEventList(int limit);
}
