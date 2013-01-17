package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository  extends JpaRepository<Stamp, Integer> {

	List<Stamp> findByPlaceStamp(PlaceStamp placeStamp);
}
