<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#btnEdit').live('click',function() {
 			c.log("submit Edit Form");
 			$('form').submit();
 		});
 		$('.cancel').live('click',function() {
 			parent.$.fancybox.close();
 		
 			
 			//$.fancybox.close();
 		});
 		 $('#bascd').validationEngine('attach', {//서브밋 후에 밸리
 			 onValidationComplete: function(form, status){
 				   c.log("submit:"+status);
 				   if(status==true){
 				   	  var title = $('.loading').attr('title');
 					  var overlay=$(this).attr('rel'); 
 		 			  c.loading(title,overlay);
 		 	    	  setTimeout("c.unloading()",1500); 
 		 			  $('#bascd').validationEngine('detach');
 		 			  $('#bascd').submit();
 				   }
 		   		}  
	 	});

		
 		 
 		 
 		
 	});
 	
</script>

		

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 기초코드 수정 </span>
	</div>
	<!-- End header -->
	<div class="content">
		<form:form commandName="bascd" method="post">
           <div class="boxtitle">
	           <c:set var="errors"><form:errors path="*" /></c:set>
	           <c:if test="${not empty errors}">
	           <span class="ico color lightbulb"></span><span>Exception:</span>
	        	    ${errors }
	           </c:if>
           </div>
           <div class="section" >
               <label> BASECD <small></small></label>   
               <div>
                <input id="basecd" type="text" name="basecd" class="validate[required,maxSize[50]] medium "  value="${bascd.basecd }"/>                                   
                <input type="hidden" name="no"   value="${bascd.no}"/>                                   
                 <span class="f_help"></span>
               </div>
               
          </div>
          <div class="section" >
               <label> GroupCode <small></small></label>   
               <div> 
               	<form:select id="grpcd" path="grpcd" multiple="false">
					<form:options items="${grpBasCdList}" itemValue="grpcd" itemLabel="grpName" />
				</form:select>
               	<span class="f_help"></span>
               	</div>                                  
          </div>
          <div class="section" >
               <label> BaseName <small></small></label>   
               <div> 
               	<input id="basName" type="text" name="basName" class="validate[required,maxSize[20]] full" value="${bascd.basName }"  />
               	<span class="f_help"></span></div>                                  
          </div>
          <div class="section" >
               <label> Remark <small></small></label>   
               <div> 
               	<input id="remark" type="text" name="remark" class=" full" value="${bascd.remark }"  />
               	<span class="f_help"></span></div>                                  
          </div>
          <div class="section" >
               <label> isDelete <small></small></label>   
               <div> 
               <form:radiobutton path="isDelete"  value="Y" label="Yes"/> 
               <form:radiobutton path="isDelete"  value="N" label="No"/> 
               <span class="f_help"></span></div>                                  
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

