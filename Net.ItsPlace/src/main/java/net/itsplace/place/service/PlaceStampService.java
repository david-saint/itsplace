package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Place;
import net.itsplace.domain.Stamp;

public interface PlaceStampService {

	public void saveStamp(Stamp stamp) ;
	public void burnStamp(Stamp stamp) ;	
	
}
