package net.itsplace.web.service;

import net.itsplace.web.dao.FeatureDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FeatureService")
public class FeatureServiceImpl implements FeatureService{
	private static final Logger logger = LoggerFactory.getLogger(FeatureServiceImpl.class);
	@Autowired
	private FeatureDao featureDao;
}
