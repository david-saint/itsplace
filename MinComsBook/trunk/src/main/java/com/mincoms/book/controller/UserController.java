package com.mincoms.book.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincoms.book.admin.repository.BaseCodeRepository;
import com.mincoms.book.admin.service.RestrictionService;
import com.mincoms.book.domain.BaseCode;
import com.mincoms.book.domain.BookCategory;
import com.mincoms.book.domain.BookCategorySub;
import com.mincoms.book.domain.BookInfo;
import com.mincoms.book.domain.BookRestriction;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.domain.dto.DtoBookRestriction;
import com.mincoms.book.security.Authority;
import com.mincoms.book.security.BookUserDetailsService;
import com.mincoms.book.security.CustomUserDetails;
import com.mincoms.book.service.BookService;
import com.mincoms.book.service.CategoryService;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.ReservationService;
import com.mincoms.book.service.UserService;
import com.mincoms.book.util.Encrypt;
/**
 * <b>도서 사용자 </b> <br />
 * <pre>
 * 도서목록, 카테고리 (Json) 
 * <b>History:</b>
 * </pre>
 * @author 김동훈
 * @version 2.0
 * @since 2012. 8. 24  
 * @throws 
 * @see 
 */
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	MessageSource messagesource;
	@Autowired
	BookService bookService;
	@Autowired
	RentalService rentalService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	UserService userService;
	@Autowired
	private BaseCodeRepository baseCodeRepository;
	@Autowired
	JsonResponse json;
	
	
	/**
	 * <b>사용자 목록 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 21
	 * @param term 사용자 아이디 
	 * @return List<UserInfo> 사용자 리스트 
	 * @throws Exception 
	 * @see 
	 */
	@ResponseBody
	@RequestMapping(value = "/user/getActiveUsers", method = RequestMethod.GET, headers="Accept=application/json")
	public List<UserInfo> getActiveUsers(@RequestParam(required=true, defaultValue="1") String term){
		logger.info("term:{}",term);
		List<String> userNames = new ArrayList();
		for(UserInfo userInfo: userService.findByUserNameContaining(term)){
			userNames.add(userInfo.getUserName());
		}
		return userService.findByUserNameContaining(term);
	}
	
	/**
	 * <b>사용자 목록 / 부서별  </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param deptId 부서 아이디 
	 * @return List<UserInfo> 사용자 리스트  
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/user/getUsersByDept", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody List<UserInfo> getUsersByDept(@RequestParam(value="deptId", required=true) Integer deptId)  {
		
		return userService.findByDeptInfo(deptId);
	}
	
	/**
	 * <b>사용자 정보 및 , GCM 안드로이드 기기 등록번호 등록  </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param UserInfo 사용자 정보
	 * @return UserInfo 사용자 정보
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
	public  @ResponseBody  UserInfo getuser(UserInfo userInfo) {
		logger.info("Android Call username:{}",userInfo.getUserName());
		UserInfo signedUser = null;
		
		signedUser = userService.findByUserName(userInfo.getUserName());
		signedUser.setGcmId(userInfo.getGcmId());
		userService.save(signedUser);
		
		return signedUser;
	}
	
	
	/**
	 * <b>패스워드 수정 </b> <br />
	 * 민워크에서 로그인시  자바 MD5 패스워드를 등록함 
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param UserInfo 사용자 정보
	 * @return UserInfo 사용자 정보
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/user/setPassword", method = RequestMethod.POST)
	public  void setuser(UserInfo userInfo) {
		logger.info("Android Call username:{}",userInfo.getUserName());
		UserInfo signedUser = null;
		signedUser = userService.findByUserName(userInfo.getUserName());
		signedUser.setPassword( Encrypt.md5Encoding(userInfo.getPassword()));
		userService.save(signedUser);
		
		//return signedUser;
	}
	/**
	 * <b>패스워드 수정 </b> <br />
	 * 민워크에서 로그인시  자바 MD5 패스워드를 등록함 
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param UserInfo 사용자 정보
	 * @return UserInfo 사용자 정보
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/user/signin", method = RequestMethod.POST)
	public   @ResponseBody UserInfo signin(UserInfo userInfo) {
		logger.info("CrossDomain username:{}",userInfo.getUserName());
		
		UserInfo signedUser = null;
		signedUser = userService.findByUserName(userInfo.getUserName());
		signedUser.setPassword( Encrypt.md5Encoding(userInfo.getPassword()));
		userService.save(signedUser);
	
		
		logger.info("signedUser :{}",signedUser.toString());
		// Principal principal = new Princip();
		// UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(localUserId, "itsplace!@#$", cuser.getAuthorities("ROLE_USER"));
		CustomUserDetails details = new CustomUserDetails(
				signedUser, 
				signedUser.getUserName(),						
				"",
				true,
				true,
				true,
				true,
				getAuthorities(signedUser.getAuthlevel(), signedUser.getUserName()));
		
		System.out.println("password:"+details.getUser().getPassword());
		UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(details, details.getUser().getPassword(), getAuthorities(signedUser.getAuthlevel(), signedUser.getUserName()));
		
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	return signedUser;
	}
	 private Collection<GrantedAuthority> getAuthorities(int authlevel, String userName) {
			// Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(3);
			//0 :  관리자
			//1 : 팀장
			//2 : 사원
			
			//모든 사용자는 일반사용자 권한도 가짐
			authList.add(new GrantedAuthorityImpl(Authority.WORKER.name()));
			
			if(authlevel == Authority.ADMIN.ordinal()){							
				authList.add(new GrantedAuthorityImpl(Authority.ADMIN.name()));
				
			}else if(authlevel == Authority.TEAMLEADER.ordinal()){		
				authList.add(new GrantedAuthorityImpl(Authority.TEAMLEADER.name()));
			}
			
			BaseCode baseCode = baseCodeRepository.findByBookManager(userName);
			if(baseCode !=null){
				authList.add(new GrantedAuthorityImpl(Authority.BOOKMANAGER.name()));
			}
			return authList;
	 }	  
}
