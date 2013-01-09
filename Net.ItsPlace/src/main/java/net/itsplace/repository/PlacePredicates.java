package net.itsplace.repository;

import net.itsplace.domain.QPlace;

import com.mysema.query.types.Predicate;

/**
 * A class which is used to create Querydsl predicates.
 * @author Petri Kainulainen
 */
public class PlacePredicates {

    public static Predicate isAuth(final String isAuth) {
        QPlace place = QPlace.place;
        return place.isAuth.eq(isAuth);
    }
}
