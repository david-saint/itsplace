package net.itsplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.itsplace.domain.Address;
import net.itsplace.domain.Bascd;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.GroupBascd;
import net.itsplace.repository.BaseRepository;
import net.itsplace.repository.GroupBaseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("AdminBaseService")
public class BaseServiceImpl implements BaseService{
	private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	@Autowired
	GroupBaseRepository groupBaseRepositoryRepo;
	@Autowired
	BaseRepository baseRepositoryRepo;
	
	private Map<String, String> baseMap;
	
	@Override
	public List<GroupBascd> getGrpBascdList() {
		
//		return adminBaseDao.getGrpBascdList();
		return groupBaseRepositoryRepo.findByIsDelete(false);
	}



	@Override
	public List<Bascd> getBascdList(String grpCd) {
		//return adminBaseDao.getBascdList(grpCd);
		return baseRepositoryRepo.findByGrpcd(grpCd);
	}



	@Override
	public void saveBascd(Bascd bascd) {
		//adminBaseDao.saveBascd(bascd);
		baseRepositoryRepo.save(bascd);
	}



	@Override
	public void editBascd(Bascd bascd) {
		
		baseRepositoryRepo.save(bascd);
	}



	@Override
	public Bascd getBascd(int no) {

		return baseRepositoryRepo.findOne(no);
	}
	

	
/*	@Autowired 
	protected ServletContext servletContext;
	
	@javax.annotation.PostConstruct
	public void init(){
		baseMap = new HashMap<String, String>();
		List<Bascd> list = getBascdALL();
		String imageHost="";
		for(int i=0;i<list.size();i++){
			baseMap.put(list.get(i).getGrpcd()+","+list.get(i).getBasekey(), list.get(i).getBasecd());
			if(list.get(i).getGrpcd().equals("MEDIA") && list.get(i).getBasekey().equals("IMAGEHOST")){
				imageHost = list.get(i).getBasName();
				logger.info(imageHost);
			}
		}
		basecd = new Basecd(baseMap);
		basecd.setImageHost(imageHost);
		servletContext.setAttribute("ImageHost", imageHost);
	}*/
	
	



	/**
	 * 그룹코드, 기초코드로 기초코드 가져오기  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param grpCd 그룹 코드 
	 * @param basekey  기초코드 네임 
	 * @return  기초코드 값 
	 * @throws 
	 * @see 
	 */
	public  String getCode(String grpCd, String basekey){
		
		return baseMap.get(grpCd+","+basekey);
		
	}

	


	@Override
	public DataTable<Address> getAddressList(String[] columns, Integer iDisplayStart,
			Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0,
			String sSearch) {
return null;
//		 DataTable<Address> table = iDisplayLength != null ?
//                 new DataTable<Address>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
//                 new DataTable<Address>(columns, sSortDir_0, iDisplayStart);
//        
//        if(sSearch ==""){
//        	  return table;
//        }
//		ArrayList<String> temp = new ArrayList<String>();
//		ArrayList<String> temp2 = new ArrayList<String>();				
//		
//		Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
//		param.put("sortDirection", sSortDir_0);
//		param.put("sortColumn", table.getOrderColumn(iSortCol_0));
//		
//		
//		StringTokenizer st = new StringTokenizer(sSearch," ");
//	    logger.info("검색어:" + sSearch);
//		while (st.hasMoreTokens()) {																
//			temp.add(st.nextToken());				
//		}
//		// 지번주소 진천동 525-5		
//		for(int i = 0 ; i<temp.size();i++){
//			if(i==0){
//				param.put("bupname", temp.get(0));
//				logger.info("지번주소 동이름:" +temp.get(0));
//			}
//			if(i==1){
//				param.put("jimain",  temp.get(1));
//				logger.info("지번주소 번지:" +temp.get(1));				
//					StringTokenizer bunji = new StringTokenizer(temp.get(1),"-");
//					while (bunji.hasMoreTokens()) {
//						temp2.add(bunji.nextToken());
//					}
//					for(int j = 0 ; j<temp2.size();j++){
//						if(j==0){
//							param.put("jimain", temp2.get(0));
//							logger.info("번지 jimain:" +temp2.get(0));
//						}
//						if(j==1){
//							param.put("jisubmain", temp2.get(1));
//							logger.info("번지 jisubmain:" +temp2.get(1));
//						}
//					}
//				
//			}
//		}
//		 List<Address> addressList= commonDao.getAddressListByDong(param);
//		 if(addressList.size()<=0){
//			 param.put("doroname", temp.get(0));
//			 addressList= commonDao.getAddressListByDoroname(param);
//		 }
//		 pagingManaer.setTotalCount(pagingManaer.getFoundRows());
//			
//			
//		 
//		  table.setRows(addressList); 
//		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
//		  
//		  return table;
	}

	
}
