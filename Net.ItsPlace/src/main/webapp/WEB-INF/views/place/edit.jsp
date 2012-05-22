<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('.address').fancybox({//autodimensions false 후 width , height 가느
				'autoDimensions':false,
				'scrolling':'auto',
				'autoScale':false,
				'height':700,
				'width':1200,
				//'centerOnScroll':true
				//'title':'사용자 정보 수정'

		});
 		
 		$("#file").change(function(){
 			$.ajaxFileUpload({
  			   url: "/place/upload",
  			   secureuri:false,
  			   fileElementId:'file',
  			   dataType: 'json',
  			   beforeSend:function()
  			   {
  			   		$("#loading").show();
  			   },
  			   complete:function()
  			   {
  			   		$("#loading").hide();
  			   },
  			   success: function (data, status)
  			   {
  			        c.log(data.msg);
  			        c.log(data.fileName);
  			        $("#fileName").attr('src',data.fileName);    
  			   },
  			   error: function (data, status, e)
  			   {
  			    	alert("status : " + status + " error : " + e);    
  			   }
  			})
 		});
 		
 		$("#upload").live('click',function() {
 			
 		});
 		$('#btnEdit').live('click',function() {
 			c.log("submit Edit Form");
 			$('#user').submit();
 		});
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		
 			
 			//$.fancybox.close();
 		});
 		 $('#user').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					 c.log("edit:"+status);
 					//$('#user').validationEngine('detach');
 					$.ajax({
 	                     url: "/admin/user/edit",
 	                     type:"POST",
 	                     data:$("form").serialize(),
 	                     beforeSend :function(){
 		                      var title = $('.loading').attr('title');
 		   	 				  var overlay=$(this).attr('rel'); 
 		   	 	 			  c.loading(title,overlay);
 	                     },
 	                     success: function(response){
 	                       if(response.status=="SUCCESS"){
 	                    	   console.log("송고");
 	                    		parent.$.fancybox.close();
 	                    		parent.test();
 	                       }else{
 	                    	   var errorInfo="";
 	                    	   for(var i =0 ; i < response.result.length ; i++){
 	                               errorInfo = "<br>" + (i + 1) +". " + response.result[i].defaultMessage;
 	                           }
 	                    	   c.log(errorInfo);
 	                       }
 	                     },
 	                     error: function(jqXHR, textStatus, errorThrown){
 	                    	alert(textStatus+jqXHR+errorThrown); 
 	                     },
 	                     complete:function(){
 	                    	//$('#user').validationEngine('detach');
 	                    	 setTimeout("c.unloading()",1500); 
 	                    
 	                     }
 	                   });//ajax
 	                   
 		 			   					 
 				 }
 			  }
	 	});

		
 	
 		
 	});//ready
 	function setAddress(nldno,lat,lng,address){
 		c.log("부모에게 넘어온 주소:"+address);	
 	}
</script>

		

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 가맹점 수정  </span>
	</div>
	<!-- End header -->
	<div class="content">
			<!-- title box -->
		<form:form commandName="place" method="post">
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
				<label> 가맹점명 <small></small></label>
				<div>
					<input id="fname" name="fname" type="text"
						class="validate[required,minSize[3],maxSize[50]] medium "
						value="${place.fname }" /> <input name="fid" value="${place.fid}"
						type="hidden" /> <span class="f_help">가맹점명 필수 입력</span>
				</div>
			</div>
			<div class="section">
				<label> 대표사진 <small></small></label>
				<img id="fileName" style="" src="${place.imageHost}${place.fileName}"></img>
				<div>
					 <input id="file" type="file" name="file" class="fileupload" />
					 <span class="f_help">가맹점명 필수 입력</span>
				</div>
			</div>
			<div class="section"> 
				<label> 신청자 <small></small></label>
				<div>
					<input id="name" type="text" name="name"
						class="validate[required,minSize[3],maxSize[50]] full"
						value="${place.name }" /> <span class="f_help">영문+숫자 혼합</span>
				</div>
			</div>
			<div class="section">
				<label> 주소  <small></small></label>
				<div>
					<input id="nldno" type="text" name="nldno"/>
					<input id="lat" type="text" name="latitude"/>
					<input id="lng" type="text" name="longitude"/>
					<input id="address" class="address iframe" href="/address/search" type="text" name="address" onclick="search()"/>
					<span class="f_help">영문+숫자 혼합</span>
				</div>
			</div>
			<div class="section">
				<label> mobile <small></small></label>
				<div>
					<input id="mobile" type="text" name="mobile"
						class="validate[required,minSize[3],maxSize[50]] full"
						value="${place.mobile }" /> <span class="f_help">영문+숫자 혼합</span>
				</div>
			</div>
			
			


			<div class="section last">
				<div>
					<a id="btnEdit" class="uibutton loading submit_form" title="Saving" rel="1">submit</a>
					<a class="uibutton special clear_form">clear form</a>
					<a class="uibutton loading  cancel" title="Checking" rel="0">Cancel</a>
				</div>
			</div>

		</form:form>

		 <div class="section">
				<label> upload <small></small></label>
				<div>
					<%--  <form id="fileupload" action="/place/upload" enctype="multipart/form-data" method="post"> --%> 
					
					 <a id="upload" class="uibutton special">upload</a>
<%-- 					  </form> --%>
					<span class="f_help">영문+숫자 혼합</span>
				</div>
		</div>






	</div>
</div>


