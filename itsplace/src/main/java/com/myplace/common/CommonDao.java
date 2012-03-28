package com.myplace.common;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;



public interface CommonDao {
	/* 주소 리스트를 가져온다*/
	public List<Address> getAddressList(Map<String, Object> param) throws DataAccessException;
	public Integer getFoundRows() throws DataAccessException;

	public List<Notice> getNoticeList(Map<String, Object> param) throws DataAccessException;
	public Notice getNotice(String nid) throws DataAccessException;
	public void saveNotice(Notice notice) throws DataAccessException;
	public void updateNotice(Notice notice) throws DataAccessException;
	public void hitNotice(String nid) throws DataAccessException;
	
}
