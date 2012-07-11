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
	public int savePlaceReview(PlaceReview placeReview)
			throws DataAccessException {
		return (Integer)getSqlMapClientTemplate().insert("place.savePlaceReview",placeReview);		
	}

	@Override
	public void editPlaceReview(PlaceReview placeReview)
			throws DataAccessException {
		getSqlMapClientTemplate().update("place.editPlaceReview",placeReview);				
	}

	@Override
	public void deletePlaceReview(int rid) throws DataAccessException {
		getSqlMapClientTemplate().update("place.deletePlaceReview", rid);		
	}

	@Override
	public PlaceReview getPlaceReview(int rid) throws DataAccessException {
		return (PlaceReview) getSqlMapClientTemplate().queryForObject("place.getPlaceReview",rid);
	}

	@Override
	public List<PlaceReview> getPlaceReviewList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("place.getPlaceReviewList",param);
	}

	@Override
	public List<PlaceReview> getPlaceReviewListAll(int fid)
			throws DataAccessException {
		return  getSqlMapClientTemplate().queryForList("place.getPlaceReviewListAll",fid);
	}

	@Override
	public void recoveryPlaceReview(int rid) throws DataAccessException {
		getSqlMapClientTemplate().update("place.recoveryPlaceReview", rid);	
		
	}

	@Override
	public void editPlaceReviewImage(PlaceReview PlaceReview)
			throws DataAccessException {
		getSqlMapClientTemplate().update("place.editPlaceReviewImage", PlaceReview);	
		
	}


}
