package net.itsplace.web.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.admin.dao.AdminBaseDao;
import net.itsplace.admin.dao.AdminBaseDaoImpl;
import net.itsplace.domain.Place;

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


}
