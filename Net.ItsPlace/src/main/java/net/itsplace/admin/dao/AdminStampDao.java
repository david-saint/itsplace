package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.StampType;
import net.itsplace.user.User;

import org.springframework.dao.DataAccessException;


public interface AdminStampDao {

	/* 스탬프 타입목록을 가져온다 */
	public List<StampType> getStamptypeList(Map<String, Object> param) throws DataAccessException;
	public void saveStampType(StampType stampType) throws DataAccessException;	
	public void editStampType(StampType stampType) throws DataAccessException;
	public StampType getStampType(int sid) throws DataAccessException;
}
