package com.myplace.franchiser;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itsplace.partner.place.PlaceComment;
import com.myplace.TestApplicationContext;
import com.myplace.common.CommonService;
import com.myplace.common.TestCommon;
import com.myplace.partner.franchiser.FranchiserMember;
import com.myplace.partner.franchiser.FranchiserService;

public class TestFranchiserServiceImpl extends TestApplicationContext {
	  
	private static final Logger logger = Logger.getLogger(TestFranchiserServiceImpl.class);

	@Autowired
	FranchiserService fService;

	
	public void getFranchiserListByRoleFranchaiser() {
		
		Map<String,Object> param = new HashMap<String, Object>()  ;
		param.put("email", "faye12005@gmail.com");
		List<FranchiserMember> list = fService.getFranchiserListByRoleFranchaiser(param);
		for(Integer i=0;i<list.size();i++) {
			
			FranchiserMember f = (FranchiserMember)list.get(i);
			logger.info(f.getFname());
			
			
		}
	}
	@Test
	public void testGetFranchiserMember() {
		FranchiserMember franchiserMember = new FranchiserMember(11,"","3333","33","eee");
		//FranchiserMember franchiserMember2 = new FranchiserMember("aaaa1","다빈치카페2","3333","33","eee");
		
	//	fService.saveFranchiserMember(franchiserMember);
		
		FranchiserMember f =  fService.getFranchiserMember(11);
		logger.info(f.toString());
	}

	

	
	public void testGetFranchiserMemberList() {
		Map<String,Object> param = new HashMap<String, Object>()  ;
		param.put("fname", "");
		List<FranchiserMember> list = fService.getFranchiserMemberList(param);
		for(Integer i=0;i<list.size();i++) {
			
			FranchiserMember f = (FranchiserMember)list.get(i);
			logger.info(f.getFname());
			logger.info(f.getAddress().getSido());
			logger.info(f.getAddress().getGugun());
		}
	}
	

	public void testPlaceComment() {
		
		
		List<PlaceComment> list = fService.getPlaceCommentList(2);
		for(Integer i=0;i<list.size();i++) {
			
			PlaceComment f = (PlaceComment)list.get(i);
			logger.info(f.getFid());
			logger.info(f.getComment());
			logger.info(f.getInpdate().toString());
			
		}
	}

}
