package net.itsplace.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.repository.PlaceEventPredicates;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.user.UserInfo;
import net.itsplace.util.QrCodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysema.query.types.Predicate;

@Service("AdminPlaceService")
 class PlaceServiceImpl implements PlaceService{
	private static final Logger logger = LoggerFactory.getLogger(PlaceService.class);
	
	@Autowired
	PlaceRepository repo;
	
	@Override
	public DataTable<Place> findPlaceList(JpaPaging paging, Boolean isAuth) {
		
          DataTable<Place> table = new DataTable<Place>(paging);
          
          Predicate predicate =  PlacePredicates.isAuth(isAuth);
          
          Page<Place> places = repo.findAll(predicate, paging.getPageable());
						 
		  table.setRows(places.getContent()); 
		  table.setiTotalDisplayRecords(places.getTotalElements());
		  return table;
	}

	@Override
	public void enablePlace(int fid) {
		Place place = repo.findOne(fid);
		place.setIsAuth(true);
		place.setEditDate(new Date());
		
		QrCodeService qr = new QrCodeService();
		
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		place.setMcode(md5.encodePassword(Integer.toString(fid),null));
	   
		place = qr.makePlaceQrAuthCode(place);
	    
	    place.setAuthCode(md5.encodePassword("0000",null));
	  
	    repo.save(place);
	    
	}

	@Override
	public void disablePlace(int fid) {
		Place place = repo.findOne(fid);
		place.setIsAuth(false);
		place.setEditDate(new Date());
		repo.save(place);
	}

	@Override
	public void editPlace(Place place) {
		repo.save(place);
		
	}

	@Override
	public Place getPlace(int fid) {
		return repo.findOne(fid);
	}

	@Override
	public int savePlace(Place place) {
	
		//place.setFid(adminPlaceDao.savePlace(place));
		Place savedPlace = repo.save(place);
		
		QrCodeService qr = new QrCodeService();
		savedPlace = qr.makePlaceQrCode(savedPlace, "url");
		
		repo.save(savedPlace);
		return savedPlace.getFid();
	}

	@Override
	public String getMcode(int fid) {
		// TODO Auto-generated method stub
		return repo.findOne(fid).getMcode();
	}

	@Override
	public boolean editAuthCode(Authcode authcode) {
		boolean result = false;
		authcode.setFid(UserInfo.getFid());
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		Place place = repo.findOne(UserInfo.getFid());
		if( place.getAuthCode()==null){
			//authcode.setNewAuthCode(md5.encodePassword(authcode.getNewAuthCode(), null));
			repo.save(place);		
			result = true;
		}else{
			//logger.info("현재인증코드:"+dbAuthcode.getAuthCode());
			//logger.info("변경인증코드:"+authcode.getNewAuthCode());
			//if(md5.isPasswordValid(dbAuthcode.getAuthCode(),authcode.getAuthCode(), null)){
			//	authcode.setNewAuthCode(md5.encodePassword(authcode.getNewAuthCode(), null));
			//	placeInfoDao.editAuthCode(authcode);		
			//	result = true;
			//}
		}
		
		return result;
	}

	public List<Place> findByRecentPalces(int limit) {
		JpaPaging paging = new JpaPaging();
		Page<Place> places = repo.findAll(PlacePredicates.isAuth(true), paging.getPageable("fid",Sort.Direction.DESC, 0, limit));
		
		return places.getContent();
	}

	@Override
	public Page<Place> findByAll(Predicate predicate, JpaPaging paging) {
		
		return repo.findAll(predicate, paging.getPageable());
	}

	@Override
	public List<Place> findByAll(Predicate predicate) {
		// TODO Auto-generated method stub
		Iterable<Place> places = repo.findAll(predicate);
		List<Place> list = new ArrayList<Place>();
		for(Place p:places){
			list.add(p);
		}
		return list;
	}
}
public interface PlaceService {
	/* 가맹점을 검색하여 리스트로 보여준다*/
	//public List<Place>  getPlaceList(Map<String, Object> param);
	public Place getPlace(int fid); 
	/**
	 *  가맹점 승인시 QR코드 생성한다
	 * */
	public void enablePlace(int fid); 
	/* 가맹점 승인취소*/
	public void disablePlace(int fid);
	/* 가맹점 수정*/
	public void editPlace(Place place);
	public int savePlace(Place place);
	public String getMcode(int fid);
	/**
	 * 데이타그리드 가맹점 검색 목록
	 * @param paging
	 * @param isDelete
	 * @return
	 */
	DataTable<Place> findPlaceList(JpaPaging paging, Boolean isAuth);
	public boolean editAuthCode(Authcode authcode);

	/**
	 * 쵝근 등록된 가맹점 목록
	 * @param limit 객수
	 * @return
	 */
	public List<Place> findByRecentPalces(int limit);
	/**
	 * 
	 * @param predicate
	 * @param paging
	 * @return
	 */
	
	public Page<Place> findByAll(Predicate predicate, JpaPaging paging);
	/**
	 * 가맹점 검색(노페이징)
	 * @param predicate
	 * @return
	 */
	public List<Place> findByAll(Predicate predicate);
}
