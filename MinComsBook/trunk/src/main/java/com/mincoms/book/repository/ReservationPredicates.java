package com.mincoms.book.repository;

import com.mysema.query.types.Predicate;

import com.mincoms.book.domain.QBookReservation;

public class ReservationPredicates {
	public static Predicate lastNameIsLike() {
       QBookReservation  bookReservation = QBookReservation.bookReservation;
       
        return  bookReservation.bookInfo.isbn.startsWithIgnoreCase("9788996276593");
    }
}
