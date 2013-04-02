package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PlaceMediaRepository  extends JpaRepository<PlaceMedia, Integer>, QueryDslPredicateExecutor<PlaceMedia>  {

	List<PlaceMedia> findByPlace(Place place);
	
	List<PlaceMedia> findByPlaceAndIsDelete(Place place, boolean isDelete);
}
