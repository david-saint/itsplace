package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.user.User;

import org.springframework.dao.DataAccessException;

public interface AdminEventDao {

	public List<PlaceEvent> getPlaceEventList(int fid) throws DataAccessException;
	public void savePlaceEvent(PlaceEvent placeEvent) throws DataAccessException;
	public void editPlaceEvent(PlaceEvent placeEvent) throws DataAccessException;
	public void deletePlaceEvent(int eid) throws DataAccessException;
	public List<PlaceEvent> getPlaceEventList(Map<String, Object> param) throws DataAccessException;
}
