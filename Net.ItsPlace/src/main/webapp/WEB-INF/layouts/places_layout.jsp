<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>


<html>
	<head>
	    <script type="text/javascript" src="<c:url value="/resources/web/js/jquery-1.8.2.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/nodejs/socket.io.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tiles/placeon.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tiles/Tile.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/tiles/demo.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/mediaquery/enquire.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/mediaquery/matchMedia.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jqModal.js" />" ></script>
		<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/normalize.css" />" /> --%>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/tiles.css" />" />
		  
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/bootstrap.css" />" />
		<link rel="stylesheet" type="text/css" href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" /> 
 		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/font/font-awesome.css" />" />
 	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/flat-ui.css" />" />
		<script type="text/javascript" src="<c:url value="/resources/web/js/bootstrap.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/web/js/bootstrap-modal.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/web/js/bootstrap-modalmanager.js" />" ></script>
 		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/bootstrap-modal.css" />" />
		<script type="text/javascript" src="<c:url value="/resources/web/js/common-1.0.0.js" />" ></script>
		
		<title><decorator:title default="place" /></title>
		<decorator:head />
	</head>
	<body>       
	<decorator:body/>
	</body>
</html>
  			