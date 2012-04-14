package net.itsplace.admin.service;

import net.itsplace.admin.dao.AdminSupportDao;
import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminSupportService")
public class AdminSupportServiceImpl implements AdminSupportService{
	private static final Logger logger = LoggerFactory.getLogger(AdminSupportServiceImpl.class);
	
	@Autowired
	private AdminSupportDao adminSupportDao;
}
