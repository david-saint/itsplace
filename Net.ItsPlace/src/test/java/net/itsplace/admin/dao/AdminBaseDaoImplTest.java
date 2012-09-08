package net.itsplace.admin.dao;

import static org.junit.Assert.*;

import java.util.List;

import net.itsplace.domain.Bascd;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.web.service.StampServiceImplTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminBaseDaoImplTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(AdminBaseDaoImplTest.class); 
	

	@Test
	public void testGetGrpBascdList() {
	//	List<Bascd> list = repo.findAll();
	}

	
}
