package net.itsplace.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.itsplace.domain.User;
import net.itsplace.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class UserPrimaryKeyValidation implements ConstraintValidator<UserPrimarykey, String>{
	private static final Logger logger = LoggerFactory.getLogger(UserPrimaryKeyValidation.class);
	
	@Autowired
	UserRepository repo;
	
	@Override
	public void initialize(UserPrimarykey arg0) {
		// TODO Auto-generated method stub
		
	} 

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		User user = repo.findOne(email);
		if (user == null){
			logger.info("유저밸리데이션 통과" );
			return true;
		}else{
			logger.info("유저밸리데이션 키 중복" );
			return false;
		}
		
	}

	

}
