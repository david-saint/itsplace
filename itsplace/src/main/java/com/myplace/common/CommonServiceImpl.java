package com.myplace.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myplace.util.Encrypt;
import com.myplace.util.PagingManager;


@Service("CommonService")
public class CommonServiceImpl implements CommonService {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CommonDao commonDao;


	/* 주소 리스트를 가져온다*/	
	public List<Address> getAddressList(Map<String, Object> param) {
		

		return commonDao.getAddressList(param);
	}
	
	public Integer getFoundRows(){
		
		int totalCount = commonDao.getFoundRows();
		
			
		return totalCount;
	}

	@Override
	public List<Notice> getNoticeList(Map<String, Object> param)
			throws DataAccessException {
		return commonDao.getNoticeList(param);
	}

	@Override
	public Notice getNotice(String nid) throws DataAccessException {
		return commonDao.getNotice(nid);
	}

	@Override
	public void saveNotice(Notice notice) throws DataAccessException {
		commonDao.saveNotice(notice);
	}

	@Override
	public void updateNotice(Notice notice) throws DataAccessException {
		commonDao.updateNotice(notice);
	}

	@Override
	public void hitNotice(String nid) throws DataAccessException {
		commonDao.hitNotice(nid);
	}
	
}
