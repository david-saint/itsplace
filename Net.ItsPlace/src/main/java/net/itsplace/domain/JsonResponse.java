package net.itsplace.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class JsonResponse {
	private static final Logger logger = LoggerFactory.getLogger(JsonResponse.class);
	
	public final static String SUCCEESS = "SUCCESS";
	public final static String FAIL = "FAIL";
	private String status = null;
	private Object result = null;
	private String paging = null;
	private int totalCount;
	
	@Autowired
	MessageSource messageSource;
	
	public JsonResponse getValidationErrorResult(BindingResult bindingResult, JsonResponse jsonResponse) {
		if(messageSource == null){
			System.out.println("messageSource 널입니다.");
		}
	    List<ValidationResult> errors = new ArrayList<ValidationResult>(); 
	    for (FieldError fieldError : bindingResult.getFieldErrors()) {    
	    	
	    	String message=null;
	    	for(int i=0; i<fieldError.getCodes().length; i++){
	    		try{
//	    			 message = this.messageSource.getMessage(fieldError.getCodes()[i], fieldError.getArguments(), Locale.getDefault());
	    			
	    			 message = this.messageSource.getMessage(fieldError.getCodes()[i],  new Object [] {fieldError.getRejectedValue()}, Locale.getDefault());
	    			 logger.info("필드에러메세지:{}",fieldError.getCodes()[i] + "message:"+message);
		    		 if(message != null){
		    			logger.info("fieldError:{}",fieldError.getCodes()[0]);
		    		    logger.info("getArguments:{}",fieldError.getArguments());
		    		    logger.info("Locale:{}",Locale.getDefault());
		    			logger.info("Validation message:{}",message);
		    			break;
		    		 }
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		 
	    	}	    	
	    
	        errors.add(new ValidationResult(fieldError.getField(), fieldError.getCode(), message));
	    }
	    jsonResponse.setResult(errors);
	    jsonResponse.setFail();
	    return jsonResponse;

	}		
	public void setFail() {
		
		this.status = this.FAIL;
	}
	public void setSuccess() {
		
		this.status = this.SUCCEESS;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getPaging() {
		return paging;
	}

	public void setPaging(String paging) {
		this.paging = paging;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
}
