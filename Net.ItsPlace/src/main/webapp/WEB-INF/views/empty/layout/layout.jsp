<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/MyFontsWebfontsKit.css" /> ">		
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" type="text/javascript"></script>
	<script  src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/jquery-ui.min.js" type="text/javascript"></script>
	<script  src="http://cloud.github.com/downloads/thinkpixellab/tilesjs/tiles.js" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/resources/admin/components/fancybox/jquery.fancybox.js"/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/fancybox/jquery.fancybox.css" />" />
	 
	<!--tiles별 레이아웃별 CSS , JAVASCRIPT -->
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
<tiles:insertAttribute name="body" />
</body>
