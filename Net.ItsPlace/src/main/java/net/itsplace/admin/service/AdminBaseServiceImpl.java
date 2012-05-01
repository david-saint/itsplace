package net.itsplace.admin.service;

import java.util.List;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itsplace.domain.Bascd;

@Service("AdminBaseService")
public class AdminBaseServiceImpl implements AdminBaseService{
	private static final Logger logger = LoggerFactory.getLogger(AdminBaseServiceImpl.class);
	
	@Autowired
	private AdminBaseDao adminBaseDao;
	
	@Override
	public List<Bascd> getGrpBascdList() {
		return adminBaseDao.getGrpBascdList();
	}



	@Override
	public List<Bascd> getBascdList(String grpCd) {
		return adminBaseDao.getBascdList(grpCd);
	}



	@Override
	public void saveBascd(Bascd bascd) {
		adminBaseDao.saveBascd(bascd);		
	}



	@Override
	public void editBascd(Bascd bascd) {
		adminBaseDao.editBascd(bascd);		
	}



	@Override
	public Bascd getBascd(int fid) {
		return adminBaseDao.getBascd(fid);
	}
}
