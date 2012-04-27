package net.itsplace.admin.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Bascd;

public interface AdminBaseService {

	/*기초코드 그룹 리스트 가져오*/
	public List<Bascd> getGrpBascdList() ;
	/*그룹별 기초코드 가져오기 */
	public List<Bascd> getBascdList(String grpCd);
	public void saveBascd(Bascd bascd);
	public void editBascd(Bascd bascd);
	public Bascd getBascd(int fid);
}
