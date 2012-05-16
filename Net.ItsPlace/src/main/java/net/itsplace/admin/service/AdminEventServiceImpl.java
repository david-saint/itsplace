package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.admin.dao.AdminEventDao;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.user.User;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminEventService")
public class AdminEventServiceImpl implements AdminEventService{
	private static final Logger logger = LoggerFactory.getLogger(AdminEventServiceImpl.class);
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	private AdminEventDao adminEventeDao;
	
	@Override
	public List<PlaceEvent> getPlaceEventList(int fid) {
		return adminEventeDao.getPlaceEventList(fid);
	}

	@Override
	public void savePlaceEvent(PlaceEvent placeEvent) {
		adminEventeDao.savePlaceEvent(placeEvent);
	}

	@Override
	public void editPlaceEvent(PlaceEvent placeEvent) {
		adminEventeDao.editPlaceEvent(placeEvent);
	}

	@Override
	public void deletePlaceEvent(int eid) {
		adminEventeDao.deletePlaceEvent(eid);
	}

	@Override
	public DataTable getPlaceEventList(String[] columns, Integer iDisplayStart,
			Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0,
			String sSearch, int fid) {
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


	

}
