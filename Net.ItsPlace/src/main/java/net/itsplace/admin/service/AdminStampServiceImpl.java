package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminStampDao;
import net.itsplace.domain.StampType;
import net.itsplace.user.UserServiceImpl;

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
	public List<StampType> getStamptypeList(Map<String, Object> param) {
		return adminStampDao.getStamptypeList(param);
	}

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
}
