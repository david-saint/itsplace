<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">	


	<!-- 공통 CSS, JAVACRIPT -->
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.7.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js" />"></script>
	 
	<script type="text/javascript" src="<c:url value="/resources/nodejs/socket.io.js" />"></script>

	<!-- 각 레이아웃별 CSS , JAVASCRIPT -->
	<tiles:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
	<c:forEach var="style" items="${styles}">
	<link rel="stylesheet" href="<c:url value="/resources/${style}" />" type="text/css" media="all" />
	</c:forEach>	
	<tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" ignore="true" />
	<c:forEach var="script" items="${scripts}">
		<script type="text/javascript" src="<c:url value="/resources/${script}" />"></script>	
	</c:forEach>
	
</head>
<body>
<tiles:insertAttribute name="header" />

<tiles:insertAttribute name="body" />

<tiles:insertAttribute name="footer" />
</body>
</html>
