<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
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
<div id="alertMessage" class="error"></div>
<div id="successLogin"></div>
<div class="text_success"><img src="/resources/admin/images/loadder/loader_green.gif"  alt="ziceAdmin" /><span>Please wait</span></div>

<div id="login" >
  <div class="ribbon"></div>
  <div class="inner">
  <div class="logo" ><img src="/resources/admin/images/logo/logo_login.png" alt="ziceAdmin" /></div>
  <div class="formLogin">
   <form name="formLogin"  id="formLogin" action="">

          <div class="tip">
				<input name="j_username" type="text"  id="username_id"  title="Username" value="faye12005@gmail.com"  />
          </div>
          <div class="tip">
				<input name="j_password" type="password" id="password"   title="Password" value="hoon1014"  />
          </div>

          <div class="loginButton">
            <div style="float:left; margin-left:-9px;">
				<input type="checkbox" id="on_off" name="remember" class="on_off_checkbox"  value="1"   />
				<span class="f_help">Remember me</span>
			</div>
			<div style="float:right; padding:3px 0; margin-right:-12px;">
              <div> 
                <ul class="uibutton-group">
                   <li><a class="uibutton normal" href="#" id="but_login" >Login</a></li>
				   <li><a class="uibutton normal" href="#" id="forgetpass">forgetpass</a></li>
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