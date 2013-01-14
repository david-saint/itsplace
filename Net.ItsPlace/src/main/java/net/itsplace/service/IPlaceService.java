package net.itsplace.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;

public interface IPlaceService {
	/* 가맹점을 검색하여 리스트로 보여준다*/
	//public List<Place>  getPlaceList(Map<String, Object> param);
	public Place getPlace(int fid); 
	/**
	 *  가맹점 승인시 QR코드 생성한다
	 * */
	public void enablePlace(int fid); 
	/* 가맹점 승인취소*/
	public void disablePlace(int fid);
	/* 가맹점 수정*/
	public void editPlace(Place place);
	public int savePlace(Place place);
	public String getMcode(int fid);
	DataTable<Place> findPlaceList(JpaPaging paging, Boolean isDelete);
	public boolean editAuthCode(Authcode authcode);
}
