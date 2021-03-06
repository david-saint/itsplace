package net.itsplace.controller.admin;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import net.itsplace.basecode.BaseCodeService;
import net.itsplace.basecode.PlaceType;
import net.itsplace.basecode.ServiceType;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.domain.Place.AddPlace;
import net.itsplace.domain.Place.EditPlace;
import net.itsplace.domain.PlaceStamp;
import net.itsplace.domain.PlaceStamp.AddPlaceStamp;
import net.itsplace.domain.PlaceStamp.EditPlaceStamp;
import net.itsplace.repository.CategoryRepository;
import net.itsplace.service.BaseService;
import net.itsplace.service.MediaService;
import net.itsplace.service.PlaceService;
import net.itsplace.service.StampBaseService;
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
@RequestMapping("/admin/place")
public class AdminPlaceController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceController.class);
	@Autowired
	PlaceService adminPlaceService;
	@Autowired
	StampBaseService stampBaseService;
	@Autowired
	protected MediaService adminMediaService;
	@Autowired
	private PagingManager pagingManaer;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	BaseCodeService baseCodeService;
	@Autowired
	BaseService commonService;
	/**
	 * 가맹점 승인관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String place(Locale locale, Model model) {
		return "admin/place/request";
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		return "admin/place/list";
	}
	
	/**
	 * 가맹점별 회원관리
	 * @param locale jhgjhgjhgjhg
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userList(Locale locale, Model model) {
		return "admin/place/user/list";
	}
	
	/**
	 * 가맹점 삭제/등록
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/stamp/list", method = RequestMethod.GET)
	public String list(@RequestParam(required=true) Integer fid, Model model) {
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("placeStampList",stampBaseService.getPlaceStampAll(fid));			
		return "admin/place/stamp/list";
	}
	
	/**
	 * 관리자가 가맹점 스탬프 수정    <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/edit", method = RequestMethod.GET)
	public String placeStamp(@RequestParam(required=true) Integer fid,
							 @RequestParam(required=true) Integer stampid,
							 Model model)  {
		//List<PlaceStamp> placeStampList = stampBaseService.getPlaceStampAll(fid);
		
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("stampTypeList",stampBaseService.getStampTypeListAll());
		model.addAttribute("themeList",commonService.getBascdList("STAMPTHEME"));
		System.out.println("djjjjjjjjjjjjjjjjjjjjjjjjjjj");
		//List<PlaceStamp> placeStampList = stampBaseService.getPlaceStampAll(fid);
		/*if(placeStampList.size()<=0){
			model.addAttribute("placeStampList",null);
			model.addAttribute("placeStamp", new PlaceStamp());
		}else{
			model.addAttribute("placeStampList",placeStampList);
			model.addAttribute("placeStamp", placeStampList.get(0));
		}*/
		model.addAttribute("placeStamp",stampBaseService.getPlaceStamp(stampid));
		return "admin/place/stamp/edit";
	}
	/**
	 * 관리자가 가맹점 스탬프를 수정한다.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/edit", method = RequestMethod.POST)
	public @ResponseBody JsonResponse placeStampSubmit(@Validated({EditPlaceStamp.class}) PlaceStamp placeStamp,BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			stampBaseService.editPlaceStamp(placeStamp);
			json.setResult(placeStamp);
			json.setStatus("SUCCESS");
			
		}		
		
		return json;
	}
	/**
	 * 관리자가 가맹점 스탬프 등록     <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/add", method = RequestMethod.GET)
	public String placeStampAdd(@RequestParam(required=true) Integer fid, Model model)  {
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		
		model.addAttribute("placeStamp", new PlaceStamp());
		model.addAttribute("stampTypeList",stampBaseService.getStampTypeListAll());
		model.addAttribute("themeList",commonService.getBascdList("STAMPTHEME"));
	//	List<PlaceStamp> placeStampList = stampBaseService.getPlaceStampAll(fid);
	
		
		
		
		return "admin/place/stamp/add";
	}
	/**
	 * 관리자가 가맹점 스탬프를 등록한.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/add", method = RequestMethod.POST)
	public @ResponseBody JsonResponse placeStampAdd(@Validated({AddPlaceStamp.class}) PlaceStamp placeStamp,BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			stampBaseService.savePlaceStamp(placeStamp);
			json.setResult(placeStamp);
			json.setStatus("SUCCESS");
			
		}		
		
		return json;
	}
	/**
	 *  관리자가 가맹점 스탬프를 삭제   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/delete", method = RequestMethod.POST)
	public @ResponseBody JsonResponse placeStampDelete(@RequestParam(required=true) Integer stampid)  {
		JsonResponse json = new JsonResponse();
		PlaceStamp placeStamp = new PlaceStamp();
		placeStamp.setStampid(stampid);
		stampBaseService.deletePlaceStamp(placeStamp);
		json.setResult("스탬프가 삭제되었습니다");
		json.setStatus("SUCCESS");
		return json;
	}
	/**
	 *  관리자가 가맹점 스탬프를  복구   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return 
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/stamp/restore", method = RequestMethod.POST)
	public @ResponseBody JsonResponse placeStampRestore(@RequestParam(required=true) Integer stampid)  {
		JsonResponse json = new JsonResponse();
		PlaceStamp placeStamp = new PlaceStamp();
		placeStamp.setStampid(stampid);
		stampBaseService.restorePlaceStamp(placeStamp);
		json.setResult("스탬프가 복구되었습니다");
		json.setStatus("SUCCESS");
		return json;
	}
	
	/**
	 * 관리자가 가맹점을 생성한다    <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model Place
	 * @return  add.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model)  {
	
		model.addAttribute("place",new Place());
		model.addAttribute("stampTypeList",stampBaseService.getStampTypeListAll());
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("placeTypeList", baseCodeService.getPlaceTypes());
		model.addAttribute("serviceTypeList",baseCodeService.getServiceTypes());
		
		return "admin/place/add";
	}
	/**
	 * Ajax 관리자가 가맹점을 등록한다. <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addSubmit(@Validated({AddPlace.class}) Place place, BindingResult result, Model model)  {
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			return "admin/place/add";
		} else {	
			logger.info(place.toString());
			if(place.getFileName()==null|| place.getFileName().equals("")){
				//place.setImageHost("http://img.itsplace.net/img");
				place.setFileName("/empty.png");
			}
			int fid = adminPlaceService.savePlace(place)	;
			logger.info(" 가맹생성 fid:{}",fid);
			return "admin/place/list";
		}	
	}
	/**
	 * 관리자가 가맹점 수정 폼을 호출한다.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) Integer fid, Model model)  {
	
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("stampTypeList",stampBaseService.getStampTypeListAll());
	
		

		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("placeTypeList", baseCodeService.getPlaceTypes());
		model.addAttribute("serviceTypeList",baseCodeService.getServiceTypes());
		
		return "admin/place/edit";
	}
	/**
	 * Ajax 관리자가 가맹점을 수정한다. <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody JsonResponse editSubmit(@Validated({EditPlace.class}) Place place, BindingResult result, Model model)  {
		JsonResponse json = new JsonResponse();
		System.out.println("edit");
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {	
			adminPlaceService.editPlace(place);
			json.setResult(place);
			json.setStatus("SUCCESS");
			
		}		
		
		return json;
	}
	/**
	 * Ajax 가맹점 승인 하고 인증코드를 0000으로 초기화한다 <br />
	 * qr인증코드 fid|md5(fid) 로 이미지를 생성하고 mcode를 md(fid)로 초기화한다.
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹점 pk
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody JsonResponse enable(@RequestParam(required=true) Integer fid)  {
		logger.info("fid:{}",fid);
		JsonResponse json = new JsonResponse();		
		
		adminPlaceService.enablePlace(fid); 
			
		json.setResult(null);
		json.setStatus("SUCCESS");
		return json;
	}
	/**
	 * Ajax 가맹점 승인취소   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 가맹점 pk
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public @ResponseBody JsonResponse disable(@RequestParam(required=true) Integer fid)  {
		logger.info("fid:{}",fid);
		JsonResponse json = new JsonResponse();		
		
		adminPlaceService.disablePlace(fid); 
			
		json.setResult(null);
		json.setStatus("SUCCESS");
		return json;
	}
	/**
	 * 가맹점관리 리스트<br> 
	 *  <br />
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
	@RequestMapping(value="/getPlaceList", method = RequestMethod.GET)
    @ResponseBody
    public DataTable<Place> getPlaceList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false) Integer iSortCol_0, 
    								@RequestParam(required=false) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch ) {
		  logger.info("iDisplayStart:{}", iDisplayStart.toString());
          logger.info("sSortDir_0:{}", sSortDir_0);
          logger.info("iSortCol_0:{}", iSortCol_0);
          logger.info("iDisplayLength:{}", iDisplayLength);
          logger.info("sSearch:{}", sSearch);
         
		String columns[] = new String[] { "fid", "fileName", "fname", "name",
										  "mobile", "isAuth", "dong", "saveDate", "editDate" };
	                                     
        JpaPaging paging = new JpaPaging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0,sSearch);
        
       
		return  adminPlaceService.findPlaceList(paging, true);
    }   
	/**
	 * 관리자 가맹점 대표 사진 업로드  <br />
	 * 이미지는 항상 새로 발생하고(업데이트없음) 대표이미지만 교체한다. 업데이트 없이 삭제로 함.
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
 	public void placeFileUpload(ImageFileUpload file, HttpServletResponse response) throws Exception {
		logger.info("filename:{}",file.getFile().getOriginalFilename());
		logger.info("fid:{}",file.getFid());
		String resultJson = "";
	
		String placeImagePath = adminMediaService.savePlaceMedia(file,file.getFid());
	//	resultJson ="{error: '',fileName:'"+commonService.getBasecd().getImageHost()+placeImagePath+"'}";	
		
		 response.setContentType("text/html");
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 
		 out.write(resultJson.getBytes());
		 response.setContentLength(out.size());
		 
		 response.getOutputStream().write(out.toByteArray());
		 response.getOutputStream().flush();
	}
	/**
	 * 관리자가 가맹점 이미지 업로드  폼을 호출한다.   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2012. 12. 24.
	 * @param fid
	 * @return  imageUpload.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/imageUpload", method = RequestMethod.GET)
	public String imageUpload(@RequestParam(required=true) Integer fid, Model model)  {
	
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("stampTypeList",stampBaseService.getStampTypeListAll());
		model.addAttribute("categoryList",commonService.getBascdList("CATEGORY"));
		model.addAttribute("placeTypeList",commonService.getBascdList("PLACETYPE"));
		model.addAttribute("serviceTypeList",commonService.getBascdList("SERVICETYPE"));
		return "admin/place/imageUpload";
	}
}
