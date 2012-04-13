<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<div id="wrapper">
	<header>
		<div class="">			
			<h1>
				잇츠플레이스 로그인
			</h1>        		
        			
		</div>	
	</header>
	<div id=>
		<div id="">
			<c:if test="!empty user">
				<div id="joinBox">
					<p>Email:aaaaa@aaaa.com ${user.email}</p>
					<p>김동훈${user.name} 님 환영합니다.</p>			
				</div>
		
			</c:if>
			<div id="login-error">${error}</div>
			<!--<form action="../j_spring_security_check" method="post" >
			-->
			<div id="">
				<form  id="loginFormName" action="<c:url value="/signin/authenticate" />"  method="post" >
					<div>
						<div><label for="j_username">Email</label></div>
						<input id="j_username" name="j_username" type="text" value="faye12005@gmail.com" />
					</div>
					<div>
						<div><label for="j_password">Password</label></div>
						<input id="j_password" name="j_password" type="password" />
					</div>
					<div>
						<label for="_spring_security_remember_me">remember me</label>
						<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" checked="checked" />
						<p>비밀번호가 기억안나요</p>
					</div>
					<button type="button" onclick="login()" class="large blue awesome">Login</button>								
				</form>
			</div>
		</div><!-- content end -->
	</div>
</div>

<script>
function login(){console.log("d");
	$.ajax({
	    url: "<c:url value="/signin/authenticate" />",
	    type: "POST",
	    data: $("#loginFormName").serialize(),
	    beforeSend: function (xhr) {
	        xhr.setRequestHeader("X-Ajax-call", "true");
	    },
	    success: function(result) {
	        if (result == "ok") {
	           alert("ok"); 
	        } else if (result == "error") {
	           alert("fal");
	        }
	    }
	}); 
}

</script>