package com.mincoms.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = IntTypeMissValidation.class)
@Documented
public @interface IntTypeMiss{
	 public abstract String message() default "{NotEmpty.title}";
	 public abstract Class[] groups() default {};
	 public abstract Class[] payload() default {};

}
