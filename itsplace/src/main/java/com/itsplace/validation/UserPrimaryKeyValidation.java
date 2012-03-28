package com.itsplace.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.user.User;
import com.myplace.user.UserDao;
import com.myplace.user.UserDaoImpl;

public class UserPrimaryKeyValidation implements ConstraintValidator<UserPrimarykey, String>{
	private static final Logger logger = LoggerFactory.getLogger(UserPrimaryKeyValidation.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void initialize(UserPrimarykey arg0) {
		// TODO Auto-generated method stub
		
	} 

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		User user = userDao.getUser(email);
		if (user == null){
			logger.info("유저밸리데이션 통과" );
			return true;
		}else{
			logger.info("유저밸리데이션 키 중복" );
			return false;
		}
		
	}

	

}
