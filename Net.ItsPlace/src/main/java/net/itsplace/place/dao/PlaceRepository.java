package net.itsplace.place.dao;

import net.itsplace.domain.Place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

	/**
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<Place> findAll(Specification<Place> spec, Pageable pageable);
}
