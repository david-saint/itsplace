<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">	
		<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.6.2.min.js" />"></script>
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
<body style="margin:0 0 0 0;padding:0 0 0 0;">


<tiles:insertAttribute name="body" />	

 
 


</body>
</html>
