package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.admin.dao.AdminUserDao;
import net.itsplace.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;


@Repository("AdminUserDao")
public class AdminUserDaoImpl extends SqlMapClientDaoSupport implements AdminUserDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminUserDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<User> getUserList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUserList", param);
		
	}
}

