package com.mincoms.book.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import com.mincoms.book.Exception.MincomsException;
import com.mincoms.book.domain.BookRental;
import com.mincoms.book.domain.JsonResponse;
import com.mincoms.book.domain.UserEtcInfo;
import com.mincoms.book.domain.UserInfo;
import com.mincoms.book.gcm.GcmApp;
import com.mincoms.book.security.SignedUser;
import com.mincoms.book.service.RentalService;
import com.mincoms.book.service.UserService;
import com.mincoms.book.util.InitApplication;
import com.google.android.gcm.server.*;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	LocaleResolver localeResolver;

	@Autowired
	UserService userService;
	@Autowired
	RentalService rentalService;
	/**
	 * <b>인텍스 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24	
	 * @param locale ?lang=ko,?lang=en  기본 로케일이 변경됨
	 * @return home.jsp 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest request) throws Exception {
		String appMode = InitApplication.AppMode;
		logger.info("어플리케이션 모드:{}",request.getSession().getServletContext().getAttribute("AppMode"));
		logger.info("Locale"+ locale.toString());		
		locale.setDefault(locale) ;
		logger.info("디폴트 로케일 설정: "+ Locale.getDefault());
		//request.getSession().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(locale.toString()));*/
		return "home";
	}
	@RequestMapping(value = "/barcode", method = RequestMethod.GET)
	public String barcode() throws Exception {
		
		return "barcode";
	}
	/**
	 * <b>도서대여 </b> <br />
	 * 도서 대출여부를 확인하고 도서를 대출한다
	 * <pre>
	 * <b>History:</b>
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param ISBN 
	 * @param Day  대출 일수
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/rental", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse rental(
			@RequestParam(required=true)  String userIdNumber,
			@RequestParam(required=true)  String isbn,
			@RequestParam(defaultValue="7") Integer day,
			Model model) throws Exception  {
		logger.debug("isbn:{}",isbn);
		logger.debug("day:{}",day);
		logger.debug("userIdNumber:{}",userIdNumber);
		
		JsonResponse json = new JsonResponse();
	    UserEtcInfo user =	userService.findByUserIdNumber(userIdNumber);
	    if(user == null){
	    	json.setFail();
	    	json.setResult("등록된  사용자가 아닙니다");
	    }else{
	    	UserInfo userInfo = userService.findByUserName(user.getUserName());
		 json = rentalService.saveRental(isbn, day, userInfo);
			
	    }
	    
		return json;
	}
	/**
	 * <b>도서반납 </b> <br />
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 9. 6
	 * @param id 반납 id 
	 * @return JsonResponse 
	 * @throws Exception 
	 * @see 
	 */
	@RequestMapping(value = "/return", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResponse returnPost(@RequestParam(required=true)  String userIdNumber,
												 @RequestParam(required=true)  String isbn) throws Exception  {
		UserEtcInfo user =	userService.findByUserIdNumber(userIdNumber);
		JsonResponse json = new JsonResponse();
		
		if(user == null){
			json.setFail();
	    	json.setResult("등록된  사용자가 아닙니다");
	    }else{
	    	UserInfo userInfo = userService.findByUserName(user.getUserName());
		    List<BookRental> rentals =  rentalService.findByRentalId(userInfo.getUserId(), isbn);		    
		    for(BookRental rental : rentals){
		    	json  = rentalService.returnBook(rental.getId());
		    }
		
	    	
	    }
	    	
		return json;
	}
	/*
	 * ?lang=ko 기본 로케일 변경 
	 * */
	@RequestMapping(value = "/locale", method = RequestMethod.GET)
	public String locale(Locale locale, Model model,HttpServletRequest request) throws Exception {				
		logger.info("기본 Locale"+ locale.toString());		
		locale.setDefault(locale) ;
		logger.info("디폴트 로케일 설정: "+ Locale.getDefault());
		return "home";
	}
	@RequestMapping(value = "/noty", method = RequestMethod.GET)
	public String noty() throws Exception {				
		String registrationId = "APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c";
		Sender sender = new Sender("AIzaSyBpTW07vNhjvRuDgsg3qZvdNQW89aOYgac");
        Message message = new Message.Builder()
        .addData("message","ssssssssssuhu!!!!").build();
        Result result = sender.send(message, registrationId, 5);
       String status = "Sent message to one device: " + result;
        System.out.println(status);
		return "home";
	}

	
	
	public void test2(){
		ArrayList<String> regid = new ArrayList<String>();
		try {

//		regid.add("APA91bFEoMDndXIRnhwEw_taALjBfgoTQitNqTWVBWvDD1D97zM_jANyHx4K_CWsrHZo3YyRQAaOPSIAjRfcxLb5-i0uKfc4PGf5pArA5xNG4RXRJkHy9jCvP8clUd5RdoZhTmfZ2GSm");
		regid.add("APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c");
		Sender sender = new Sender("AIzaSyBpTW07vNhjvRuDgsg3qZvdNQW89aOYgac");
		String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);
		boolean SHOW_ON_IDLE = true;	//기기가 활성화 상태일때 보여줄것인지
		int LIVE_TIME = 1;	//기기가 비활성화 상태일때 GCM가 메시지를 유효화하는 시간
		int RETRY = 0;	//메시지 전송실패시 재시도 횟수
		
		Message message = new Message.Builder()
		.collapseKey(MESSAGE_ID)
		.delayWhileIdle(SHOW_ON_IDLE)
		.timeToLive(LIVE_TIME)
		.addData("message","PUSHssssssssssssss!!!54야후11")
		.build();
		
//			MulticastResult result = sender.send(message,regid,RETRY);
//			Result result = sender.send(message,"APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c",RETRY);
			Result result = sender.send(message,"APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c",RETRY);
		
			if (result.getMessageId() != null) {
				 String canonicalRegId = result.getCanonicalRegistrationId();
				 if (canonicalRegId != null) {
				   // same device has more than on registration ID: update database
					// System.out.println
				 }
				} else {
				 String error = result.getErrorCodeName();
				 if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
				   // application has been removed from device - unregister database
				 }
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
