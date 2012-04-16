package net.itsplace.web.dao;

import javax.annotation.Resource;

import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("FeatureDao")
public class FeatureDaoImpl extends SqlMapClientDaoSupport implements FeatureDao{

	private static final Logger logger = LoggerFactory.getLogger(FeatureDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
}
