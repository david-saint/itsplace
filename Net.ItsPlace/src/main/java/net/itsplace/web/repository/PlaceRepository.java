package net.itsplace.web.repository;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.Place;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
