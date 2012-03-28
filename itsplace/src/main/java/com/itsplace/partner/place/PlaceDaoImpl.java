package com.itsplace.partner.place;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.itsplace.partner.place.PlaceComment;
import com.myplace.partner.franchiser.FranchiserMember;

@Repository("PlaceDAO")
public class PlaceDaoImpl extends SqlMapClientDaoSupport implements PlaceDao {
	//protected final Log log = LogFactory.getLog(getClass());
	private static final Logger logger = LoggerFactory.getLogger(PlaceDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public List<PlaceComment> getPlaceCommentRecentList(
			Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("getPlaceCommentRecentList", param);
	}

	@Override
	public List<FranchiserMember> getPlaceListByDong(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getPlaceListByDong", param);
	}
	
	
	



	
	



}
