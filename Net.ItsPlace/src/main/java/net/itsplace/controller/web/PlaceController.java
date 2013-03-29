package net.itsplace.controller.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceComment.AddPlaceComment;
import net.itsplace.module.event.PlaceEventService;
import net.itsplace.module.menu.PlaceMenuService;
import net.itsplace.repository.PlacePredicates;
import net.itsplace.service.MediaService;
import net.itsplace.service.PlaceCommentService;
import net.itsplace.service.PlaceReviewService;
import net.itsplace.service.PlaceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.mysema.query.types.Predicate;

@Controller
public class PlaceController {
	private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
	@Autowired
	PlaceService placeService;
	@Autowired
	PlaceReviewService placeReviewService;
	@Autowired
	PlaceCommentService placeCommentService;
	@Autowired
	PlaceEventService placeEventService;
	@Autowired
	MediaService mediaService;
	
	@Autowired
	PlaceMenuService placeMenuService;
	
		
	
	@Inject
	private ConnectionRepository connectionRepository;

	private  Twitter twitter;
	
	
	@Inject
	public PlaceController(Twitter twitter){
		this.twitter = twitter;
	}
	
	@RequestMapping(value = "/places", method = RequestMethod.GET)
	public String places(Locale locale, Model model) {
		Locale.setDefault(locale);
		//System.out.println("기본로케일"+locale.getDefault());
		//System.out.println("기본로케일"+Locale.getDefault());
		return "web/place/list";
	}
	@RequestMapping(value = "/place/test", method = RequestMethod.GET)
	public String ptest(Model model) {
		
		return "web/place/test";
	}
	/**
	 * 웹 가맹점 검색
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search/place", method = RequestMethod.POST)
	public @ResponseBody JsonResponse  places(@RequestParam(required=false, defaultValue="1") Integer currentPage,
			 									@RequestParam(required=false, defaultValue="10") Integer pageSize ,
			 									@RequestParam(required=false, defaultValue="10") Integer pageGroupSize ,
			 									@RequestParam(required=false, defaultValue="") String searchWord 
			 									){
		logger.info("currentPage:{}",currentPage);
		logger.info("pageSize:{}",pageSize);
		logger.info("pageGroupSize:{}",pageGroupSize);
		logger.info("searchWord:{}",searchWord);
		//Map<String, Object> param  = pagingManaer.createMysqlLimit(currentPage, pageSize);
		
		JpaPaging paging = new JpaPaging();
		Predicate predicate =PlacePredicates.isAuth(true);
		
		Page<Place> list = placeService.findByAll(predicate, paging);
		
		JsonResponse json = new JsonResponse();
		json.setResult(list.getContent());
		json.setSuccess();
		return json;
	}
	/**
	 * 가맹점 상세보기  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2012. 5. 18.
	 * @param fid
	 * @param model
	 * @return view.jsp
	 * @throws 
	 * @see 
	 */

