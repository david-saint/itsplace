package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminPlaceDao;
import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;
import net.itsplace.user.UserServiceImpl;
import net.itsplace.util.QrCodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("AdminPlaceService")
public class AdminPlaceServiceImpl implements AdminPlaceService{
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceServiceImpl.class);
	
	@Autowired
	private AdminPlaceDao adminPlaceDao;
	
	@Override
	public List<Place> getPlaceList(Map<String, Object> param)
			{
		return adminPlaceDao.getPlaceList(param);
	}

	@Override
	public void enablePlace(int fid) {
		adminPlaceDao.enablePlace(fid);
	}

	@Override
	public void disablePlace(int fid) {
		adminPlaceDao.disablePlace(fid);
	}

	@Override
	public void editPlace(Place place) {
		adminPlaceDao.editPlace(place);
		
	}

	@Override
	public Place getPlace(int fid) {
		return adminPlaceDao.getPlace(fid);
	}

	@Override
	public int savePlace(Place place) {
	
		place.setFid(adminPlaceDao.savePlace(place));
		QrCodeService qr = new QrCodeService();
		place = qr.makePlaceQrCode(place, "url");
		place = qr.makePlaceQrAuthCode(place);
		adminPlaceDao.editPlacerQrcode(place);
		
		return place.getFid();
	}


	
}
