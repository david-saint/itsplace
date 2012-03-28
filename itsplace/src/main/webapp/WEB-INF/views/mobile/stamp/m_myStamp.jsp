<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>

<script type="text/javascript">
$( document ).bind( "pageinit", function( event, data ){
	
	 
});	
$(document).ready(function(){
	var fid = '${stamptypeRegister.fid}';
	parent.resizeHeight(fid,$('body').height());
});

</script>

<div class="stamp_box">
	
	<c:forEach var="stamppedList" items="${stamppedListAll}"  >
		<ul>
			<c:forEach var="stamp" items="${stamppedList}" varStatus ="status">
				<li id="${stamp.pid}" class="stamp_column ${stamp.eventday}"  title="<fmt:formatDate value="${stamp.inpdate}" pattern="yyyy-MM-dd hh:mm:ss"/>"  pid="${stamp.pid}" date="">
						
						${status.index+1}
						
						</li>
			</c:forEach>
		</ul>
		<div class="remark"> 
			<pre>${stamptypeRegister.remark}</pre>
			
		</div>	
	</c:forEach>	
			
</div>		


