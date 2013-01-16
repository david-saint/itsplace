package net.itsplace.service;

import java.util.Date;
import java.util.List;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.StampType;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.repository.PlaceStampRepository;
import net.itsplace.repository.StampTypeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mysema.query.types.Predicate;

@Service("StampBaseService")
public class StampBaseServiceImpl implements StampBaseService{
	private static final Logger logger = LoggerFactory.getLogger(StampBaseServiceImpl.class);
	
	@Autowired
	private PlaceService placeService;
	@Autowired
	PlaceStampRepository placeStampRepository;
	@Autowired
	StampTypeRepository stampTypeRepository;
	
	@Override
	public void saveStampType(StampType stampType){
		//adminStampDao.saveStampType(stampType);
		stampTypeRepository.save(stampType);
	}

	@Override
	public void editStampType(StampType stampType){
		//adminStampDao.editStampType(stampType);
		stampTypeRepository.save(stampType);
	}

	@Override
	public StampType getStampType(int sid) {
		//return adminStampDao.getStampType(sid);
		return stampTypeRepository.findOne(sid);
	}

	@Override
	public void deleteStampType(int sid) {
		//adminStampDao.deleteStampType(sid);
		StampType stampType = getStampType(sid);
		stampType.setIsDelete(true);
		stampType.setEditDate(new Date());
		stampTypeRepository.save(stampType);
	}

	@Override
	public void restoreStampType(int sid) {
		StampType stampType = getStampType(sid);
		stampType.setIsDelete(false);
		stampType.setEditDate(new Date());
		stampTypeRepository.save(stampType);	
	}

	@Override
	public List<StampType> getStampTypeListAll() {
		return stampTypeRepository.findAll();
	}

	
	@Override
	public DataTable<StampType> getStampTypeList(JpaPaging paging, Boolean isAuth) {
		
          DataTable<StampType> table = new DataTable<StampType>(paging);
          
         // Predicate predicate =  PlacePredicates.isAuth(isAuth);
          
          Page<StampType> list = stampTypeRepository.findAll( paging.getPageable());
						 
		  table.setRows(list.getContent()); 
		  table.setiTotalDisplayRecords(list.getTotalElements());
		  return table;
	}
	
	
	
	@Override
	public List<PlaceStamp> getPlaceStampAll(int fid){
		
		return placeStampRepository.findByPlace(placeService.getPlace(fid));
	}

	@Override
	public void savePlaceStamp(PlaceStamp placeStamp) {
		placeStampRepository.save(placeStamp);
	}

	@Override
	public void editPlaceStamp(PlaceStamp placeStamp) {
		placeStampRepository.save(placeStamp);
	}

	@Override
	public PlaceStamp getPlaceStamp(int stampid) {
		return placeStampRepository.findOne(stampid);
	}

	@Override
	public void deletePlaceStamp(PlaceStamp placeStamp) {
		placeStamp.setIsDelete(true);
		placeStamp.setEditDate(new Date());
		placeStampRepository.save(placeStamp);	
	}

	@Override
	public void restorePlaceStamp(PlaceStamp placeStamp) {
		placeStamp.setIsDelete(false);
		placeStamp.setEditDate(new Date());
		placeStampRepository.save(placeStamp);	
	}
}
