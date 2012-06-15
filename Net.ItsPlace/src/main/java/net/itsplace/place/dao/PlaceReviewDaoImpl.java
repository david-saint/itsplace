package net.itsplace.place.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.PlaceReview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("placeReviewDao")
public class PlaceReviewDaoImpl  extends SqlMapClientDaoSupport implements  PlaceReviewDao {

	private static final Logger logger = LoggerFactory.getLogger(PlaceReviewDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public void savePlaceReview(PlaceReview placeReview)
			throws DataAccessException {
		getSqlMapClientTemplate().insert("savePlaceReview",placeReview);		
	}

	@Override
	public void editPlaceReview(PlaceReview placeReview)
			throws DataAccessException {
		getSqlMapClientTemplate().update("place.editPlaceReview",placeReview);				
	}

	@Override
	public void deletePlaceReview(int rid) throws DataAccessException {
		getSqlMapClientTemplate().update("place.deleteMenu", rid);		
	}

	@Override
	public PlaceReview getPlaceReview(int rid) throws DataAccessException {
		return (PlaceReview) getSqlMapClientTemplate().queryForObject("place.getMenuList",rid);
	}

	@Override
	public List<PlaceReview> getPlaceReviewList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("place.getPlaceReviewList",param);
	}


}
