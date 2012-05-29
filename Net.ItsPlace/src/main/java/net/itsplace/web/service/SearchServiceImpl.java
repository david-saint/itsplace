package net.itsplace.web.service;


import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.web.dao.SearchDao;
import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SearchService")
public class SearchServiceImpl implements SearchService{
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Autowired
	private SearchDao searchDao;
	
	/**
	 * 가맹점 정보 가져오기
	 * @param p
	 * @return
	 */
	public List<Place>  placeInfo(Place p)
	{
		return searchDao.placeInfo(p);
	}

	@Override
	public List<Place> getPlaceList(Map<String, Object> param) {
		return searchDao.getPlaceList(param);
	}
}
