package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminPlaceDao;
import net.itsplace.domain.Franchiser;
import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminPlaceService")
public class AdminPlaceServiceImpl implements AdminPlaceService{
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceServiceImpl.class);
	
	@Autowired
	private AdminPlaceDao adminPlaceDao;

	@Override
	public List<Franchiser> getFranchiserList(Map<String, Object> param) {
		return adminPlaceDao.getFranchiserList(param);
	}
}
