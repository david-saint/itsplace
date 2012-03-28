<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<div data-role="content" id="loginPage">	
	<div id="login-error">${error}</div>
	<form id="loginForm" action="<c:url value="/signin/authenticate" />" method="post" >
		<div data-role="fieldcontain">
			<label for="j_username">Email</label> 
			<input id="j_username" name="j_username" type="text" placeholder="이메일" />
			<label for="j_password">Password</label> 
			<input id="j_password" name="j_password" type="password" placeholder="비밀번호" />
			<label for="_spring_security_remember_me">remember me</label>
			<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" checked="checked" />
		</div>		
		
		<button id="submit" type="submit">로그인</button>
				<!-- <a data-role="button" data-transition="flip" href='<c:url value="/user/login" />' >로그인</a> -->
		<a data-role="button" data-transition="pop"  href='<c:url value="/user/newAccount" />' >회원가입</a>
	</form>
</div>
