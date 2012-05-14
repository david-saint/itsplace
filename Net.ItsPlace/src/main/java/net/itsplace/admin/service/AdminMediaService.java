package net.itsplace.admin.service;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;

public interface AdminMediaService {
	/* 가맹점 미디어  저장  */
	public String savePlaceMedia(ImageFileUpload file,int fid);
	
	/* 가맹점 미디어  수정 */
	public void updatePlaceMedia(Pmedia media);
	
}
