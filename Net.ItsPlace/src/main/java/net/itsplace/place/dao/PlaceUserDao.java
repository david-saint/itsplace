package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceUser;
import net.itsplace.user.User;

import org.springframework.dao.DataAccessException;


public interface PlaceUserDao {
	/*가맹점 직원의   가맹점 리스트 */
	public List<PlaceUser> getPlaceListByEmail(String email) throws DataAccessException;
	/*가맹점 점장의 가맹점 리스트 */
	public List<Place> getFranchiserListByEmail(String email) throws DataAccessException;
	/* 가맹점 직원리스트 */
	public List<PlaceUser> getPlaceUserList(Map<String, Object> param) throws DataAccessException;
	/* 가맹점 직원등록  */	
	public void savePlaceUser(PlaceUser placeUser)  throws DataAccessException;	
	/* 가맹점 직원삭제 */
	public void deletePlaceUser(int uid)  throws DataAccessException;
	
	public PlaceUser getPlaceUser(PlaceUser placeUser) throws DataAccessException;
	
}
