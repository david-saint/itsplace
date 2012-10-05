package com.mincoms.book.admin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mincoms.book.dao.BookDaoImpl;
import com.mincoms.book.domain.dto.DtoRentalStatics;

@Repository("StaticsDao")
public class StaticsImpl extends SqlMapClientDaoSupport implements StaticsDao{
	private static final Logger logger = LoggerFactory.getLogger(StaticsDao.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	@Override
	public List<DtoRentalStatics> getRentalStatics(Map<String, Object> param)
			throws DataAccessException {
		return  getSqlMapClientTemplate().queryForList("statics.getRentalStatics",param);
	}

	@Override
	public long getRentalStaticsCount(Map<String, Object> param)
			throws DataAccessException {
		 	return (Long) getSqlMapClientTemplate().queryForObject("statics.getRentalStaticsCount",param);
	}
}
