package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminStampDao;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.StampType;
import net.itsplace.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("AdminStampService")
public class AdminStampServiceImpl implements AdminStampService{
	private static final Logger logger = LoggerFactory.getLogger(AdminStampServiceImpl.class);
	
	@Autowired
	private AdminStampDao adminStampDao;

	
	@Override
	public void saveStampType(StampType stampType){
		adminStampDao.saveStampType(stampType);
	}

	@Override
	public void editStampType(StampType stampType){
		adminStampDao.editStampType(stampType);
	}

	@Override
	public StampType getStampType(int sid) {
		return adminStampDao.getStampType(sid);
	}

	@Override
	public void deleteStampType(int sid) {
		adminStampDao.deleteStampType(sid);
	}

	@Override
	public void restoreStampType(int sid) {
		adminStampDao.restoreStampType(sid);		
	}

	@Override
	public List<StampType> getStampTypeListAll() {
		return adminStampDao.getStampTypeListAll();
	}

	@Override
	public List<StampType> getStampTypeList(Map<String, Object> param) {
		return adminStampDao.getStampTypeList(param);
	}

	@Override
	public List<PlaceStamp> getPlaceStampAll(int fid){
		return adminStampDao.getPlaceStampAll(fid);
	}

	@Override
	public void savePlaceStamp(PlaceStamp placeStamp) {
		adminStampDao.savePlaceStamp(placeStamp);		
	}

	@Override
	public void editPlaceStamp(PlaceStamp placeStamp) {
		adminStampDao.editPlaceStamp(placeStamp);
	}

	@Override
	public PlaceStamp getPlaceStamp(int stampid) {
		return adminStampDao.getPlaceStamp(stampid);
	}

	@Override
	public void deletePlaceStamp(PlaceStamp placeStamp) {
		placeStamp.setIsDelete("Y");
		adminStampDao.deletePlaceStamp(placeStamp);
	}

	@Override
	public void restorePlaceStamp(PlaceStamp placeStamp) {
		placeStamp.setIsDelete("Y");
		adminStampDao.deletePlaceStamp(placeStamp);
	}
}
