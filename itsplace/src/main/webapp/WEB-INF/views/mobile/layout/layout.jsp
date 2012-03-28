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
	<!-- 모바일 레이아웃-->
	<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" /> 
	<meta name="viewport" content="initial-scale=1.0,user-scalable=no">
	-->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	
	<!-- 공통 CSS, JAVACRIPT -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
	
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0rc1/jquery.mobile-1.0rc1.min.css">
	
	<script type="text/javascript" src="<c:url value="/resources/blackbirdjs/1.0/blackbird.js" />"></script>
	<link rel="stylesheet" href="<c:url value="/resources/blackbirdjs/1.0/blackbird.css" />" type="text/css" media="screen" />
	
	<!-- <script type="text/javascript" src="<c:url value="/resources/openapi/kakaotalk/kakaoLink.js" />"></script> -->

	<script type="text/javascript" src="<c:url value="/resources/common/1.0/common-1.0.0.js" />"></script>			
	
	
	<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=59b0ffd18daca3f14cfc189f695c2df0e209b389" charset="utf-8" /></script>
	
	<link rel="stylesheet" href="<c:url value="/resources/place/1.0/m_place.css" />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value="/resources/stamp/1.0/m_stamp.css" />" type="text/css" media="all">
	
	<link rel="stylesheet" href="<c:url value="/resources/common/1.0/mobileCommon-1.0.0.css" />" type="text/css" media="all" />	
	
	<script type="text/javascript" src="http://code.jquery.com/mobile/1.0rc1/jquery.mobile-1.0rc1.min.js"></script>	
	
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



<div data-role="page">
<tiles:insertAttribute name="header" />			
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />	
</div>
 
 


</body>
</html>
