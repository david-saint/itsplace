package net.itsplace.admin.dao;

import java.util.List;

import javax.annotation.Resource;

import net.itsplace.domain.Bascd;
import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("AdminBaseDao")
public class AdminBaseDaoImpl extends SqlMapClientDaoSupport implements AdminBaseDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminBaseDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Bascd> getGrpBascdList() throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getGrpBascdList");
	}

	@Override
	public List<Bascd> getBascdList(String grpCd) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getBascdList", grpCd);
	}

	@Override
	public void saveBascd(Bascd bascd) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveBascd",bascd);
	}

	@Override
	public void editBascd(Bascd bascd) throws DataAccessException {
		getSqlMapClientTemplate().update("editBascd",bascd);		
	}

	@Override
	public Bascd getBascd(int fid) throws DataAccessException {
		return (Bascd) getSqlMapClientTemplate().queryForObject("getBascd",fid);
	}

	@Override
	public void delete(int fid) throws DataAccessException {
		getSqlMapClientTemplate().update("delete",fid);
	}
	
}
