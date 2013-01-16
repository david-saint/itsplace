package net.itsplace.repository;

import net.itsplace.domain.PlaceUser;
import net.itsplace.domain.StampType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StampTypeRepository  extends JpaRepository<StampType, Integer> {

}
