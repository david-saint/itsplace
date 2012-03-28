package com.itsplace.admin;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.itsplace.partner.place.PlaceComment;
import com.myplace.partner.franchiser.FranchiserMember;


public interface AdminService {
	/* 가맹점을 승인한다*/
	public void updateFranchiserAuth(FranchiserMember franchiserMember) ;
	
}
 