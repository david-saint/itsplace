package com.myplace.partner.franchiser.stamp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.myplace.partner.franchiser.FranchiserDaoImpl;

@Repository("StampDao")
public class StampDaoImpl extends SqlMapClientDaoSupport  implements StampDao {
private static final Logger logger = LoggerFactory.getLogger(FranchiserDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	
	@Override
	public void saveStamp(Stamp stamp) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveStamp",stamp);
		
	}

	@Override
	public List<Stamp> getUserStampListByMobile(Stamp stamp)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUserStampListByMobile",stamp);

	}

	@Override
	public List<Stamp> getStamptypeList() throws DataAccessException {
		
		return getSqlMapClientTemplate().queryForList("getStamptypeList");
	}


	@Override
	public void saveStampRegister(Stamp stamp) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveStampRegister",stamp);
		
		
	}


	@Override
	public Stamp getStampRegister(Stamp stamp) throws DataAccessException {
		return (Stamp)getSqlMapClientTemplate().queryForObject("getStampRegister",stamp);
		
		
	}


	@Override
	public void updateStampRegister(Stamp stamp) throws DataAccessException {
		getSqlMapClientTemplate().update("updateStampRegister",stamp);
		
		
	}


	@Override
	public Stamp getStamptypeRegister(int fid) throws DataAccessException {
		// TODO Auto-generated method stub
		return (Stamp)getSqlMapClientTemplate().queryForObject("getStamptypeRegister",fid);
	}


	@Override
	public void deleteStamp(String pid) throws DataAccessException {
		getSqlMapClientTemplate().update("deleteStamp",pid);
		
	}


	@Override
	public List<Stamp> getUserStampListByEmail(Stamp stamp)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUserStampListByEmail",stamp);

	}


	@Override
	public List<Stamp> getUserStampListByMain()
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUserStampListByMain");
	}


	@Override
	public void updateStamp(Stamp stamp) throws DataAccessException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("updateStamp",stamp);
	}


	@Override
	public List<Stamp> getUserStampListByFid(Stamp stamp) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUserStampListByFid",stamp);
	}


	@Override
	public List<Stamp> getUserStampListByFcode(Stamp stamp)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUserStampListByFcode",stamp);
	}

	

}
