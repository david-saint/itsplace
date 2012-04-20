package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.admin.dao.AdminUserDao;
import net.itsplace.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


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
	
	public User getUser(String email) throws DataAccessException{			
		return (User)getSqlMapClientTemplate().queryForObject("getUser",email);		
	}
	
	public void saveUser(User user) throws DataAccessException {		
		getSqlMapClientTemplate().insert("setUser",user);
	}

	public void updateUser(User user) throws DataAccessException {
		getSqlMapClientTemplate().update("updateUser",user);
		
	}
}

