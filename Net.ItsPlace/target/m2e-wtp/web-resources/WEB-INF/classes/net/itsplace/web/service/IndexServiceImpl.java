package net.itsplace.web.service;

import net.itsplace.user.UserServiceImpl;
import net.itsplace.web.dao.IndexDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IndexService")
public class IndexServiceImpl implements IndexService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IndexDao indexDao;
}
