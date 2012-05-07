<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#btnAdd').live('click',function() { 			
 			$('#authcode').submit();
 		});
 		$('#authcode').validationEngine('attach', {
 			  onValidationComplete: function(form, status){
	 			   if(status==true){
	 				  $.ajax({
	 	                     url: "/place/auth",
	 	                     type:"POST",
	 	                     data:$("form").serialize(),
	 	                     beforeSend :function(){
	 		                      var title = $('.loading').attr('title');
	 		   	 				  var overlay=$(this).attr('rel'); 
	 		   	 	 			  c.loading(title,overlay);
	 	                     },
	 	                     success: function(response){
	 	                       if(response.status=="SUCCESS"){
	 	                    	  c.showSuccess("인증코드를 변경하였습니다",1000);
	 	                    	
	 	                       }else{
	 	                    	   c.showError(response.result,1000);
	 	                       }
	 	                     },
	 	                     error: function(jqXHR, textStatus, errorThrown){
	 	                    	 c.showError(textStatus+jqXHR+errorThrown,1000); 
	 	                     },
	 	                     complete:function(){
	 	                    	 setTimeout("c.unloading()",500); 
	 	                    
	 	                     }
	 	               });//ajax
	 			   }
	 	  	}  
	 	}); //validationEngine 
 	});//ready
 	
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 인증코드 관리   </span>
	</div>
	<div class="content">
		<form:form  action="auth" commandName="authcode" method="post">
	   		<div class="boxtitle">
		       <c:set var="errors"><form:errors path="*" /></c:set>
		       <c:if test="${not empty errors}">
		        <span class="ico color lightbulb"></span><span>Exception:</span>
		        ${errors }
		       </c:if>
		    </div>
            <div class="section" >
                <label> 현재 인증코드  <small></small></label>   
                <div>
                 <input id="authcode" type="text" name="authCode" class="validate[required,minSize[4],maxSize[4]] medium "  value="${authcode.authCode }"/>                                   
                  <span class="f_help"인증코드/span>
                </div>
           </div>
           <div class="section" >
                <label> 변경할 인증코드  <small></small></label>   
                <div>
                 <input id="newAuthCode" type="text" name="newAuthCode" class="validate[required,minSize[4],maxSize[4]] medium "  value="${authcode.newAuthCode }"/>                                   
                  <span class="f_help"인증코드/span>
                </div>
           </div>
           <div class="section" >
                <label> 변경할 인증코드 재입력 <small></small></label>   
                <div>
                 <input id="confirmAuthCode" type="text" name="confirmAuthCode" class="validate[required,minSize[4],maxSize[4],equals[newAuthCode]] medium "  value="${authcode.confirmAuthCode }"/>                                   
                  <span class="f_help"인증코드/span>
                </div>
           </div>
         
         
	       <div class="section last">
	            <div>
	                 <a id="btnAdd" class="uibutton loading submit_form" title="Saving" rel="1" >submit</a> 
	                 <a class="uibutton special clear_form"  >clear form</a> 
	                 <a class="uibutton loading cancel" title="Checking" rel="0" >Cancel</a> 
	            </div>
	       </div>
	  </form:form>
	</div>
</div>


