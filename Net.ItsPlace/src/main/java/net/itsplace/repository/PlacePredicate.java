package net.itsplace.repository;


import com.mysema.query.types.Predicate;
import net.itsplace.domain.QPlace;

public class PlacePredicate {
	 public static Predicate isAuth(final String isAuth) {
		QPlace place = QPlace.place;
		return place.isAuth.eq(isAuth);
	}
}
