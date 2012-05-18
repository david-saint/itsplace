package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceUser;
import net.itsplace.user.User;
import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("PlaceUserDao")
public class PlaceUserDaoImpl extends SqlMapClientDaoSupport implements PlaceUserDao {
	private static final Logger logger = LoggerFactory.getLogger(PlaceUserDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<PlaceUser> getPlaceUserList(Map<String, Object> param) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("place.getPlaceUserList",param);
	}

	@Override
	public void savePlaceUser(PlaceUser placeUser) throws DataAccessException {
		getSqlMapClientTemplate().insert("place.savePlaceUser",placeUser);
	}

	

	@Override
	public void deletePlaceUser(int uid) throws DataAccessException {
		getSqlMapClientTemplate().update("place.deletePlaceUser",uid);
	}

	@Override
	public List<PlaceUser> getPlaceListByEmail(String email)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("place.getPlaceListByEmail",email);
	}

	@Override
	public List<Place> getFranchiserListByEmail(String email)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("place.getFranchiserListByEmail",email);
	}

	@Override
	public PlaceUser getPlaceUser(PlaceUser placeUser)
			throws DataAccessException {
		return (PlaceUser) getSqlMapClientTemplate().queryForObject("place.getPlaceUser",placeUser);
	}
	
	
	
}
