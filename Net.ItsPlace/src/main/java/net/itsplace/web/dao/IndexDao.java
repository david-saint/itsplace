package net.itsplace.web.dao;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;

import org.springframework.dao.DataAccessException;

public interface IndexDao {
	public List<Place> getRecentPlaceList(int limit) throws DataAccessException;
	public List<PlaceEvent> getRecentEventList(int limit) throws DataAccessException;
}
