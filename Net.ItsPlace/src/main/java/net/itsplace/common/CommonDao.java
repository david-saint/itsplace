package net.itsplace.common;

import java.util.List;

import net.itsplace.domain.Bascd;

import org.springframework.dao.DataAccessException;


public interface CommonDao {

	
	public List<Bascd> getBascdList(String grpCd) throws DataAccessException;
	
}
