package net.itsplace.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;

import org.springframework.dao.DataAccessException;

public interface IPlaceEventService {
	public List<PlaceEvent> getPlaceEventList(int fid);
	public PlaceEvent getPlaceEvent(int eid) ;
	public void savePlaceEvent(PlaceEvent placeEvent);
	public void editPlaceEvent(PlaceEvent placeEvent);
	public void deletePlaceEvent(int eid);;
	//public DataTable getPlaceEventList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch,int fid);
	//public DataTable getPlaceEventListAll(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch);
	DataTable<PlaceEvent> findPlaceEventist(JpaPaging paging, Boolean isDeleted);
	DataTable<PlaceEvent> findByPlace(JpaPaging paging, int fid);
}
