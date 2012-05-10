package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceAware;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.itsplace.domain.Authcode;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;
import net.itsplace.place.dao.PlaceInfoDao;
import net.itsplace.place.dao.PlaceStampDao;
import net.itsplace.user.User;
import net.itsplace.user.UserInfo;
import net.itsplace.util.Encrypt;
import net.itsplace.util.PagingManager;
import net.itsplace.util.StringUtil;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


@Service("placeStampService")
public class PlaceStampServiceImpl implements PlaceStampService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceStampServiceImpl.class);
	
	@Autowired
	private PlaceStampDao placeStampDao;
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	private PlaceInfoDao placeInfoDao;
	
	@Override
	public boolean saveStamp(Stamp stamp,String authCode) {
		boolean result = false;
		Authcode dbAuthcode = placeInfoDao.getAuthCode(UserInfo.getFid());
		if(dbAuthcode.getAuthCode()==null){
			result = false;
		}else{		
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			if(md5.isPasswordValid(dbAuthcode.getAuthCode(),authCode, null)){
				placeStampDao.saveStamp(stamp);		
				result = true;
			}else{
				result = false;
			}
		}
		return result;
		
	}

	@Override
	public boolean burnStamp(Stamp stamp,String authCode) {
		boolean result = false;
		Authcode dbAuthcode = placeInfoDao.getAuthCode(UserInfo.getFid());
		if(dbAuthcode.getAuthCode()==null){
			result = false;
		}else{		
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			if(md5.isPasswordValid(dbAuthcode.getAuthCode(),authCode, null)){
				placeStampDao.burnStamp(stamp);	
				result = true;
			}else{
				result = false;
			}
		}
		return result;
		
	}

	@Override
	public DataTable<Stamp> getPlaceStampUserList(String[] columns,
			Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0,
			String sSortDir_0, String sSearch) {
		
		DataTable<Stamp> table = iDisplayLength != null ?
                 new DataTable<Stamp>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                 new DataTable<Stamp>(columns, sSortDir_0, iDisplayStart);
 
		
		  Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
		  param.put("sortDirection", sSortDir_0);
		  param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		  param.put("search", sSearch);
		  param.put("fid", UserInfo.getFid());
			
		  List<Stamp> userList=  placeStampDao.getPlaceStampUserList(param);
		  
		  pagingManaer.setTotalCount(pagingManaer.getFoundRows());
			
			
		 
		  table.setRows(userList); 
		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		  
		  return table;
		
	}

	@Override
	public List<PlaceStamp> getPlaceStampListByEmail(Map<String, Object> param) {
		return placeStampDao.getPlaceStampListByEmail(param);
	}

	@Override
	public List<Stamp> getPlaceStampedListByEmail(Map<String, Object> param) {
		return placeStampDao.getPlaceStampedListByEmail(param);
	}

	@Override
	public List<PlaceStamp> getPlaceStampList(Map<String, Object> param) {
		return placeStampDao.getPlaceStampList(param);
	}


	
	
}
