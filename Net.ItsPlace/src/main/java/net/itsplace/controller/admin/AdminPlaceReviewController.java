package net.itsplace.controller.admin;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.PlaceReview;
import net.itsplace.domain.PlaceReview.AddPlaceReview;
import net.itsplace.domain.PlaceReview.EditPlaceReview;
import net.itsplace.domain.Stamp;
import net.itsplace.service.BaseServiceImpl;
import net.itsplace.service.PlaceService;
import net.itsplace.service.PlaceReviewService;
import net.itsplace.user.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminPlaceReviewController  {
	private static final Logger logger = LoggerFactory.getLogger(AdminPlaceReviewController.class);
	@Autowired
	private BaseServiceImpl commonService;
	@Autowired
	private PlaceReviewService placeReviewService;
	@Autowired
	private PlaceService adminPlaceService;

	/**
	 * 리뷰 리뷰관리 <br> 
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param fid 리뷰 pk
	 * @return DataTables
	 * @throws 
	 * @see a
	 */
	@RequestMapping(value = "/admin/place/review/list", method = RequestMethod.GET)
	public String list(@RequestParam(required=false) Integer fid, Model model) {
		
		model.addAttribute("placeReviewList", placeReviewService.getPlaceReviewAll(fid));
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		
		return "admin/place/review/list";
	}
	
	/**
	 * 리뷰 회원검색  <br />
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
	@RequestMapping(value="/admin/place/review/getReviewList")
    @ResponseBody
    public DataTable<Stamp> getReviewList(
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
                  
                    //B.stampedTotal, B.stampedLastDate, A.PROFILEIMAGEURL, A.EMAIL, A.NAME, A.MOBILE
                    String columns[] = new String[]{"profileImageUrl", "email", "name", "mobile", "stampedTotal", "stampedLastDate"};
                    
                    JpaPaging paging = new JpaPaging(columns,iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0,sSearch);
                 
                   
                    return  placeReviewService.getPlaceReviewList(paging,UserInfo.getFid());
           
                   
    }       
	
	/**
	 * 리뷰 리뷰관리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/place/review/add", method = RequestMethod.GET)
	public String add(@RequestParam(required=false) Integer fid, ModelMap model) {
		
		model.addAttribute("place",adminPlaceService.getPlace(fid));
		model.addAttribute("placeReview", new PlaceReview());

		return "admin/place/review/add";
	}
	/**
	 * 리뷰  생성 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/admin/place/review/add", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse addSubmit(@Validated({AddPlaceReview.class}) PlaceReview placeReview, BindingResult result, Model model) {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info("place:"+placeReview.toString());
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getFieldError().getDefaultMessage());
			json.setStatus("FAIL");
		} else {	
			
			placeReviewService.savePlaceReview(placeReview);
			json.setResult("리뷰를 등록하였씁니다");
			json.setStatus("SUCCESS");
		}
		return json;
	}
	/**
	 * 리뷰 리뷰 수정 폼 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/place/review/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) Integer rid, ModelMap model) {
		PlaceReview placeReview = placeReviewService.getPlaceReview(rid);
		model.addAttribute("placeReview", placeReview);		
		model.addAttribute("place",adminPlaceService.getPlace(placeReview.getPlace().getFid()));
		return "admin/place/review/edit";
	}
	/**
	 * 리뷰  수 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/admin/place/review/edit", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse editSubmit(@Validated({EditPlaceReview.class}) PlaceReview placeReview, BindingResult result, Model model) {
		JsonResponse json = new JsonResponse();
		if (result.hasErrors()) {
			logger.info("placeReview:"+placeReview.toString());
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			json.setResult(result.getAllErrors());
			json.setStatus("FAIL");
		} else {
			placeReviewService.editPlaceReview(placeReview);
			json.setResult("리뷰를 수정하였습니다");
			json.setStatus("SUCCESS");
		}
		return json;
	}
	
	/**
	 * 리뷰  삭제  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/admin/place/review/delete", method = RequestMethod.POST)
	@ResponseBody
 	public JsonResponse delete(@RequestParam(required=true) Integer rid, Model model) {
		JsonResponse json = new JsonResponse();
		try{
			placeReviewService.deletePlaceReview(rid);
			json.setResult("리뷰를 삭제하였습니다");
			json.setStatus("SUCCESS");
		}catch(Exception e){
			json.setStatus("FAIL");
		}
		
		return json;
	}
	/**
	 * 리뷰  삭제  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/admin/place/review/recovery", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse recovery(@RequestParam(required=true) Integer rid, Model model) {
		JsonResponse json = new JsonResponse();
		try{
			placeReviewService.recoveryPlaceReview(rid);
			json.setResult("리뷰를 복원하였습니다");
			json.setStatus("SUCCESS");
		}catch(Exception e){
			json.setStatus("FAIL");
		}
		
		return json;
	}
	/**
	 *메뉴  사진 업로드  <br />
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/admin/place/reviewImageUpload", method = RequestMethod.POST)
 	public void placeFileUpload(ImageFileUpload file, BindingResult result, Model model, HttpServletResponse response) throws Exception {
		logger.info("filename:{}",file.getFile().getOriginalFilename());
		
		
		String resultJson = "";
		
			
			
			PlaceReview placeReview = placeReviewService.savePlaceReviewImage(file);
		//	logger.info(commonService.getBasecd().getImageHost()+placeReview.getFilePath());
		//	resultJson ="{rid:'"+placeReview.getRid()+"',fileName:'"+commonService.getBasecd().getImageHost()+placeReview.getFilePath()+"'}";	
		
		 response.setContentType("text/html");
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 
		 out.write(resultJson.getBytes());
		 response.setContentLength(out.size());
		 
		 response.getOutputStream().write(out.toByteArray());
		 response.getOutputStream().flush();
	}
}


