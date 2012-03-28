package com.myplace.partner.franchiser;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import com.itsplace.partner.place.PlaceComment;


public interface FranchiserService {
	public FranchiserMember getFranchiserMember(int i);
	
	/* 가맹점 Geolocation으로  가맹점 아이디를 가져온다*/
	public List<FranchiserMember> getFranchiserMemberByLocation(FranchiserMember franchiserMember);
	/* 가맹점 위치정보저장*/
	public void savePFLocation(Location location) ;
	
	
	/* 가맹점 신청 */
	public FranchiserMember saveFranchiserMember(FranchiserMember franchiserMember,HttpServletRequest request);
	
	public FranchiserMember setQrcodeFile(FranchiserMember franchiserMember,String url);
	
	/* 가맹점 정보수정 */
	public void updateFranchiserMember(FranchiserMember franchiserMember);
	
	public List<FranchiserMember> getFranchiserMemberList(Map<String, Object> param) throws DataAccessException;
	/* 가맹점을 5개 가져와 리스트로 보여준다*/
	public List<FranchiserMember>  getFranchiserMemberListByMain();
	

	/* 가맹점 코멘트를 등록한다 */
	public void savePlaceComment(PlaceComment placeComment);
	
	/* 가맹점 코멘트 리스트를 가져온다 */
	public List<PlaceComment> getPlaceCommentList(int fid);
	/* 가맹점 코멘트 삭제한다 */
	public void  deletePlaceComment(PlaceComment placeComment);
	
	/* 가맹주가 신청한  가맹리스트를 보여준다*/
	public List<FranchiserMember>  getFranchiserListByRoleFranchaiser(Map<String, Object> param);
	
	public FranchiserMember getFranchiserFcode(String fcode);
	
}
 