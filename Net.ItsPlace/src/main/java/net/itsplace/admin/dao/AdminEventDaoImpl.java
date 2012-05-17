package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.Pmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("AdminEventDao")
public class AdminEventDaoImpl extends SqlMapClientDaoSupport implements AdminEventDao {
private static final Logger logger = LoggerFactory.getLogger(AdminEventDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<PlaceEvent> getPlaceEventList(int fid) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getPlaceEventList",fid);
	}

	@Override
	public void savePlaceEvent(PlaceEvent placeEvent)
			throws DataAccessException {
		getSqlMapClientTemplate().insert("savePlaceEvent",placeEvent);
	}

	@Override
	public void editPlaceEvent(PlaceEvent placeEvent)
			throws DataAccessException {
		getSqlMapClientTemplate().update("editPlaceEvent",placeEvent);
	}

	@Override
	public void deletePlaceEvent(int eid) throws DataAccessException {
		getSqlMapClientTemplate().update("deletePlaceEvent",eid);
	}

	@Override
	public List<PlaceEvent> getPlaceEventList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getPlaceEventDataTable", param);
	}

	@Override
	public PlaceEvent getPlaceEvent(int eid) throws DataAccessException {
		return (PlaceEvent)getSqlMapClientTemplate().queryForObject("getPlaceEvent",eid);
	}

	
}
