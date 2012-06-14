package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.admin.dao.AdminBaseDaoImpl;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceReview;
import net.itsplace.domain.Stamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("PlaceDao")
public class PlaceDaoImpl extends SqlMapClientDaoSupport implements PlaceDao {
	private static final Logger logger = LoggerFactory.getLogger(PlaceDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public Place getPlace(int fid)
			throws DataAccessException {
		return (Place) getSqlMapClientTemplate().queryForObject("web.getPlace",fid);
	}

	@Override
	public List<PlaceComment> getPlaceCommentList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceCommentList", param);
	}

	@Override
	public List<PlaceEvent> getPlaceEventListByPlace(int fid)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceEventListByPlace", fid);
	}

	@Override
	public List<Stamp> getPlaceStampListByPlace(int fid)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceStampListByPlace", fid);
	}

	@Override
	public List<PlaceMenu> getPlaceMenuList(int fid) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceMenuList", fid);
	}
	@Override
	public List<PlaceReview> getPlaceReviewList(int fid)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceReviewList", fid);
	}

	@Override
	public List<PlaceMedia> getPlaceMediaListt(int fid)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("web.getPlaceMediaListt", fid);
	}



}
