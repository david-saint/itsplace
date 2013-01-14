package net.itsplace.service;

import java.util.Date;
import java.util.List;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.repository.PlaceEventPredicates;
import net.itsplace.repository.PlaceEventRepository;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mysema.query.types.Predicate;


@Service("AdminEventService")
public class PlaceEventService implements IPlaceEventService{
	private static final Logger logger = LoggerFactory.getLogger(PlaceEventService.class);
	@Autowired
	private PagingManager pagingManaer;
	
	
	@Autowired
	PlaceEventRepository repo;
	@Autowired
	PlaceRepository placeRepo;
	@Override
	public List<PlaceEvent> getPlaceEventList(int fid) {

		Place place = new Place();
		place.setFid(fid);
		return repo.findByPlace(place);
	}

	@Override
	public void savePlaceEvent(PlaceEvent placeEvent) {
		
		//adminEventeDao.savePlaceEvent(placeEvent);
		repo.save(placeEvent);
	}

	@Override
	public void editPlaceEvent(PlaceEvent placeEvent) {
		repo.save(placeEvent);
	}

	@Override
	public void deletePlaceEvent(int eid) {
		PlaceEvent placeEvent = repo.findOne(eid);
		placeEvent.setIsDelete(true);
		placeEvent.setEditDate(new Date());
		repo.save(placeEvent);
	}

	/*@Override
	public DataTable getPlaceEventList(String[] columns, 
			Integer iDisplayStart,
			Integer iDisplayLength, 
			Integer iSortCol_0, 
			String sSortDir_0,
			String sSearch, 
			int fid) {
		    DataTable<PlaceEvent> table = iDisplayLength != null ?
                  new DataTable<PlaceEvent>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                  new DataTable<PlaceEvent>(columns, sSortDir_0, iDisplayStart);
  
		
		  Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
		  param.put("sortDirection", sSortDir_0);
		  param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		  param.put("search", sSearch);
		  param.put("fid", fid);
			
		  List<PlaceEvent> placeEventList= adminEventeDao.getPlaceEventList(param);
		  
		  pagingManaer.setTotalCount(pagingManaer.getFoundRows());
			
			
		 
		  table.setRows(placeEventList); 
		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		  
		  return table;
	}
*/
	@Override
	public PlaceEvent getPlaceEvent(int eid) {
		return repo.findOne(eid);
	}

	/*@Override
	public DataTable getPlaceEventListAll(String[] columns,
			Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0,
			String sSortDir_0, String sSearch) {
	    DataTable<PlaceEvent> table = iDisplayLength != null ?
                new DataTable<PlaceEvent>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                new DataTable<PlaceEvent>(columns, sSortDir_0, iDisplayStart);

		
		  Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
		  param.put("sortDirection", sSortDir_0);
		  param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		  param.put("search", sSearch);
		
		  List<PlaceEvent> placeEventList= adminEventeDao.getPlaceEventListAll(param);
		  
		  pagingManaer.setTotalCount(pagingManaer.getFoundRows());
			
			
		 
		  table.setRows(placeEventList); 
		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		  
		  return table;
	}
*/

	@Override
	public DataTable<PlaceEvent> findPlaceEventist(JpaPaging paging, Boolean isDelete) {
		
          DataTable<PlaceEvent> table = new DataTable<PlaceEvent>(paging);
          
          Predicate predicate =  PlaceEventPredicates.isDelete(isDelete);
          
          Page<PlaceEvent> placeEvents = repo.findAll(predicate, paging.getPageable());
          
          for(PlaceEvent placeEvent:placeEvents){
        	  logger.info(placeEvent.toString());
          }
						 
          
		  table.setRows(placeEvents.getContent()); 
		  
		  table.setiTotalDisplayRecords(placeEvents.getTotalElements());
		  logger.info("결과:{}",table.getiDisplayLength());
		  logger.info("결과:{}",table.getiTotalRecords());
		  return table;
	}
	@Override
	public DataTable<PlaceEvent> findByPlace(JpaPaging paging, int fid) {
		
          DataTable<PlaceEvent> table = new DataTable<PlaceEvent>(paging);
          
          
          Predicate predicate =  PlaceEventPredicates.byPlace(placeRepo.findOne(fid));
          
          Page<PlaceEvent> placeEvents = repo.findAll(predicate, paging.getPageable());
          
          for(PlaceEvent placeEvent:placeEvents){
        	  logger.info(placeEvent.toString());
          }
						 
		  table.setRows(placeEvents.getContent()); 
		  
		  table.setiTotalDisplayRecords(placeEvents.getTotalElements());
		  logger.info("결과:{}",table.getiDisplayLength());
		  logger.info("결과:{}",table.getiTotalRecords());
		  return table;
	}

}
