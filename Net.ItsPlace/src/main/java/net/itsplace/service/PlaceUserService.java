package net.itsplace.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceUser;
import net.itsplace.domain.User;

import org.springframework.dao.DataAccessException;

public interface PlaceUserService {

	public List<PlaceUser> getPlaceListByEmail(String email) ;
	
	/* 가맹점 직원등록  */	
	public void savePlaceUser(PlaceUser placeUser);	
	/* 가맹점 직원삭제 */
	public void deletePlaceUser(int uid);
	DataTable<PlaceUser> findPlaceUserList(JpaPaging paging,  int fid);
}
