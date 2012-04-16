package net.itsplace.admin.dao;

import javax.annotation.Resource;

import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("AdminPlaceDao")
public class AdminPlaceDaoImpl extends SqlMapClientDaoSupport implements AdminPlaceDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
}
