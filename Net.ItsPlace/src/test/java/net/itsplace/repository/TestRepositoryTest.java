package net.itsplace.repository;

import static org.junit.Assert.*;

import java.util.Locale;

import net.itsplace.basecode.ServiceType;
import net.itsplace.domain.Place;
import net.itsplace.domain.TestEnum;
import net.itsplace.domain.TestRepo;
import net.itsplace.init.TestApplicationContext;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.web.repository.PlaceRepositoryTest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
public class TestRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger  = LoggerFactory.getLogger(TestRepositoryTest.class); 
	
	@Autowired
	TestRepo repo;

	@Autowired
	MessageSource messageSource;
	@Test
	public void enumTest(){
		TestEnum t = new TestEnum();
		t.setName("hoon");
		t.setServiceType(ServiceType.Premier);
		repo.save(t);
		System.out.println(messageSource.getMessage("signup", null, Locale.getDefault()));
	}
}
