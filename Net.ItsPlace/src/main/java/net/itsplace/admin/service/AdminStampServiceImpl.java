package net.itsplace.admin.service;

import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("AdminStampService")
public class AdminStampServiceImpl implements AdminStampService{
	private static final Logger logger = LoggerFactory.getLogger(AdminStampServiceImpl.class);
}
