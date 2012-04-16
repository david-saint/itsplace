package net.itsplace.admin.dao;

import javax.annotation.Resource;

import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
