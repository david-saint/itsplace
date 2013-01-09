package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PlaceRepository extends JpaRepository<Place, Integer>, QueryDslPredicateExecutor<Place> {

	
}
