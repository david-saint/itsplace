package net.itsplace.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.GroupBascd;

public interface IBaseService {

	/*기초코드 그룹 리스트 가져오*/
	public List<GroupBascd> getGrpBascdList() ;
	/*그룹별 기초코드 가져오기 */
	public List<Bascd> getBascdList(String grpCd);
	public void saveBascd(Bascd bascd);
	public void editBascd(Bascd bascd);
	public Bascd getBascd(int fid);
	
	
	
	
	
	
	
	public DataTable getAddressList(String columns[],  Integer iDisplayStart, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0, String sSearch);
}
