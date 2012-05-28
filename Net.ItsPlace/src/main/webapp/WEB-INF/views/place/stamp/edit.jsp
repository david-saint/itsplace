<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
 	$(document).ready(function(){
 		drawStampCanvas($('#theme').val());
 		$('#sid').change(function(){
 			drawStampCanvas($('#theme').val());//테마와 스탬프를 그린다	
 		});
 		$('#theme').change(function(){
 			drawStampCanvas($(this).val());//테마와 스탬프를 그린다	
 		});
 		
 		$( ".date" ).datepicker({ 
 			dateFormat: 'yy-mm-dd',
 			numberOfMonths: 1
 		});
 		var action= "/admin/place/stamp/edit";
 		
		
 		$('#btnSubmit').live('click',function() {
 			c.log("submit Form");
 			$('#placeStamp').submit();
 		});
 		$('#btnDelete').live('click',function() {
 			$.ajax({
                 url: "/place/stamp/delete",
                 type:"POST",
                 data:"stampid="+$(this).attr('stampid'),
                 beforeSend :function(){
   	 	 			  c.loading("delete",0);
                 },
                 success: function(response){
                 	if(response.status=="SUCCESS"){
                 		c.showSuccess(response.result,1000);
                 	}
                	
                 },
                 error: function(jqXHR, textStatus, errorThrown){
                	c.showError(textStatus+"j"+jqXHR+"e:"+errorThrown);
                 },
                 complete:function(){
                	 setTimeout("c.unloading()",500); 
                	 datatable.fnStandingRedraw();
                 }
            });//ajax */
 		});
 		$('.cancel').live('click',function() {
 			document.location.href = "/place/stamp/list";
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
 	function drawStampCanvas(theme){
 		$('.stamp').remove();
 		var sid = $('#sid').val(); // 스탬프 타입
 		var stampcount = $('#'+sid).attr('stampcount');
 		var eventday = $('#'+sid).attr('eventday');
			var tag = "<ul class=\"stamp\">";
			for(var i=1;i<=stampcount;i++){
				tag+="<li>"+i+"</li>";
			}
		tag += "</ul>";
		c.log("theme:"+theme);
		$('#'+sid).removeClass();
		$('#'+sid).addClass(theme); // 테마 선택 
		$('#'+sid).append(tag);
		
 	}
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 스탬프 수정 - ${placeStamp.stampTitle}  </span>
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
			<div class="boxtitle">
				새로운 스탬프를 추가합니다. 기존에 사용하던 스탬프 고객들은 사용중이던 스탬프를 모두 사용후 새로운 스탬프를 사용하게 됩니다.
			</div>
			<div class="section">
				<label> 스탬프명 <small></small></label>
				<div>
					<input id="stampTitle" name="stampTitle" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeStamp.stampTitle}" /> 
						<input id="fid" name="fid" value="${place.fid}" type="hidden" /> <span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 스탬프 유형  <small> </small></label>
				<div>
					<form:select id="sid" path="sid" multiple="false">
						<form:options items="${stampTypeList}" itemValue="sid"	itemLabel="title" />
					</form:select>
					<span class="f_help"></span>
				</div>
				
			</div>
			<div class="section">
				<label> 스탬프 테마  <small> </small></label>
				<div>
					<form:select id="theme" path="theme" multiple="false">
						<form:options items="${themeList}" itemValue="basecd"	itemLabel="basName" />
					</form:select>
				</div>
				<div>
                  <ul>
                 	<c:forEach items="${themeList}" var="theme">
						<li class="albumImage" theme="${theme.basecd}">
                            <div class="picHolder">
                              <span class="image_highlight"></span><!--// Highlight Images -->
                              <a href="${theme.baseval}" rel='glr'></a><!--// Hide Double Click Link Images -->
                              <img src="${theme.baseval}" title="Drag Image to delete"/><!--//  Images -->
                              <div class="picTitle">${theme.basName}</div><!--//  Images Title-->
                            </div>
                        </li>
					</c:forEach>
              	  </ul>
				</div>
				<div class="clear"></div>
				
							
			</div>
			<div class="section">
				<label> 미리보기   <small></small></label>
				<div class="stamps">
					<c:forEach items="${stampTypeList}" var="stampType" varStatus ="status">
						<div id="${stampType.sid}"   stampcount="${stampType.stampcount }" eventday="${stampType.eventday}" remark="${stampType.remark }"></div>
					</c:forEach>
				</div>
				<div class="clear"></div>
							
			</div>
			<div class="section"> 
				<label> 스탬프설명  <small></small></label>
				<div>
					<textarea name="content" col="100"></textarea>
						<span class="f_help"></span>
				</div>
			</div>
			<div class="section"> 
				<label> 시작일 <small></small></label>
				<div>
					<input id="startDate" type="text" name="startDate"
						class="samll date"
						value="<fmt:formatDate value="${placeStamp.startDate }" pattern="yyyy-MM-dd"/>" /> 
						<span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 종료일  <small></small></label>
				<div>
					<input id="endDate" type="text" name="endDate"
						class="small date"
						value="<fmt:formatDate value="${placeStamp.endDate }" pattern="yyyy-MM-dd"/>" />  
						<span class="f_help"></span>
				</div>
			</div>
			
			<div class="section last">
				<div>
					<a id="btnSubmit" class="uibutton loading submit_form" title="Saving" rel="1">submit</a> 
					<a class="uibutton special clear_form">clear form</a>
					<a class="uibutton loading  cancel" title="Checking" rel="0">Cancel</a>
				</div>
			</div>
			                                 
          
		</form:form>
			
	</div>
</div>


