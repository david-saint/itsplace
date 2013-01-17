package net.itsplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.ReportAsSingleViolation;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.Stamp;
import net.itsplace.domain.Stamped;
import net.itsplace.repository.StampRepository;
import net.itsplace.user.UserInfo;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;




@Service("placeStampService")
 class PlaceStampServiceImpl implements PlaceStampService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceStampService.class);
	
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	StampRepository stampRepo;
	@Autowired
	PlaceService placeService;
	@Override
	public boolean saveStamp(Stamp stamp,String authCode) {
		boolean result = false;
		Place place = placeService.getPlace(UserInfo.getFid());
//		Authcode dbAuthcode = placeInfoDao.getAuthCode(UserInfo.getFid());
//		if(dbAuthcode.getAuthCode()==null){
//			result = false;
//		}else{		
//			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//			if(md5.isPasswordValid(dbAuthcode.getAuthCode(),authCode, null)){
//				placeStampDao.saveStamp(stamp);		
//				result = true;
//			}else{
//				result = false;
//			}
//		}
		return result;
		
	}
	@SuppressWarnings("unused")
	@Override
	public boolean saveStamp(Stamp stamp) {
		boolean result = false;
		try{
			//placeStampDao.saveStamp(stamp);		
			result = true;
		}catch(Exception e){
			e.printStackTrace();
			result = false;
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
	//	placeStampDao.burnStamp(stamp);	
		return true;
		
	}

	@Override
	public DataTable<Stamp> getPlaceStampUserList(JpaPaging paging) {
		 DataTable<Stamp> table = new DataTable<Stamp>(paging);
         //Page<BookInfo> books = (Page<BookInfo>)bookRepo.findByTitleContainingOrAuthors(paging.getsSearch(),paging.getsSearch(), paging.getPageable());
         
     
   		
   		
         Page<Stamp> list = stampRepo.findAll(paging.getPageable());
        			 
         
		  table.setRows(list.getContent()); 
		  
		  table.setiTotalDisplayRecords(list.getTotalElements());
		  logger.info("결과:{}",table.getiDisplayLength());
		  logger.info("결과:{}",table.getiTotalRecords());
		  return table;
			
		//  List<Stamp> userList=  placeStampDao.getPlaceStampUserList(param);
		  
		  //pagingManaer.setTotalCount(pagingManaer.getFoundRows());
			
			
		 
	//	  table.setRows(userList); 
		 // table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		  
		//  return table;
		
	}

	@Override
	public List<Stamped> getPlaceStampListByEmail(Map<String, Object> param) {
		List<PlaceStamp> placeStampList = null ;//placeStampDao.getPlaceStampListByEmail(param);
		if(placeStampList.size()==0){
			logger.info("가맹점 첫 스탬프 적립하는 회원임 ");
			placeStampList = getPlaceStampList((Integer)param.get("fid"));
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
									// stampped.setAttribute("StampedEventday");//당첨받는날
									 logger.info(stampped.getPid() + stampped.getStatus()+"당첨받는날");
								 }else{
									 
									 //stampped.setAttribute("Stampped"); 
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
							// blank.setAttribute("Eventday"); // 스탬프는 안찍히고 당첨받는날
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
		return null;//placeStampDao.getPlaceStampedListByEmail(param);
	}

	@Override
	public List<PlaceStamp> getPlaceStampList(int fid) {
		return  null;// placeStampDao.getPlaceStampList(fid);
	}


	
}

public interface PlaceStampService {

	public boolean saveStamp(Stamp stamp,String authCode) ;
	public boolean burnStamp(Stamp stamp,String authCode) ;	
	
	public DataTable<Stamp> getPlaceStampUserList(JpaPaging paging);
	/* 회원 스탬프 타입 리스트  */
	public List<Stamped> getPlaceStampListByEmail(Map<String, Object> param);
	public List<Stamp> getPlaceStampedListByEmail(Map<String, Object> param);
	public List<PlaceStamp> getPlaceStampList(int fid) ;
	boolean saveStamp(Stamp stamp);
}