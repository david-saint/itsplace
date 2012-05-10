package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;

public interface PlaceStampService {

	public boolean saveStamp(Stamp stamp,String authCode) ;
	public boolean burnStamp(Stamp stamp,String authCode) ;	
	
	public DataTable<Stamp> getPlaceStampUserList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch);
	/* 회원 스탬프 타입 리스트  */
	public List<PlaceStamp> getPlaceStampListByEmail(Map<String, Object> param);
	public List<Stamp> getPlaceStampedListByEmail(Map<String, Object> param);
	public List<PlaceStamp> getPlaceStampList(Map<String, Object> param) ;
}
