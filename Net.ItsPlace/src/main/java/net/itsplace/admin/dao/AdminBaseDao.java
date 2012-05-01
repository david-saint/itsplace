package net.itsplace.admin.dao;

import java.util.List;

import net.itsplace.domain.Bascd;

import org.springframework.dao.DataAccessException;


public interface AdminBaseDao {

	/*기초코드 그룹 리스트 가져오*/
	public List<Bascd> getGrpBascdList() throws DataAccessException;
	/*그룹별 기초코드 가져오기 */
	public List<Bascd> getBascdList(String grpCd) throws DataAccessException;
	
	public void saveBascd(Bascd bascd)  throws DataAccessException;
	public void editBascd(Bascd bascd)  throws DataAccessException;
	public Bascd getBascd(int fid) throws DataAccessException;

	public void delete(int fid)  throws DataAccessException;
	
}
