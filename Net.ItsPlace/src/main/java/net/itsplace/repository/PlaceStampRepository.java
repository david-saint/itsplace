package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceStamp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceStampRepository  extends JpaRepository<PlaceStamp, Integer> {
	
	List<PlaceStamp> findByPlace(Place place);

}
