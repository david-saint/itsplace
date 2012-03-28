package com.itsplace.admin;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myplace.common.CommonService;
import com.myplace.partner.franchiser.FranchiserController;
import com.myplace.partner.franchiser.FranchiserMember;
import com.myplace.partner.franchiser.FranchiserService;
import com.myplace.user.User;
import com.myplace.user.UserService;
import com.myplace.util.PagingManager;



/**
 * 웹 관리자 기능   
 * */

@Controller
public class AdminController {
        
	private static final Logger logger =  LoggerFactory.getLogger(FranchiserController.class);
	@Autowired
	private FranchiserService fService;
	
	@Autowired
	private AdminService aService;
	
	@Autowired
	private UserService uService;
	
	private PagingManager pagingManaer;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * <pre>
	 *	가맹점 신청 승인: 승인이 완료되면 가맹점 전용 로그인 아이디(fid) 계정이 생성된다				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 29.
	 * @param 
	 * @return  가맹점 신청(가입) 페이지
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/admin/auth/{fid}/{authyn}", method = RequestMethod.GET)
 	public @ResponseBody String auth(
 			 @PathVariable int fid,
 			 @PathVariable String authyn) {
		
		
		logger.info("프랜차이즈 FID:" +fid +" 승인여부:"+ authyn);
		FranchiserMember franchiserMember = fService.getFranchiserMember(fid);
		
		franchiserMember.setAuthyn(authyn); // Y/N
		
		aService.updateFranchiserAuth(franchiserMember);
		
		if(authyn.equals("Y")){			
			User user = new User();
			user.setEmail(Integer.toString(fid));
			user.setPassword(Integer.toString(fid));
			user.setName(franchiserMember.getFname());
			user.setRole("ROLE_FID");
			
			
			if((User)uService.getUser(Integer.toString(fid))==null  ){
				uService.saveUser(user);		
			}else{
				uService.updateUserEnable(user);
				logger.info("가맹점 신청 승인이 되고 가맹점 아이디(fid):" + fid +" 가 PUSER 에 이미 등록되어 있습니다");				
			}			
			logger.info("가맹점 신청 승인 완료");
			return "authSuccess";
		}else{
			logger.info("가맹점 신청 승인 취소");			
			User user = new User();
			user.setEmail(Integer.toString(fid));			
			uService.updateUserDisable(user);
			return "authInvoke";
		}
		
		
	}
	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public String administrator() {
	
		return "admin/main";
	}
	
	
}