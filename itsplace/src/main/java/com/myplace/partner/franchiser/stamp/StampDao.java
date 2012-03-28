package com.myplace.partner.franchiser.stamp;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myplace.partner.franchiser.FranchiserMember;

public interface StampDao {


	/* 스탬프를 찍는다 */
	public void saveStamp(Stamp stamp) throws DataAccessException;
	
	/* 스탬프 당첨을 사용한다 */
	public void updateStamp(Stamp stamp) throws DataAccessException;
	
	/* 스탬프를 취소한다*/
	public void deleteStamp(String pid) throws DataAccessException;
	
	/* 스탬프 목록을 가져온다 */
	public List<Stamp> getUserStampListByMobile(Stamp stamp) throws DataAccessException;	
	public List<Stamp> getUserStampListByEmail(Stamp stamp) throws DataAccessException;
	public List<Stamp> getUserStampListByMain() throws DataAccessException;
	
	/* 스탬프 타입목록을 가져온다 */
	public List<Stamp> getStamptypeList() throws DataAccessException;
	
	/* 가맹점에서 사용중인 스탬프타입을 가져온다 */
	public Stamp getStamptypeRegister(int fid) throws DataAccessException;
	
	/* 가맹점에서 사용할 스탬프를 설정한다 */
	public void saveStampRegister(Stamp stamp) throws DataAccessException;

	public Stamp getStampRegister(Stamp stamp) throws DataAccessException;
	
	public void updateStampRegister(Stamp stamp) throws DataAccessException;
	
	/*사용자 스탬를 가져온다 가맹정별*/
	public List<Stamp> getUserStampListByFid(Stamp stamp) throws DataAccessException;
	
	public List<Stamp> getUserStampListByFcode(Stamp stamp) throws DataAccessException; 
}
