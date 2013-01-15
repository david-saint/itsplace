package net.itsplace.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.PlaceComment;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;

public interface PlaceCommentService {
	public DataTable<PlaceComment> getPlaceCommentList(String columns[],Integer iDisplayStart,Integer iDisplayLength,Integer iSortCol_0,String sSortDir_0, String sSearch, int fid);
	public void savePlaceComment(PlaceComment placeComment);
	public PlaceComment getPlaceComment(int cid);
	public boolean deletePlaceComment(int cid) ;
	public Page<PlaceComment> findPlaceCommentList(JpaPaging paging,int fid);
}
