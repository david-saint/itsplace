package com.mincoms.book.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mincoms.book.domain.DeptInfo;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.DeptRepository;
import com.mincoms.book.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	public UserInfo save(UserInfo user) {
		UserInfo saved = userRepo.save(user);
		if(saved == null){
			return null;
		}else{
			return saved;
		}
	}


	
	public List<UserInfo> findActiveAll() {
		return userRepo.findByIsDeleted(false);
		
	}

	public UserInfo findByUserId(int userId) {
		return userRepo.findByUserId(userId);
	}

	@Override
	public UserInfo findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	@Override
	public List<UserInfo> findByUserNameContaining(String userName) {
		return userRepo.findByUserNameContaining(userName);
	}

	@Override
	public List<UserInfo> findByDeptInfo(int deptId) {
		DeptInfo deptInfo = deptRepo.findOne(deptId);
		return userRepo.findByDeptInfo(deptInfo);
	}

}
