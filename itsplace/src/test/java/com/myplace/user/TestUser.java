package com.myplace.user;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import com.myplace.user.*;
public class TestUser {
	Validator validator;
	  
	  @Before
	  public void init() throws Exception {
	    ValidatorFactory factory = 
	      Validation.buildDefaultValidatorFactory();
	    this.validator = factory.getValidator();
	  }
	 @Test
	  public void testUserInsertValidation_OK() throws Exception {
	    User user = new User();
	    
	    user.setEmail("dddd@ddd.com");
	  
	    
	    Set<ConstraintViolation<User>> violations = 
	      validator.validate(user);
	    
	    System.out.println("violations: " + violations);
	    
	    Assert.assertEquals(violations.size(), 0);
	  }

}
