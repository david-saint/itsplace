package net.itsplace.common;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import net.itsplace.domain.Bascd;
import net.itsplace.user.User;



public interface CommonService {

	public List<Bascd> getBascdList(String grpCd);
	public  List<Bascd>  getBascdALL(); 
	public  String getCode(String grpCd, String basekey);	
	public Basecd getBasecd();
}
