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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import com.mincoms.book.Exception.MincomsException;
import com.mincoms.book.gcm.GcmApp;
import com.mincoms.book.util.InitApplication;
import com.google.android.gcm.server.*;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired 
	ServletContext servletContext;
	@Autowired
	LocaleResolver localeResolver;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest request) throws Exception {
		String appMode = InitApplication.AppMode;
		logger.info("어플리케이션 모드:{}",servletContext.getAttribute(appMode));
		logger.info("Welcome home! the client locale is "+ locale.toString());
		logger.info("ccccccccccc");
		locale.setDefault(locale) ;
		logger.info("디폴트 로케일: "+ Locale.getDefault());
		//request.getSession().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(locale.toString()));*/
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		//GcmApp gcm = new GcmApp();
	//	gcm.sender("APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c",
	//			"AIzaSyBpTW07vNhjvRuDgsg3qZvdNQW89aOYgac", "홈이야");
		
/*		GcmApp.sender("APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c",
					"AIzaSyBpTW07vNhjvRuDgsg3qZvdNQW89aOYgac", "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		*/
		//test2();
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}

	public void test() {
		throw  new MincomsException("머야야야야양야양" +"{javax.validation.constraints.NotNull.message}");
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
