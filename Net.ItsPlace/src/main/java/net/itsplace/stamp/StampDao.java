package net.itsplace.stamp;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Stamp;

import org.springframework.dao.DataAccessException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public interface StampDao {
	
	public void saveStamp(Stamp stamp) throws DataAccessException;
	public void burnStamp(Stamp stamp) throws DataAccessException;	

}
