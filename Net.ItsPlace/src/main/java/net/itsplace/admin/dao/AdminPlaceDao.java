package net.itsplace.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import net.itsplace.domain.Franchiser;

public interface AdminPlaceDao {

	/* 가맹점을 검색하여 리스트로 보여준다*/
	public List<Franchiser>  getFranchiserList(Map<String, Object> param) throws DataAccessException;
	
}
