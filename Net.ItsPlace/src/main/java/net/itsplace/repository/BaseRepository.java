package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BaseRepository extends JpaRepository<Bascd, Integer> {

	//public List<Bascd> findByGrpName(String grpName);
	
	public List<Bascd> findByGrpcd(String grpcd);
}
