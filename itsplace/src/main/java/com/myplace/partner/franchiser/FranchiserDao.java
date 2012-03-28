package com.myplace.partner.franchiser;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.itsplace.partner.place.PlaceComment;


public interface FranchiserDao {
	/* 가맹점 아이디로  가맹점 정보를 가져온다*/
	public FranchiserMember getFranchiserMember(int fid) throws DataAccessException;
	
	/* 가맹점 Geolocation으로  가맹점 아이디를 가져온다*/
	public List<FranchiserMember> getFranchiserMemberByLocation(FranchiserMember franchiserMember) throws DataAccessException;
	
	/* 가맹점을 검색하여 리스트로 보여준다*/
	public List<FranchiserMember>  getFranchiserMemberList(Map<String, Object> param) throws DataAccessException;
	/* 가맹점을 5개 가져와 리스트로 보여준다*/
	public List<FranchiserMember>  getFranchiserMemberListByMain() throws DataAccessException;
	
	/* 가맹점 위치정보저장*/
	public void savePFLocation(Location location) throws DataAccessException;
	
	/* 가맹점을 신청한다*/
	public int saveFranchiserMember(FranchiserMember franchiserMember) throws DataAccessException;
	/* 가맹점 정보수정 */
	public void updateFranchiserMember(FranchiserMember franchiserMember) throws DataAccessException;
	public void updateFranchiserMemberQrcode(FranchiserMember franchiserMember) throws DataAccessException;
	
	/* 가맹점 코멘트를 등록한다 */
	public void savePlaceComment(PlaceComment placeComment) throws DataAccessException;
	
	/* 가맹점 코멘트 리스트를 가져온다 */
	public List<PlaceComment>  getPlaceCommentList(int fid) throws DataAccessException;
	
	/* 가맹점 코멘트 삭제한다 */
	public void  deletePlaceComment(PlaceComment placeComment) throws DataAccessException;
	
	/* 가맹주가 신청한  가맹리스트를 보여준다*/
	public List<FranchiserMember>  getFranchiserListByRoleFranchaiser(Map<String, Object> param) throws DataAccessException;
	
	public FranchiserMember getFranchiserFcode(String fcode) throws DataAccessException;
}
