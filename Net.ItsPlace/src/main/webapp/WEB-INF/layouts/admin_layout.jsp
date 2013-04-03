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
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font/font-awesome.css" />" />
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
        <script type="text/javascript" src="<c:url value="/resources/admin/components/jscrollpane/mousewheel.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/jscrollpane/mwheelIntent.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/admin/components/jscrollpane/jscrollpane.min.js"/>"></script>
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
 		<script  src="<c:url value="/resources/js/upload/ajaxfileupload.js" />" type="text/javascript"></script>
		
		
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
                 <%--    <img src="<sec:authentication property="Principal.User.profileImageUrl" />" alt="Online" class="avatar"/>
					<div class="setting" title="Profile Setting"><b>반갑습니다,</b> <b class="red"><sec:authentication property="Principal.User.Name" />님</b><img src="<c:url value="/resources/admin/images/gear.png" />" class="gear"  alt="Profile Setting" ></div>
					<div class="logout" title="Disconnect"><b >Logout</b> <img src="<c:url value="/resources/admin/images/connect.png" />" name="connect" class="disconnect" alt="disconnect" ></div> --%> 
                </div>
        </div><!-- End Header -->
			<div id="shadowhead"></div>
                 <div id="left_menu">
                    <ul id="main_menu" class="main_menu">
                      <li class="limenu0 select"><a href="<c:url value="/admin" />"><span class="ico gray shadow home" ></span><b>admin Dashboard</b></a></li>
                      <sec:authorize ifAnyGranted="ROLE_ADMIN">
	                      <li class="limenu1" ><a href="<c:url value="/admin/base/list" />" ><span class="ico gray shadow window"></span><b>기초코드</b></a>
	                      </li>
	                      <li class="limenu" ><a href="<c:url value="/admin/user/list" />"><span class="ico gray  dimensions" ></span><b>사용자관리</b></a>
	                      </li>
	                      <li class="limenu" ><a href="<c:url value="/admin/place/list" />"><span class="ico gray shadow  spreadsheet"></span><b>가맹점관리 </b> </a></li>
	                      <li class="limenu" ><a href="<c:url value="/admin/stamp/list" />"><span class="ico gray shadow pictures_folder"></span><b>스탬프관리  </b></a></li>
	                      <li class="limenu" ><a href="<c:url value="/exception/list" />"><span class="ico gray shadow pictures_folder"></span><b>예외관리  </b></a></li>
	                      </li>
                      </sec:authorize>	
					  <!-- 파트너 -->
					   <sec:authorize ifAnyGranted="ROLE_FRANCHISER">
			                <li class="limenu1" ><a href="<c:url value="/partner/stamp/list" />" ><span class="ico gray shadow window"></span><b>스탬프관리 </b></a>
			                </li>
			                <li class="limenu" ><a href="<c:url value="/partner/user/list" />"><span class="ico gray  dimensions" ></span><b>직원관리</b></a>
			                </li>
			                <li class="limenu" ><a href="<c:url value="/partner/edit" />"><span class="ico gray shadow  spreadsheet"></span><b>가맹점관리 </b> </a></li>
			                <li class="limenu" ><a href="<c:url value="/partner/auth" />"><span class="ico gray shadow stats_lines"></span><b>인증코드 관리 </b> </a>
			             
			                </li>
			                <li class="limenu" ><a href="<c:url value="/partner/stamp/customers" />"><span class="ico gray shadow  file"></span><b>스탬프 적립 및 소진 </b></a></li>
			                <li class="limenu " ><a href="<c:url value="/partner/getQrCode" />"><span class="ico gray shadow calendar"></span><b>QR코드 출력 </b></a></li>
			                <li class="limenu" ><a href="<c:url value="/partner/event/list" />"><span class="ico gray  shadow paragraph_align_left"></span><b>이벤트관리</b></a></li>
			                <li class="limenu" ><a href="<c:url value="/partner/menu/list" />"><span class="ico gray shadow abacus"></span><b>메뉴관리  </b></a></li>
		               	</sec:authorize> 
                        
                    </ul>
               </div>
               
			<div id="content">
                <div class="inner">
					<div class="topcolumn" >
					 <sec:authorize ifAnyGranted="ROLE_FRANCHISER">
						<div  id="selectPlace" style="border:0px solid red;position:absolute;padding-top:15px">
							<sec:authentication property="Principal.placeListSelect" htmlEscape="false"/>
						</div>
					</sec:authorize>
						<div class=""></div>
                            <ul  id="shortcut" style="height:7px">
                             <%-- 	<li> <a href="#" title="Back To home"> <img  src="<c:url value="/resources/admin/images/icon/shortcut/home.png"/>" alt="home"/><strong>Home</strong> </a> </li>
                                <li> <a href="#" title="Website Graph"> <img src="<c:url value="/resources/admin/images/icon/shortcut/graph.png"/>" alt="graph"/><strong>Graph</strong> </a> </li>
                                <li> <a href="#" title="Setting" > <img src="<c:url value="/resources/admin/images/icon/shortcut/setting.png"/>" alt="setting" /><strong>Setting</strong></a> </li> 
                                <li> <a href="#" title="Messages"> <img src="<c:url value="/resources/admin/images/icon/shortcut/mail.png"/>" alt="messages" /><strong>Message</strong></a><div class="notification" >10</div></li> --%>
                            </ul>
							<div class="clear"></div>
					</div>   
                    <div class="clear"></div> 
                      
					<decorator:body/>
			

					<div class="clear"></div>

                    <div id="footer"> &copy; Copyright 2012 <span class="tip"><a  href="/" title="MinCommunication" >place </a> </span> </div>

				</div> <!--// End inner -->
			</div> <!--// End content -->   
	</body>
</html>
