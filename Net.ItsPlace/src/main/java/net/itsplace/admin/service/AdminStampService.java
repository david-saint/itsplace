package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.StampType;

public interface AdminStampService {
	/* 스탬프 타입목록을 가져온다 */
	public List<StampType> getStampTypeList(Map<String, Object> param);
	public List<StampType> getStampTypeListAll();
	public void saveStampType(StampType stampType);	
	public void editStampType(StampType stampType);
	public StampType getStampType(int sid);
	public void deleteStampType(int sid);
	public void restoreStampType(int sid);
	
	/* 가맹점 스탬프 타입 가져오기 */
	public List<PlaceStamp> getPlaceStampAll(int fid) throws DataAccessException;
}
