package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;
import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("AdminPlaceDao")
public class AdminPlaceDaoImpl extends SqlMapClientDaoSupport implements AdminPlaceDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Place> getPlaceList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("admin.getPlaceList",param);
	}

	@Override
	public void enablePlace(int fid) throws DataAccessException {
		getSqlMapClientTemplate().update("admin.enablePlace",fid);
	}

	@Override
	public void disablePlace(int fid) throws DataAccessException {
		getSqlMapClientTemplate().update("admin.disablePlace",fid);
	}

	@Override
	public void editPlace(Place place) throws DataAccessException {
		getSqlMapClientTemplate().update("admin.editPlace",place);		
	}

	@Override
	public Place getPlace(int fid) throws DataAccessException {
		return (Place) getSqlMapClientTemplate().queryForObject("admin.getPlace",fid);
	}

	@Override
	public int savePlace(Place place) throws DataAccessException {
		return (Integer)getSqlMapClientTemplate().insert("admin.savePlace",place);
	}

	@Override
	public void editPlacerQrcode(Place place) throws DataAccessException {
		getSqlMapClientTemplate().update("admin.editPlacerQrcode",place);
		
	}

	@Override
	public void editMcode(Place place) throws DataAccessException {
		getSqlMapClientTemplate().update("admin.editMcode",place);		
	}

	@Override
	public void editAuthCode(Place place) throws DataAccessException {
		getSqlMapClientTemplate().update("admin.editAuthCode",place);				
	}

	@Override
	public String getMcode(int fid) throws DataAccessException {
		return (String) getSqlMapClientTemplate().queryForObject("admin.getMcode",fid);
	}

	

}
