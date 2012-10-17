package com.mincoms.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.repository.BookRepository;



public class UniqueIsbnValidation implements ConstraintValidator<UniqueIsbn, String>{
	private static final Logger logger = LoggerFactory.getLogger(UniqueIsbnValidation.class);

	@Autowired
	private BookRepository repo;
	@Override
	public void initialize(UniqueIsbn arg0) {
		
	}

	@Override
	public boolean isValid(String isbn, ConstraintValidatorContext arg1) {
	
		try{
			BookInfo book = repo.findByIsbn(isbn);
			if(book!=null){
				return false;
			}else{
				return true;
			}
			
		}catch(Exception e){
			return false;
		}
	}
	


}
