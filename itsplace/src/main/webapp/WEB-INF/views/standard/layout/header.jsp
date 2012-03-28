<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<div id="top-bar" class="clearfix">
	<div id="top-inner" class="clearfix">
		<div class="logoBox">
			<h1>
				<a href="<c:url value="/" />">its Place</a>
			</h1>
		</div>
		<div class="menuBox">
			<form id="searchForm" action="<c:url value="/map/search" />"
				method="GET">
				<input value="" id="search" placeholder="검색" name="q" type="text">
			</form>
			<ul class="nav">
				<li><a href="<c:url value="/stamp/myStamp" />">my Stamp</a></li>
				<li><a href="<c:url value="/map/search?q=" />">Map</a></li>
				<li><a href="<c:url value="/partner/newFranchiser" />">가맹점신청</a>
				</li>
			</ul>
		</div>
		<div class="loginBox">
			<sec:authorize ifNotGranted="ROLE_USER">
				<a id="join-link" href="<c:url value="/user/newAccount" />">가입
					하셨나요? </a>
				<a id="login-link" onclick="common_login()">Log In »</a>
				<div id="dropdownLogin">
					<form action="<c:url value="/signin/authenticate" />" method="post">
						<fieldse t class="textbox">
							<label class="username">
								<p>email</p> <input id="j_username" name="j_username"
								type="text" value="faye12005@gmail.com" />
							</label> <label class="password"> <span>Password</span> <input
								id="j_password" name="j_password" type="password" /> <input
								type="hidden" id="redirect_after_login"
								name="redirect_after_login" value="">
							</label>
						</fieldset>
						<fieldset>
							<label class="remember"> <input
								id="_spring_security_remember_me"
								name="_spring_security_remember_me" type="checkbox" /> <span>Remember
									me</span>
							</label>
							<button type="submit" class="small blue awesome">Login</button>
						</fieldset>


						<p>
							<a class="forgot" href="/account/resend_password">Forgot
								password?</a><br> <a class="mobile" href="/account/complete">Already
								using Twitter via SMS?</a>
						</p>
					</form>
				</div>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
				<a id="login-link" href="#"> <i id="top_profileImage"><img
						src="${USERSESSION.profileImageUrl}" /></i> <em>${USERSESSION.name}<!--<sec:authentication property="name" />-->
				</em> <strong><a href="<c:url value="/logout" />">LogOut</a></strong>
				</a>
			</sec:authorize>
		</div>
	</div>
</div>

<script type="text/javascript">
	 $(function() {
		 
		 $('#redirect_after_login').val(location.href);
		 
	 });
 
 			
</script>