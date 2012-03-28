<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<!-- Panel -->
<div id="toppanel">
	<div id="panel">
		<div class="content clearfix">
			<div class="left">
				<h1>Its Place Main</h1>
				<h2>가입을 하시면 스마트폰에</h2>		
				<p class="grey">You can put anything you want in this sliding panel: videos, audio, images, forms... The only limit is your imagination!</p>
				<h2>Download</h2>
				<p class="grey">To download this script go back to <a href="http://web-kreation.com/index.php/tutorials/nice-clean-sliding-login-panel-built-with-jquery" title="Download">article &raquo;</a></p>
			</div>
			<div class="left">
				<!-- Login Form -->
				<form action="<c:url value="/signin/authenticate" />" method="post"   class="clearfix">
					<h1>Member Login</h1>
					<label for="j_username">Email:</label>
					<input id="j_username" name="j_username" type="text" value="faye12005@gmail.com" size="23" />	
					<label  for="j_password">Password:</label>
					<input id="j_password" name="j_password" type="password" />
					<input type="hidden" id="redirect_after_login" name="redirect_after_login" value="">
	            	<label><input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox"  /> &nbsp;Remember me</label>
        			<div class="clear"></div>
					<input type="submit" name="submit" value="Login" class="bt_login" />
					<a class="lost-pwd" href="#">비밀번호를 잊어버리셨나요?</a>
				</form>
			</div>
			<div class="left right">			
				<!-- Register Form -->
				<form action="#" method="post">
					<h1>가입하세요! Sign Up!</h1>				
					<label  for="signup">Email:</label>
					<input type="text" name="signup" id="signup" value="" size="23" />
					<label  for="email">Password:</label>
					<input  type="text" name="email" id="email" size="23" />
					<label  for="email">Name:</label>
					<input  type="text" name="email" id="email" size="23" />
					<label  for="email">Mobile:</label>
					<input  type="text" name="email" id="email" size="23" />
					
					<input type="submit" name="submit" value="Register" class="bt_register" />
				</form>
			</div>
		</div>
</div> <!-- /login -->	

	<!-- The tab on top -->	
	<div class="tab">
		<ul class="login">
			<li class="left">&nbsp;</li>
			
			<li id="toggle">
				<sec:authorize ifNotGranted="ROLE_USER">
					<a id="open" class="open" href="#">로그인 | 가입하기</a>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
			        		<ul>
			        			<li style="padding-top:5px;"><img src="${USERSESSION.profileImageUrl}" width="30" height="30" /></i></li>
			        			<li style="padding-top:10px;">${USERSESSION.name}</li>
			        			<li style="padding-top:10px;"><a href="<c:url value="/logout" />">LogOut</a></li>
			        		</ul>        		
						
						
			    </sec:authorize>					
				<a id="close" style="display: none;" class="close" href="#">Close Panel</a>			
			</li>
			<li class="right">&nbsp;</li>
		</ul> 
	</div> <!-- / top -->
	
</div> <!--panel -->


 <script type="text/javascript">
	 $(function() {
		 
		 $('#redirect_after_login').val(location.href);
		 
	 });
 
 			
</script>