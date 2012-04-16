package net.itsplace.web.dao;

import javax.annotation.Resource;

import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("SupportDao")
public class SupportDaoImpl extends SqlMapClientDaoSupport implements SupportDao{

	private static final Logger logger = LoggerFactory.getLogger(SupportDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
}
