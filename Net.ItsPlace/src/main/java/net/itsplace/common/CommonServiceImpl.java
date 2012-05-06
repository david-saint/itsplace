package net.itsplace.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itsplace.admin.dao.AdminUserDao;
import net.itsplace.domain.Bascd;
import net.itsplace.user.User;
import net.itsplace.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;



@Service("CommonService")
public class CommonServiceImpl implements CommonService{
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private CommonDao commonDao;

	private Map<String, String> baseMap;
	
	private Basecd basecd;
	
	
	@javax.annotation.PostConstruct
	public void init(){
		baseMap = new HashMap<String, String>();
		List<Bascd> list = getBascdALL();
		for(int i=0;i<list.size();i++){
			baseMap.put(list.get(i).getGrpcd()+","+list.get(i).getBasekey(),list.get(i).getBasecd());
		}
		basecd = new Basecd(baseMap);
	}
	
	/**
	 * 그룹코드로 기초코드 리스트 가져오기  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param grpCd 그룹 코드 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@Override
	public List<Bascd> getBascdList(String grpCd) {
		return commonDao.getBascdList(grpCd);
	}


	/**
	 * 모든 기초코드 리스트 가져오기  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param  
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@Override
	public List<Bascd> getBascdALL()  {
		return commonDao.getBascdALL();
	}

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

	public Basecd getBasecd() {
		return basecd;
	}

	

}
