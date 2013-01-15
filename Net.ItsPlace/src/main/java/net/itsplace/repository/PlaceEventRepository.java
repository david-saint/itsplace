package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.GroupBascd;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PlaceEventRepository extends JpaRepository<PlaceEvent, Integer>, QueryDslPredicateExecutor<PlaceEvent> {

	
	
	List<PlaceEvent> findByIsDelete(Boolean isDelete);
	List<PlaceEvent> findByPlace(Place place);
;
	
}

