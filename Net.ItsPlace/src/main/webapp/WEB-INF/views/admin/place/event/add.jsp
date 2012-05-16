<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
 	$(document).ready(function(){
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 		$('.edit').fancybox({//autodimensions false 후 width , height 가느
				'autoDimensions':false,
				'scrolling':'auto',
				'autoScale':false,
				'height':500,

			});
 		
 		
 		
 		$('#btnSubmit').live('click',function() {
 			c.log("submit Form");
 		

 			$('#placeEvent').submit();
 		});
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		});
 		$('#placeEvent').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					$.ajax({
 	                     url:"/admin/place/event/add",
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
	function make_actions(oObj) {
 		var id = oObj.aData['fid'];
 		//c.log(oObj.aData[ oObj.iDataRow ][1] );
 		c.log(""+oObj.aData['placeStamp.sid']);
 		var editAction = '<span class="tip"><a class="edit iframe" href="/admin/place/edit?fid='+id+'" original-title="Edit"><img src="/resources/admin/images/icon/icon_edit.png"></a><span>';
 		var stampEditAction = '<span class="tip"><a class="edit" href="/admin/place/stamp/edit?fid='+id+'" original-title="stamp-edit"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>';
 		var stampAddAction = '<span class="tip"><a class=""" href="/admin/place/stamp/add?fid='+id+'" original-title="stamp-add"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>';
 		var eventAddAction = '<span class="tip"><a class="edit iframe" href="/admin/place/stamp/add?fid='+id+'" original-title="stamp-add"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>';
 		var deleteAction = '<span class="tip"><a class="delete" fid="'+id+'" original-title="Delete"><img src="/resources/admin/images/icon/icon_delete.png"></a><span>';
 		
 		return  stampAddAction +"&nbsp;&nbsp;"+ stampEditAction +"&nbsp;&nbsp;"+ editAction + "&nbsp;&nbsp;" + deleteAction ; 
 	}
 	function refresh(){
 		c.log("refresh");
 		datatable.fnStandingRedraw();
 	}
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 이벤트 관리 - ${place.fname}  </span>
	</div>
	<div class="content">
		<form:form commandName="placeEvent" method="post">
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
			<div class="boxtitle">
				이벤트 관리 
			</div>
			<div class="section">
				<label> 이벤트명  <small></small></label>
				<div>
					<input id="stampTitle" name="stampTitle" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeEvent.title}" /> 
						<input id="eid" name="eid" value="${placeEvent.eid}" type="text" /> <span class="f_help"></span>
						<input id="fid" name="fid" value="${place.fid}" type="text" /> <span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 내용  <small></small></label>
				<div>
					<textarea>${placeEvent.content}</textarea>
				</div>
			</div>
			
			<div class="section"> 
				<label> 시작일 <small></small></label>
				<div>
					<input id="startDate" type="text" name="startDate"
						class="validate[required,maxSize[50]] samll date"
						value="<fmt:formatDate value="${placeEvent.startDate }" pattern="yyyy-MM-dd"/>" /> 
						<span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 종료일  <small></small></label>
				<div>
					<input id="endDate" type="text" name="endDate"
						class="validate[required,maxSize[50]] small date"
						value="<fmt:formatDate value="${placeEvent.endDate }" pattern="yyyy-MM-dd"/>" />  
						<span class="f_help"></span>
				</div>
			</div>
			<div class="section" >
               <label> isDelete <small></small></label>   
               <div> 
               <form:radiobutton path="isDelete"  value="Y" label="Yes"/> 
               <form:radiobutton path="isDelete"  value="N" label="No"/> 
               <span class="f_help"></span>
            </div> 
			<div class="section last">
				<div>
					<a id="btnSubmit" class="uibutton loading submit_form" title="Saving" rel="1">submit</a> 
					<a class="uibutton special clear_form">clear form</a>
					<a class="uibutton loading  cancel" title="Checking" rel="0">Cancel</a>
				</div>
			</div>
			                                 
          </div>
		</form:form>
			<div class="setion">
				<table class="display staticBase" id="static">
				<thead>
					<tr>
						<th>이벤트명 </th>
						<th>EditDate</th>
						<th>StartDate</th>
						<th>EndDate</th>
						<th>isDelete</th>
						<th>Management</th>
					</tr>
				</thead>
				 <tbody>
					<c:forEach items="${placeEventList}" var="placeEvent">
						<tr>
							<td>${placeEvent.stampTitle}</td>
							<td><fmt:formatDate value="${placeEvent.editDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeEvent.startDate }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${placeEvent.endDate }" pattern="yyyy-MM-dd"/></td>
							<td>${placeEvent.isDelete}</td>
							<td>
								<span class="tip"><a class="edit" href="/admin/place/stamp/edit?fid=${placeEvent.eid}&stampid=${placeEvent.eid}" original-title="stamp-edit"><img src="/resources/admin/images/icon/color_18/notepad.png"></a><span>
							</td>
						</tr>
					</c:forEach>
				</tbody> 
				</table>
			</div>
	</div>
</div>


