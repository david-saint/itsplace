<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="도서정보수정"/>
<html>
  <head>
    <title>${title}</title>
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		$('#bookCategoryRoot').change(function(){
	 			  $.getJSON(
	 		             "/book/getBookCategory?decorator=exception", 
	 		             {rootid: $('#bookCategoryRoot').val()},
	 		             function(data) {
	 		                  var html = '';
	 		                  var len = data.length;
	 		                  for(var i=0; i<len; i++){
	 		                       html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
	 		                   }
	 		                  $('#bookCategory').children().remove();
	 		                  $('#bookCategory').append(html);
	 		                  $('#bookCategory').selectmenu('destroy');
	 		                  $('#bookCategory').selectmenu();
	 		                  console.log(html);
	 		             }
	 		          );
	 		});
	 		
	 		$('#btnAddAjax').live('click',function() {
	 			$.ajax({
                     url: "/book/edit",
                     type:"POST",                                
                     data:$("form").serialize(),
                     beforeSend :function(xhr){
                    	 xhr.setRequestHeader("Accept", "application/json");
	                      var title = $('.loading').attr('title');
	   	 				  var overlay=$(this).attr('rel'); 
	   	 	 			  //c.loading(title,overlay);
                     },
                     success: function(response){
                       if(response.status=="SUCCESS"){
                    	   console.log("송고");
                    		parent.$.fancybox.close();
                    		parent.test();
                       }else{
                    	   var errorInfo="";
                    	   console.log(response.status);
                    	   console.log(response.result);
                    	   for(var i =0 ; i < response.result.length ; i++){
                               errorInfo += "<br>" + (i + 1) +". " + response.result[i].defaultMessage;
                           }
                    	   console.log(errorInfo);
                       }
                     },
                     error: function(xhr, status, error){
                    	alert("status:"+xhr.status+"status:"+status+"error:"+error + xhr.responseText); 
                     },
                     complete:function(){
                    	//$('#user').validationEngine('detach');
                    	 //setTimeout("c.unloading()",1500); 
                    
                     }
                   });//ajax
	 		});
		    
	 	    $('#btnAdd').live('click',function() {
	 			$('form').submit();
	 		}); 
	 	});
	 	
	</script>
</head>
<body>		
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> ${title} </span>
	</div>
	<div class="content">
		<form:form  action="edit" commandName="book" method="post">
           <div class="boxtitle">
	          
           </div>
           <div class="section" >
               <label> ISBN <small>ISBN 코드입력</small></label>   
               <div>
               		<form:input path="isbn" /><form:errors path="isbn" cssClass="error" />                                   
               		<span class="f_help">기호없이 숫자만 입력하세요</span>
               </div>
          </div>         
          <div class="section" >
               <label>분류 <small></small></label>   
               <div> 
               		<form:select id="bookCategoryRoot" path="bookCategory.bookCategoryRoot.id" multiple="false">
						<form:options items="${categoryRootList}" itemValue="id" itemLabel="name" />
					</form:select>
				 	<form:select id="bookCategory" path="bookCategory.id" multiple="false">
						<form:options items="${categoryList}" itemValue="id" itemLabel="name" />
					</form:select>		
				 	<form:errors path="bookCategory.bookCategoryRoot.id" cssClass="error" />     	
				 	<form:errors path="bookCategory.id" cssClass="error" />     	
               	<span class="f_help"></span>
               	</div>                                  
          </div>  
          <div class="section" >
               <label> Title <small></small></label>   
               <div> 
               		<form:input path="title" /><form:errors path="title" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
          <div class="section" >
               <label> Authors <small></small></label>   
               <div> 
               		<form:input path="authors" />
               		<span class="f_help">예> 홍길동, 이명박, 박근혜</span>
               </div>                                  
          </div>
          <div class="section" >
               <label> 수향 <small></small></label>   
               <div> 
               		<form:input path="count" /><form:errors path="count" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
          <div class="section" >
               <label>출판사 <small></small></label>   
               <div> 
               		<input  type="text" name="publisher" class="full"   />
               		<span class="f_help"></span>
               </div>                                  
          </div>
     
          <div class="section" >
               <label>출판일 <small></small></label>   
               <div> 
               		<input  type="text" name="publishedDate" class="full"   />
               		<span class="f_help">예)1980-10-14</span>
               </div>                                  
          </div>
          <div class="section" >
               <label>섬네일이미지 <small></small></label>   
               <div> 
               		<input  type="text" name="thumbnail" class="full"   />
               		<span class="f_help">url 입력</span>
               </div>                                  
          </div>
           
           <div class="section last right">
               <div>
                <a id="btnAdd" class="uibutton loading submit_form" title="Saving" rel="1" >submit</a> 
                <a id="btnAddAjax" class="uibutton loading submit_form" title="Saving" rel="1" >submit-Ajax</a> 
                <a class="uibutton special clear_form"  >clear form</a> 
                <a class="uibutton loading cancel" title="Checking" rel="0" >Cancel</a> 
               </div>
           </div>
        
       </form:form>
	</div>
</div>
</body>

