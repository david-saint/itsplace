package net.itsplace.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.repository.PlaceReviewRepository;
import net.itsplace.util.ImageService;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface PlaceReviewService {
	public int savePlaceReview(PlaceReview PlaceReview) ;
	public void editPlaceReview(PlaceReview PlaceReview) ;
	public void deletePlaceReview(int rid)  ;
	public void recoveryPlaceReview(int rid) ;
	public PlaceReview getPlaceReview(int rid)  ;
	public List<PlaceReview> getPlaceReviewAll(int fid);
	public PlaceReview savePlaceReviewImage(ImageFileUpload file);
	DataTable getPlaceReviewList(JpaPaging paging, int fid);
}
@Service("PlaceReviewService")
 class PlaceReviewServiceImpl implements PlaceReviewService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceReviewService.class);
	@Autowired
	private BaseService commonService;
	@Autowired
	private ImageService imageService;
	@Autowired
	PlaceReviewRepository repo;
	@Autowired
	PlaceRepository placeRepo;

	@Override
	public int savePlaceReview(PlaceReview placeReview) {
		PlaceReview saved = repo.save(placeReview);
		return saved.getRid();
	}

	@Override
	public void editPlaceReview(PlaceReview PlaceReview) {
		repo.save(PlaceReview);
	}

	@Override
	public void deletePlaceReview(int rid) {
		PlaceReview review = repo.findOne(rid);
		review.setIsDelete(true);
		repo.save(review);
	}

	@Override
	public PlaceReview getPlaceReview(int rid) {
		return repo.findOne(rid);
	}

	@Override
	public DataTable getPlaceReviewList(JpaPaging paging, int fid) {
		  DataTable<PlaceReview> table = new DataTable<PlaceReview>(paging);
    		
          Page<PlaceReview> lists = repo.findAll(paging.getPageable());
         			 
          
		  table.setRows(lists.getContent()); 
		  
		  table.setiTotalDisplayRecords(lists.getTotalElements());
		  return table;
	}
	
	@Override
	public List<PlaceReview> getPlaceReviewAll(int fid){
		return repo.findByPlace(placeRepo.findOne(fid));
	}
	

	
	@Override
	public void recoveryPlaceReview(int rid) {
		PlaceReview review = repo.findOne(rid);
		review.setIsDelete(false);
		repo.save(review);
	}
	@Override
	public PlaceReview savePlaceReviewImage(ImageFileUpload file) {
		
		String orinalImagePath="";
		PlaceReview placeReview = new PlaceReview();
		Place place = placeRepo.findOne(file.getFid());
		try {
			orinalImagePath = imageService.convertToPng(file.getFile(),280,230);
			placeReview.setFilePath(orinalImagePath);
			//placeReview.setImageHost(commonService.getBasecd().getMediaImageHost());			
			placeReview.setPlace(place);
			
			if(file.getId() <= 0 ){
				int _rid = savePlaceReview(placeReview);				
				logger.info("savePlaceReviewImage _rid:{}",_rid);
				placeReview.setRid(_rid);
		
			}else{
				placeReview.setRid(file.getId());
				repo.save(placeReview);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return placeReview;
	}
	
}
