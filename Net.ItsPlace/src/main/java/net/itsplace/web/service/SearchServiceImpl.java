package net.itsplace.web.service;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
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
		//System.out.println("기본로케일"+Locale.getDefault());
		if(Locale.getDefault().equals("ko")){
			System.out.println("기본로케일"+Locale.getDefault());
			return searchDao.getPlaceList(param);
		}else{
			System.out.println("로케일"+Locale.getDefault());
			List<Place> list = searchDao.getPlaceList(param);
			for(int i=0;i<list.size();i++){
				Place place =list.get(i);
				place.setFname(place.getEname());
				list.set(i, place);
			}
			return list;
		}
		
	}

	@Override
	public List<PlaceEvent> getPlaceEventList(Map<String, Object> param) {
		return searchDao.getPlaceEventList(param);
	}

	@Override
	public List<Place> getPlaceListByTile(Map<String, Object> param) {
		String locale = "ko";
		if(locale.equals(Locale.getDefault().toString())){
			System.out.println("기본로케일"+Locale.getDefault().toString());
			return searchDao.getPlaceListByTile(param);
		}else{
			System.out.println("로케일"+Locale.getDefault());
			//System.out.println("로케일"+Locale.getDefault().toLanguageTag());
			System.out.println("로케일"+Locale.getDefault().toString());
			System.out.println("로케일"+Locale.getDefault().getDisplayLanguage());
			List<Place> list = searchDao.getPlaceListByTile(param);
			for(int i=0;i<list.size();i++){
				Place place =list.get(i);
				place.setFname(place.getEname());
				list.set(i, place);
			}
			return list;
		}
		
	}
}
