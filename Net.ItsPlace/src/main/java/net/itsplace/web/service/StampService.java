package net.itsplace.web.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;

import org.springframework.dao.DataAccessException;

public interface StampService {
	/*적립된 가맹점 리스트*/
	public  List<Place> getPlaceStampedList(String email);
	/*적립된  스탬프 리스트 가맹점별*/
	public  List<PlaceStamp> getStampedList(Map<String, Object> param) ;
}
