package net.itsplace.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.Social;
import net.itsplace.domain.User;
import net.itsplace.repository.PlaceEventPredicates;
import net.itsplace.repository.UserRepository;
import net.itsplace.util.Encrypt;
import net.itsplace.util.MailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;

public interface UserService {
	public User getUser(String email);
	public User getUser(String email, String token);
	public User getUserByPasswordLink(String passwordLInk);
	public User getUserByMobile(String mobile);
	
	/* 사용자를 저장한다*/
	public void saveUser(User user);
	public void updateUser(User user);
	/*리셋 요청시 업데이트함 로그인시 링크 삭제함*/
	public boolean updateUserPasswordLink(String url, String email);
	public void updateUserPassword(User user);
	/* 사용자를 사용 정지한다*/
	public void updateUserDisable(User user) ;
	public void updateUserEnable(User user) ;
	
//	public List<User> getUserList(Map<String, Object> param) throws DataAccessException;
	
	public DataTable getUserList(JpaPaging paging);	
}
@Service("UserService")
class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private MailService mailService;
	@Autowired
	UserRepository repo;
	
	
	@Transactional(readOnly=true)
	public User getUser(String email) {
		return repo.findOne(email);
	}


	@Transactional(readOnly=true)
	public User getUser(String email, String token) {
		
		
		return null;
		
	}
			
		
	public void saveUser(User user) {	
		
		
		user.setPassword(Encrypt.md5Encoding(user.getPassword()));
		user.setSaveDate(new Date());
		repo.save(user);
						
	}

	@Transactional(readOnly=true)
	public List<User> getUserList(Map<String, Object> param){
		
		return repo.findAll();
	}


	public void updateUser(User user) {
		String socialID="";	
		
//		if(user.getProfileImageType().equals("itsplace")){
//			Social social = new Social();
//			social.setEmail(user.getEmail());
//			social.setSocial("twitter");
//			String imageUrl = userDao.getUserConnection(social);
//			
//			user.setProfileImageUrl(imageUrl);
//			
//		}else if(user.getProfileImageType().equals("twitter")){
//			Social social = new Social();
//			social.setEmail(user.getEmail());
//			social.setSocial("twitter");
//			String imageUrl = userDao.getUserConnection(social);
//			
//			user.setProfileImageUrl(imageUrl);
//			//api.twitter.com/1/users/profile_image?screen_name=faye12005&size=bigger
//			//http://api.twitter.com/1/users/profile_image/faye12005.json
//			
//			
//		}else if(user.getProfileImageType().equals("facebook")){
//			Social social = new Social();
//			social.setEmail(user.getEmail());
//			social.setSocial("facebook");
//			String imageUrl = userDao.getUserConnection(social);				
//			user.setProfileImageUrl(imageUrl);
//			
//		}else if(user.getProfileImageType().equals("me2day")){
//			Social social = new Social();
//			social.setEmail(user.getEmail());
//			social.setSocial("me2day");
//			
//			social = userDao.getSocial(social);
//			
//			//socialID = StringUtil.getEmailId(user.getTwitter());
//			
//			user.setProfileImageUrl("http://static1.me2day.net/images/user/"+social.getUserid()+"/profile.png");
//		}
		
		logger.info("소셜 프로파일 사진:" + user.getProfileImageUrl());
		
		if(user.getProfileImageUrl()==""){
			user.setProfileImageUrl("http://localhost:8090/MyPlace/resources/images/whoami.png");
		}		
		user.setEditDate(new Date());
		repo.save(user);
	}


	

	public void updateUserDisable(User user) {
		 //userDao.updateUserDisable(user);
		user.setIsDelete(true);
		repo.save(user);
	}


	@Override
	public void updateUserEnable(User user) {
		user.setIsDelete(false);
		repo.save(user);
		
	}


	@Override
	public User getUserByMobile(String mobile) {
		return repo.findByMobile(mobile);
	}


	@Override
	public boolean updateUserPasswordLink(String url, String email){
		User user = getUser(email);
		if(user == null){
			return false;
		}
		String link = Encrypt.md5Encoding(user.getEmail() + Math.random());
		user.setPasswordLink(link);
		link = url + "/" + link;
		
		
		repo.save(user);
		mailService.sendMail("faye12005@gmail.com", user.getEmail(), "비밀번호 변경", "비번변경하세요 :" +link, user.getName());

		return true;
	}


	@Override
	public void updateUserPassword(User user) {
		repo.save(user);
	}


	@Override
	public User getUserByPasswordLink(String passwordLink) {
		return repo.findByPasswordLink(passwordLink);
	}
	
	public DataTable getUserList(JpaPaging paging){

        DataTable<User> table = new DataTable<User>(paging);
        
      //  Predicate predicate =  PlaceEventPredicates.isDelete(isDelete);
        
        Page<User> list = repo.findAll( paging.getPageable());
        			 
        
		  table.setRows(list.getContent()); 
		  
		  table.setiTotalDisplayRecords(list.getTotalElements());
		  logger.info("결과:{}",table.getiDisplayLength());
		  logger.info("결과:{}",table.getiTotalRecords());
		  return table;
		  
	}

	
	
}
