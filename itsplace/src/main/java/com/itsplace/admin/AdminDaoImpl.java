package com.itsplace.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.itsplace.partner.place.PlaceComment;
import com.myplace.partner.franchiser.FranchiserMember;

@Repository("AdminDAO")
public class AdminDaoImpl extends SqlMapClientDaoSupport implements AdminDao {
	//protected final Log log = LogFactory.getLog(getClass());
	private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public void updateFranchiserAuth(FranchiserMember franchiserMember)
			throws DataAccessException {
		getSqlMapClientTemplate().update("updateFranchiserAuth",franchiserMember);
		
	}
	
	
	


	
	



}
