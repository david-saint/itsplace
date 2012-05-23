package net.itsplace.place.dao;

import javax.annotation.Resource;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.Place;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;


@Repository("placeInfoDao")
public class PlaceInfoDaoImpl  extends SqlMapClientDaoSupport implements  PlaceInfoDao {

	private static final Logger logger = LoggerFactory.getLogger(PlaceStampDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public void editAuthCode(Authcode authcode) throws DataAccessException {
		getSqlMapClientTemplate().update("place.editAuthCode",authcode);		
	}

	@Override
	public Authcode getAuthCode(int fid) throws DataAccessException {
	
		return 	(Authcode)getSqlMapClientTemplate().queryForObject("place.getAuthCode",fid)	;
	}

	@Override
	public Place getPlace(int fid) throws DataAccessException {
		return (Place) getSqlMapClientTemplate().queryForObject("place.getPlace", fid);
	}

}
