package net.itsplace.admin.dao;

import java.util.List;

import javax.annotation.Resource;

import net.itsplace.domain.BasCd;
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
	public List<BasCd> getGrpBascdList() throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("getGrpBascdList");
	}

	@Override
	public List<BasCd> getBascdList(String grpCd) throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("getBascdList", grpCd);
	}
	
}
