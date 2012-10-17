package com.mincoms.validation;

import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.BookRepository;
import com.mincoms.book.service.UserService;



public class ExistUsersValidation implements ConstraintValidator<ExistUsers, List<String>>{
	private static final Logger logger = LoggerFactory.getLogger(ExistUsersValidation.class);
 

	@Autowired
	private UserService service;
	@Override
	public void initialize(ExistUsers arg0) {
		
	}
	
	@Override
	public boolean isValid(List<String> userNames, ConstraintValidatorContext arg1) {
		for(int i=0; i< userNames.size();i++){
			logger.info(userNames.get(i));
			UserInfo userInfo = service.findByUserName(userNames.get(i));
			if(userInfo == null){
				return false;	
			}
		}
		return true;
	}
}


