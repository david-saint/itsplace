<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="title" value="대출정지이력"/>
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
           	
           </div>
          
          <table class="display">
          	<thead>
				<tr>
					<th width="100">정지일자</th>
					<th width="100">해제일자</th>
					<th width="100">이름</th>
				</tr>	
			<tbody>
				<c:forEach items="${bookRestrictions}" var="bookRestriction">
				<tr>
					<td><fmt:formatDate value="${bookRestriction.regDate}" pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${bookRestriction.solveDate}" pattern="yyyy-MM-dd" /></td>
					<td>${bookRestriction.userInfo.userRname }</td>
				</tr>
				</c:forEach>
			</tbody>	
          </table>
       	
       	 
       	
	</div>
</div>	
</body>

