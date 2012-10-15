<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#btnAdd').live('click',function() {
 			c.log("submit Form");
 			$('#stampType').submit();
 		});
 		
 		
 		   $('#stampType').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
	 			   // alert("The form status is: " +status+", it will never submit");
	 			   c.log("submit:"+status);
	 			   if(status==true){
	 				   var title = $('.loading').attr('title');
	 				  var overlay=$(this).attr('rel'); 
	 	 			  c.loading(title,overlay);
	 	 	    	  setTimeout("c.unloading()",1500); 
	 	 			$('#stampType').validationEngine('detach');
	 	 			$('#stampType').submit();
	 	 			
	 			   }
	 	  }  
	 	});  
 	});
 	
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 스탬프 타입 등록  </span>
	</div>
	<div class="content">
		<form:form  action="signup" commandName="user" method="post">
	   		<div class="boxtitle">
		       <c:set var="errors"><form:errors path="*" /></c:set>
		       <c:if test="${not empty errors}">
		        <span class="ico color lightbulb"></span><span>Exception:</span>
		        ${errors }
		       </c:if>
		    </div>
            <div class="section" >
                <label>이름  <small></small></label>   
                <div>
                 	<form:input path="name" cssClass="small"/><form:errors path="name" cssClass="error" />
               		<span class="f_help">기호없이 숫자만 입력하세요</span>
                </div>
           </div>
           <div class="section" >
                <label> Email <small></small></label>   
                <div> 
                	<form:input path="email" cssClass="small"/><form:errors path="email" cssClass="error" />
               		<span class="f_help">기호없이 숫자만 입력하세요</span>
                </div>                                  
           </div>
	       <div class="section" >
	       		<label> 비밀번호 <small></small></label>   
                <div> 
                	<form:input path="name" cssClass="small"/><form:errors path="name" cssClass="error" />
               		<span class="f_help">기호없이 숫자만 입력하세요</span>
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


