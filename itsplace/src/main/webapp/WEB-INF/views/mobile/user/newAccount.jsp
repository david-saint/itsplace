<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
 <script type="text/javascript">
 //캐차테마변경
 var RecaptchaOptions = {
    theme : 'white'
 };
 </script>

<div data-role="content" id="loginPage">	
	
	<form:form  action="newAccount" commandName="user" method="post">
		 
			<form:errors path="*" cssClass="errorblock" element="div" />
			<div data-role="fieldcontain">
	
				<label for="email">Email</label>
				<form:input id="email" path="email"  placeholder="이메일" />				
				
				<label for="password">Password</label>
				<form:input id="password" path="password" placeholder="비밀번호"  />
				
				<label for="name">Name</label>
				<form:input id="name" path="name" placeholder="이름"  />
				<!--<form:errors path="name" cssClass="error" />-->
				<label for="mobile">Mobile</label>
				<form:input id="mobile" path="mobile"  placeholder="전화번호"  />
				
			<!--<div>
					<script type="text/javascript" src="http://api.recaptcha.net/challenge?k=6LerHscSAAAAAJ-Cpt77wpMi-smuw5XY-8eJjR3d">
					</script>
					<noscript>
					   <iframe src="http://api.recaptcha.net/noscript?k=6LerHscSAAAAAJ-Cpt77wpMi-smuw5XY-8eJjR3d"
					                height="300" width="500" frameborder="0"></iframe><br>
					   <textarea name="recaptcha_challenge_field" rows="3" cols="40">
					   </textarea> 
					   <input type="hidden" name="recaptcha_response_field" value="manual_challenge">
					</noscript>
			</div> catcaha -->
		</div>
				
		<button type="submit" data-icon="plus">가입하기&raquo;</button>
		<a id="test"  data-icon="back" data-rel="back" data-transition="fade" data-role="button">돌아가기</a>
				

	</form:form> 
	
</div>

<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function(){
	$('#mobile').click(function() {
		window.CallAndroid.getPhoneNumber();
	});
	$('#email').click(function() {
		window.CallAndroid.getEmail();
	});
	
});

function Android_getPhoneNumber(n){
	
	$('#mobile').val(n.replace('+8210','010'));
}
function Android_getEmail(n){
	
	$('#email').val(n);
}
</script>