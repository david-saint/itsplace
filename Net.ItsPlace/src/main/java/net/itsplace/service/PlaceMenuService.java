package net.itsplace.service;

import java.util.List;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.user.UserInfo;

import org.springframework.dao.DataAccessException;

public interface PlaceMenuService {
	public int saveMenu(PlaceMenu placeMenu) ;
	public PlaceMenu savePlaceMenuImage(ImageFileUpload file) ;
	public void editMenu(PlaceMenu placeMenu) ;
	public void deleteMenu(int mnid)  ;
	public PlaceMenu getMenu(int mnid)  ;
	DataTable<PlaceMenu> getMenuList(JpaPaging paging, int fid);
	public List<PlaceMenu> findByPlace(int fid);
	
}