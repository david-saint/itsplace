package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.PlaceMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("placeMenuDao")
public class PlaceMenuDaoImpl  extends SqlMapClientDaoSupport implements  PlaceMenuDao {

	private static final Logger logger = LoggerFactory.getLogger(PlaceMenuDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public void saveMenu(PlaceMenu placeMenu) throws DataAccessException {
		getSqlMapClientTemplate().insert("place.savePlaceMenu",placeMenu);		
	}

	@Override
	public void editMenu(PlaceMenu placeMenu) throws DataAccessException {
		getSqlMapClientTemplate().update("place.editPlaceMenu",placeMenu);		
	}

	@Override
	public void deleteMenu(int mnid) throws DataAccessException {
		getSqlMapClientTemplate().update("place.deleteMenu",mnid);			
	}

	@Override
	public PlaceMenu getMenu(int mnid) throws DataAccessException {
		return (PlaceMenu) getSqlMapClientTemplate().queryForObject("place.getPlaceMenu", mnid);
	}

	@Override
	public List<PlaceMenu> getMenuList(Map<String, Object> param) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("place.getMenuList",param);
	}

}
