package net.itsplace.admin.service;

import net.itsplace.admin.dao.AdminStampDao;
import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminStampService")
public class AdminStampServiceImpl implements AdminStampService{
	private static final Logger logger = LoggerFactory.getLogger(AdminStampServiceImpl.class);
	
	@Autowired
	private AdminStampDao adminStampDao;
}
