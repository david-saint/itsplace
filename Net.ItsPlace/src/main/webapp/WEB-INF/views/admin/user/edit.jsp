<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#btnEdit').live('click',function() {
 			c.log("submit Edit Form");
 			$('#user').submit();
 		});
 		 $('#user').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					 c.log("edit:"+status);
 					//$('#user').validationEngine('detach');
 					$.ajax({
 	                     url: "/admin/user/edit",
 	                     type:"POST",
 	                     data:$("form").serialize(),
 	                     beforeSend :function(){
 		                      var title = $('.loading').attr('title');
 		   	 				  var overlay=$(this).attr('rel'); 
 		   	 	 			  c.loading(title,overlay);
 	                     },
 	                     success: function(response){
 	                       if(response.status=="SUCCESS"){
 	                    	   console.log("송고");
 	                       }else{
 	                    	   var errorInfo="";
 	                    	   for(var i =0 ; i < response.result.length ; i++){
 	                               errorInfo = "<br>" + (i + 1) +". " + response.result[i].defaultMessage;
 	                           }
 	                    	   c.log(errorInfo);
 	                       }
 	                     },
 	                     error: function(jqXHR, textStatus, errorThrown){
 	                    	alert(textStatus+jqXHR+errorThrown); 
 	                     },
 	                     complete:function(){
 	                    	//$('#user').validationEngine('detach');
 	                    	 setTimeout("c.unloading()",1500); 
 	                     }
 	                   });//ajax
 	                   
 		 			   					 
 				 }
 			  }
	 	});

		
 		 
 		 
 		
 	});
 	
</script>

		

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 기초코드 </span>
	</div>
	<!-- End header -->
	<div class="content">
						<!-- title box -->
					<form:form   commandName="user" method="post">
                        <div class="boxtitle">
                        <c:set var="errors"><form:errors path="*" /></c:set>
                        <c:if test="${not empty errors}">
	                        <span class="ico color lightbulb"></span><span>Exception:</span>
	                        ${errors }
                        </c:if>
                        	
                        </div>
						  
                      
                        	
                              <div class="section" >
                                  <label> Email <small></small></label>   
                                  <div>
	                                  <input id="email" type="text"  class="validate[required,minSize[3],maxSize[50]] medium "  value="${user.email }"/>                                   
	                                   <span class="f_help">Email 필수 입력</span>
                                  </div>
                                  
                             </div>
                             <div class="section" >
                                  <label> Password <small></small></label>   
                                  <div> 
                                  	<input id="password" type="text" name="password" class="validate[required,minSize[3],maxSize[50]] full" value="${user.password }"  />
                                  	<span class="f_help">영문+숫자 혼합</span></div>                                  
                             </div>
                         	<div class="section" >
                                  <label> Name <small></small></label>   
                                  <div> 
                                  	<input id="name" type="text" name="name" class="validate[required,minSize[3],maxSize[50]] full" value="${user.name }"  />
                                  	<span class="f_help">영문+숫자 혼합</span></div>                                  
                             </div>
                        
                              
                              <div class="section last">
                                  <div>
	                                  <a id="btnEdit" class="uibutton loading submit_form" title="Saving" rel="1" >submit</a> 
	                                  <a class="uibutton special clear_form"  >clear form</a> 
	                                  <a class="uibutton loading cancel" title="Checking" rel="0" >Cancel</a> </div>
	                             </div>
                           
                          </form:form>
		
		
		
	
		


	
		
	</div>
</div>


