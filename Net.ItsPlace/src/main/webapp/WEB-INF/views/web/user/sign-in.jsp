<%@page import="java.util.Locale"%>
<%@ page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<c:set var="title" value="메인페이지 " />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/js/signin/supersized.css" />" />
    <script type="text/javascript" src="<c:url value="/resources/web/js/signin/supersized.3.2.7.min.js" />" ></script>
    <script type="text/javascript" src="<c:url value="/resources/web/js/signin/supersized-init.js" />" ></script>
	<style>
	ul{
		margin:0;
	}
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span8">dddddd
			</div>
			<div class="span4">
			
				<div class="login-form" style="filter:alpha(opacity=85);opacity: 0.85;-moz-opacity:0.2;">
		            <form method="post" action="signin/authenticate">
		            <div class="control-group">
		              <input type="email" name="j_username" class="login-field" value="" placeholder="Enter your name" id="login-name">
		              <label class="login-field-icon fui-man-16" for="login-name"></label>
		            </div>
		
		            <div class="control-group">
		              <input type="password" name="j_password" class="login-field" value="" placeholder="Password" id="login-pass">
		              <label class="login-field-icon fui-lock-16" for="login-pass"></label>
		            </div>
		
		            <button class="btn btn-primary btn-large btn-block">Login</button>
		            </form>
		            
		            <form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
						<input type="hidden" name="scope"
							value="publish_stream,user_photos,offline_access,email" /> <input
							type="hidden" id="on_off" name="_spring_security_remember_me"
							value="1" />
						<fieldset>
							<button type="submit" class="btn btn-large facebook">
								<i class="icon-facebook"></i><span>페이스북 아이디로 로그인</span>
							</button>
						</fieldset>
					</form>
					<form id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST">
						<button type="submit" class="btn btn-large facebook">
								<i class="icon-facebook"></i><span>트위터 아이디로 로그인</span>
							</button>
					</form>
							${error}    
		            <a id="forgot-pass" href="/passwordreset" class="login-link" href="#">Lost your password?</a>
		            <a id="forgot-pass" href="/passwordreset" class="login-link" href="#">Email로 가입하기 </a>
		            
		            
		            <!-- signup -->
		            <form:form   commandName="user" method="post" action="/sign-in">
			            <div class="control-group">
			             <!--  <input type="text" name="name" class="login-field" value="" placeholder="Enter your name" id="signup-name"> -->
			              <form:input path="name" cssClass="login-field" id="signup-name"/>
			              <form:errors path="name" cssClass="error" />		
			              <label class="login-field-icon fui-man-16" for="login-name"></label>
			            </div>
						<div class="control-group">
			              <!-- <input type="email" name="email" class="login-field" value="" placeholder="Enter your email" id="signup-email"> -->
			              <form:input path="email" cssClass="login-field" id="signup-email"/>
			              <form:errors path="email" cssClass="error" />
			              <label class="login-field-icon fui-man-16" for="login-name"></label>
			            </div>
			            <div class="control-group">
			              <!-- <input type="password" name="password" class="login-field" value="" placeholder="Password" id="signup--pass"> -->
			              <form:input path="password" cssClass="login-field" id="signup-password"/>
			              <form:errors path="password" cssClass="error" />
			              <label class="login-field-icon fui-lock-16" for="signup-password"></label>
			            </div>
			
			            <button class="btn btn-primary btn-large btn-block">signup</button>
		            </form:form>
	          	</div>
			</div>
		</div>
		<div class="row">
			
		</div>
		
	</div>
</body>
</html>
