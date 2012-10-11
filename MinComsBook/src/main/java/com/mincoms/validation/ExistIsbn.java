package com.mincoms.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;



@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ExistIsbnValidation.class)
@Documented
public @interface ExistIsbn {
	 public abstract String message() default "{ExistIsbn}";
	 public abstract Class[] groups() default {};
	 public abstract Class[] payload() default {};
}
