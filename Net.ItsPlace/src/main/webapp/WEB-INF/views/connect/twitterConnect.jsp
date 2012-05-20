<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">	
	<!-- 공통 CSS, JAVACRIPT -->
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.6.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/common/1.0/common-1.0.0.js" />"></script>	
	<link rel="stylesheet" href="<c:url value="/resources/common/1.0/common-1.0.0.css" />" type="text/css" media="screen" />
	
<script type="text/javascript">
$(document).ready(function() {
	$('#twittwe_submit').trigger('click');
});
</script>

</head>

<body>

<h3>Connect to Twitter</h3>

<form action="<c:url value="/connect/twitter" />" method="POST">
	
	<p><button id="twittwe_submit" type="submit"><img src="<c:url value="/resources/openapi/twitter/connect-with-twitter.png" />"/></button></p>
	
</form>
</body>
</html>