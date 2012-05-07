package net.itsplace.place.service;

import net.itsplace.domain.Authcode;

import org.springframework.dao.DataAccessException;

public interface PlaceInfoService {

	public boolean editAuthCode(Authcode authcode);
	
}
