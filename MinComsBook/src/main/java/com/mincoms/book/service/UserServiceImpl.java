package com.mincoms.book.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mincoms.book.domain.Authorities;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.repository.AuthoritiesRepository;
import com.mincoms.book.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthoritiesRepository authRepo;
	
	public UserInfo save(UserInfo user) {
		UserInfo saved = userRepo.save(user);
		if(saved == null){
			return null;
		}else{
			return saved;
		}
	}

	public List<Authorities> findByAuthorities(String userName) {
		UserInfo user = findByUserName(userName);
		return authRepo.findByUser(user);
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

}
