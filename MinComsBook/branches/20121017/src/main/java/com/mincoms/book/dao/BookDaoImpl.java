package com.mincoms.book.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.dto.DtoBookInfo;

@Repository("BookDao")
public class BookDaoImpl extends SqlMapClientDaoSupport implements BookDao{
	private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<DtoBookInfo> getBookInfoReservationList(Map<String, Object> parameter) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("book.getBookInfoReservationList",parameter);
	}

	@Override
	public long getBookInfoReservationListCount(Map<String, Object> parameter) throws DataAccessException {
		
		long totalCount ;
			try{				
				totalCount = (Long) getSqlMapClientTemplate().queryForObject("book.getBookInfoReservationListCount",parameter);
			}catch(NullPointerException e){
				totalCount = 0;
			}
			return totalCount;
	}

}
