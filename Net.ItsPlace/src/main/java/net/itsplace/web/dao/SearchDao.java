package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Place;
import net.sf.json.JSONArray;

public interface SearchDao {
	/**
	 * 가맹점 정보 가져오기
	 * @param p
	 * @return
	 */
	public List<Place>  placeInfo(Place p);
	public List<Place>  getPlaceList(Map<String, Object> param) throws DataAccessException;
}
