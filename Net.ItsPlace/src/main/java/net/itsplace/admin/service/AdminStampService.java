package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.StampType;

public interface AdminStampService {
	/* 스탬프 타입목록을 가져온다 */
	public List<StampType> getStamptypeList(Map<String, Object> param);
	public void saveStampType(StampType stampType);	
	public void editStampType(StampType stampType);
	public StampType getStampType(int sid);
}
