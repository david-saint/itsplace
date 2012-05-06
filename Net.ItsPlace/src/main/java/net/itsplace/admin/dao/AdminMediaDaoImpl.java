package net.itsplace.admin.dao;

import javax.annotation.Resource;

import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("AdminMediaDao")
public class AdminMediaDaoImpl extends SqlMapClientDaoSupport implements AdminMediaDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminMediaDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	@Override
	public void savePlaceMedia(Pmedia media) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveMedia",media);
	}
	@Override
	public void updatePlaceImage(Place place) throws DataAccessException {
		getSqlMapClientTemplate().insert("updatePlaceImage",place);		
	}
	@Override
	public void updatePlaceMedia(Pmedia media) throws DataAccessException {
		getSqlMapClientTemplate().insert("updatePlaceMedia",media);
	}

}
