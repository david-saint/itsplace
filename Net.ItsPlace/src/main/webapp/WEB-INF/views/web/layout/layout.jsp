<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
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
		
		<!-- 자바스크립트 디버그 
		<script type="text/javascript" src="<c:url value="/resources/blackbirdjs/1.0/blackbird.js" />"></script>
		<link rel="stylesheet" href="<c:url value="/resources/blackbirdjs/1.0/blackbird.css" />" type="text/css" media="screen" />
		-->
		
		<!-- 공통 CSS, JAVACRIPT 
		<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.7.1.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js" />"></script>
		<!-- <script type="text/javascript" src="http://www.trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
		<script type="text/javascript" src="http://www.trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>
		
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

		
		<!-- 각 레이아웃별 CSS , JAVASCRIPT -->

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
