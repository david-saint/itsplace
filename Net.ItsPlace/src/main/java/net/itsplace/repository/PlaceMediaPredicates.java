package net.itsplace.repository;

import net.itsplace.domain.QPlace;
import net.itsplace.domain.QPlaceMedia;

import com.mysema.query.types.expr.BooleanExpression;

public class PlaceMediaPredicates {
	public static BooleanExpression isAuth(final Boolean isDelete) {
    	QPlaceMedia placeMedia = QPlaceMedia.placeMedia;
    	
        return placeMedia.isDelete.eq(isDelete);   	
    }
}
