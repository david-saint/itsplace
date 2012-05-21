package net.itsplace.place.service;

import java.util.ArrayList;
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
import net.itsplace.domain.Stamped;
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
		/*Authcode dbAuthcode = placeInfoDao.getAuthCode(UserInfo.getFid());
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
		}*/
		placeStampDao.burnStamp(stamp);	
		return true;
		
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
	public List<Stamped> getPlaceStampListByEmail(Map<String, Object> param) {
		List<PlaceStamp> placeStampList = placeStampDao.getPlaceStampListByEmail(param);
		if(placeStampList.size()==0){
			logger.info("가맹점 첫 스탬프 적립하는 회원임 ");
			placeStampList = getPlaceStampList(param);
		}
		
		List<Stamped> stamppedListAll = new ArrayList<Stamped>();
		//List<Object> stamppedListAll2 = new ArrayList<Object>();
		
		for(int i=0;i<placeStampList.size();i++){//스탬프타입별(종류)별로 적립된 스탬프 만든다
			logger.info("stamptype:{}"+placeStampList.get(i).getStampTitle()+placeStampList.get(i).getStampid());
			param.put("stampid", placeStampList.get(i).getStampid());
			logger.info("placeStampList.size():{}",placeStampList.size());
			
			List<Stamp> stampedList = getPlaceStampedListByEmail(param);
			if(stampedList.size()<=0){
				return null;
			}
			int totalStampedCount = stampedList.size(); //적립된 스탬프 토탈카운트  수
			logger.info("totalStampedCount:{}",totalStampedCount);
			int stampCount = placeStampList.get(i).getStampType().getStampcount();
			int stampTypeCount = (totalStampedCount/stampCount)+(totalStampedCount%stampCount==0?0:1);// 스탬프 타입 개수 		
			
			int leftCount = 0;
			logger.info("stampTypeCount:{}",stampTypeCount);
			logger.info("eventday:{}",placeStampList.get(i).getStampType().getEventday());
			for(int j=0;j<stampTypeCount;j++){
				List<Stamp> stampList = new ArrayList<Stamp>();	
				
				for(int k=1;k<stampCount+1;k++){
					
					if(leftCount<totalStampedCount){
						logger.info("leftcount:{}",leftCount);
						if(k<stampedList.size()+1){
							 Stamp stampped = (Stamp)stampedList.get(leftCount);
							 logger.info(stampped.getPid() + stampped.getStatus());
							 
							 if(stampped == null){
								 logger.info("stamped null:"+leftCount);
							 }else{
								// logger.info("stamped k:{}",k);
								 if(k % placeStampList.get(i).getStampType().getEventday()==0){
									 stampped.setAttribute("StampedEventday");//당첨받는날
									 logger.info(stampped.getPid() + stampped.getStatus()+"당첨받는날");
								 }else{
									 
									 stampped.setAttribute("Stampped"); 
								 }
							 }
							 
							
							
							 stampList.add(stampped);
							 //적립된 스탬프 생성
							 leftCount++;
						 }
					}else{
						 logger.info("인덱스:"+i + "data:blank");
							
						 Stamp blank = new Stamp();
						 if(k % placeStampList.get(i).getStampType().getEventday()==0){
							 blank.setAttribute("Eventday"); // 스탬프는 안찍히고 당첨받는날
						 }
						 stampList.add(blank);
					}
				}
				Stamped s = new Stamped();
				logger.info("placeStampList.get(i).getTheme()"+placeStampList.get(i).getTheme());
				s.setPlaceStamp( placeStampList.get(i));
				s.setStampList(stampList);
				stamppedListAll.add(s);
			//	stamppedListAll.add(stampList);
				
			}
		}
		return stamppedListAll;
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
