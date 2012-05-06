package net.itsplace.place.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.itsplace.admin.service.AdminBaseService;
import net.itsplace.admin.service.AdminMediaService;
import net.itsplace.admin.service.AdminPlaceService;
import net.itsplace.admin.service.AdminStampService;
import net.itsplace.common.CommonService;
import net.itsplace.domain.Bascd;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Bascd.EditBascd;
import net.itsplace.domain.Place;
import net.itsplace.domain.Pmedia;
import net.itsplace.user.UserInfo;
import net.itsplace.util.FtpService;
import net.itsplace.util.ImageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class PlaceInfoController {

	private static final Logger logger = LoggerFactory.getLogger(PlaceInfoController.class);
	@Autowired
	private AdminPlaceService adminPlaceService;
	@Autowired
	private AdminStampService adminStampService;
	@Autowired
	private AdminMediaService adminMediaService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/place/edit", method = RequestMethod.GET)
	public String placeInfo(Model model) {
		model.addAttribute("place",adminPlaceService.getPlace(UserInfo.getFid()));
		model.addAttribute("stampTypeList",adminStampService.getStampTypeListAll());
		
		return "place/edit";
	}
	/**
	 * 가맹점 수정  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/edit", method = RequestMethod.POST)
 	public String placeInfoEdit(@Validated({EditBascd.class}) Place place, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			return "place/edit";
		} else {	
			adminPlaceService.editPlace(place);
			
			return "place/edit";
		}
	
	}
	/**
	 * 가맹점 대표 사진 업로드  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/upload", method = RequestMethod.POST)
 	public void placeFileUpload(ImageFileUpload file, BindingResult result, Model model, HttpServletResponse response) throws Exception {
		logger.info("filename:"+file.getFile().getOriginalFilename());
		String resultJson = "";
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			
		}else{	
			
			String orinalImagePath = imageService.convertToPng(file.getFile(),0,0);//원본
			String placeImagePath = imageService.convertToPng(file.getFile(),280,230);//대표 이미지(가맹점뷰어시) 
			String placeThumnailPath = imageService.convertToPng(file.getFile(),80,80);

			Pmedia media = new Pmedia();
			media.setFid(UserInfo.getFid());
			media.setEmail(UserInfo.getEmail());
			media.setmType(commonService.getBasecd().getMediaImage());
			media.setSize(commonService.getBasecd().getMediaLarge());
			media.setmUrl(orinalImagePath);
			media.setHost(commonService.getBasecd().getMediaImageHost());
			media.setIsDelete("N");
			logger.info("원본이미지 저장 size:"+commonService.getBasecd().getMediaLarge());
			adminMediaService.savePlaceMedia(media); //원본 이미지
			
			media.setSize(commonService.getBasecd().getMediaThumbnail());
			media.setmUrl(placeThumnailPath);			
			logger.info("썸네 저장");
			adminMediaService.savePlaceMedia(media); //썸네일 이미지 
			
			Place place = new Place();
			place.setFid(UserInfo.getFid());
			place.setFileName(placeImagePath);
			place.setImageHost(commonService.getBasecd().getMediaImageHost());
			adminMediaService.updatePlaceImage(place);
			
			
			logger.info("원본:{}",orinalImagePath);
			logger.info("가맹점대표이미지:{}",placeImagePath);
			logger.info("썸네일:{}",placeThumnailPath);
			resultJson ="{error: '',fileName:'"+commonService.getBasecd().getMediaImageHost()+placeImagePath+"'}";	
		}
		 response.setContentType("text/html");
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 
		 out.write(resultJson.getBytes());
		 response.setContentLength(out.size());
		 
		 response.getOutputStream().write(out.toByteArray());
		 response.getOutputStream().flush();
	}
	/**
	 * 인증코드  관리폼 팝업   <br />
	 * ROLE_FRANCHISER
	 * 가맹점 관리자 인증코드 수정    
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/auth", method = RequestMethod.GET)
	public String auth(Model model) {
		
		return "place/auth";
	}
	/**
	 * 인증코드 발급   <br />
	 * ROLE_FRANCHISER
	 * 가맹점 관리자 인증코드 수정    
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param 
	 * @return 
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/place/auth", method = RequestMethod.POST)
	public String authSubmit(Model model) {
		
		return "place/auth";
	}
}
