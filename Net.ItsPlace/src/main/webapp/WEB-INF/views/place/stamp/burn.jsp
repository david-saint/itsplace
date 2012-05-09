<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
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
           <%-- 
            <div class="section" >
              
                <p>--------------------------</p>
              
                <c:forEach var="stamppedList" items="${stamppedListAll}"  >
					<ul style="border:1px solid blue;">
						<c:forEach var="stamp" items="${stamppedList}" varStatus ="status">
							<li id="${stamp.pid}" class="stamp_column ${stamp.placeStamp.stampType.eventDay}"  title=""  pid="${stamp.pid}">
									<fmt:formatDate value="${stamp.saveDate}" pattern="yyyy-MM-dd hh:mm:ss" />
									${status.index+1}
									${stamp.attribute}
									${stamp.placeStamp.theme}
									
									</li>
						</c:forEach>
					</ul>
					<div>
						<pre></pre>
					</div>	
				</c:forEach>	 
				
           </div>
           --%>
                <c:forEach var="stamppedList" items="${stamppedListAll}">
                <div style="border:1px solid blue" class="${stamppedList.placeStamp.theme}" stampid="${stamppedList.placeStamp.stampid}">
                
                	<p>${stamppedList.placeStamp.stampTitle}</p>
                	<p> 유효기간: 
	                	<fmt:formatDate value="${stamppedList.placeStamp.startDate}" pattern="yyyy-MM-dd" />
	                	~ <fmt:formatDate value="${stamppedList.placeStamp.endDate}" pattern="yyyy-MM-dd" />
                	</p>
                
					<ul style="display:block"> 
						<c:forEach var="stamp" items="${ stamppedList.stampList}" varStatus="status">
							<li class="${stamp.attribute}" pid="${stamp.pid}" saveDate="<fmt:formatDate value="${stamp.saveDate}" pattern="yyyy-MM-dd" />">
								${status.index+1}
							</li>
						</c:forEach>
					</ul>
				</div>
				</c:forEach>	
         
          
         
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


