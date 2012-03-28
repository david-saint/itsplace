<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">	

	<!-- 자바스크립트 디버그 -->
	<script type="text/javascript" src="<c:url value="/resources/blackbirdjs/1.0/blackbird.js" />"></script>
	<link rel="stylesheet" href="<c:url value="/resources/blackbirdjs/1.0/blackbird.css" />" type="text/css" media="screen" />
	
	
	<!-- 공통 CSS, JAVACRIPT -->
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.7.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js" />"></script>
	<!-- <script type="text/javascript" src="http://www.trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="http://www.trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>
	 -->
	<script type="text/javascript" src="<c:url value="/resources/common/1.0/common-1.0.0.js" />"></script>	
	<link rel="stylesheet" href="<c:url value="/resources/common/1.0/common-1.0.0.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/common/1.0/paging.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/common/1.0/awesome.css" />" type="text/css" media="screen" />
	
	 <script type="text/javascript" src="<c:url value="/resources/jquery/pnotify-1.0.2/jquery.pnotify.min.js" />"></script>
	 
	 <script type="text/javascript" src="<c:url value="/resources/openapi/kakaotalk/kakaoLink.js" />"></script>
	 
	<link rel="stylesheet" href="<c:url value="/resources/jquery/pnotify-1.0.2/jquery.pnotify.default.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />
<!--	
	<script type="text/javascript" src="<c:url value="/resources/jquery/Gritter/js/jquery.gritter.min.js" />"></script>
	<link rel="stylesheet" href="<c:url value="/resources/jquery/Gritter/css/jquery.gritter.css" />" type="text/css" media="screen" />
 -->
	<!-- 메뉴 CSS, JAVACRIPT -->
	
	
	
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
