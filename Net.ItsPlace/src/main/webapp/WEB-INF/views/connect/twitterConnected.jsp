<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">	
	<!-- 공통 CSS, JAVACRIPT -->
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.6.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/common/1.0/common-1.0.0.js" />"></script>	
	<link rel="stylesheet" href="<c:url value="/resources/common/1.0/common-1.0.0.css" />" type="text/css" media="screen" />
	
<script type="text/javascript">

</script>

</head>

<body>
<h3>Connected to Twitter</h3>

<form id="disconnect" method="post">
	<div class="formInfo">
		<p>승인되었습니다</p>		
	</div>

	<button type="submit">연결끊기Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<p><a href="<c:url value="/twitter" />">View your Twitter profile</a></p>


</body>
</html>
