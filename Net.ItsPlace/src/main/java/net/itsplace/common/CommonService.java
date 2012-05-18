package net.itsplace.common;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import net.itsplace.domain.Address;
import net.itsplace.domain.Bascd;
import net.itsplace.domain.DataTable;
import net.itsplace.user.User;



public interface CommonService {

	public List<Bascd> getBascdList(String grpCd);
	public List<Bascd>  getBascdALL(); 
	public String getCode(String grpCd, String basekey);	
	public Basecd getBasecd();
	public Integer getFoundRows() throws DataAccessException;
	
	public DataTable getAddressList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch);
}
