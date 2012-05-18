package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Place;

import org.springframework.dao.DataAccessException;


public interface PlaceDao {
	public Place getPlace(int fid) throws DataAccessException;
}
