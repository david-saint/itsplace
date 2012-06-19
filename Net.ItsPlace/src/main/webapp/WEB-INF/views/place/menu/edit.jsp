
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
 		$('#btnSubmit').live('click',function() {
 			c.log("submit Form");
 			$('#placeMenu').submit();
 		});
 		$('.cancel').live('click',function() {
 			c.log("cancel2");
 			parent.$.fancybox.close();
 		});
 		$("#file").change(function(){
 			var data ={mnid:$('#mnid').val()};
 			$.ajaxFileUpload({
  			   url: "/place/menuUpload", 
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
  			        c.log("mnid:"+data.mnid);
  			        $('#mnid').val(data.mnid);
  			        $("#filePath").attr('src',data.fileName); 
  			      	parent.refresh();
  			   },
  			   error: function (data, status, e)
  			   {
  			    	alert("status : " + status + " error : " + e);    
  			   }
  			})
 		});
 		$('#placeMenu').validationEngine('attach', {//서브밋 후에 밸리
 			  onValidationComplete: function(form, status){
 				 if(status==true){
 					$.ajax({
 	                     url:"/place/menu/edit",
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
 	                    	    parent.datatableRedraw(response.result,true);
	 	                    }else{
	 	                   		parent.datatableRedraw(response.result,false);
	 	                    }
 	                     },
 	                     error: function(jqXHR, textStatus, errorThrown){
 	                    	alert(jqXHR.status+":"+errorThrown); 
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
		<span><span class="ico gray home"></span> 이벤트 수정  </span>
	</div>
	<div class="content">
		<form:form commandName="placeMenu" method="post">
			<input type="text" id="mnid" name="mnid" value="${placeMenu.mnid }" />
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
				메뉴 관리 
			</div>
			<div class="section">
				<label> 메뉴사진 <small></small></label>
				<img id="filePath" style="" src="${placeMenu.filePath}"></img>
				<div>
					 <input id="file" type="file" name="file" class="fileupload" />
					 <span class="f_help"></span>
				</div>
			</div>
			<div class="section">
				<label> 메뉴명  <small></small></label>
				<div>
					<input id="title" name="title" type="text"
						class="validate[required,maxSize[50]] medium "
						value="${placeMenu.title}" /> 
					<input id="mnid" name="mnid" type="hidden"
						class=""
						value="${placeMenu.mnid}" /> 	
						
				</div>
			</div>
			<div class="section">
				<label> 메뉴설명  <small></small></label>
				<div>
					<textarea name="content">${placeMenu.content}</textarea>
				</div>
			</div>
		
			<div class="section">
				<label> 가격   <small></small></label>
				<div>
					<input id="price" name="price" type="text"
						class="validate[required,maxSize[10]] medium "
						value="${placeMenu.price}" /> 
				</div>
			</div>
			<div class="section" >
               <label> 할인여부  <small></small></label>   
               <div> 
               <form:radiobutton path="isSale"  value="Y" label="Yes"/> 
               <form:radiobutton path="isSale"  value="N" label="No"/> 
               <span class="f_help"></span>
               </div>
            </div> 
            <div class="section">
				<label> 할인가격   <small></small></label>
				<div>
					<input id="salePrice" name="salePrice" type="text"
						class="validate[required,maxSize[10]] medium "
						value="${placeMenu.salePrice}" /> 
				</div>
			</div>
			  <div class="section">
				<label> sort   <small></small></label>
				<div>
					<input id="sort" name="sort" type="text"
						class="validate[required,maxSize[10]] medium "
						value="${placeMenu.sort}" /> 
				</div>
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
	</div>
</div>


