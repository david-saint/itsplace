package net.itsplace.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;

import com.mysema.query.types.Predicate;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;

public interface PlaceService {
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
	/**
	 * 데이타그리드 가맹점 검색 목록
	 * @param paging
	 * @param isDelete
	 * @return
	 */
	DataTable<Place> findPlaceList(JpaPaging paging, Boolean isAuth);
	public boolean editAuthCode(Authcode authcode);

	/**
	 * 쵝근 등록된 가맹점 목록
	 * @param limit 객수
	 * @return
	 */
	public List<Place> findByRecentPalces(int limit);
	/**
	 * 
	 * @param predicate
	 * @param paging
	 * @return
	 */
	
	public Page<Place> findByAll(Predicate predicate, JpaPaging paging);
	/**
	 * 가맹점 검색(노페이징)
	 * @param predicate
	 * @return
	 */
	public List<Place> findByAll(Predicate predicate);
}
