package net.itsplace.admin.dao;

import javax.annotation.Resource;

import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("AdminSupportDao")
public class AdminSupportDaoImpl extends SqlMapClientDaoSupport implements AdminSupportDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminSupportDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
}
