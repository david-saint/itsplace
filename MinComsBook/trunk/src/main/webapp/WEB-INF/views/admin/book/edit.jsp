<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<c:set var="title" value="도서수정"/>
<html>
  <head>
    <title>${title}</title>
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		$('#bookCategoryRoot').change(function(){
	 			  $.getJSON(
	 		             "${context}/book/getBookCategorySub?decorator=exception", 
	 		             {root_id: $('#bookCategoryRoot').val()},
	 		             function(data) {
	 		                  var html = '';
	 		                  var len = data.length;
	 		                // $('#bookCategory').children().remove();
	 		                  if(len>0){
	 		                	 for(var i=0; i<len; i++){
		 		                       html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
		 		                   }
		 		                  $('#bookCategorySub').children().remove();
		 		                  $('#bookCategorySub').append(html);
		 		                  $('#bookCategorySub').selectmenu('destroy');
		 		                  $('#bookCategorySub').selectmenu();
		 		                // $('#bookCategorySub').selectmenu(0);
	 		                  }else{
	 		                	 html += '<option>중분류</option>';
	 		                	 $('#bookCategorySub').children().remove();
	 		                	 $('#bookCategorySub').append(html);
		 		                 $('#bookCategorySub').selectmenu('destroy');
		 		                 $('#bookCategorySub').selectmenu();
	 		                  }
	 		                 
	 		             });
	 		});
	 		$('#bookCategorySub').change(function(){
	 			  $.getJSON(
	 		             "${context}/book/getBookCategory?decorator=exception", 
	 		             {sub_id: $('#bookCategorySub').val()},
	 		             function(data) {
	 		                  var html = '';
	 		                  var len = data.length;
	 		                  if(len>0){
	 		                	 for(var i=0; i<len; i++){
		 		                       html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
		 		                   }
		 		                  $('#bookCategory').children().remove();
		 		                  $('#bookCategory').append(html);
		 		                  $('#bookCategory').selectmenu('destroy');
		 		                  $('#bookCategory').selectmenu();
	 		                  }else{
	 		                	 	 html += '<option>소분류</option>';
		 		                	 $('#bookCategory').children().remove();
		 		                	 $('#bookCategory').append(html);
			 		                 $('#bookCategory').selectmenu('destroy');
			 		                 $('#bookCategory').selectmenu();
		 		              }
	 		                  
	 		             });
	 		});
	 		
	 		
	 	
		    
	 	    $('#btnEdit').live('click',function() {
	 			$('form').submit();
	 		}); 
	 	});
	 	
	</script>
</head>
<body>		
<div class="widget">
	<div class="header">
		<span><span class="ico gray pencil"></span> ${title} </span>
	</div>
	<div class="content">
		<form:form  action="edit" commandName="bookInfo" method="post">
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
               		<form:select id="bookCategoryRoot" path="bookCategory.bookCategorySub.bookCategoryRoot.id" multiple="false">
						<form:options items="${categoryRootList}" itemValue="id" itemLabel="name" />
					</form:select>
               		<form:select id="bookCategorySub" path="bookCategory.bookCategorySub.id" multiple="false">
						<form:options items="${categorySubList}" itemValue="id" itemLabel="name" />
					</form:select>
				 	<form:select id="bookCategory" path="bookCategory.id" multiple="false">
						<form:options items="${categoryList}" itemValue="id" itemLabel="name" />
					</form:select>		
				 	<form:errors path="bookCategory.bookCategorySub.bookCategoryRoot.id" cssClass="error" />     	
				 	<form:errors path="bookCategory.id" cssClass="error" />     	
               	<span class="f_help"></span>
               	</div>                                  
          </div>  
          <div class="section" >
               <label> 제목 <small></small></label>   
               <div> 
               		<form:input path="title" cssClass="full"/>
               		<form:errors path="title" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
          <div class="section" >
               <label> 작가 <small></small></label>   
               <div> 
               		<form:input path="authors" cssClass="medium"/>
               		<form:errors path="authors" cssClass="error" />
               		<span class="f_help">예> 홍길동, 이명박, 박근혜</span>
               </div>                                  
          </div>
          <div class="section" >
               <label> 수량 <small></small></label>   
               <div> 
               		<form:input path="count" /><form:errors path="count" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
          <div class="section" >
               <label>출판사 <small></small></label>   
               <div> 
               		<form:input path="publisher" cssClass="medium"/>
               		<form:errors path="publisher" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
     
          <div class="section" >
               <label>출판일 <small></small></label>   
               <div> 
               		<form:input path="publishedDate" /><form:errors path="publishedDate" cssClass="error" />
               		<span class="f_help">예)1980-10-14</span>
               </div>                                  
          </div>
          <div class="section" >
               <label>섬네일이미지 <small></small></label>   
               <div> 
               		<form:input path="thumbnail" cssClass="medium"/>
               		<form:errors path="thumbnail" cssClass="error" />
               		<span class="f_help">url 입력</span>
               </div>                                  
          </div>
          <div class="section" >
               <label>삭제여부 <small></small></label>   
               <div> 
               		<form:checkbox path="isDeleted" cssClass="on_off_checkbox" /><form:errors path="isDeleted" cssClass="error " />
               		<span class="f_help"></span>
               </div>                                  
          </div>
           
           <div class="section last right">
               <div>
                <a id="btnEdit" class="uibutton loading submit_form large" title="Saving" rel="1" >저장</a>                 
                <a class="uibutton special cancel large" title="Checking" rel="0" >취소</a> 
               </div>
           </div>
        
       </form:form>
	</div>
</div>
</body>

