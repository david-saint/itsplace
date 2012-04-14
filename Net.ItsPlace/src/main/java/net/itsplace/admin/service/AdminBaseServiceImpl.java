package net.itsplace.admin.service;

import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("AdminBaseService")
public class AdminBaseServiceImpl implements AdminBaseService{
	private static final Logger logger = LoggerFactory.getLogger(AdminBaseServiceImpl.class);
}
