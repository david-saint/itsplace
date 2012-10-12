<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<html>
<head>
	<title>로그인</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/zice.style.css" />" />   
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/icon.css" />" />   
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/components/tipsy/tipsy.css" />" />   
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/components/effect/jquery-jrumble.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/components/tipsy/jquery.tipsy.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/components/checkboxes/iphone.check.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/components/ui/jquery.ui.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/login.js" />" ></script>
		
<script type="text/javascript">
		 	$(document).ready(function(){
		 		if("${back}"=="back"){
		 			//document.location.href =
		 			//window.history.back(-2);
		 		}
		 	}); 
		 	
</script>
<style type="text/css">
		html {
			background-image: none;
		}
		label.iPhoneCheckLabelOn span {
			padding-left:0px
		}
		#versionBar {
			background-color:#212121;
			position:fixed;
			width:100%;
			height:35px;
			bottom:0;
			left:0;
			text-align:center;
			line-height:35px;
			z-index:11;
		}
		.copyright{
			text-align:center; font-size:10px; color:#CCC;
		}
		.copyright a{
			color:#A31F1A; text-decoration:none
		}    
</style>     
</head>
<body>
<div id="alertMessage" class="error"></div>
<div id="successLogin"></div>
<div class="text_success"><img src="<c:url value="${context}/resources/images/loadder/loader_green.gif" />"  alt="ziceAdmin" /><span>Please wait</span></div>

<div id="login" >
  <!-- <div class="ribbon"></div> -->
  <div class="inner">
  <div class="logo" ><img src="<c:url value="${context}/resources/images/logo/ci.png" />" alt="ziceAdmin" /></div>
  <div class="formLogin">
   <form name="formLogin"  id="formLogin" action="">

          <div class="tip">
				<input name="j_username" type="text"  id="username_id"  title="Username" value="dhkim"  />
          </div>
          <div class="tip">
				<input name="j_password" type="password" id="password"   title="Password" value="1111"  />
          </div>

          <div class="loginButton">
            <div style="float:left; margin-left:-9px;">
				<input type="checkbox" id="on_off" name="_spring_security_remember_me" class="on_off_checkbox"  value="1"   />
				<span class="f_help">기억하기</span>
			</div>
			<div style="float:right; padding:3px 0; margin-right:-12px;">
              <div> 
                <ul class="uibutton-group">
                   <li><a class="uibutton normal large" href="#" id="but_login" >로그인</a></li>
				   <!-- <li><a class="uibutton normal" href="#" id="forgetpass">비밀번호찾기</a></li> -->
               </ul>
              </div>
			  
            </div>
			<div class="clear"></div>
		  </div>

    </form>
  </div>
</div>
  <div class="clear"></div>
  <div class="shadow"></div>
</div>

<!--Login div-->
<div class="clear"></div>
<div id="versionBar" >
  <div class="copyright" > &copy; Copyright 2012  All Rights Reserved <span class="tip"><a  href="#" title="Zice Admin" >Your company</a> </span> </div>
  <!-- // copyright-->
</div>
</body>
</html>