package net.itsplace.web.dao;

import java.util.List;

import net.itsplace.domain.Place;
import net.sf.json.JSONArray;

public interface SearchDao {
	/**
	 * 가맹점 정보 가져오기
	 * @param p
	 * @return
	 */
	public List<Place>  placeInfo(Place p);
}
