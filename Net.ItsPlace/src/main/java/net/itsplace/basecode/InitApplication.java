package net.itsplace.basecode;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class InitApplication implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {
	private final Logger logger = Logger.getLogger(getClass().getName());
	public void initialize(ConfigurableWebApplicationContext ctx) {
		
		
		
		
		String activeProfile = "";
		String imageHost = "";
		for(String profile :ctx.getEnvironment().getActiveProfiles()){
			activeProfile = profile;
			logger.info("적용 프로파일:"+profile);
			ctx.getServletContext().setAttribute("ActiveProfile",profile);
		}
		if(activeProfile.equals("Development")){
			imageHost = "http://itsplace.sungwon-it.com/img";
			
		}else if(activeProfile.equals("Deploy")){
			imageHost = "http://itsplace.sungwon-it.com/img";
			
		}
		
		ctx.getServletContext().setAttribute("ImageHost", imageHost);
		logger.info("적용 이미지호스트 URL:"+imageHost);
		
	}

}