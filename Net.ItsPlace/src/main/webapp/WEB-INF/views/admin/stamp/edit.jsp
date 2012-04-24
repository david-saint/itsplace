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
		<form:form  action="add" commandName="stampType" method="post">
	   		<div class="boxtitle">
		       <c:set var="errors"><form:errors path="*" /></c:set>
		       <c:if test="${not empty errors}">
		        <span class="ico color lightbulb"></span><span>Exception:</span>
		        ${errors }
		       </c:if>
		    </div>
            <div class="section" >
                <label> 스탬프타입명  <small></small></label>   
                <div>
                 <input id="title" type="text" name="title" class="validate[required,minSize[3],maxSize[20]] medium "  value="${stampType.title }"/>                                   
                  <span class="f_help">스탬프 타입 이름 </span>
                </div>
           </div>
           <div class="section" >
                <label> 스탬프갯수 <small></small></label>   
                <div> 
                	<input id="stampcount" type="text" name="stampcount" class="validate[required,maxSize[20]] full" value="${stampType.stampcount }"  />
                	<span class="f_help">stampcount </span>
                </div>                                  
           </div>
	       <div class="section" >
	       		<label> 스탬프 주기일 <small></small></label>   
                <div> 
                	<input id="eventday" type="text" name="eventday" class="validate[required,maxSize[20]] full" value="${stampType.eventday }"  />
                	<span class="f_help">eventday</span>
                </div>                                  
	       </div>
	       <div class="section" >
	       		<label> 비고 <small></small></label>   
                <div> 
                	<input id="remark" type="text" name="remark" class="validate[required,maxSize[20]] full" value="${stampType.remark }"  />
                	<span class="f_help">remark</span>
                </div>                                  
	       </div>
	       <div class="section" >
	       		<label> 표시순서 <small></small></label>   
                <div> 
                	<input id="dispseq" type="text" name="dispseq" class="validate[required,maxSize[20]] full" value="${stampType.dispseq }"  />
                	<span class="f_help">dispseq</span>
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