	@RequestMapping(value = "/place/view/{fid}", method = RequestMethod.GET)
	public String view( @PathVariable Integer fid, Model model) {
		model.addAttribute("place", placeService.getPlace(fid));
		//Map<String, Object> param  = pagingManaer.createMysqlLimit(1, 10);
	//	param.put("fid", fid);
		
		
		model.addAttribute("placeEventList", placeEventService.getPlaceEventList(fid));
		model.addAttribute("placeMediaList",mediaService.findByPlace(fid));
		model.addAttribute("placeMenuList", placeMenuService.findByPlace(fid));
		model.addAttribute("placeReviewList",placeReviewService.getPlaceReviewAll(fid));
//		model.addAttribute("placeStampList",placeService.getPlaceStampListByPlace(fid));
		
	//	List<PlaceComment> placeCommentList = placeService.getPlaceCommentList(param);
		//List<PlaceComment> placeCommentList = placeService.getPlaceCommentList(fid);
		//model.addAttribute("placeCommentList",placeCommentList);
		//model.addAttribute("placeCommentCount",placeCommentList.size());
	
		try{					
			Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook.class);
			if (facebook == null) {
				logger.info("facebook null");
				model.addAttribute("facebook", null);
			}else{
				model.addAttribute("facebook", facebook.getApi().userOperations().getUserProfile());
				logger.info("getDisplayName:{}",facebook.getDisplayName());
				logger.info("getImageUrl:{}",facebook.getImageUrl());
				
			}
			Connection<Twitter> twitter = connectionRepository.findPrimaryConnection(Twitter.class);
			if (twitter == null) {
				logger.info("twitter null");
				model.addAttribute("twitter", null);
			}else{
				model.addAttribute("twitter", twitter.getApi().userOperations().getUserProfile());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "web/place/view";
	}
	/**
	 * 가맹점 댓글 저장  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2012. 5. 18.
	 * @param fid
	 * @param model
	 * @return view.jsp
	 * @throws 
	 * @see 
	 */
	 @RequestMapping(value = "/place/addComment", method = RequestMethod.POST)
	 public @ResponseBody JsonResponse addComment(@Validated({AddPlaceComment.class}) PlaceComment placeComment, BindingResult result, Model model){
		 logger.info("comment:"+placeComment.getComment());
		 JsonResponse json = new JsonResponse();
		 if (result.hasErrors()) {
				logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
				json.setResult(result.getFieldError().getDefaultMessage());
				json.setFail();
		 }else{
			 placeCommentService.savePlaceComment(placeComment);			
			 json.setResult("");
			 json.setStatus("SUCCESS");
			 
			 //twitter.directMessageOperations().sendDirectMessage(placeComment.getEmail(),placeComment.getComment());
		 }
	    	return json;
	 }
	 /**
		 * 가맹점 댓글 삭제   <br />
		 * 
		 * @author 김동훈
		 * @version 1.0, 2012. 5. 18.
		 * @param cid 코멘트 pk
		 * @return view.jsp
		 * @throws 
		 * @see 
		 */
		 @RequestMapping(value = "/place/deleteComment", method = RequestMethod.POST)
		 public @ResponseBody JsonResponse deleteComment(@RequestParam(required=true) Integer cid){
			 JsonResponse json = new JsonResponse();
			 if(placeCommentService.deletePlaceComment(cid)){
				 json.setResult("");
				 json.setResult("SUCCESS");
			 }else{
				 json.setResult("댓글 삭제  권한이 없습니다.");
					json.setResult("FAIL");
			 }
			
		     return json;
		 }
	 /**
		 * 가맹점 코멘트 리스트 
		 * @param currentPage
		 * @param pageSize
		 * @param pageGroupSize
		 * @param fid
		 * @return
		 */
		@RequestMapping(value = "/place/getPlaceCommentListJson", method = RequestMethod.GET)
		public @ResponseBody JsonResponse  getPlaceCommentList(@RequestParam(required=false, defaultValue="1") Integer currentPage,
														 @RequestParam(required=false, defaultValue="10") Integer pageSize ,
														 @RequestParam(required=false, defaultValue="10") Integer pageGroupSize,
														 @RequestParam(required=false, defaultValue="0") Integer fid
														){

			String columns[] = new String[]{"title", "startDate", "endDate"};                                       
            JpaPaging paging = new JpaPaging(columns, currentPage, pageGroupSize, 0, "desc", "");
            
			Page<PlaceComment> placeCommentList = placeCommentService.findPlaceCommentList(paging, fid);
			int totalCount = (int) placeCommentList.getTotalElements();
		//	String paging = pagingManaer.creatPaging(currentPage, pageSize, totalCount, pageGroupSize);
			
			JsonResponse json = new JsonResponse();
		    json.setResult(placeCommentList);
			//json.setPaging(paging);
			json.setTotalCount(totalCount);
			return json;
		}
		@RequestMapping(value = "/place/hellowWorld", method = RequestMethod.GET, produces = "application/json")
		
		public @ResponseBody List<HellowWorld> HellowWorld(){
			logger.info("헬로우");
			List<HellowWorld> list = new ArrayList();
			HellowWorld h = new HellowWorld();
			h.setPart1("j part1");
			h.setPart2("34  ap2");
			list.add(h);
			list.add(h);
			return list;
		}
		public class HellowWorld{
			private String part1;
			private String part2;
			public String getPart1() {
				return part1;
			}
			public void setPart1(String part1) {
				this.part1 = part1;
			}
			public String getPart2() {
				return part2;
			}
			public void setPart2(String part2) {
				this.part2 = part2;
			}
			
			
		}
}



