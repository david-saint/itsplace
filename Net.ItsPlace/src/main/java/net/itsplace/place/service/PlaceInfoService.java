package net.itsplace.place.service;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;

import org.springframework.dao.DataAccessException;

public interface PlaceInfoService {

	public boolean editAuthCode(Authcode authcode);
	public Place getPlace(int fid);
}
