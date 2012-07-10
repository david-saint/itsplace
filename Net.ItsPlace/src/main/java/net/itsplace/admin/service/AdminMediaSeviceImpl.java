package net.itsplace.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import net.itsplace.admin.dao.AdminMediaDao;
import net.itsplace.admin.dao.AdminPlaceDao;
import net.itsplace.common.CommonService;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.user.UserInfo;
import net.itsplace.util.ImageService;

@Service("AdminMediaService")
public class AdminMediaSeviceImpl implements AdminMediaService{
	private static final Logger logger = LoggerFactory.getLogger(AdminMediaSeviceImpl.class);
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private ImageService imageService;

	@Autowired
	private AdminMediaDao adminMediaDao;

	@Override
	public String savePlaceMedia(ImageFileUpload file,int fid) {
		
		String orinalImagePath="";
		String placeImagePath="";
		String placeThumnailPath="";
		try {
			orinalImagePath = imageService.convertToPng(file.getFile(),0,0);
			placeImagePath = imageService.convertToPng(file.getFile(),280,230);//대표 이미지(가맹점뷰어시) 
			placeThumnailPath = imageService.convertToPng(file.getFile(),80,80);
	
			PlaceMedia media = new PlaceMedia();
			media.setFid(fid);
			media.setEmail(UserInfo.getEmail());
			media.setmType(commonService.getBasecd().getMediaImage());
			media.setSize(commonService.getBasecd().getMediaLarge());
			media.setmUrl(orinalImagePath);
			media.setHost(commonService.getBasecd().getMediaImageHost());
			media.setIsDelete("N");
			media.setmTitle("");
			logger.info("원본이미지 저장 size:"+commonService.getBasecd().getMediaLarge());
			adminMediaDao.savePlaceMedia(media);//원본 이미지
			
			media.setSize(commonService.getBasecd().getMediaThumbnail());//썸네일 이미지 
			media.setmUrl(placeThumnailPath);			
			logger.info("썸네일  저장");
			adminMediaDao.savePlaceMedia(media);//썸네일 이미지
			
			media.setSize(commonService.getBasecd().getMediaMedium());
			media.setmUrl(placeImagePath);			
			logger.info("대표이미지 저장 ");
			adminMediaDao.savePlaceMedia(media);//대표이미지
			
			updatePlaceMedia(media);//대표이미지 수정함 place 테이블 
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//원본
		
		return placeImagePath;
	}

	

	/**
	 * 미디어 수정(mType=I 이미지, mType=M 동영<br />
	 * 이미지일경우 가맹점 대표이미지 업데이트 함 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param media
	 * @return  void
	 * @throws 
	 * @see 
	 */
	@Override
	public void updatePlaceMedia(PlaceMedia media) throws DataAccessException {
		
		if(media.getmType().equals("I")){
			Place place = new Place();
			place.setImageHost(media.getHost());
			place.setFid(media.getFid());
			place.setFileName(media.getmUrl());
			adminMediaDao.updatePlaceImage(place);
		}else{
			adminMediaDao.updatePlaceMedia(media);
		}
		
	}
}
