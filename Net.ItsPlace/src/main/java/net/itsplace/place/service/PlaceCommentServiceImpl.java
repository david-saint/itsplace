package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceComment;
import net.itsplace.place.dao.PlaceCommentDao;
import net.itsplace.place.dao.PlaceInfoDao;
import net.itsplace.user.User;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("placeCommentService")
public class PlaceCommentServiceImpl  implements PlaceCommentService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceCommentServiceImpl.class);
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	private PlaceCommentDao placeCommentDao;

	@Override
	public DataTable<PlaceComment> getPlaceCommentList(String columns[],Integer iDisplayStart,Integer iDisplayLength,Integer iSortCol_0,String sSortDir_0, String sSearch,int fid){
		  DataTable<PlaceComment> table = iDisplayLength != null ?
                  new DataTable<PlaceComment>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                  new DataTable<PlaceComment>(columns, sSortDir_0, iDisplayStart);
		  Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
		  param.put("sortDirection", sSortDir_0);
		  param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		  param.put("search", sSearch);
			
		  List<PlaceComment> userList= placeCommentDao.getPlaceCommentList(param);
		  
		  pagingManaer.setTotalCount(pagingManaer.getFoundRows());
		 
		  table.setRows(userList); 
		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		return table;
	}

	@Override
	public void savePlaceComment(PlaceComment placeComment) {
		placeCommentDao.savePlaceComment(placeComment);
	}

	@Override
	public void deletePlaceComment(int cid) {
		placeCommentDao.deletePlaceComment(cid);		
	}

}
