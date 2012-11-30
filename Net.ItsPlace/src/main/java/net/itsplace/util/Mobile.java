package net.itsplace.util;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;

public class Mobile {

	public static Boolean isMobile(Device device,SitePreference sitePreference){
		Boolean result = false;
		System.out.println("mobile:"+device.isMobile() + "sitePreference:"+sitePreference);
		if (device.isMobile()) {
			
			result = true;     
        } else {
        	result = false;        
        }
		if (sitePreference == SitePreference.MOBILE) {
			result = true;     
        } else {
        	result = false;       
        }
		System.out.println("mobile:"+result);
		return result;
	}
}
