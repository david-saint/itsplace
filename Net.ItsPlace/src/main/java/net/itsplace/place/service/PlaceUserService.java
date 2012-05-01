package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceUser;
import net.itsplace.user.User;

import org.springframework.dao.DataAccessException;

public interface PlaceUserService {

	public List<PlaceUser> getPlaceListByEmail(String email) ;
	public List<Place> getFranchiserListByEmail(String email);
	
	/* 가맹점 직원리스트 */
	public List<PlaceUser> getPlaceUserList(Map<String, Object> param) ;
	/* 가맹점 직원등록  */	
	public void savePlaceUser(PlaceUser placeUser);	
	/* 가맹점 직원삭제 */
	public void deletePlaceUser(int uid);
}
