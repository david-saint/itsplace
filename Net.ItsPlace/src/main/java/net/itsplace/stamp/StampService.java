package net.itsplace.stamp;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Stamp;

import org.springframework.dao.DataAccessException;


public interface StampService {

	public void saveStamp(Stamp stamp);
	public void burnStamp(Stamp stamp); 
	
}
 