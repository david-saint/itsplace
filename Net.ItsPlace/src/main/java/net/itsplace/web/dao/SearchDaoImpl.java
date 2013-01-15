package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("SearchDao")
public class SearchDaoImpl extends SqlMapClientDaoSupport implements SearchDao{

	private static final Logger logger = LoggerFactory.getLogger(SearchDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * 가맹점 정보 가져오기
	 * @param p
	 * @return
	 */
	public List<Place> placeInfo(Place p)
	{
		return getSqlMapClientTemplate().queryForList("web.getPlaceInfo", p);
	}

	@Override
	public List<Place> getPlaceList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceList", param);
	}

	@Override
	public List<PlaceEvent> getPlaceEventList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceEventList", param);
	}

	@Override
	public List<Place> getPlaceListByTile(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("web.getPlaceListByTile", param);
	}

	
}
