package net.itsplace.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;

public interface MediaService {
	/**
	 * place 의 대표이미지를 업데이트하고 placeMedia에도 isProfile = 'Y' 로 저장
	 * @param file
	 * @param fid
	 * @return
	 */
	public String savePlaceMedia(ImageFileUpload file,int fid);
	
	/* 가맹점 미디어  수정 */
	public void updatePlaceMedia(PlaceMedia media);
	/* 가맹점 대표 이미지 삭제  */
	public void deleteMediaProfile(PlaceMedia media);
	
	public List<PlaceMedia> findByPlace(int fid);
}
