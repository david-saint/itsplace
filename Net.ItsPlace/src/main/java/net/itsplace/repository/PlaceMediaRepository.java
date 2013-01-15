package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceMediaRepository  extends JpaRepository<PlaceMedia, Integer> {

	List<PlaceMedia> findByPlace(Place place);
}
