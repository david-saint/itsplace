<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE>
<!--[if lt IE 7]><html dir="ltr" lang="ko-KR" class="ie6"><![endif]-->
<!--[if IE 7]><html dir="ltr" lang="ko-KR" class="ie7"><![endif]-->
<!--[if IE 8]><html dir="ltr" lang="ko-KR" class="ie8"><![endif]-->
<!--[if gt IE 8]><!--><html dir="ltr" lang="ko-KR"><!--<![endif]-->
	<head>
		<meta content="text/html;charset=utf-8" http-equiv="content-type" />
		<meta name="description" content="cmsmasters responsive html5 website template" />
		<meta name="keywords" content="html5, css3, template, responsive, adaptive" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="shortcut icon" href="<c:url value="/resources/oakland/images/favicon.ico" />" type="image/x-icon" />
		<link rel="stylesheet" href="<c:url value="/resources/oakland/css/style.css"  />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/oakland/css/styles/fonts.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Cookie:regular"  type="text/css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Cambo:regular,italic,bold,bolditalic" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/resources/oakland/css/styles/jquery.prettyPhoto.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/fancybox/jquery.fancybox.css" />" />
		<link rel="stylesheet" href="<c:url value="/resources/common/common.css" />" type="text/css" media="screen" />
		<!--[if lt IE 9]>
			<link rel="stylesheet" href="<c:url value="/resources/oakland/css/styles/ie.css" />" type="text/css" />
			<link rel="stylesheet" href="<c:url value="/resources/oakland/css/styles/ie_css3.css" />" type="text/css" media="screen" />
		<![endif]-->
		
		<script src="<c:url value="/resources/oakland/js/modernizr.custom.all.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/oakland/js/css3MediaQueries.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/oakland/js/jquery-1.7.1.min.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/oakland/js/jquery.easing.js" />" type="text/javascript"></script>		
		<script src="<c:url value="/resources/oakland/js/jquery.prettyPhoto.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/oakland/js/jquery.script.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/oakland/js/jquery.validationEngine.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/oakland/js/jquery.validationEngine-lang.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/oakland/js/jquery.cmsmsResponsiveSlider-1.0.min.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/jquery.url.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/jquery.inputhints.js" />" type="text/javascript"></script>
		<script type="text/javascript" src="<c:url value="/resources/admin/components/fancybox/jquery.fancybox.js"/>"></script>
		
		<!-- 각 레이아웃별 CSS , JAVASCRIPT -->
		<tiles:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
		<c:forEach var="style" items="${styles}">
		<link rel="stylesheet" href="<c:url value="/resources/${style}" />" type="text/css" media="all" />
		</c:forEach>	
		<tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" ignore="true" />
		<c:forEach var="script" items="${scripts}">
			<script type="text/javascript" src="<c:url value="/resources/${script}" />"></script>	
		</c:forEach>
<!-- 		다음 지도 API -->
<!-- 3cc715fbd2c405578092bdae6c2a3a6867790d9f -->
		<title>ItsPlace</title>
	</head>
	<body>
	<div class="colored_block"></div>
	<!-- Start Page -->
	<section id="page">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</section>
	<!-- Finish Page -->
	</body>
</html>
