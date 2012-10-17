package com.mincoms.book.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.Paging;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.util.Encrypt;
import com.mincoms.test.TestApplicationContext;

public class UserRepositoryTest extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Autowired
	UserRepository userRepo;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Ignore
	public void testFineOne(){
		UserInfo user = userRepo.findByUserId(2);
		//logger.info(user.getUserRname()+user.getDept().getDeptName());
		
		//userRepo.f
	
	}
	@Ignore
	public void testFindByUserName() {
		String columns[] = { "title","isbn" };

		Paging page = new Paging(columns,1,10,1,"desc","");
		//DataTable<Book> table =  bookService.getBookList(page);
		Page<UserInfo> users = userRepo.findAll(new PageRequest(page.getiDisplayStart(),page.getiDisplayLength()));
		for(UserInfo user: users){
			//logger.info(user.getUserRname() + user.getDept().getDeptName());
		}
		logger.info("number:{}",users.getNumber());
		logger.info("number:{}",users.getNumber());
		logger.info("numbere:{}",users.getNumberOfElements());
		logger.info("numbertotalpage:{}",users.getTotalPages());
		logger.info("size:{}",users.getSize());
		logger.info("size:{}",users.getTotalElements());
	}

	@Test
	public void testSave() {
		UserInfo user = userRepo.findByUserName("dhkim");
		user.setPassword(Encrypt.md5Encoding("1111"));
		userRepo.save(user);
		
	}

}
