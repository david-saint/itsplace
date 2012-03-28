package com.myplace.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.ui.Model;

import com.myplace.partner.franchiser.stamp.Stamp;
import com.myplace.partner.franchiser.stamp.StampController;
public class StandardOrMobile {
	
	private static final Logger logger =  LoggerFactory.getLogger(StandardOrMobile.class);
	
	public  static String  getPageName(Device device,SitePreference sitePreference,String mobile,String standard){
		if(device.isMobile()){			
			if (sitePreference == SitePreference.NORMAL) {
				logger.info("웹페이지 리턴 페이지:"+standard);
				return standard;
			}else{
				logger.info("모바일 리턴 페이지:"+mobile);
				return mobile;
			}
			
		}else{			
			if (sitePreference == SitePreference.MOBILE) {
				logger.info("모바일 리턴 페이지:"+mobile);
				return mobile;
			}else{
				logger.info("웹페이지 리턴 페이지:"+standard);
				return standard;
			}			
		}
		
	}
	
}
