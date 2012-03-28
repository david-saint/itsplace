<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>



<style type="text/css">

</style>

<script type="text/javascript">
$( document ).bind( "pageinit", function( event, data ){
	
	 $('#footer_myStamp').addClass("ui-btn-active");
});	
$(document).ready(function(){
	/*$('iframe').each(function(i) {
		$(this).height();
		logger("높이"+$(this).height());
		
	});
	*/
	//$('iframe').height(100);
});
function resizeHeight(fid,height){
	logger(fid+" :: "+height);
	$('#'+fid).height(height+5);
}
</script>

<div data-role="content" id="content" >
	<ul>
	<c:forEach var="stamp" items="${myStampList}" >				  					
  		<li class="stampList">
  			<a data-transition="fade" href="<c:url value="/place/placeView/${stamp.fid}" /> ">
  				<img src="<c:url value="/resources/images/" />${stamp.filename}" />
  				<span>${stamp.fname}</span>  				
  			</a>
  			  			
  			<div>
	  			<iframe id="${stamp.fid}" frameborder="0" framespacing="0" width="100%"  name="frameDS" src="<c:url value="/stamp/mobile/?mobile=${stamp.mobile}&stamptype=${stamp.stamptype}&fid=${stamp.fid}&urlType=myStamp" />">
	  			</iframe>
  			</div>			
  		</li>
  	</c:forEach>
  	</ul>	

</div>