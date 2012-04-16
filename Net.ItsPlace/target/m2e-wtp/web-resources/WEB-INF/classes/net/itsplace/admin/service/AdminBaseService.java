package net.itsplace.admin.service;

import java.util.List;

import net.itsplace.domain.BasCd;

public interface AdminBaseService {

	/*기초코드 그룹 리스트 가져오*/
	public List<BasCd> getGrpBascdList() ;
	/*그룹별 기초코드 가져오기 */
	public List<BasCd> getBascdList(String grpCd);
}
