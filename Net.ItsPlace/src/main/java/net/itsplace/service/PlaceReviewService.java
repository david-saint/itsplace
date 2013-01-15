package net.itsplace.service;

import java.util.List;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;

import org.springframework.dao.DataAccessException;

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
