package net.itsplace.repository;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.Place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceRepo extends JpaRepository<Place, Integer>, QueryDslPredicateExecutor<Place> {

	/**
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	//Page<Place> findAll(Specification<Place> spec, Pageable pageable);
}
