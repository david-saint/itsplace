package net.itsplace.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.Social;
import net.itsplace.repository.UserRepository;
import net.itsplace.user.User;
import net.itsplace.util.Encrypt;
import net.itsplace.util.MailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("UserService")
public class UserServiceImpl implements UserService {
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
	
	public DataTable getUserList(String columns[],Integer iDisplayStart,Integer iDisplayLength,Integer iSortCol_0,String sSortDir_0, String sSearch,String role){
		  DataTable<User> table = iDisplayLength != null ?
                new DataTable<User>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                new DataTable<User>(columns, sSortDir_0, iDisplayStart);

		
//		  Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
//		  param.put("sortDirection", sSortDir_0);
//		  param.put("sortColumn", table.getOrderColumn(iSortCol_0));
//		  param.put("search", sSearch);
//		  param.put("role", role);
//			
//		  List<User> userList= adminUserDao.getUserList(param);
//		  
//		  pagingManaer.setTotalCount(pagingManaer.getFoundRows());
//			
//			
//		 
//		  table.setRows(userList); 
//		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		  
		  return table;
	}

	
	
}