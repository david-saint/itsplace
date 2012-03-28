package com.itsplace.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itsplace.partner.place.PlaceComment;
import com.myplace.partner.franchiser.FranchiserMember;
import com.myplace.partner.franchiser.FranchiserService;
import com.myplace.util.DurationFromNow;
import com.myplace.util.Encrypt;


@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminDao adminDao;
	
	  
	
	@Inject 
	private FileSystemResource fsResource;



	public void updateFranchiserAuth(FranchiserMember franchiserMember) {
		adminDao.updateFranchiserAuth(franchiserMember);
		
	}


	
	
	
}
