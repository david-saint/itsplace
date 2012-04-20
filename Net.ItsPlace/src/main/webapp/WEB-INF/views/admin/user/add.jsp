<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#btnAdd').live('click',function() {
 			c.log("submit Form");
 			$('#user').submit();
 		});
 		   $('#user').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
	 			   // alert("The form status is: " +status+", it will never submit");
	 			   c.log("submit:"+status);
	 			   if(status==true){
	 				   var title = $('.loading').attr('title');
	 				  var overlay=$(this).attr('rel'); 
	 	 			  c.loading(title,overlay);
	 	 	    	  setTimeout("c.unloading()",1500); 
	 	 			$('#user').validationEngine('detach');
	 	 			$('#user').submit();
	 	 			
	 			   }
	 	  }  
	 	});  

 		
 		 
 		
 	});
 	
</script>

		

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 사용지 등록  </span>
	</div>
	<!-- End header -->
	<div class="content">
					<!-- title box -->
					<form:form  action="add" commandName="user" method="post">
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
	                                  <input id="email" type="text" name="email" class="validate[required,minSize[3],maxSize[20]] medium "  value="${user.email }"/>                                   
	                                   <span class="f_help">Email 필수 입력</span>
                                  </div>
                                  
                             </div>
                             <div class="section" >
                                  <label> Password <small></small></label>   
                                  <div> 
                                  	<input id="password" type="text" name="password" class="validate[required,minSize[3],maxSize[20]] full" value="${user.password }"  />
                                  	<span class="f_help">영문+숫자 혼합</span></div>                                  
                             </div>
                         	<div class="section" >
                                  <label> Name <small></small></label>   
                                  <div> 
                                  	<input id="name" type="text" name="name" class="validate[required,minSize[3],maxSize[20]] full" value="${user.name }"  />
                                  	<span class="f_help">영문+숫자 혼합</span></div>                                  
                             </div>
                        
                              
                              <div class="section last">
                                  <div>
	                                  <a id="btnAdd" class="uibutton loading submit_form" title="Saving" rel="1" >submit</a> 
	                                  <a class="uibutton special clear_form"  >clear form</a> 
	                                  <a class="uibutton loading cancel" title="Checking" rel="0" >Cancel</a> </div>
	                             </div>
                           
                          </form:form>
		
		
	
		


	
		
	</div>
</div>


