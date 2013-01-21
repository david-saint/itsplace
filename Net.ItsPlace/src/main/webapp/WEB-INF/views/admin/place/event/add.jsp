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
 		/* $('#btnSubmit').live('click',function() {
 			c.log("submit Form");
 			$('#placeEvent').submit();
 		}); */
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		});
 		$('#btnSubmit').live('click',function() {
 			var url = "${context}/partner/event/add";
 			
 			$.ajax({
                 url: url,
                 type:"POST",                                
                 data:$("form").serialize(),                   
                 success: function(response){
                   if(response.status=="SUCCESS"){
                	   var delay =1000;
                	   c.showSuccess(response.result,delay);
                	   setTimeout('c.location("${context}")',delay);
                   }else{                 
                	   c.log(response.result);
                	   for(var i =0 ; i < response.result.length ; i++){
                           var field = "#"+response.result[i].field;  
                           console.log(field);
                           if($(field).length <= 0 ){
                        	  field =  $('select[name='+response.result[i].field+']').next()//label;
                        	   c.log(field);
                           }
                          
                           $(field).attr('original-title',response.result[i].message);
                           $(field).tipsy({trigger: 'manual', gravity: 'w'});
                           $(field).tipsy("show");
                           $(field).bind('click',function(){
                        	   $(this).tipsy("hide");
                           });
                       }
                	   
                   }
                 },
                 complete:function(){
                	 //setTimeout("c.unloading()",1500); 
                 }
               });//ajax
 		});
 	});//ready
	
 	function refresh(){
 		c.log("refresh");
 		datatable.fnStandingRedraw();
 	}
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 이벤트 생성  - ${place.fname}  </span>
	</div>
	<div class="content">
		<form:form commandName="placeEvent" method="post">
			
			<input type="hidden" value="${place.fid}" id="place.fid" name="place.fid" />
			
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
					<input id="title" name="title" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeEvent.title}" /> 
						
				</div>
			</div>
			<div class="section">
				<label> 내용  <small></small></label>
				<div>
					<textarea name="content">${placeEvent.content}</textarea>
				</div>
			</div>
			
			<div class="section"> 
				<label> 시작일 <small></small></label>
				<div>
					<input id="startDate" type="text" name="startDate"
						class=" samll date"
						value="<fmt:formatDate value="${placeEvent.startDate }" pattern="yyyy-MM-dd"/>" /> 
						<span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 종료일  <small></small></label>
				<div>
					<input id="endDate" type="text" name="endDate"
						class=" small date"
						value="<fmt:formatDate value="${placeEvent.endDate }" pattern="yyyy-MM-dd"/>" />  
						<span class="f_help"></span>
				</div>
			</div>
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<div class="section" >
               <label> 승인여부  <small></small></label>   
               <div> 
               <form:radiobutton path="isAuth"  value="1" label="Yes"/> 
               <form:radiobutton path="isAuth"  value="0" label="No"/> 
               <span class="f_help"></span>
            </div> 
			</sec:authorize>
			<div class="section last">
				<div>
					<a id="btnSubmit" class="uibutton loading submit_form" title="Saving" rel="1">submit</a> 
					<a class="uibutton special clear_form">clear form</a>
					<a class="uibutton loading  cancel" title="Checking" rel="0">Cancel</a>
				</div>
			</div>
			                                 
          </div>
		</form:form>
	</div>
</div>


