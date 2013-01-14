package net.itsplace.repository;

import net.itsplace.domain.Place;
import net.itsplace.domain.QPlaceEvent;

import com.mysema.query.types.expr.BooleanExpression;

public class PlaceEventPredicates {
	   public static BooleanExpression isAuth(final  Boolean  isAuth) {
		   QPlaceEvent placeEvent = QPlaceEvent.placeEvent;
	    	
	       return placeEvent.isAuth.eq(isAuth);    	
	    }
	   
	    public static BooleanExpression isDelete(final Boolean isDelete) {
	    	QPlaceEvent placeEvent = QPlaceEvent.placeEvent;
	        return  placeEvent.isDelete.eq(isDelete);    	
	    }
	    
	    public static BooleanExpression byPlace(final Place place) {
	    	QPlaceEvent placeEvent = QPlaceEvent.placeEvent;
	        return  placeEvent.place.eq(place);   	
	    }
}
