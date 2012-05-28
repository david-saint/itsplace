<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
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
 		$('#btnAdd').live('click',function() {
 			c.log("submit  Form");
 			$('#place').submit();
 		});
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		
 			
 			//$.fancybox.close();
 		});
 		 $('#place').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					 c.log("edit:"+status);
 					//$('#user').validationEngine('detach');
 					$.ajax({
 	                     url: "/admin/place/add",
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
 	
</script>

		

<div class="widget">
	<div class="header">
		<span><span class="ico gray add"></span> 가맹점 생성 </span>
	</div>
	<div class="content">
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
						value="${place.fname }" /> 
					<input name="fid" value="${place.fid}"
						type="hidden" /> <span class="f_help">가맹점명 필수 입력</span>
				</div>
			</div>
			<div class="section">
				<label> 가맹점 타입    <small>  </small></label>
				<div>
					<form:select id="placeType" path="placeType" multiple="placeType">
						<form:options items="${placeTypeList}" itemValue="basecd"	itemLabel="basName" />
					</form:select>
					<span class="f_help">sss</span>
				</div>
			</div>
			<div class="section">
				<label> 가맹점 분류   <small>카테고리  </small></label>
				<div>
					<form:select id="category" path="category" multiple="category">
						<form:options items="${categoryList}" itemValue="basecd"	itemLabel="basName" />
					</form:select>
					<span class="f_help">sss</span>
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
						class="validate[required,minSize[2],maxSize[50]] full"
						value="${place.name }" /> <span class="f_help">영문+숫자 혼합</span>
				</div>
			</div>
			<div class="section">
				<label> 연락처  <small></small></label>
				<div>
					<input id="mobile" type="text" name="mobile"
						value="${place.mobile }" /> <span class="f_help">숫자</span>
				</div>
			</div>
			<div class="section">
				<label> 주소  <small></small></label>
				<div>
					<input id="fullAddress" class="address full iframe" readonly href="/address/search" type="text" name="fullAddress" value="${place.fullAddress }" />
					
					<input id="lat" type="hidden" name="latitude" value="${place.latitude}" />
					<input id="lng" type="hidden" name="longitude" value="${place.longitude}" />
					<input id="sido" type="hidden" name="sido" value="${place.sido}" />
					<input id="gugun" type="hidden" name="gugun" value="${place.gugun}" />
					<input id="dong" type="hidden" name="dong" value="${place.dong}" />
					<input id="newAddress" type="hidden" name="newAddress" value="${place.newAddress}" />
					
					<span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 서비스  타입    <small>  </small></label>
				<div>
					<form:select id="serviceType" path="serviceType" multiple="serviceType">
						<form:options items="${serviceTypeList}" itemValue="basecd"	itemLabel="basName" />
					</form:select>
					<span class="f_help">sss</span>
				</div>
			</div>
			<div class="section last">
				<div>
					<a id="btnAdd" class="uibutton loading submit_form" title="Saving" rel="1">submit</a>
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


