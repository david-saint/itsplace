package com.myplace.common;

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

@Repository("CommonDAO")
public class CommonDaoImpl extends SqlMapClientDaoSupport implements CommonDao {
	//protected final Log log = LogFactory.getLog(getClass());
	private static final Logger logger = LoggerFactory.getLogger(CommonDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Address> getAddressList(Map<String, Object> param)
			throws DataAccessException {
		
		List<Address> addressList =  getSqlMapClientTemplate().queryForList("getAddressListByDong", param);
		if(addressList == null || addressList.size()<=0){
			return getSqlMapClientTemplate().queryForList("getAddressListByDoroname", param);
		}else{
			return addressList;
		}
	}

	public Integer getFoundRows()
			throws DataAccessException {		
		return (Integer) getSqlMapClientTemplate().queryForObject("getFoundRows");
	}

	@Override
	public List<Notice> getNoticeList(Map<String, Object> param)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getNoticeList", param);
	}

	@Override
	public Notice getNotice(String nid) throws DataAccessException {
		
		return (Notice) getSqlMapClientTemplate().queryForObject("getNotice",nid);
	}

	@Override
	public void saveNotice(Notice notice) throws DataAccessException {
		getSqlMapClientTemplate().insert("saveNotice",notice);
		
	}

	@Override
	public void updateNotice(Notice notice) throws DataAccessException {
		getSqlMapClientTemplate().update("updateNotice",notice);
		
	}
	@Override
	public void hitNotice(String nid) throws DataAccessException {
			getSqlMapClientTemplate().update("hitNotice",nid);
			
	}




	
	



}
