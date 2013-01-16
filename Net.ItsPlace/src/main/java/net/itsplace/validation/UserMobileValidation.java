package net.itsplace.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.itsplace.domain.User;
import net.itsplace.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class UserMobileValidation implements ConstraintValidator<UserMobileUnique, String>{
	private static final Logger logger = LoggerFactory.getLogger(UserPrimaryKeyValidation.class);
	
	@Autowired
	UserRepository repo;
	
	@Override
	public void initialize(UserMobileUnique arg0) {
		// TODO Auto-generated method stub
		
	} 

	@Override
	public boolean isValid(String mobile, ConstraintValidatorContext arg1) {
		if(mobile==null){
			logger.info("류대폰 번호가 없음" );
			
		}
		User user = repo.findByMobile(mobile);
		if (user == null){
			logger.info("유저밸리데이션 통과" );
			return true;
		}else{
			logger.info("유저밸리데이션 키 중복" );
			return false;
		}
		
	}

	

}
