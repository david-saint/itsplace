package net.itsplace.place.dao;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.Stamp;

import org.springframework.dao.DataAccessException;

public interface PlaceInfoDao {
	public void editAuthCode(Authcode authcode) throws DataAccessException;
	public Authcode getAuthCode(int fid) throws DataAccessException;
	
}
