package net.itsplace.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.StampType;

public interface StampBaseService {
	/* 스탬프 타입목록을 가져온다 */
	
	public List<StampType> getStampTypeListAll();
	public void saveStampType(StampType stampType);	
	public void editStampType(StampType stampType);
	public StampType getStampType(int sid);
	public void deleteStampType(int sid);
	public void restoreStampType(int sid);
	
	/* 가맹점 스탬프 타입 가져오기 */
	public List<PlaceStamp> getPlaceStampAll(int fid);
	
	/*가맹점 스탬프 등록 및 수*/
	public void savePlaceStamp(PlaceStamp placeStamp) ;	
	public void editPlaceStamp(PlaceStamp placeStamp) ;
	public PlaceStamp getPlaceStamp(int stampid);
	public void deletePlaceStamp(PlaceStamp placeStamp);
	public void restorePlaceStamp(PlaceStamp placeStamp);
	DataTable<StampType> getStampTypeList(JpaPaging paging, Boolean isAuth);
}
