package net.itsplace.admin.dao;

import javax.annotation.Resource;

import net.itsplace.admin.dao.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("UserDao")
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
}
