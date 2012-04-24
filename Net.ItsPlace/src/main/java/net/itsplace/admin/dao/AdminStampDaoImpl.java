package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.StampType;
import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("AdminStampDao")
public class AdminStampDaoImpl extends SqlMapClientDaoSupport implements AdminStampDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminStampDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<StampType> getStamptypeList(Map<String, Object> param) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getStampTypeList",param);
	}

	@Override
	public void saveStampType(StampType stampType) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveStampType",stampType);		
	}

	@Override
	public void editStampType(StampType stampType) throws DataAccessException {
		getSqlMapClientTemplate().update("editStampType",stampType);
	}

	@Override
	public StampType getStampType(int sid) throws DataAccessException {
		return (StampType) getSqlMapClientTemplate().queryForObject("getStampType", sid);
	}
	
}
