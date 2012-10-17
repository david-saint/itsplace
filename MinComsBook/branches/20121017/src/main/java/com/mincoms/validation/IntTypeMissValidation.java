package com.mincoms.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class IntTypeMissValidation implements ConstraintValidator<IntTypeMiss, String>{
	private static final Logger logger = LoggerFactory.getLogger(IntTypeMissValidation.class);
 
	@Override
	public void initialize(IntTypeMiss arg0) {
	}
	@Override
	public boolean isValid(String number, ConstraintValidatorContext arg1) {
		String pattern = "^[0-9]*$";
		logger.info("타입미스매치"+number);
		System.out.println("타입미스매치"+number);
		if( Pattern.matches(pattern, number.toString())){
			return true;
		}else{
			return false;	
		}
	}
}
