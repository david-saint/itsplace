package net.itsplace.repository;

import net.itsplace.domain.QPlace;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

public class PlacePredicates {

    public static BooleanExpression isAuth(final Boolean isAuth) {
    	QPlace place = QPlace.place;
    	
        return place.isAuth.eq(isAuth);    	
    }
    public static BooleanExpression likeFname(final String fname) {
    	QPlace place = QPlace.place;
    	
        return place.ename.like(fname);
    	
    }
}
