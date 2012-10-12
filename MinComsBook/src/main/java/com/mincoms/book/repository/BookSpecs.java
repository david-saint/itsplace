package com.mincoms.book.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookInfo_;


public class BookSpecs {
	
	
	 public static Specification<BookInfo> AuthorsContaining(final String keyword) {
		 return new Specification<BookInfo>() {
			 @Override
			 public Predicate toPredicate(Root<BookInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 Predicate predicate = cb.conjunction();
				 if(keyword.equals("")){
					 return predicate;
				 }else{
					 String likePattern = SpecUtil.getLikePattern(keyword);
					 return cb.like(cb.lower(root.<String>get(BookInfo_.authors)), likePattern); 
				 }
				 
			 }
			 
		 };
	 }

	 public static Specification<BookInfo> TitleContaining(final String keyword) {
	        return new Specification<BookInfo>() {
	            @Override
	            public Predicate toPredicate(Root<BookInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            	 Predicate predicate = cb.conjunction();
					 if(keyword.equals("")){
						 return predicate;
					 }else{
		            	  String likePattern = SpecUtil.getLikePattern(keyword);
		            	  return cb.like(cb.lower(root.<String>get(BookInfo_.title)), likePattern);
					 }
	            }
	            
	           
	        };
	  }
	 public static Specification<BookInfo> IsDeleted(final String isDeleted) {
		 return new Specification<BookInfo>() {
			 @Override
			 public Predicate toPredicate(Root<BookInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 Predicate predicate = cb.conjunction();
				 if(isDeleted.equals("")){
					 return predicate;
				 }else{
					 final Path<BookInfo> title = root.<BookInfo> get("isDeleted");
					 boolean result = false;
					 if(isDeleted.equals("1")){
						 result = true;
					 }
					 return cb.equal(root.get("isDeleted"), result); 
				 }
			 }
			 
		 };
	 }
	
}
