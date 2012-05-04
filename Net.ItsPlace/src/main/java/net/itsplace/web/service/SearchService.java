package net.itsplace.web.service;

import java.util.List;

import net.itsplace.domain.Place;
import net.sf.json.JSONArray;

public interface SearchService {
	
	/**
	 * 가맹점 정보 가져오기
	 * @param p
	 * @return
	 */
	public List<Place> placeInfo(Place p);

}
