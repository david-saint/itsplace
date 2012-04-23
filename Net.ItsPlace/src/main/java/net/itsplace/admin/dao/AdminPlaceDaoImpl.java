package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.Franchiser;
import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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

	@Override
	public List<Franchiser> getFranchiserList(Map<String, Object> param) throws DataAccessException {
		return  getSqlMapClientTemplate().queryForList("getFranchiserList",param);
	}
}
