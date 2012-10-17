package com.mincoms.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.repository.BookRepository;

public class ExistIsbnValidation implements ConstraintValidator<ExistIsbn, String>{
	private static final Logger logger = LoggerFactory.getLogger(ExistIsbnValidation.class);

	@Autowired
	private BookRepository repo;
	@Override
	public void initialize(ExistIsbn arg0) {
		
	}

	@Override
	public boolean isValid(String isbn, ConstraintValidatorContext arg1) {
	
		try{
			BookInfo book = repo.findByIsbn(isbn);
			if(book!=null){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			return false;
		}
	}
	


}

