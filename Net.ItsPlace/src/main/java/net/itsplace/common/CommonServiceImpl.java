package net.itsplace.common;

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

	/**
	 * 기초코드 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@Override
	public List<Bascd> getBascdList(String grpCd) {
		return commonDao.getBascdList(grpCd);
	}
	
	
	

}
