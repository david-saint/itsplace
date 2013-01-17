package net.itsplace.repository;

import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository  extends JpaRepository<Stamp, Integer> {

	
}
