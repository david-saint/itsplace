package com.itsplace.partner.place.event;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.itsplace.partner.place.PlaceDaoImpl;


@Repository("EventDAO")
public class EventDaoImpl extends SqlMapClientDaoSupport implements EventDao {


private static final Logger logger = LoggerFactory.getLogger(PlaceDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<Event> getEventList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getEventList",param);
	}
	
	@Override
	public Event getEvent(String eid) throws DataAccessException {
		
		return (Event)getSqlMapClientTemplate().queryForObject("getEvent",eid);
	}
	
	@Override
	public int saveEvent(Event event) throws DataAccessException {

		return (Integer)getSqlMapClientTemplate().insert("saveEvent",event);
	}
	
	@Override
	public void updateEvent(Event event) throws DataAccessException {
		getSqlMapClientTemplate().update("updateEvent",event);		
	}
}