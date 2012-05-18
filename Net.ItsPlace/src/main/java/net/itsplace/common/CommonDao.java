package net.itsplace.common;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Address;
import net.itsplace.domain.Bascd;

import org.springframework.dao.DataAccessException;



public interface CommonDao {

	
	public List<Bascd> getBascdList(String grpCd) throws DataAccessException;
	public List<Bascd>  getBascdALL() throws DataAccessException;
	
	public List<Address> getAddressListByDong(Map<String, Object> param) throws DataAccessException;
	public List<Address> getAddressListByDoroname(Map<String, Object> param) throws DataAccessException;
	
	public Integer getFoundRows() throws DataAccessException;
}
