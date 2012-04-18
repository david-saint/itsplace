package net.itsplace.admin.service;

import java.util.List;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itsplace.domain.BasCd;

@Service("AdminBaseService")
public class AdminBaseServiceImpl implements AdminBaseService{
	private static final Logger logger = LoggerFactory.getLogger(AdminBaseServiceImpl.class);
	
	@Autowired
	private AdminBaseDao adminBaseDao;
	
	@Override
	public List<BasCd> getGrpBascdList() {
		// TODO Auto-generated method stub
		return adminBaseDao.getGrpBascdList();
	}



	@Override
	public List<BasCd> getBascdList(String grpCd) {
		// TODO Auto-generated method stub
		return adminBaseDao.getBascdList(grpCd);
	}
}