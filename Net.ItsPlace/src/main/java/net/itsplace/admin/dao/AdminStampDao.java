package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.StampType;
import net.itsplace.user.User;

import org.springframework.dao.DataAccessException;


public interface AdminStampDao {

	/* 스탬프 타입목록을 가져온다 */
	public List<StampType> getStampTypeList(Map<String, Object> param) throws DataAccessException;
	public List<StampType> getStampTypeListAll() throws DataAccessException;
	public void saveStampType(StampType stampType) throws DataAccessException;	
	public void editStampType(StampType stampType) throws DataAccessException;
	public StampType getStampType(int sid) throws DataAccessException;
	
	public void deleteStampType(int sid) throws DataAccessException;	
	public void restoreStampType(int sid) throws DataAccessException;
	
	/* 가맹점 스탬프 타입 가져오기 */
	public List<PlaceStamp> getPlaceStampAll(int fid) throws DataAccessException;
	
	/*가맹점 스탬프 등록 및 수정 */
	public void savePlaceStamp(PlaceStamp placeStamp) throws DataAccessException;	
	public void editPlaceStamp(PlaceStamp placeStamp) throws DataAccessException;
	public PlaceStamp getPlaceStamp(int stampid) throws DataAccessException;
}
