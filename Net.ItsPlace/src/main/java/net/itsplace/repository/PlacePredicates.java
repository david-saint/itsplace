package net.itsplace.repository;

import net.itsplace.domain.QPlace;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * A class which is used to create Querydsl predicates.
 * @author Petri Kainulainen
 */
public class PlacePredicates {

    public static BooleanExpression isAuth(final String isAuth) {
    	QPlace place = QPlace.place;
    	
        return place.isAuth.eq(isAuth);    	
    }
    public static BooleanExpression likeFname(final String fname) {
    	QPlace place = QPlace.place;
    	
        return place.ename.like(fname);
    	
    }
}
