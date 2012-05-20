package net.itsplace.web.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.PlaceComment;
import net.itsplace.domain.PlaceComment.AddPlaceComment;
import net.itsplace.domain.Social;
import net.itsplace.domain.Bascd.EditBascd;
import net.itsplace.place.service.PlaceCommentService;
import net.itsplace.web.service.IndexService;
import net.itsplace.web.service.PlaceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
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

@Controller
public class PlaceController {
	private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private PlaceCommentService placeCommentService;
	
	@Inject
	private ConnectionRepository connectionRepository;

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
		List<PlaceComment> placeCommentList = placeService.getPlaceCommentList(fid);
		model.addAttribute("placeCommentList",placeCommentList);
		model.addAttribute("placeCommentCount",placeCommentList.size());
		
		Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook.class);
		if (facebook == null) {
			model.addAttribute("facebook", null);
		}else{
			model.addAttribute("facebook", facebook.getApi().userOperations().getUserProfile());
			logger.info("getDisplayName:{}",facebook.getDisplayName());
			logger.info("getImageUrl:{}",facebook.getImageUrl());
			
		}
		Connection<Twitter> twitter = connectionRepository.findPrimaryConnection(Twitter.class);
		if (twitter == null) {
			model.addAttribute("twitter", null);
		}else{
			model.addAttribute("twitter", twitter.getApi().userOperations().getUserProfile());
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
	 @RequestMapping(value = "/place/addComment", method = RequestMethod.GET)
	 public @ResponseBody JsonResponse addComment(@Validated({AddPlaceComment.class}) PlaceComment placeComment, BindingResult result, Model model){
		 JsonResponse json = new JsonResponse();
		 if (result.hasErrors()) {
				logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
				json.setResult("댓글 입력에 실패 하였습니다");
				json.setResult("FAIL");
		 }else{
			 placeCommentService.savePlaceComment(placeComment);
			 json.setResult("");
			 json.setResult("SUCCESS");
		 }
	    	return json;
	    }
}
