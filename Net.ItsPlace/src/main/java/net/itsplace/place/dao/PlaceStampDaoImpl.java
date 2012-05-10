package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.*;
@Repository("placeStampDao")
public class PlaceStampDaoImpl extends SqlMapClientDaoSupport implements PlaceStampDao {

	private static final Logger logger = LoggerFactory.getLogger(PlaceStampDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public void saveStamp(Stamp stamp) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveStamp", stamp);
	}

	@Override
	public void burnStamp(Stamp stamp) throws DataAccessException {
		getSqlMapClientTemplate().insert("burnStamp", stamp);	
	}

	@Override
	public List<Stamp> getPlaceStampUserList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getPlaceStampUserList",param);
	}

	@Override
	public List<PlaceStamp> getPlaceStampListByEmail(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getPlaceStampListByEmail",param);
	}

	@Override
	public List<Stamp> getPlaceStampedListByEmail(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getPlaceStampedListByEmail",param);
	}

	@Override
	public List<PlaceStamp> getPlaceStampList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getPlaceStampList",param);
	}
	
	


}
