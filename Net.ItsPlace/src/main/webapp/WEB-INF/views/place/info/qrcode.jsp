<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
 	$(document).ready(function(){
 		
 	});//ready
 	
</script>
<div class="widget">
	<div class="header">
		<span><span class="ico gray home"></span>    </span>
	</div>
	<div class="content">
		${place.fname }
		<img src="${place.qrcode }" />
	</div>
</div>


