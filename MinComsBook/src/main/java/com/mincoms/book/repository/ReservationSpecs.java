package com.mincoms.book.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookInfo_;
import com.mincoms.book.domain.BookReservation;
import com.mincoms.book.domain.BookReservation_;

public class ReservationSpecs {
	 public static Specification<BookReservation> titleEqual(final String keyword) {
	        return new Specification<BookReservation>() {
	            @Override
	            public Predicate toPredicate(Root<BookReservation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            	Predicate predicate = cb.conjunction();
	            	//query.groupBy(root.<BookReservation> get("id"));
	            	
	            	
	            	//query.multiselect(cb.count(root.<BookReservation> get("bookInfo")),root.<BookReservation> get("bookInfo"))
	            	
	            	
	            	//Root<BookReservation> c = query.from(BookReservation.class);
	            
	            /*	query.groupBy(root.<BookReservation> get("bookInfo")
			            	,root.get(BookReservation_.isCanceled)
			            	,root.get(BookReservation_.userInfo)
			            	,root.get(BookReservation_.bookRental)
	            	);*/
	            
	            	return predicate;
	            }

	        };
	        
	        
	    }
}
