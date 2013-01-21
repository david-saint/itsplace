package net.itsplace.exception;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;




public class ExceptionSpecs {
	 public static Specification<AppException> searchId(final int id) {
	        return new Specification<AppException>() {
	        	 @Override
		            public Predicate toPredicate(Root<AppException> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		            	Predicate predicate = cb.conjunction();
		            	return cb.equal(root.get("id"), id); 
		            }
	        };
	 }
	 public static Specification<AppException> searchMessage(final String keyword) {
		 return new Specification<AppException>() {
			 @Override
			 public Predicate toPredicate(Root<AppException> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 Predicate predicate = cb.conjunction();
				 String likePattern = getLikePattern(keyword);
				 //return cb.like(cb.lower(root.<String>get(AppException_.message)), likePattern);
				 return predicate;
			 }
			 
			 private String getLikePattern(final String keyword) {
				 StringBuilder pattern = new StringBuilder();
				 pattern.append("%");
				 pattern.append(keyword.toLowerCase());
				 pattern.append("%");
				 return pattern.toString();
			 }
		 };
	 }

	 public static Specification<AppException> isCompletd(final boolean isCompleted) {
	        return new Specification<AppException>() {
	            @Override
	            public Predicate toPredicate(Root<AppException> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            	Predicate predicate = cb.conjunction();
	            	return cb.equal(root.get("isCompleted"), isCompleted); 
	            }
	        };
	 }
	 public static Specification<AppException> isDeleted(final boolean isDeleted) {
	        return new Specification<AppException>() {
	            @Override
	            public Predicate toPredicate(Root<AppException> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            	Predicate predicate = cb.conjunction();
	            	return cb.equal(root.get("isDeleted"), isDeleted); 
	            }
	        };
	 }
}
