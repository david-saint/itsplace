package com.mincoms.book.admin.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mincoms.book.domain.AppException;
import com.mincoms.book.domain.BookRestriction;

public class RestrictionSpecs {
	 public static Specification<BookRestriction> isSolved(final boolean isSolved) {
	        return new Specification<BookRestriction>() {
	            @Override
	            public Predicate toPredicate(Root<BookRestriction> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            	
	            	if(isSolved){
	            		return  cb.isNotNull(root.get("solveDate"));
	            	}else{
	            		return  cb.isNull(root.get("solveDate"));
	            	}
	            	
	            }
	        };
	 }
}
