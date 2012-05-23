package net.itsplace.place.service;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.Place;
import net.itsplace.place.dao.PlaceInfoDao;
import net.itsplace.place.dao.PlaceStampDao;
import net.itsplace.user.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;




@Service("placeInfoService")
public class PlaceInfoServiceImpl  implements PlaceInfoService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceInfoServiceImpl.class);
	
	@Autowired
	private PlaceInfoDao placeInfoDao;

	@Override
	public boolean editAuthCode(Authcode authcode) {
		boolean result = false;
		authcode.setFid(UserInfo.getFid());
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		Authcode dbAuthcode = placeInfoDao.getAuthCode(authcode.getFid());
		if(dbAuthcode.getAuthCode()==null){
			authcode.setNewAuthCode(md5.encodePassword(authcode.getNewAuthCode(), null));
			placeInfoDao.editAuthCode(authcode);		
			result = true;
		}else{
			logger.info("현재인증코드:"+dbAuthcode.getAuthCode());
			logger.info("변경인증코드:"+authcode.getNewAuthCode());
			if(md5.isPasswordValid(dbAuthcode.getAuthCode(),authcode.getAuthCode(), null)){
				authcode.setNewAuthCode(md5.encodePassword(authcode.getNewAuthCode(), null));
				placeInfoDao.editAuthCode(authcode);		
				result = true;
			}
		}
		
		return result;
	}

	@Override
	public Place getPlace(int fid) {
		
		return placeInfoDao.getPlace(fid);
	}



}
