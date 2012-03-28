<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>


<div  data-role="header"> 
	<h1 id="title"> 스탬프 등록</h1> 
</div>
<div data-role="content">
 	<h1 >${fname}</h1>
 	<h1 id="event"> </h1>
	<div data-role="fieldcontain">
		<form action="<c:url value="/stamp/stampByUser/register" />" method="post">			
			<label for="password">비밀번호</label>		
			<input id="password" name="stampPassword" value=""  type="text"/>	
			
			<input name="stamptype" value="${stamptype}" type="hidden" />	
			<input name="fid" name="fid" value="${fid}" type="hidden" />
						
			<input id="eventday" name="eventday" value="${eventday}" type="checkbox"/>
			
			<label id="even" for="eventday" >스탬프사용</label>		
			
			<input type="submit" value="등록"/>
			<a id="test"  data-rel="back" data-transition="fade" data-role="button">취소</a>
		</form> 
	</div>
</div>


<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function(){
	
	 var eventday = $('#eventday').val();
	 log.info("eventday:"+eventday);
	 if(eventday!="none"){
		//이벤트와 등록을 동시에 가능함
		 $('#eventday').val(eventday);
		 $('#even').css('display',"block");
		 $('#eventday').css('display',"block");
		 
		 $('#event').text(eventday +" 회")
	 }else{
		 $('#eventday').val("none");
		 $('#eventday').css('display',"none");
		 $('#even').css('display','none');
		 
		 $('#event').css('display',"none");
	 }
	 
});

</script>
