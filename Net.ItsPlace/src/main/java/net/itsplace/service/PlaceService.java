package net.itsplace.service;

import java.util.Date;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.repository.PlaceEventPredicates;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.util.QrCodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysema.query.types.Predicate;

@Service("AdminPlaceService")
public class PlaceService implements IPlaceService{
	private static final Logger logger = LoggerFactory.getLogger(PlaceService.class);
	
	@Autowired
	PlaceRepository repo;
	
	@Override
	public DataTable<Place> findPlaceList(JpaPaging paging, Boolean isDelete) {
		
          DataTable<Place> table = new DataTable<Place>(paging);
          
          Predicate predicate =  PlaceEventPredicates.isDelete(isDelete);
          
          Page<Place> places = repo.findAll(predicate, paging.getPageable());
          
          for(Place placeEvent:places){
        	  logger.info(placeEvent.toString());
          }
						 
		  table.setRows(places.getContent()); 
		  
		  table.setiTotalDisplayRecords(places.getTotalElements());
		  logger.info("결과:{}",table.getiDisplayLength());
		  logger.info("결과:{}",table.getiTotalRecords());
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


	
}
