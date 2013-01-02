<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		$('#fullAddress').fancybox({//autodimensions false 후 width , height 가느
			'autoDimensions':false,
			'scrolling':'auto',
			'autoScale':false,
			'height':700,
			'width':1200,
			

		});
 		$("#file").change(function(){
 			var data ={fid:$('#fid').val()};
 			$.ajaxFileUpload({
  			   url: "/admin/place/upload",
  			   data: data,
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
  			      	parent.refresh();
  			   },
  			   error: function (data, status, e)
  			   {
  			    	alert("status : " + status + " error : " + e);    
  			   }
  			});
 		});
 		$('#btnEdit').live('click',function() {
 			c.log("submit Edit Form");
 			$('form').submit();
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
 	                     url: "/admin/place/edit",
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
 	                    		parent.refresh();
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

		
 		 
 		 
 		
 	});
 	function setAddress(address,lat,lng,localName_1,localName_2,localName_3,newAddress){
 		address = address.replace(/<b>/g,"");
 		address = address.replace(/<\/b>/g,"");
 		$('#fullAddress').val(address);
 		$('#lat').val(lat);
 		$('#lng').val(lng);
 		$('#sido').val(localName_1);
 		$('#gugun').val(localName_2);
 		$('#dong').val(localName_3);
 		$('#newAddress').val(newAddress);
 		
 		c.log("부모에게 넘어온 주소:"+localName_1+localName_2+localName_3+newAddress);	
 	}
</script>

		

<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> 가맹점 정보 수정  </span>
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
						value="${place.fname }" /> 
					<input id="fid" name="fid" value="${place.fid}"
						type="hidden" /> <span class="f_help">필수 입력</span>
				</div>
			</div>
			<div class="section">
				<label> 가맹점 타입    <small>  </small></label>
				<div>
					<form:select id="placeType" path="placeType" multiple="placeType">
						<form:options items="${placeTypeList}" itemValue="basecd"	itemLabel="basName" />
					</form:select>
					<span class="f_help"></span>
				</div>
			</div>
			
			<div class="section">
				<label> 가맹점 분류   <small>카테고리  </small></label>
				<div>
					<form:select id="category" path="category" multiple="category">
						<form:options items="${categoryList}" itemValue="basecd"	itemLabel="basName" />
					</form:select>
					<span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 대표사진 <small></small></label>
				<img id="fileName" style="" src="${place.imageHost}${place.fileName}"></img>
				<div>
					 <input id="file" type="file" name="file" class="fileupload"  value="${place.fileName}"/>
					 <span class="f_help"></span>
				</div>
			</div>
			<div class="section"> 
				<label> 신청자 <small></small></label>
				<div>
					<input id="name" type="text" name="name"
						class="validate[required,minSize[3],maxSize[50]] middle"
						value="${place.name }" /> <span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 가맹점 연락처  <small></small></label>
				<div>
					<input id="phone1" type="text" name="phone1"
						value="${place.phone1 }" /> <span class="f_help">유선전화</span>
				</div>
			</div>
			<div class="section">
				<label> 휴대폰  <small></small></label>
				<div>
					<input id="mobile" type="text" name="mobile"
						value="${place.mobile }" /> <span class="f_help"></span>
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
					
					<span class="f_help">지도상에 좌표 표시 가능함</span>
				</div>
			</div>
			<div class="section">
				<label> 서비스  타입    <small>  </small></label>
				<div>
					<form:select id="serviceType" path="serviceType" multiple="serviceType">
						<form:options items="${serviceTypeList}" itemValue="basecd"	itemLabel="basName" />
					</form:select>
					<span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 승인여부 <small></small></label>
				<div>
					<form:radiobutton path="isAuth"  value="Y" label="승인"/> 
               		<form:radiobutton path="isAuth"  value="N" label="미승인"/>
               	</div>	 
			</div>
			<div class="section">
				<label> 비고 <small></small></label>
				<div>
					<textarea cols="100" name="remark">${place.remark }</textarea>
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








	</div>
</div>


