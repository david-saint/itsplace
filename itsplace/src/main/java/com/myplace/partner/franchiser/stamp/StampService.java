package com.myplace.partner.franchiser.stamp;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface StampService {

	/* 스탬프를 찍는다 */
	public String saveStamp(Stamp stamp) ;
	/* 스탬프 당첨을 사용한다 */
	public void updateStamp(Stamp stamp) ;
	/* 스탬프를 취소한다*/
	public void deleteStamp(String pid);
	
	/* 휴대폰 번호로 스탬프 목록을 가져온다 */
	public List<Stamp> getUserStampListByMobile(Stamp stamp);
	/* email로 스탬프 목록을 가져온다 */
	public List<Stamp> getUserStampListByEmail(Stamp stamp) ;
	public List<Stamp> getUserStampListByMain();
	/* 스탬프 전체 타입목록을 가져온다 */
	public List<Stamp> getStamptypeList();
	
	/* 가맹점에서 사용중인 스탬프타입을 가져온다 */
	public Stamp getStamptypeRegister(int fid);
	
	
	/* 가맹점에서 사용할 스탬프를 설정한다 */
	public void saveStampRegister(Stamp stamp) ;
	public Stamp getStampRegister(Stamp stamp) ;
	public void updateStampRegister(Stamp stamp);
	
	
	/*사용자 스탬를 가져온다 가맹정별*/
	public List<Stamp> getUserStampListByFid(Stamp stamp);
	public List<Stamp> getUserStampListByFcode(Stamp stamp);
}
