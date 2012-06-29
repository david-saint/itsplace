
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
 			$('#placeReview').submit();
 		});
 		
 		$('#placeReview').validationEngine('attach', {
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					$.ajax({
 	                     url:"/admin/place/review/edit",
 	                     type:"POST",
 	                     data:$("form").serialize(),
 	                     beforeSend :function(){
 		                      var title = $('.loading').attr('title');
 		   	 				  var overlay=$(this).attr('rel'); 
 		   	 	 			  c.loading(title,overlay);
 	                     },
 	                     success: function(response){
 	                    	if(response.status=="SUCCESS"){
 	                    		c.showSuccess(response.result,1000); 	                    	 
	 	                    }else{
	 	                    	c.showError(response.result,1000); 	  
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
	
 	function refresh(){
 		c.log("refresh");
 		datatable.fnStandingRedraw();
 	}
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 리뷰 수정   - ${place.fname} </span>
	</div>
	<div class="content">
		<form:form commandName="placeReview" method="post">
			<input type="hidden" name="rid" value="${placeReview.rid }" />
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
				<label> 리뷰제목  <small></small></label>
				<div>
					<input id="title" name="title" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeReview.title }" /> 
						
				</div>
			</div>
			<div class="section">
				<label> 내용  <small></small></label>
				<div>
					<textarea name="content">${placeReview.content }</textarea>
				</div>
			</div>
			<div class="section">
				<label> 리뷰링크주소  <small></small></label>
				<div>
					<input id="siteUrl" name="siteURL" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeReview.siteURL }" /> 
						
				</div>
			</div>
			<div class="section">
				<label> 섬네일이미지  <small></small></label>
				<div>
					<input id="filePath" name="filePath" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeReview.filePath }" /> 
						
				</div>
			</div>
			<div class="section last">
				<div>
					<a id="btnSubmit" class="uibutton loading submit_form" title="Saving" rel="1">저장</a> 					
					<a href="/admin/place/review/list?fid=${place.fid}" class="uibutton loading  cancel" title="Checking" rel="0">목록</a>
				</div>
			</div>
			                                 
          </div>
		</form:form>
	</div>



