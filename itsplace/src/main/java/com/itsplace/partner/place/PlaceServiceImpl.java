package com.itsplace.partner.place;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itsplace.partner.place.PlaceComment;
import com.myplace.partner.franchiser.FranchiserMember;
import com.myplace.util.DurationFromNow;
import com.myplace.util.Encrypt;


@Service("PlaceService")
public class PlaceServiceImpl implements PlaceService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);
	
	@Autowired
	private PlaceDao placeDao;

	public List<PlaceComment> getPlaceCommentRecentList(
			Map<String, Object> param) {
		
		List<PlaceComment> list = placeDao.getPlaceCommentRecentList(param);
		for(Integer i = 0 ; i < list.size(); i++){
			PlaceComment pc  = list.get(i);
			pc.setWriteDate(DurationFromNow.getTimeDiffLabel(pc.getInpdate()));
			list.set(i,pc);
		}
		return list;
	}

	@Override
	public List<FranchiserMember> getPlaceListByDong(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return placeDao.getPlaceListByDong(param);
	}
	
	

	
	
}
