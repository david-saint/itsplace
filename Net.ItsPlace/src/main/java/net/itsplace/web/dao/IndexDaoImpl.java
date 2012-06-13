package net.itsplace.web.dao;

import java.util.List;

import javax.annotation.Resource;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("IndexDao")
public class IndexDaoImpl extends SqlMapClientDaoSupport implements IndexDao{

	private static final Logger logger = LoggerFactory.getLogger(IndexDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Place> getRecentPlaceList(int limit) throws DataAccessException {
		return  getSqlMapClientTemplate().queryForList("web.getRecentPlaceList", limit);
	}

	@Override
	public List<PlaceEvent> getRecentEventList(int limit)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getRecentEventList", limit);	}
}
