package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import net.itsplace.common.CommonService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;
import net.itsplace.place.dao.PlaceMenuDao;
import net.itsplace.place.dao.PlaceReviewDao;
import net.itsplace.util.ImageService;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.itsplace.domain.ImageFileUpload;
@Service("PlaceReviewService")
public class PlaceReviewServiceImpl implements PlaceReviewService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceReviewServiceImpl.class);
	@Autowired
	private CommonService commonService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private PlaceReviewDao placeReviewDao;
	@Autowired
	private PagingManager pagingManaer;

	@Override
	public int savePlaceReview(PlaceReview PlaceReview) {
		return placeReviewDao.savePlaceReview(PlaceReview)		;
	}

	@Override
	public void editPlaceReview(PlaceReview PlaceReview) {
		placeReviewDao.editPlaceReview(PlaceReview)		;
	}

	@Override
	public void deletePlaceReview(int rid) {
		placeReviewDao.deletePlaceReview(rid)	;	
	}

	@Override
	public PlaceReview getPlaceReview(int rid) {
		return placeReviewDao.getPlaceReview(rid);
	}

	@Override
	public DataTable getPlaceReviewList(String[] columns, 
			Integer iDisplayStart,
			Integer iDisplayLength, 
			Integer iSortCol_0, 
			String sSortDir_0,
			String sSearch, 
			int fid) {
		    DataTable<PlaceReview> table = iDisplayLength != null ?
                  new DataTable<PlaceReview>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                  new DataTable<PlaceReview>(columns, sSortDir_0, iDisplayStart);
  
		
		  Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
		  param.put("sortDirection", sSortDir_0);
		  param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		  param.put("search", sSearch);
		  param.put("fid", fid);
			
		  List<PlaceReview> placeReviewList= placeReviewDao.getPlaceReviewList(param);
		  
		  pagingManaer.setTotalCount(pagingManaer.getFoundRows());
			
			
		 
		  table.setRows(placeReviewList); 
		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		  
		  return table;
	}

	@Override
	public List<PlaceReview> getPlaceReviewListAll(int fid) {
		return placeReviewDao.getPlaceReviewListAll(fid);
	}

	@Override
	public void recoveryPlaceReview(int rid) {
		placeReviewDao.recoveryPlaceReview(rid)		;
	}
	@Override
	public PlaceReview savePlaceReviewImage(ImageFileUpload file) {
		
		String orinalImagePath="";
		PlaceReview placeReview = new PlaceReview();
		
		try {
			orinalImagePath = imageService.convertToPng(file.getFile(),280,230);
			placeReview.setFilePath(orinalImagePath);
			placeReview.setImageHost(commonService.getBasecd().getMediaImageHost());			
			placeReview.setFid(file.getFid());
			
			if(file.getId() <= 0 ){
				int _rid = savePlaceReview(placeReview);				
				logger.info("savePlaceReviewImage _rid:{}",_rid);
				placeReview.setRid(_rid);
		
			}else{
				placeReview.setRid(file.getId());
				placeReviewDao.editPlaceReviewImage(placeReview);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return placeReview;
	}
	
}
