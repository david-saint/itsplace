package net.itsplace.web.service;

import net.itsplace.web.dao.SupportDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SupportService")
public class SupportServiceImpl implements SupportService{
	private static final Logger logger = LoggerFactory.getLogger(SupportServiceImpl.class);
	
	@Autowired
	private SupportDao supportDao;
}