package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.PlaceUser;
import net.itsplace.domain.Stamp;

import org.springframework.dao.DataAccessException;


public interface PlaceStampDao {


	public void saveStamp(Stamp stamp) throws DataAccessException;
	public void burnStamp(Stamp stamp) throws DataAccessException;	
	/* 가맴점 회원/비회원 리스트 */
	public List<Stamp> getPlaceStampUserList(Map<String, Object> param) throws DataAccessException;

	/* 회원 스탬프 타입 리스트  */
	public List<Stamp> getPlaceStampListByEmail(Map<String, Object> param) throws DataAccessException;
}
