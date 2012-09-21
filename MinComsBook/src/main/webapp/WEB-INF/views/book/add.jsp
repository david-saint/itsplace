<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="도서 등록"/>
<html>
  <head>
    <title>{$title}</title>
	<script type="text/javascript">
 		var google = {	 			 
 			bookSearch : function(isbn)
 		 	{
 		 		console.log("search:"+isbn);
 			   
 			    	google.s = document.createElement('script');
 			    	google.s.type ='text/javascript';
 			    	google.s.charset ='utf-8';
 			    	google.s.src = "https://www.googleapis.com/books/v1/volumes?q="+isbn+"&callback=googleBookApiHandleResponse";
 			      document.getElementsByTagName('head')[0].appendChild(google.s);
 			   
 		 	}
 		};	 	
	 	$(document).ready(function(){
	 		$('#btnGoogleBookApi').click(function(){
	 			 var url = "https://www.googleapis.com/books/v1/volumes?q=9788979144086&callback=googleBookApiHandleResponse";
	 				// $.getJSON(url);
	 			google.bookSearch($('#isbn').val());
	 		});
	 		$('#bookCategoryRoot').selectmenu(2);
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
	 			var url = "/book/add";
	 			url += "?decorator=exception";
	 			$.ajax({
                     url: url,
                     type:"POST",                                
                     data:$("form").serialize(),
                     beforeSend :function(xhr){
                    	 xhr.setRequestHeader("Accept", "application/json");
	                      var title = $('.loading').attr('title');
	   	 				  var overlay=$(this).attr('rel'); 
	   	 	 			  //c.loading("title",1);
                     },
                     success: function(response){
                       if(response.status=="SUCCESS"){
                    	   var delay =1000;
                    	   c.showSuccess(response.result,delay);
                    	   setTimeout('c.location("/")',delay);
                       }else{                    	  
                    	   for(var i =0 ; i < response.result.length ; i++){
                               var field = "#"+response.result[i].field;                               
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
		    
	 	    $('#btnAdd').live('click',function() {
	 			$('form').submit();
	 		}); 
	 	});
	 	function googleBookApiHandleResponse(response){
	 		 
	 		
	 		
	 		      for (var i = 0; i < response.items.length; i++) {
	 		        var item = response.items[i];
	 		       
	 		        $('#title').val(item.volumeInfo.title);
	 		        $('#authors').val(item.volumeInfo.authors);
	 		        $('#publisher').val(item.volumeInfo.publisher);
	 		        $('#publishedDate').val(item.volumeInfo.publishedDate);
	 		        $('#thumbnail').val(item.volumeInfo.thumbnail);
	 		       
	 		     /*   document.getElementById("title").value=item.volumeInfo.title;
	 		        // in production code, item.text should have the HTML entities escaped.
	 		        document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title
	 		        + "<br>" + item.volumeInfo.authors
	 		        + "<br>" + item.volumeInfo.publisher
	 		        + "<br>" + item.volumeInfo.publishedDate
	 		        + "<br>" + item.volumeInfo.imageLinks.thumbnail
	 		        ; */
	 		        
	 		      }
	 		 
	 	}
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	</script>
</head>
<body>	
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span> ${title} </span>
	</div>	
	<div class="content">
		<form:form  action="add" commandName="bookInfo" method="post">
           <div class="boxtitle">
	           <c:set var="errors"><form:errors path="*" /></c:set>
	           <c:if test="${not empty errors}">
	           <span class="ico color lightbulb"></span><span>Exception:</span>
	        	    ${errors }
	           </c:if>
           </div>
           <div class="section" >
               <label> ISBN <small>ISBN 코드입력</small></label>   
               <div>
               		<form:input path="isbn" cssClass="small"/><form:errors path="isbn" cssClass="error" />
               		<a id="btnGoogleBookApi" class="uibutton normal large icon add">도서정보API</a>                                  
               		<span class="f_help">기호없이 숫자만 입력하세요</span>
               </div>
          </div>  
          <div class="section" >
               <label>분류 <small></small></label>   
               <div> 
               		<form:select id="bookCategoryRoot" path="bookCategory.bookCategoryRoot.id" multiple="false">
						<form:options items="${categoryRootList}" itemValue="id" itemLabel="name" />
					</form:select>
				 	<select id="bookCategory" name="bookCategory.id">
				 		<option>하위분류</option>
				 	</select>		
				 	<form:errors path="bookCategory.bookCategoryRoot.id" cssClass="error" />     	
				 	<form:errors path="bookCategory.id" cssClass="error" />     	
               	<span class="f_help"></span>
               	</div>                                  
          </div>       
          <div class="section" >
               <label> Title <small></small></label>   
               <div> 
               		<form:input path="title"  cssClass="medium"/><form:errors path="title" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
          <div class="section" >
               <label> Authors <small></small></label>   
               <div> 
               		<form:input path="authors"  cssClass="medium"/>
               		<span class="f_help">예> 홍길동, 이명박, 박근혜</span>
               </div>                                  
          </div>
          <div class="section" >
               <label> 수량<small></small></label>   
               <div> 
               		<form:input path="count" /><form:errors path="count" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
          <div class="section" >
               <label> 섬네일<small></small></label>   
               <div> 
               		<form:input path="thumbnail"  cssClass="full"/><form:errors path="thumbnail" cssClass="error" />
               		<span class="f_help"></span>
               </div>                                  
          </div>
          <div class="section" >
               <label>출판사 <small></small></label>   
               <div> 
               		<input  type="text" name="publisher" class="medium"   />
               		<span class="f_help"></span>
               </div>                                  
          </div>
     
          <div class="section" >
               <label>출판일 <small></small></label>   
               <div> 
               		<input  type="text" name="publishedDate" class="medium"   />
               		<span class="f_help">예)1980-10-14</span>
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

