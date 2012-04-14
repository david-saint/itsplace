package net.itsplace.user;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceAware;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.itsplace.util.Encrypt;
import net.itsplace.util.StringUtil;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


@Service("UserService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly=true)
	public User getUser(String email) {
		
		User dbUser = null;	
		dbUser = userDao.getUser(email);		
		if(dbUser != null){			
			
			return dbUser;
		}else{
			return null;
			//throw new RuntimeException("User does not exist!");
			
			
		}
			
		
	}


	@Transactional(readOnly=true)
	public User getUser(String email, String token) {
		
		
		User user =  userDao.getUser(email,token);
		if(user != null){
			userDao.getUser(email,token);
		}
		
		return user;
		
	}
			
		
	public void saveUser(User user) {	
		
		user.setPassword(Encrypt.md5Encoding(user.getPassword()));
		
		try{
			userDao.setUser(user);		
		}catch(Exception e){
			logger.info("회원가입 에러 키 주");
			logger.info("회원가입 에러 키 주"+e.toString());
			logger.info("회원가입 에러 키 주:"+			e.getClass().toString());
			logger.info(e.getMessage());
		}
						
	}

	@Transactional(readOnly=true)
	public List<User> getUserList(Map<String, Object> param){
		if(logger.isDebugEnabled()){
			List<User> ul = userDao.getUserList(param);
			if (ul != null){				
				for(Integer i=0;i<ul.size();i++){
					 User user = (User)ul.get(i);
					logger.debug("사용자정보:" + user.toString());
				}
			}
			
		}
		return userDao.getUserList(param);
	}


	public void updateUser(User user) {
		String socialID="";	
		
		if(user.getProfileImageType().equals("itsplace")){
			Social social = new Social();
			social.setEmail(user.getEmail());
			social.setSocial("twitter");
			String imageUrl = userDao.getUserConnection(social);
			
			user.setProfileImageUrl(imageUrl);
			
		}else if(user.getProfileImageType().equals("twitter")){
			Social social = new Social();
			social.setEmail(user.getEmail());
			social.setSocial("twitter");
			String imageUrl = userDao.getUserConnection(social);
			
			user.setProfileImageUrl(imageUrl);
			//api.twitter.com/1/users/profile_image?screen_name=faye12005&size=bigger
			//http://api.twitter.com/1/users/profile_image/faye12005.json
			
			
		}else if(user.getProfileImageType().equals("facebook")){
			Social social = new Social();
			social.setEmail(user.getEmail());
			social.setSocial("facebook");
			String imageUrl = userDao.getUserConnection(social);				
			user.setProfileImageUrl(imageUrl);
			
		}else if(user.getProfileImageType().equals("me2day")){
			Social social = new Social();
			social.setEmail(user.getEmail());
			social.setSocial("me2day");
			
			social = userDao.getSocial(social);
			
			//socialID = StringUtil.getEmailId(user.getTwitter());
			
			user.setProfileImageUrl("http://static1.me2day.net/images/user/"+social.getUserid()+"/profile.png");
		}
		
		logger.info("소셜 프로파일 사진:" + user.getProfileImageUrl());
		
		if(user.getProfileImageUrl()==""){
			user.setProfileImageUrl("http://localhost:8090/MyPlace/resources/images/whoami.png");
		}		
		userDao.updateUser(user);
	}


	public void saveSocial(Social social) {
		if (userDao.getSocial(social)==null){
			userDao.saveSocial(social);
		}else{
			userDao.updateSocial(social);
		}
		
	}


	public void updateSocial(Social social) {
		// TODO Auto-generated method stub
		userDao.updateSocial(social);
	}


	public Social getSocial(Social social) {
		// TODO Auto-generated method stub
		return userDao.getSocial(social);
	}


	public void updateUserDisable(User user) {
		 userDao.updateUserDisable(user);
	}


	@Override
	public void updateUserEnable(User user) {
		userDao.updateUserEnable(user);
		
	}


	@Override
	public User getUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userDao.getUserByMobile(mobile);
	}


	
	
}
