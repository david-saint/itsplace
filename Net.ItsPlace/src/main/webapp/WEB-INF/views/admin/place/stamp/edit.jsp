<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		var action= "/admin/place/stamp/add";
 		$('#btnEdit').live('click',function() {
 			c.log("submit Form");
 			<c:if test="${not empty plascStamp}">
 				action = "/admin/place/stamp/edit";
 			</c:if>
 			

 			$('#placeStamp').submit();
 		});
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		});
 		$('#placeStamp').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					$.ajax({
 	                     url:action,
 	                     type:"POST",
 	                     data:$("form").serialize(),
 	                     beforeSend :function(){
 		                      var title = $('.loading').attr('title');
 		   	 				  var overlay=$(this).attr('rel'); 
 		   	 	 			  c.loading(title,overlay);
 	                     },
 	                     success: function(response){
 	                       if(response.status=="SUCCESS"){
 	                    		parent.$.fancybox.close();
 	                    		parent.test();
 	                       }else{
 	                    	   var errorInfo="";
 	                    	   for(var i =0 ; i < response.result.length ; i++){
 	                               errorInfo = "<br>" + (i + 1) +". " + response.result[i].defaultMessage;
 	                           }
 	                    	   c.log(errorInfo);
 	                    	   c.showError(errorInfo,1000);
 	                       }
 	                     },
 	                     error: function(jqXHR, textStatus, errorThrown){
 	                    	alert(textStatus+jqXHR+errorThrown); 
 	                     },
 	                     complete:function(){
 	                    	 setTimeout("c.unloading()",1500); 
 	                    
 	                     }
 	                  });//ajax
 				 }
 			  }
	 	});//validation
 	});//ready
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 스탬프 등록 - ${place.fname}  </span>
	</div>
	<div class="content">
		<form:form commandName="placeStamp" method="post">
			<div class="boxtitle">
				<c:set var="errors">
					<form:errors path="*" />
				</c:set>
				<c:if test="${not empty errors}">
					<span class="ico color lightbulb"></span>
					<span>Exception:</span>
	              ${errors }
	             </c:if>

			</div>
			<div class="section">
				<label> 스탬프명 <small></small></label>
				<div>
					<input id="stampTitle" name="stampTitle" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeStamp.stampTitle}" /> 
						<input id="fid" name="fid" value="${place.fid}" type="text" /> <span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 스탬프명 <small></small></label>
				<div>
					<form:select id="sid" path="sid" multiple="false">
						<form:options items="${stampTypeList}" itemValue="sid"	itemLabel="title" />
					</form:select>
				</div>
			</div>
			<div class="section"> 
				<label> 시작일 <small></small></label>
				<div>
					<input id="startDate" type="text" name="startDate"
						class="validate[required,maxSize[50]] full"
						value="${placeStamp.startDate }" /> <span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 종료일  <small></small></label>
				<div>
					<input id="endDate" type="text" name="endDate"
						class="validate[required,maxSize[50]] full"
						value="${placeStamp.endDate }" /> <span class="f_help"></span>
				</div>
			</div>
			<div class="section last">
				<div>
					<a id="btnEdit" class="uibutton loading submit_form" title="Saving" rel="1">submit</a> 
					<a class="uibutton loading  cancel" title="Checking" rel="0">Cancel</a>
				</div>
			</div>
		</form:form>
			<div class="setion">
				<table class="display staticBase" id="static">
				<thead>
					<tr>
						<th>StampTitle</th>
						<th>EditDate</th>
						<th>StartDate</th>
						<th>EndDate</th>
						<th>isDelete</th>
					</tr>
				</thead>
				 <tbody>
					<c:forEach items="${placeStampList}" var="placeStamp">
						<tr>
							<td>${placeStamp.stampTitle}</td>
							<td>${placeStamp.editDate}</td>
							<td>${placeStamp.startDate}</td>
							<td>${placeStamp.endDate}</td>
							<td>${placeStamp.isDelete}</td>
						</tr>
					</c:forEach>
				</tbody> 
				</table>
			</div>
	</div>
</div>


