package com.myplace.common;

import java.security.Principal;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myplace.openapi.DaumController;
import com.myplace.user.User;
import com.myplace.user.UserController;
import com.myplace.user.UserService;
import com.myplace.util.PagingManager;


/**
 * <pre>
 *	각 서비스에 공통적으로 자주 사용될 서비스		
 * </pre>
 * @author 김동훈
 * @version 1.0, 2011. 8. 21.
 * @see 
 */
@Controller
public class CommonController {
	private static final Logger logger =  LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private CommonService commonService;

	private String addresssPaging;

	/**
	 * <pre>
	 *				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 21.
	 * @param 동이름
	 * @return addressList 주소리스트 JSON
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/common/getAddressList/{searchWord}/{currentPage}/{pageSize}/{pageGroupSize}", method=RequestMethod.GET)
	public  @ResponseBody List<Address> commonGetAddressList(
			 @PathVariable String searchWord,			 
			 @PathVariable String currentPage,
			 @PathVariable String pageSize,
			 @PathVariable String pageGroupSize,
			 Model model) {
	  
		if(searchWord.equals("")){
			return null;
		}
		
	//진료과 문자열 저장 임시 배열
	//ArrayList<String> temp = new ArrayList<String>();
		
	//지번주소 먼저 검색 후 도로명 검색
	
	
		PagingManager pagingManaer = new PagingManager();		
		Map<String, Object> param  = pagingManaer.createMysqlLimit(currentPage, pageSize);
	
		 
			// 지번주소 진천동 525-5
					
			ArrayList<String> temp = new ArrayList<String>();
			ArrayList<String> temp2 = new ArrayList<String>();				
			
			StringTokenizer st = new StringTokenizer(searchWord," ");
		    logger.info("검색어:" + searchWord);
			while (st.hasMoreTokens()) {																
				temp.add(st.nextToken());				
			}
			
			for(int i = 0 ; i<temp.size();i++){
				if(i==0){
					param.put("bupname", temp.get(0));
					logger.info("지번주소 동이름:" +temp.get(0));
				}
				if(i==1){
					param.put("jimain",  temp.get(1));
					logger.info("지번주소 번지:" +temp.get(1));				
						StringTokenizer bunji = new StringTokenizer(temp.get(1),"-");
						while (bunji.hasMoreTokens()) {
							temp2.add(bunji.nextToken());
						}
						for(int j = 0 ; j<temp2.size();j++){
							if(j==0){
								param.put("jimain", temp2.get(0));
								logger.info("번지 jimain:" +temp2.get(0));
							}
							if(j==1){
								param.put("jisubmain", temp2.get(1));
								logger.info("번지 jisubmain:" +temp2.get(1));
							}
						}
					
				}
			}
		
	

		List<Address> addressList = commonService.getAddressList(param);		
		//logger.info("전체게시물수;---------"+commonService.getFoundRows());
		
		pagingManaer.creatPaging(currentPage,pageSize,commonService.getFoundRows(),pageGroupSize);
							
		logger.info(pagingManaer.getPageHtml());
		
		addresssPaging = pagingManaer.getPageHtml();
		
		
	  return addressList;
	}
	/**
	 * <pre>
	 * 	페이징 호츨 Ajax				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 21.
	 * @param String 
	 * @return String 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/common/getPaging", method = RequestMethod.GET )
	public  @ResponseBody String commonGetPaging(){				
		//PagingManager pagingManaer = new PagingManager();
		
	//	pagingManaer.creatPaging(currentPage,pageSize,commonService.getFoundRows(),pageGroupSize);
		
		logger.info("주소페이징:" + addresssPaging);
		return addresssPaging;
	}
	/**
	 * <pre>
	 * 	Jquery Ajax Test restful이 아님 겟방식 호출(@RequestParam)				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 21.
	 * @param String 
	 * @return String 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/common/getAjax", method = RequestMethod.GET )
	public  @ResponseBody String getAjax(@RequestParam String testParam) {
		
		logger.info("ajax test");
		return "helloAjax" + testParam;
	}
	/**
	 * <pre>
	 * 	Jquery getJson Test restful이 아님 겟방식 호출(@RequestParam)				
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 21.
	 * @param String 
	 * @return String 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/common/getJson", method = RequestMethod.GET )
	public  @ResponseBody Map<String,String> getJson(@RequestParam String testParam) {
	
		logger.info("json test");
		Map<String, String> jsonMap = new HashMap<String, String>()  ;
		
		jsonMap.put("mail","test@test.com"+testParam);
		jsonMap.put("name","바보야"+testParam);
		return jsonMap;
	}

	@RequestMapping(value="/common/getJsonObject/{testParam}", method = RequestMethod.GET )
	public  @ResponseBody User getJsonObject(@PathVariable String testParam) {
		
		logger.info("json test");
		User user = new User();
		user.setEmail("이메일테스트_"+ testParam);
		user.setName("홍길동_"+ testParam);
		
		return user;
	}
	
 
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public String testControl2(){
		return "test2";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String testControl(
			@RequestParam(value="pageNo",required=false) String currentPage,
			@RequestParam(value="pageSize",required=false) String pageSize,
			@RequestParam(value="pageGroupSize",required=false) String pageGroupSize,
			Model model) {
		
		
		PagingManager pagingManaer = new PagingManager();		
		Map<String, Object> param  = pagingManaer.createMysqlLimit(currentPage, pageSize);		
		List<Address> addressList = commonService.getAddressList(param);		
		pagingManaer.creatPaging(currentPage,pageSize,commonService.getFoundRows(),pageGroupSize);
		
		
		
		
		logger.info(pagingManaer.getPageHtml());
		
		model.addAttribute("addressList",addressList);
		model.addAttribute("pagingManaer",pagingManaer);
		
		return "test"; 
	}
	
}
