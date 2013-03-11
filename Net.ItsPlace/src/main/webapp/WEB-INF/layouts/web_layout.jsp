<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>


<html>
	<head>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.1.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/mosaic/js/mosaic.1.0.1.min.js" />" ></script>
	
		<script type="text/javascript" src="<c:url value="/resources/js/mediaquery/enquire.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/mediaquery/matchMedia.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jqModal.js" />" ></script>
		
		<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/normalize.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/common.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/button.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/font/font-awesome.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/mosaic/css/mosaic.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/index.css" />" /> --%>

		<!-- bootstrap 전환 -->  
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/bootstrap.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/flat-ui.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/mosaic/css/mosaic.css" />" />

    
		<title><decorator:title default="place" /></title>
		<decorator:head />
	</head>
	<body>       
	<decorator:body/>
	</body>
</html>
  			
  		