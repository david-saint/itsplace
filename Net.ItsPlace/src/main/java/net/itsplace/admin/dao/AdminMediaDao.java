package net.itsplace.admin.dao;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;

import org.springframework.dao.DataAccessException;

public interface AdminMediaDao {
	/* 미디어 업로드 */
	public void savePlaceMedia(PlaceMedia media) throws DataAccessException;
	/**
	 * 가맹점 대표 이미지 수정
	 * Place Table 대표이미지 업데이트 
	 * @param place
	 * @throws DataAccessException
	 */
	public void updatePlaceImage(Place place) throws DataAccessException;
	/* 미디어  수정 */
	public void updatePlaceMedia(PlaceMedia media) throws DataAccessException;
	
	/**
	 * 가맹점 대표 이미지 삭제
	 * @param media
	 * @throws DataAccessException
	 */
	public void deleteMediaProfile(PlaceMedia media) throws DataAccessException;
	
}
