package net.itsplace.web.service;


import net.itsplace.web.dao.StampDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StampService")
public class StampServiceImpl implements StampService{
	private static final Logger logger = LoggerFactory.getLogger(StampServiceImpl.class);
	
	@Autowired
	private StampDao stampDao;
}
