package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.PlaceMenu;

import org.springframework.dao.DataAccessException;

public interface PlaceMenuDao {

	public void saveMenu(PlaceMenu placeMenu) throws DataAccessException;
	public void editMenu(PlaceMenu placeMenu) throws DataAccessException;
	public void deleteMenu(int mnid)  throws DataAccessException;
	public PlaceMenu getMenu(int mnid)  throws DataAccessException;
	public List<PlaceMenu> getMenuList(Map<String, Object> param)  throws DataAccessException;
}
