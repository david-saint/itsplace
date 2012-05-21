package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("StampDao")
public class StampDaoImpl extends SqlMapClientDaoSupport implements StampDao{

	private static final Logger logger = LoggerFactory.getLogger(StampDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Place> getPlaceStampedList(String email)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceStampedList",email);
	}

	@Override
	public List<PlaceStamp> getStampedList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getStampedList",param);
	}


}
