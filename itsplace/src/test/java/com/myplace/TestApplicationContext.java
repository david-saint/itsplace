package com.myplace;
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

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * <pre>
 * tiles-context는 제외함 테스트 코드에서 UI는 테스트하지않는다.				
 * </pre>
 * @author 김동훈
 * @version 1.0, 2011. 8. 22.
 * @see 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
"classpath:/config/db-context.xml", 
"classpath:/config/root-context.xml",
"classpath:/config/servlet-context.xml", 
"classpath:/config/view-context.xml",
"classpath:/config/upload-context.xml",
"classpath:/config/mail-context.xml",
"classpath:/config/social-context.xml",
"classpath:/config/security-context.xml"})
public abstract class TestApplicationContext {

@Autowired protected ApplicationContext context;

}


