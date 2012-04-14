package net.itsplace.web.service;

import net.itsplace.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("SearchService")
public class SearchServiceImpl implements SearchService{
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
}
