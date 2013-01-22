
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
 		
 		$('.cancel').live('click',function() {
 			parent.$.fancybox.close();
 		});
 		$('#btnSubmit').live('click',function() {
 			var url = "${context}/partner/menu/edit";
 			
 			$.ajax({
                 url: url,
                 type:"POST",                                
                 data:$("form").serialize(),                   
                 success: function(response){
                   if(response.status=="SUCCESS"){
                	   var delay =1000;
                	   c.showSuccess(response.result,delay);
                	   parent.datatable.fnStandingRedraw();
                	   setTimeout('parent.$.fancybox.close()',delay);
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
 		$("#file").change(function(){
 			var data ={id:$('#mnid').val()};
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
               <form:radiobutton path="isSale"  value="1" label="Yes"/> 
               <form:radiobutton path="isSale"  value="0" label="No"/> 
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
					<a class="uibutton loading  cancel" title="Checking" rel="0">Cancel</a>
				</div>
			</div>
          </div>
		</form:form>
	</div>
</div>


