package net.itsplace.place.service;

import java.util.List;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;

import org.springframework.dao.DataAccessException;

public interface PlaceReviewService {
	public int savePlaceReview(PlaceReview PlaceReview) ;
	public void editPlaceReview(PlaceReview PlaceReview) ;
	public void deletePlaceReview(int rid)  ;
	public void recoveryPlaceReview(int rid) ;
	public PlaceReview getPlaceReview(int rid)  ;
	public DataTable getPlaceReviewList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch,int fid);
	public List<PlaceReview> getPlaceReviewListAll(int fid);
	public PlaceReview savePlaceReviewImage(ImageFileUpload file);
}
