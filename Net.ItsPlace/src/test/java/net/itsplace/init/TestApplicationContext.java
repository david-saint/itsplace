package net.itsplace.init;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
 
import junit.framework.TestCase;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * 
 * 			
 * 
 * @author 김동훈
 * @version 1.0, 2011. 8. 22.
 * @see 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("Test")
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/ibatis-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/servlet-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/view-context.xml",
		"file:src/main/webapp/WEB-INF/spring/upload-context.xml",
		"file:src/main/webapp/WEB-INF/spring/mail-context.xml",
		"file:src/main/webapp/WEB-INF/spring/social-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public abstract class TestApplicationContext {

	@Autowired protected ApplicationContext context;

}