package net.itsplace.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.itsplace.domain.Address;
import net.itsplace.domain.Bascd;
import net.itsplace.user.UserDao;
import net.itsplace.user.UserDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("CommonDao")
public class CommonDaoImpl extends SqlMapClientDaoSupport implements CommonDao {
	private static final Logger logger = LoggerFactory.getLogger(CommonDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	
	@Override
	public List<Bascd> getBascdList(String grpCd) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("common.getBascdList",grpCd);
	}


	@Override
	public List<Bascd> getBascdALL() throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("common.getBascdALL");
	}


	@Override
	public Integer getFoundRows() throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject("common.getFoundRows");
	}


	@Override
	public List<Address> getAddressListByDong(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("common.getAddressListByDong",param);
	}


	@Override
	public List<Address> getAddressListByDoroname(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("common.getAddressListByDoroname",param);
	}


	

	
	
}
