package net.itsplace.admin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.BasCd;

public interface AdminBaseDao {

	/*기초코드 그룹 리스트 가져오*/
	public List<BasCd> getGrpBascdList() throws DataAccessException;
	/*그룹별 기초코드 가져오기 */
	public List<BasCd> getBascdList(String grpCd) throws DataAccessException;
	
}
