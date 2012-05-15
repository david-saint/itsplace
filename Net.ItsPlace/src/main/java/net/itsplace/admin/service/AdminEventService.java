package net.itsplace.admin.service;

import java.util.List;

import net.itsplace.domain.PlaceEvent;

import org.springframework.dao.DataAccessException;

public interface AdminEventService {
	public List<PlaceEvent> getPlaceEventList(int fid);
	public void savePlaceEvent(PlaceEvent placeEvent);
	public void editPlaceEvent(PlaceEvent placeEvent);
}
