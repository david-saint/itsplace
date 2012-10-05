<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="대출정지자 해제"/>
<html>
  <head>
    <title>{$title}</title>
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		$('#btnCancel').click(function(){
	 			parent.$.fancybox.close();
	 		});
			$('#btnSolveBook').click(function(){
	 			var url = "/admin/restriction/solve";
	 			url += "?decorator=exception";
	 			$.ajax({
                     url: url,
                     type:"POST",                                
                     data:$("form").serialize(),
                     beforeSend :function(xhr){
                    	 xhr.setRequestHeader("Accept", "application/json");
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
           <input type="hidden"  name="id" value="${bookRestriction.id }" />
         
           <div class="section" >
               <label>해제 사유 <small></small></label>   
               <div>
               		<textarea cols="50" rows="5" name="solveReason"></textarea>                              
               		<span class="f_help"></span>
               </div>
          </div>  
           <div class="section last right">
               <div>
           			<a id="btnSolveBook" class="uibutton normal">해제</a>
           			<a id="btnCancel" class="uibutton normal">취소</a>
           	   </div>
           </div>    
       	  </form>
	</div>
</div>	
</body>

