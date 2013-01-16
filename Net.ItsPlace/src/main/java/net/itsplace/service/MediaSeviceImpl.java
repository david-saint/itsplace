package net.itsplace.service;

import java.util.List;

import net.itsplace.basecode.ImageSize;
import net.itsplace.basecode.MediaType;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.User;
import net.itsplace.repository.PlaceMediaRepository;
import net.itsplace.user.UserInfo;
import net.itsplace.util.ImageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("MediaService")
public class MediaSeviceImpl implements MediaService{
	private static final Logger logger = LoggerFactory.getLogger(MediaSeviceImpl.class);
	
	@Autowired
	protected BaseServiceImpl commonService;
	@Autowired
	private ImageService imageService;

	@Autowired
	PlaceService placeService;
	@Autowired
	UserService userService;
	@Autowired
	PlaceMediaRepository repo;

	@Override
	public String savePlaceMedia(ImageFileUpload file,int fid) {
		
		String orinalImagePath="";
		String placeImagePath="";
		String placeThumnailPath="";
		Place place = placeService.getPlace(fid);
		User user = userService.getUser(UserInfo.getEmail());
		try {
			orinalImagePath = imageService.convertToPng(file.getFile(),0,0);
			placeImagePath = imageService.convertToPng(file.getFile(),280,230);//대표 이미지(가맹점뷰어시) 
			placeThumnailPath = imageService.convertToPng(file.getFile(),80,80);
	
			PlaceMedia media = new PlaceMedia();
			media.setPlace(place);
			media.setUser(user);
			media.setmType(MediaType.Image.name());
			media.setSize(ImageSize.Lagrge.name());
			media.setmUrl(orinalImagePath);
			media.setIsDelete(false);
			media.setmTitle("");
			media.setIsProfile(true); //대표이미지 
		//	logger.info("원본이미지 저장 size:"+commonService.getBasecd().getMediaLarge());
			repo.save(media);
			
			
//			
//			//media.setSize(commonService.getBasecd().getMediaMedium());
//			media.setmUrl(placeImagePath);			
//			logger.info("대표이미지 저장 ");
//			adminMediaDao.savePlaceMedia(media);
//			
//			//media.setSize(commonService.getBasecd().getMediaThumbnail());//썸네일 이미지 
//			media.setmUrl(placeThumnailPath);			
//			logger.info("썸네일  저장");
//			adminMediaDao.savePlaceMedia(media);//썸네일 이미지
//			
//			
//			Place place = new Place();
//			place.setImageHost(media.getHost());
//			place.setFid(media.getFid());
//			place.setFileName(media.getmUrl());
//			adminMediaDao.updatePlaceImage(place);
			
			

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
//			Place place = new Place();
//			place.setImageHost(media.getHost());
//			place.setFid(media.getFid());
//			place.setFileName(media.getmUrl());
//			adminMediaDao.updatePlaceImage(place);
		}else{
			//adminMediaDao.updatePlaceMedia(media);
		}
		
	}



	@Override
	public void deleteMediaProfile(PlaceMedia media) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<PlaceMedia> findByPlace(int fid) {
		
		return repo.findByPlace(placeService.getPlace(fid));
	}
}
