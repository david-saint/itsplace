package com.myplace.partner.franchiser;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.itsplace.partner.place.PlaceComment;

@Repository("FranchiserDAO")
public class FranchiserDaoImpl extends SqlMapClientDaoSupport implements FranchiserDao {
	//protected final Log log = LogFactory.getLog(getClass());
	private static final Logger logger = LoggerFactory.getLogger(FranchiserDaoImpl.class);
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	
	
	public FranchiserMember getFranchiserMember(int fid) throws DataAccessException{			
		return (FranchiserMember)getSqlMapClientTemplate().queryForObject("getFranchiserMember",fid);		
	}



	public int saveFranchiserMember(FranchiserMember franchiserMember) throws DataAccessException {		
		
		return (Integer)getSqlMapClientTemplate().insert("saveFranchiserMember",franchiserMember);
	}



	public List<FranchiserMember> getFranchiserMemberList (Map<String, Object> param)
			throws DataAccessException {
		
		logger.info("sqlmap:------------>getFranchiserMemberList");
		return getSqlMapClientTemplate().queryForList("getFranchiserMemberList", param);
		
	}



	public void savePlaceComment(PlaceComment placeComment)
			throws DataAccessException {
		getSqlMapClientTemplate().insert("savePlaceComment",placeComment);		
	}



	public List<PlaceComment> getPlaceCommentList(int fid) throws DataAccessException {
		logger.info("sqlmap:------------>getPlaceCommentList");
		return getSqlMapClientTemplate().queryForList("getPlaceCommentList", fid);
		
		
	}



	public void deletePlaceComment(PlaceComment placeComment) throws DataAccessException {
		
		getSqlMapClientTemplate().delete("deletePlaceComment",placeComment);
		
		
	}



	public List<FranchiserMember> getFranchiserListByRoleFranchaiser(
			Map<String, Object> param) throws DataAccessException {
		logger.info("sqlmap:------------>getFranchiserListByRoleFranchaiser");
		return getSqlMapClientTemplate().queryForList("getFranchiserListByRoleFranchaiser", param);
		
	}



	@Override
	public List<FranchiserMember> getFranchiserMemberListByMain() throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getFranchiserMemberListByMain");
	}



	@Override
	public  List<FranchiserMember> getFranchiserMemberByLocation(
			FranchiserMember franchiserMember) throws DataAccessException {
		// TODO Auto-generated method stub
		
		return getSqlMapClientTemplate().queryForList("getFranchiserMemberByLocation", franchiserMember);
	}



	@Override
	public void updateFranchiserMember(FranchiserMember franchiserMember)
			throws DataAccessException {
		 getSqlMapClientTemplate().update("updateFranchiserMember",franchiserMember);
		
	}



	@Override
	public void savePFLocation(Location location) throws DataAccessException {
		  getSqlMapClientTemplate().insert("savePFLocation",location);
		 
	}



	@Override
	public void updateFranchiserMemberQrcode(FranchiserMember franchiserMember)
			throws DataAccessException {
		getSqlMapClientTemplate().update("updateFranchiserMemberQrcode",franchiserMember);
		
	}



	@Override
	public FranchiserMember getFranchiserFcode(String fcode)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return (FranchiserMember)getSqlMapClientTemplate().queryForObject("getFranchiserFcode",fcode);
	}





	
	



}
