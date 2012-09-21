package com.mincoms.book.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookInfo_;
import com.mincoms.book.domain.BookRental;


public class BookSpecs {
	
	
	 public static Specification<BookInfo> titleEqual(final String keyword) {
		 return new Specification<BookInfo>() {
			 @Override
			 public Predicate toPredicate(Root<BookInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 
				 Predicate predicate = cb.conjunction();
				 final Path<BookInfo> title = root.<BookInfo> get("title");
				 
				 return cb.equal(root.get("title"), keyword); 
			 }
			 
		 };
	 }

	 public static Specification<BookInfo> titleIsLike(final String keyword) {
	        return new Specification<BookInfo>() {
	            @Override
	            public Predicate toPredicate(Root<BookInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            	String likePattern = getLikePattern(keyword);
	            	  return cb.like(cb.lower(root.<String>get(BookInfo_.title)), likePattern);
	            }
	            
	            private String getLikePattern(final String searchTerm) {
	                StringBuilder pattern = new StringBuilder();
	                pattern.append(searchTerm.toLowerCase());
	                pattern.append("%");
	                return pattern.toString();
	            }
	        };
	  }
}
