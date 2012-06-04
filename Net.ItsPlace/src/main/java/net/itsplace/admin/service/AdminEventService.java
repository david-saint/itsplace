package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceEvent;

import org.springframework.dao.DataAccessException;

public interface AdminEventService {
	public List<PlaceEvent> getPlaceEventList(int fid);
	public PlaceEvent getPlaceEvent(int eid) ;
	public void savePlaceEvent(PlaceEvent placeEvent);
	public void editPlaceEvent(PlaceEvent placeEvent);
	public void deletePlaceEvent(int eid);;
	public DataTable getPlaceEventList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch,int fid);
	public DataTable getPlaceEventListAll(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch);
}
