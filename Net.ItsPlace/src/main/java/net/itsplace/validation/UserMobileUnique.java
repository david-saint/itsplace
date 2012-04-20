package net.itsplace.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserMobileValidation.class)
@Documented
public @interface UserMobileUnique{
	 public abstract String message() default "휴대폰은이 이미 사용중입니다";
	 public abstract Class[] groups() default {};
	 public abstract Class[] payload() default {};

}
