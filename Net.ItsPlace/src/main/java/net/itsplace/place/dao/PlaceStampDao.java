package net.itsplace.place.dao;

import java.util.List;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.Stamp;

import org.springframework.dao.DataAccessException;


public interface PlaceStampDao {


	public void saveStamp(Stamp stamp) throws DataAccessException;
	public void burnStamp(Stamp stamp) throws DataAccessException;	
	
}
