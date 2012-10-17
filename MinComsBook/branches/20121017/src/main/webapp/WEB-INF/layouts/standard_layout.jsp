<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
        <link rel="shortcut icon" type="image/ico" href="<c:url value="/resources/images/favicon2.ico" />" /> 
        
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datatables.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zice.style.css" />" />
		<link rel="stylesheet" type="text/css"href="<c:url value="/resources/css/icon.css" />"  />

		<!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />
		 -->
	    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/ui-custom.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/timepicker.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/colorpicker/css/colorpicker.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/elfinder/css/elfinder.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/datatables/dataTables.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/validationEngine/validationEngine.jquery.css" />" />     
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/jscrollpane/jscrollpane.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/fancybox/jquery.fancybox.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/tipsy/tipsy.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/editor/jquery.cleditor.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/chosen/chosen.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/confirm/jquery.confirm.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/sourcerer/sourcerer.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/fullcalendar/fullcalendar.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/Jcrop/jquery.Jcrop.css" />" />   
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<c:url value="/resources/components/flot/excanvas.min.js" />"></script><![endif]-->
		          
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/components/ui/jquery.ui.min.js" />" ></script>		     
		<script type="text/javascript" src="<c:url value="/resources/components/effect/jquery-jrumble.js" />" ></script>
		<script type="text/javascript" src="<c:url value="/resources/components/ui/jquery.autotab.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/ui/timepicker.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/colorpicker/js/colorpicker.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/checkboxes/iphone.check.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/elfinder/js/elfinder.full.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/datatables/dataTables.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/datatables/ColVis.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/scrolltop/scrolltopcontrol.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/fancybox/jquery.fancybox.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/jscrollpane/mousewheel.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/jscrollpane/mwheelIntent.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/jscrollpane/jscrollpane.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/spinner/ui.spinner.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/tipsy/jquery.tipsy.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/editor/jquery.cleditor.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/chosen/chosen.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/confirm/jquery.confirm.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/validationEngine/jquery.validationEngine.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/validationEngine/jquery.validationEngine-en.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/vticker/jquery.vticker-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/sourcerer/sourcerer.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/fullcalendar/fullcalendar.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/flot/flot.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/flot/flot.pie.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/flot/flot.resize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/flot/graphtable.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/uploadify/swfobject.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/uploadify/uploadify.js"/>"></script>        
        <script type="text/javascript" src="<c:url value="/resources/components/checkboxes/customInput.jquery.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/effect/jquery-jrumble.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/filestyle/jquery.filestyle.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/placeholder/jquery.placeholder.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/components/Jcrop/jquery.Jcrop.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/imgTransform/jquery.transform.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/webcam/webcam.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/components/rating_star/rating_star.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/components/dualListBox/dualListBox.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/components/smartWizard/jquery.smartWizard.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/components/maskedinput/jquery.maskedinput.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/components/highlightText/highlightText.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/components/elastic/jquery.elastic.source.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.cookie.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/zice.custom.js"/>"></script>    
 		<script  src="<c:url value="/resources/js/common-1.0.0.js" />" type="text/javascript"></script>
 		
 		<%-- <script  src="<c:url value="/resources/common/common-1.0.0.js" />" type="text/javascript"></script>
 		<script  src="<c:url value="/resources/js/upload/ajaxfileupload.js" />" type="text/javascript"></script>
		 --%>
 
		
		
		<title>도서관리시스템 - <decorator:title default="제목없음" /></title>
		<decorator:head />
		<script type="text/javascript">
	 		$(document).ready(function(){
	 			$('.limenu > a').each(function(){
	 				if($(this).attr('href') == window.location.pathname){
	 					$(this).parent().addClass('select');
	 				}
	 			});
	 			$('.subListmenu > a').each(function(){
	 				if($(this).attr('href') == window.location.pathname){
	 					$(this).parent().parent().parent().addClass('select');
	 				}
	 			});
	 		});
	 	</script>
	</head>
	<body class="dashborad">     
		<div id="alertMessage" class="error"></div>
		<!-- Header style="background: url(http://work.mincoms.com:62560/Images/Layout/header_img_120618.jpg) no-repeat right;"-->
        <div id="header" >
                <div id="account_info"> 
					<div class="setting" title="Profile Setting">
						 <%-- <b> <sec:authentication property="Principal.User.userRname" /></b> --%>
						 <b class="red"></b><img src="<c:url value="/resources/images/gear.png" />" class="gear"  alt="Profile Setting" >
				    </div>
					<%-- <div class="logout" title="Disconnect"><b >Logout</b> <img src="<c:url value="/resources/images/connect.png" />" name="connect" class="disconnect" alt="disconnect" ></div> --%>  
                </div>
        </div><!-- End Header -->
			<div id="shadowhead"></div>
                   
              <div id="left_menu">
                    <ul id="main_menu" class="main_menu">
                      <li class="limenu"><a href="http://work.mincoms.com:62560/"><span class="ico gray shadow home" ></span><b>MinWork</b></a></li>
                      <sec:authorize ifAnyGranted="BOOKMANAGER, ADMIN">
                      <li class="limenu" >
                      	<a><span class="ico gray shadow window"></span><b>카테고리관리</b></a>
                      	<ul>
                      		<li class="subListmenu">
                      			<a href="<c:url value="/admin/category/root" />">대분류</a>
                      		</li>
                      		<li class="subListmenu">
                      			<a href="<c:url value="/admin/category/sub" />">중분류</a>
                      		</li>
                      		<li class="subListmenu">
                      			<a href="<c:url value="/admin/category" />">소분류</a>
                      		</li>
                      	</ul>
                      </li>
                    
                      <li class="limenu" >
                      	<a href="<c:url value="/admin/book/list" />" ><span class="ico gray shadow   encrypt"></span><b>도서관리</b></a>                      	
                      </li>
                      
                        </sec:authorize>
                      <li class="limenu" ><a href="<c:url value="/book/search" />"><span class="ico gray shadow  spreadsheet" ></span><b>도서목록</b></a></li>
                      <li class="limenu" ><a href="<c:url value="/book/rentals" />"><span class="ico gray  file" ></span><b>나의도서</b></a></li>
                      <sec:authorize ifAnyGranted="ADMIN">
	                        <li class="limenu" >
	                      		<a href="<c:url value="/admin/restriction/list" />" ><span class="ico gray shadow   encrypt"></span><b>대출정지</b></a>                      	
	                        </li>
                      		<li class="limenu" >
                      			<a href="<c:url value="/admin/statics/rentals" />"><span class="ico gray  dimensions" ></span><b>대출현황</b></a>
                      		</li>
                      		<li class="limenu" >
                      			<a href="<c:url value="/admin/exception/list" />"><span class="ico gray  dimensions" ></span><b>Exception</b></a>
                        	</li>
                      </sec:authorize>
                      
                    </ul>
               </div>
               
			<div id="content">
                <div class="inner">
					<div class="topcolumn" >
						<div class=""></div>
                            <ul  id="shortcut" style="height:7px">
                             <%--    <li> <a href="#" title="Back To home"> <img  src="<c:url value="/resources/images/icon/shortcut/home.png"/>" alt="home"/><strong>Home</strong> </a> </li>
                                <li> <a href="#" title="Website Graph"> <img src="<c:url value="/resources/images/icon/shortcut/graph.png"/>" alt="graph"/><strong>Graph</strong> </a> </li>
                                <li> <a href="#" title="Setting" > <img src="<c:url value="/resources/images/icon/shortcut/setting.png"/>" alt="setting" /><strong>Setting</strong></a> </li> 
                                <li> <a href="#" title="Messages"> <img src="<c:url value="/resources/images/icon/shortcut/mail.png"/>" alt="messages" /><strong>Message</strong></a><div class="notification" >10</div></li> --%>
                            </ul>
							<div class="clear"></div>
					</div>   
                    <div class="clear"></div> 
                      
					<decorator:body/>
			

					<div class="clear"></div>

                    <div id="footer"> &copy; Copyright 2012 <span class="tip"><a  href="/" title="MinCommunication" >MinCommunication </a> </span> </div>

	</div> <!--// End inner -->
</div> <!--// End content -->   
	</body>
</html>
