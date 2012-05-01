<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta content="text/html;charset=utf-8" http-equiv="content-type" />
		<meta name="description" content="cmsmasters responsive html5 website template" />
		<meta name="keywords" content="html5, css3, template, responsive, adaptive" />
		<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
		<!--[if lt IE 9]>
          <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!-- Link shortcut icon-->
        <link rel="shortcut icon" type="image/ico" href="<c:url value="/resources/admin/images/favicon2.ico" />" /> 
        
        <link href="<c:url value="/resources/admin/css/zice.style.css" />" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/admin/css/icon.css" />" rel="stylesheet" type="text/css" />

		<!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />
		 -->
	    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/css/ui-custom.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/css/timepicker.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/colorpicker/css/colorpicker.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/elfinder/css/elfinder.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/datatables/dataTables.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/validationEngine/validationEngine.jquery.css" />" />     
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/jscrollpane/jscrollpane.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/fancybox/jquery.fancybox.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/tipsy/tipsy.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/editor/jquery.cleditor.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/chosen/chosen.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/confirm/jquery.confirm.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/sourcerer/sourcerer.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/fullcalendar/fullcalendar.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admin/components/Jcrop/jquery.Jcrop.css" />" />   
		        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<c:url value="/resources/admin/components/flot/excanvas.min.js" />"></script><![endif]-->
		        
		        
		          
		<script  src="<c:url value="/resources/admin/js/jquery.min.js" />" type="text/javascript"></script>
		
		<script  src="<c:url value="/resources/admin/components/effect/jquery-jrumble.js" />" type="text/javascript"></script>
		<script  src="<c:url value="/resources/admin/components/ui/jquery.ui.min.js" />" type="text/javascript"></script>		     
		<script type="text/javascript" src="<c:url value="/resources/admin/components/ui/jquery.autotab.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/ui/timepicker.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/colorpicker/js/colorpicker.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/checkboxes/iphone.check.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/elfinder/js/elfinder.full.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/datatables/dataTables.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/datatables/ColVis.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/scrolltop/scrolltopcontrol.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/fancybox/jquery.fancybox.js"/>"></script>
        <%-- <script type="text/javascript" src="<c:url value="/resources/admin/components/jscrollpane/mousewheel.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/jscrollpane/mwheelIntent.js"/>"></script>
         --%><script type="text/javascript" src="<c:url value="/resources/admin/components/jscrollpane/jscrollpane.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/spinner/ui.spinner.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/tipsy/jquery.tipsy.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/editor/jquery.cleditor.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/chosen/chosen.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/confirm/jquery.confirm.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/validationEngine/jquery.validationEngine.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/validationEngine/jquery.validationEngine-en.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/vticker/jquery.vticker-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/sourcerer/sourcerer.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/fullcalendar/fullcalendar.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/flot/flot.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/flot/flot.pie.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/flot/flot.resize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/flot/graphtable.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/uploadify/swfobject.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/uploadify/uploadify.js"/>"></script>        
        <script type="text/javascript" src="<c:url value="/resources/admin/components/checkboxes/customInput.jquery.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/effect/jquery-jrumble.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/filestyle/jquery.filestyle.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/placeholder/jquery.placeholder.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/admin/components/Jcrop/jquery.Jcrop.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/imgTransform/jquery.transform.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/webcam/webcam.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/admin/components/rating_star/rating_star.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/admin/components/dualListBox/dualListBox.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/admin/components/smartWizard/jquery.smartWizard.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/admin/components/maskedinput/jquery.maskedinput.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/highlightText/highlightText.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/admin/components/elastic/jquery.elastic.source.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/js/jquery.cookie.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/js/zice.custom.js"/>"></script>    
 
 		<script  src="<c:url value="/resources/common/common-1.0.0.js" />" type="text/javascript"></script>
		
 
		
		<!-- 각 레이아웃별 CSS , JAVASCRIPT -->
		<tiles:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
		<c:forEach var="style" items="${styles}">
		<link rel="stylesheet" href="<c:url value="/resources/${style}" />" type="text/css" media="all" />
		</c:forEach>	
		<tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" ignore="true" />
		<c:forEach var="script" items="${scripts}">
			<script type="text/javascript" src="<c:url value="/resources/${script}" />"></script>	
		</c:forEach>
		<title>ItsPlace Administrator</title>
	</head>
	<body class="dashborad">        
        

		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />

	</body>
</html>
