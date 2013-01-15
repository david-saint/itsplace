package net.itsplace.repository;

import net.itsplace.domain.QPlace;
import net.itsplace.domain.QUser;

import com.mysema.query.types.expr.BooleanExpression;

public class UserPredicates {
	 public static BooleanExpression eqMobile(final String mobile) {
	    	QUser user = QUser.user;
	    	
	        return user.mobile.eq(mobile);	
	    }
	   
}
