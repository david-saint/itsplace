package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.PlaceComment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
@Repository("placeCommentDao")
public class PlaceCommentDaoImpl  extends SqlMapClientDaoSupport implements  PlaceCommentDao {

	private static final Logger logger = LoggerFactory.getLogger(PlaceCommentDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<PlaceComment> getPlaceCommentList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("place.getPlaceCommentList",param);
	}

	@Override
	public void savePlaceComment(PlaceComment placeComment)
			throws DataAccessException {
		getSqlMapClientTemplate().insert("place.savePlaceComment",placeComment);
	}

	@Override
	public void deletePlaceComment(int cid) throws DataAccessException {
		getSqlMapClientTemplate().update("place.deletePlaceComment",cid);
	}

}
