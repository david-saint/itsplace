package net.itsplace.admin.service;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;

public interface AdminMediaService {

	public void savePlaceMedia(Pmedia media);
	/* 가맹점 대표 이미지 수정 */
	public void updatePlaceImage(Place place) throws DataAccessException;
	/* 가맹점 미디어  수정 */
	public void updatePlaceMedia(Pmedia media) throws DataAccessException;
	
}
