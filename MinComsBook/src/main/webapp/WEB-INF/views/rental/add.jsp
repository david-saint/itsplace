<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="도서 대출"/>
<html>
  <head>
    <title>{$title}</title>
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		$('#btnCancel').click(function(){
	 			parent.$.fancybox.close();
	 		});
			$('#btnRental').click(function(){
				
	 			var url = "/book/rental";
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
                    	   parent.datatable.fnStandingRedraw();
                    	   setTimeout('parent.$.fancybox.close();',delay);
                       }else{
                    	   c.showError(response.result);
                    	   
                       }
                     },
                     
                     complete:function(){
                    	 //setTimeout("c.unloading()",1500); 
                     }
                   });//ajax
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
           <div class="boxtitle">
	           <c:set var="errors"><form:errors path="*" /></c:set>
	           <c:if test="${not empty errors}">
	           <span class="ico color lightbulb"></span><span>Exception:</span>
	        	    ${errors }
	           </c:if>
           </div>
           <form>
           <input type="hidden" name="isbn" value="${rental.bookInfo.isbn }" />
           <div class="section" >
           		<label> <img src="${rental.bookInfo.thumbnail }" width="130" height="170" align=""/> <small></small></label>   
           		<span>${rental.bookInfo.title }<span>
           </div>
           <div class="section" >
               <label> 대여일수 <small></small></label>   
               <div>
               		<select id="day" name="day">
               			<option value="1">1일</option>	
               			<option value="2">2일</option>	
               			<option value="3">3일</option>	
               			<option value="4">4일</option>	
               			<option value="5">5일</option>	
               			<option value="6">6일</option>	
               			<option value="7">7일</option>	
               		</select>
               		                              
               		<span class="f_help"></span>
               </div>
          </div>  
           <div class="section last right">
               <div>
           			<a id="btnRental" class="uibutton normal   ">도서대출</a>
           			<a id="btnCancel" class="uibutton normal   ">취소</a>
           	   </div>
           </div>    
       	  </form>
	</div>
</div>	
</body>

