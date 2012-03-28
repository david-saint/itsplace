/**
 * 
 */
package com.myplace.partner.franchiser;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.myplace.common.Address;
import com.myplace.common.CommonService;
import com.myplace.common.mail.MailService;
import com.myplace.partner.franchiser.image.FranchiserImage;
import com.myplace.partner.franchiser.stamp.Stamp;
import com.myplace.partner.franchiser.stamp.StampService;
import com.myplace.user.User;
import com.myplace.util.Encrypt;
import com.myplace.util.PagingManager;
import com.myplace.util.StandardOrMobile;
import com.myplace.util.StringUtil;

@Controller
public class FranchiserController {
        
	private static final Logger logger =  LoggerFactory.getLogger(FranchiserController.class);
	@Autowired
	private FranchiserService fService;
	
	private PagingManager pagingManaer;
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private StampService stampService;

	/**
	 * 
	 *	가맹점 신청 폼 페이지				
	 *  
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return  가맹점 신청(가입) 페이지
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/newFranchiser", method = RequestMethod.GET)
 	public String newFranchiser(Map model) {
		logger.info("신규 가맹점 신청폼");
		FranchiserMember franchiserMember = new FranchiserMember();
		
		model.put("franchiserMember", franchiserMember);
		
		return "franchiser/newFranchiser";
	}
	
	/**
	 * 
	 *	가맹점 등록 프로세스: 신청 승인이 되면 가맹주와 가맹점을 매핑시주게 되며 <br>
	 *  가맹점 QR Code 생성 및 신청 성공 메일을 발송한 	
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/newFranchiser", method = RequestMethod.POST)
	public String newFranchiserSubmit(@Valid FranchiserMember franchiserMember, BindingResult result,HttpServletRequest request,Model model) {
		
		if (result.hasErrors()) {			
		/*	for(ObjectError error : result.getAllErrors()){
				 logger.debug("에러발생:"+error.getCode() +  " - " + error.getDefaultMessage());
			 }
		*/		 
			logger.info("신규 가맹점 신청: 에러발생:{}",result.getFieldError().getDefaultMessage());
			return "franchiser/newFranchiser";			
		} else {										
			
			
			
			
			franchiserMember = fService.saveFranchiserMember(franchiserMember,request);
			
			model.addAttribute("franchiserMember",franchiserMember);
			
			return "franchiser/newFranchiserConfirm";
		}
	
	}
	/**
	 * <pre>
	 *	가맹점 정보 수정 		
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 30.
	 * @param 
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/franchiserDetail/{fid}", method = RequestMethod.GET)
	public String franchiserDetail(Model model,
			 @PathVariable int fid) {
		
		FranchiserMember franchiserMember = fService.getFranchiserMember(fid);
		model.addAttribute("franchiserMember", franchiserMember);
		List<Stamp> stamptypeList = stampService.getStamptypeList();
		model.addAttribute("stamptypeList",stamptypeList);
		
		
		return "franchiser/franchiserDetail";
		
	}
	
	@RequestMapping(value = "/partner/franchiserDetail", method = RequestMethod.POST)
	public String franchiserDetailSubmit(FranchiserMember franchiserMember) {
		
		fService.updateFranchiserMember(franchiserMember);
		
		Stamp stamp = new Stamp();
		stamp.setFid(franchiserMember.getFid());
		stamp.setStamptype(franchiserMember.getStamptype());
		stamp.setRemark(franchiserMember.getRemark());
		
		
		stampService.saveStampRegister(stamp);
		return "redirect:/partner/franchiserDetail/"+franchiserMember.getFid();
		
	}
	

	
	
	/**
	 * <pre>
	 *	가맹점 리스트(다음맵 마커표시)				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return  가맹점 리스트 페이지
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/map/search", method = RequestMethod.GET)
 	public String franchiserList(Model model,@RequestParam String q ,Device device,SitePreference sitePreference) {
		FranchiserMember franchiserMember = new FranchiserMember();
		model.addAttribute("franchiserMember", franchiserMember);
		model.addAttribute("q", q);
		
		
		String result = StandardOrMobile.getPageName(device, sitePreference,  "franchiser/m_franchiserList",   "franchiser/franchiserList");
		
		if (result.equals("franchiser/m_franchiserList")){
			logger.info("searchWord-----------------:" +q);
			logger.info("searchWord-----------------:" +q);
			Map<String, Object> param  = new HashMap<String, Object>();
			if(q.equals("") || q==""){
				//동이름이없거나 동이름으로 검색된 결과가 없다면
				param.put("startRow",0);
				param.put("endRow",10);
			}else{
				param.put("startRow",0);
				param.put("endRow",100);	
			}
			
			param.put("searchWord", q);
			param.put("searchType", "byDong");
			
			List<FranchiserMember> franchiserMemberList = fService.getFranchiserMemberList(param);				
			if(franchiserMemberList.size()==0){
				param.put("searchType", "byFname");//업체명
				franchiserMemberList = fService.getFranchiserMemberList(param);
				logger.info("업체명 검색완료-----------------:" );
				
			}else{
				logger.info("동이름 검색완료-----------------:" );
			}
			
			model.addAttribute("franchiserMemberList", franchiserMemberList);
			model.addAttribute("searchWord", q);
		//	ModelAndView mav = new ModelAndView();
		//   mav.addObject("FranchiserMemberList", FranchiserMemberList);
		}
		
		return StandardOrMobile.getPageName(device, sitePreference,  "franchiser/m_franchiserList",   "franchiser/franchiserList");						
		
	}
	/**
	 * <pre>
	 *				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 21.
	 * @param 
	 * @return  모바일 가맹점 검색
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/partner/mFranchiserList", method=RequestMethod.GET)
	public  String mFranchiserList(Model model,			
			@RequestParam String searchWord,
			 HttpServletRequest request){
	  
		logger.info("searchWord-----------------:" +searchWord);
		logger.info("searchWord-----------------:" +searchWord);
		Map<String, Object> param  = new HashMap<String, Object>();
	
				
		User user = (User)request.getSession().getAttribute("USERSESSION");
		if (user != null){
			param.put("ROLE",user.getRole());
			logger.info("사용자 권한-----------------:" + user.getRole());
		}else{
			logger.info("사용자 권한-----------------이 없습니다. 세션 이즈 널:" );
		}
		

	
		
		param.put("startRow",0);
		param.put("endRow",100);
		
		param.put("searchWord", searchWord);
		param.put("searchType", "byDong");//동이름
		
		List<FranchiserMember> franchiserMemberList = fService.getFranchiserMemberList(param);				
		if(franchiserMemberList.size()==0){
			param.put("searchType", "byFname");//업체명
			franchiserMemberList = fService.getFranchiserMemberList(param);
			logger.info("업체명 검색완료-----------------:" );
			
		}else{
			logger.info("동이름 검색완료-----------------:" );
		}
		
		model.addAttribute("franchiserMemberList", franchiserMemberList);
		model.addAttribute("searchWord", searchWord);
	//	ModelAndView mav = new ModelAndView();
	//   mav.addObject("FranchiserMemberList", FranchiserMemberList);
	    
	  return "franchiser/m_franchiserList";
	}
	/**
	 * <pre>
	 *				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 21.
	 * @param 
	 * @return  가맹점리스트 JSON
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/partner/franchiserListJson/{searchType}/{searchWord}/{currentPage}/{pageSize}/{pageGroupSize}", method=RequestMethod.GET)
	public  @ResponseBody List<FranchiserMember> franchiserListJson(
			 @PathVariable String searchType,
			 @PathVariable String searchWord,
			 @PathVariable String currentPage,
			 @PathVariable String pageSize,
			 @PathVariable String pageGroupSize,HttpServletRequest request){
	  
		if(searchType.equals("")){
			return null;
		}
		
		logger.info("searchWord:{}",searchWord);
	
		pagingManaer = new PagingManager();		
		Map<String, Object> param  = pagingManaer.createMysqlLimit(currentPage, pageSize);
	
				
		User user = (User)request.getSession().getAttribute("USERSESSION");
		if (user != null){
			param.put("ROLE",user.getRole());
			logger.info("사용자 권한-----------------:" + user.getRole());
		}else{
			logger.info("사용자 권한-----------------이 없습니다. 세션 이즈 널:" );
		}
		

	
		
		
		
		param.put("searchWord", searchWord);
		param.put("searchType", "byDong");//동이름
		
		List<FranchiserMember> franchiserMemberList = fService.getFranchiserMemberList(param);
		
		
		if(franchiserMemberList.size()==0){
			param.put("searchType", "byFname");//업체명
			franchiserMemberList = fService.getFranchiserMemberList(param);
			logger.info("업체명 byFname-----------------:" + searchWord);
			if(franchiserMemberList.size()==0){
				return null;
			}
		}else{
			logger.info("franchiserMemberList-----------------:" );
		}
		
		
		pagingManaer.creatPaging(currentPage,pageSize,commonService.getFoundRows(),pageGroupSize);
		
	//	ModelAndView mav = new ModelAndView();
	//   mav.addObject("FranchiserMemberList", FranchiserMemberList);
	    
	  return franchiserMemberList;
	}
	/**
	 * <pre>
	 * 	가맹점 리스트 페이징 호츨 Ajax				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 1.
	 * @param String 
	 * @return String 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/partner/getFranchiserListPaging", method = RequestMethod.GET )
	public  @ResponseBody String getFranchiserListPaging(){				
		logger.info("페이징 호출;"+pagingManaer.getPageHtml());
		return pagingManaer.getPageHtml();
	}
	/**
	 * <pre>
	 * 	가맹점 리스트 페이징 호츨 Ajax 가맹점 수				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 1.
	 * @param String 
	 * @return String 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/partner/getFranchiserCount", method = RequestMethod.GET )
	public  @ResponseBody String getFranchiserCount(){				
		logger.info("Franchiser totalCount:{}", pagingManaer.getTotalCount());
		return Integer.toString(pagingManaer.getTotalCount());
	}
	/**
	 * <pre>
	 *	가맹주 메인 페이지			
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 24.
	 * @param 
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/main", method = RequestMethod.GET)
 	public String main(Model model,HttpSession session) {
	
		Map<String,Object> param = new HashMap<String, Object>()  ;
		
		param.put("email", SecurityContextHolder.getContext().getAuthentication().getName());
		
		List<FranchiserMember> list = fService.getFranchiserListByRoleFranchaiser(param);
		List<Service> services = new ArrayList<Service>();
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i=0; i<list.size();i++){
			FranchiserMember fMember = (FranchiserMember)list.get(i);
			Service service = new Service();
			if(fMember.getStype().equals("N")){
				service.setStypeName("FID: "+StringUtil.addZero(fMember.getFid(),5)+" "+fMember.getFname() +" [ " +fMember.getStypeName()+" ]" );
			}else{
				service.setStypeName("FID: "+StringUtil.addZero(fMember.getFid(),5)+" "+fMember.getFname() +" [ " +fMember.getStypeName()+" ] ~ " + formatter.format(fMember.getEndDate()) );			
			}
			service.setFid(fMember.getFid());
			
			services.add(service);
		}
		FranchiserMember franchiserMember  = fService.getFranchiserMember (((FranchiserMember)list.get(0)).getFid());
		model.addAttribute("franchiserMemberList", list);
		session.setAttribute("services", services);		
		model.addAttribute("services", services);
		model.addAttribute("franchiserMember", franchiserMember);
		
		
		return "franchiser/main";
	}
	/**
	 * <pre>
	 *	가맹주 설정 및 정보 페이지			
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 24.
	 * @param 
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/main/{fid}", method = RequestMethod.GET)
 	public String info(@PathVariable int fid,Model model,HttpSession session) {
		FranchiserMember franchiserMember  = fService.getFranchiserMember(fid);
		model.addAttribute("franchiserMember", franchiserMember);
		
		List<Service> services = (List<Service>)session.getAttribute("services");
		model.addAttribute("services", services);
		
		return "franchiser/main";
	
	}
	/**
	 * <pre>
	 *	가맹점 메인 페이지			
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 24.
	 * @param 
	 * @return  가맹점 리스트 페이지
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/fid", method = RequestMethod.GET)
 	public String fid(Map model) {
		Map<String,Object> param = new HashMap<String, Object>()  ;
		
		//param.put("email", SecurityContextHolder.getContext().getAuthentication().getName());
		FranchiserMember f = fService.getFranchiserMember(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));
	
		model.put("franchiserMember", f);
		
		
		return "franchiser/fid";
	}
	/**
	 * <pre>
	 *	가맹점 위치정보 저장		
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 24.
	 * @param 
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/partner/location/{fid}", method = RequestMethod.GET)
	public String location(Map model,
			@PathVariable String fid
			) {
		
		Location location = new Location();
		location.setFid(fid);
		
		
		
		model.put("location", location);
		
		
		return "franchiser/m_location";
	}
	
	@RequestMapping(value = "/partner/index", method = RequestMethod.GET)
	public String index(){
				
		return "franchiser/index";
	}
	@RequestMapping(value = "/partner/2", method = RequestMethod.GET)
	public String i2(){		
		return "franchiser/2";
	}
	@RequestMapping(value = "/partner/3", method = RequestMethod.GET)
	public String i3(){		
		return "franchiser/3";
	}
	@RequestMapping(value = "/partner/4", method = RequestMethod.GET)
	public String i4(){		
		return "franchiser/24";
	}
	
	

}