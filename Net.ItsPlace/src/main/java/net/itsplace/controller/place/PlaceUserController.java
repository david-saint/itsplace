package net.itsplace.controller.place;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.PlaceUser;
import net.itsplace.domain.User;
import net.itsplace.domain.User.AddUser;
import net.itsplace.service.BaseService;
import net.itsplace.service.PlaceService;
import net.itsplace.service.PlaceUserService;
import net.itsplace.service.UserService;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/place/user")
public class PlaceUserController {

	private static final Logger logger = LoggerFactory.getLogger(PlaceUserController.class);
	@Autowired
	PlaceUserService placeUserService;
	@Autowired
	UserService userService;
	@Autowired
	PlaceService placeService;
	/**
	 * 가맹 직원 리스트 <br />
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
	
		return "place/user/list";
	}
	/**
	 * 가맹점 직원 등록 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  add.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.info("직원등록폼  ");
		return "place/user/add";
	}
	/**
	 * 가맹점 직원 등록  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
 	public String addSubmit(String[] email,Integer fid, Model model) {
		for(int i=0;i<email.length;i++){
			PlaceUser placeUser = new PlaceUser();
			placeUser.setUser(userService.getUser(email[i]));
			placeUser.setPlace(placeService.getPlace(fid));
			placeUserService.savePlaceUser(placeUser);
		}
		return "redirect:/place/user/list";
	}
	/**
	 * Ajax 관리자가 사용자를 탈퇴시킨  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JsonResponse delete(@RequestParam(required=true) Integer uid)  {
		logger.info("uid:{}",uid);
		JsonResponse json = new JsonResponse();		
		
		placeUserService.deletePlaceUser(uid); 
			
		json.setResult(null);
		json.setStatus("SUCCESS");
		return json;
	}
	
	/**
	 * 가맹점 회원 관리,  리스트 관리자는 사용자상세정보, 수정, 삭제 기 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param iDisplayStart 페이지 번
	 * @param iDisplayLength 페이지 로우수 (한페이지에 보여줄 로우수)
	 * @param iSortCol_0 sort할 컬럼 번호 
	 * @param sSortDir_0 sort할 방향(asc/desc)
	 * @param sSearch 검색
	 * @return DataTables
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value="/getPlaceUserList")
    @ResponseBody
    public DataTable<PlaceUser> getPlaceUserList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false) Integer iSortCol_0, 
    								@RequestParam(required=false) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch,
                                    @RequestParam(required=false) Integer fid) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0);
                    logger.info("iSortCol_0:{}", iSortCol_0);
                    logger.info("iDisplayLength:{}", iDisplayLength);
                    logger.info("sSearch:{}", sSearch);
                    logger.info("fid:{}", fid);
                  
                    
                	
                    String columns[] = new String[]{"uid", "profileImageUrl", "email", "name", "mobile", "isDelete",  "saveDate", "editDate"};
                    
                    
                    JpaPaging paging = new JpaPaging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0,sSearch);
                    
                   
                    return placeUserService.findPlaceUserList(paging, fid);
           
                   
    }       
	
	
	
}
