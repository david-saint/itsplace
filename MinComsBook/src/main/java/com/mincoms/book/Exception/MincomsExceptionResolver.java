package com.mincoms.book.Exception;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.util.WebUtils;

import com.mincoms.book.admin.controller.AdminController;
import com.mincoms.book.admin.repository.ExceptionRepository;
import com.mincoms.book.domain.AppException;


public class MincomsExceptionResolver extends SimpleMappingExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(MincomsExceptionResolver.class);
	private String ajaxErrorView;
	
	@Autowired
	private ExceptionRepository exceptionRepo;
	
	@Autowired
	private MessageSource messageSource;
	 
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		AppException appException = null;
		MethodConstraintViolationException methodValidation = null;
		
		if(ex.getClass().getName().equals("org.hibernate.validator.method.MethodConstraintViolationException")){
			
		    methodValidation = (MethodConstraintViolationException) ex;
 			Set<MethodConstraintViolation<?>> constraintViolations = methodValidation.getConstraintViolations();
 			System.out.println(constraintViolations.size());
 			for (MethodConstraintViolation<?> violation : methodValidation.getConstraintViolations()) {
 				System.out.println(violation.getMessage());
 				System.out.println(violation.getConstraintDescriptor());
    		}
	 		
		}else{
		    AppException e = new AppException();
	        StringBuffer sb = new StringBuffer();
	        
	        e.setMessage(ex.getMessage());
	        StackTraceElement[] st =ex.getStackTrace();
	        for (StackTraceElement element : st) {
	        //    logger.error(element.toString());
	        	 
	            sb.append(element.toString());
	        }
	        
	        ex.printStackTrace();        
	        e.setStackTrace(sb.toString().replaceAll("[\"]","aaa"));
	        e.setDeleted(false);
	        e.setCompleted(false);
	        e.setRegDate(new Date());
	        appException = exceptionRepo.save(e);
		}
	 		
  	   
    
        
		if( isAjax(request)) {        
			String viewName = determineViewName(ex, request);
			String exceptionMessage ="";
			if(appException != null){
				exceptionMessage= "Exception ID = ["+appException.getId()+"] \n";
				exceptionMessage += messageSource.getMessage("admin.error.message",null, Locale.getDefault())+" \n";
			}else{//메소드 밸리데이션
				for (MethodConstraintViolation<?> violation : methodValidation.getConstraintViolations()) {
					exceptionMessage += violation.getMessage() + "\n";
	 				
	    		}
			}
			
			if(logger.isDebugEnabled()){
				//exceptionMessage += sb.toString();
			}
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode); //status 코드 적용
                    
            }
           
            	System.out.println("오류메세지 true:"+ajaxErrorView);
                //exceptionMessage += "\n" + getExceptionMessage(ex);
	            ModelAndView m = new ModelAndView(ajaxErrorView);
	            m.addObject("ajaxExceptionMessage", exceptionMessage);
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            
	            logger.info("=========================================== Ajax Exception") ;
            return m;
        }else{
        	logger.info("===========================================Exception") ;
    	    return super.doResolveException(request, response, handler, ex);
        }
 	       

	}
	
	private String getExceptionMessage(Throwable e) {
	        String message = "";
	        while( e != null ) {
	            message += e.getMessage() + "\n";
	            e = e.getCause();
	        }
	      
	        return message;
	}
	
	private boolean isAjax(HttpServletRequest request) {
			        
       Enumeration enumeration = request.getHeaderNames();
       while (enumeration.hasMoreElements()){
       	String temp = enumeration.nextElement().toString() ;
       	System.out.println(temp);
       	
       }
		if(request.getHeader("Accept")!= null){			
			//System.out.println("X-Requested-With:"+request.getHeader("X-Requested-With"));			 
			if(request.getHeader("Accept").equals("application/json")){
				System.out.println("Accept:"+request.getHeader("Accept"));
				return true;
			}
			else{
				System.out.println("Accept:"+request.getHeader("Accept"));
				return false;
			}
		}else{
			System.out.println("Accept null");
			return false;
		}        
    }
	
	public String getAjaxErrorView() {
		return ajaxErrorView;
	}

	public void setAjaxErrorView(String ajaxErrorView) {
		this.ajaxErrorView = ajaxErrorView;
	}

	
	
}
