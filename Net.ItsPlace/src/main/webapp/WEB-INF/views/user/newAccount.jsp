<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

 <script type="text/javascript">
 //캐차테마변경
 var RecaptchaOptions = {
    theme : 'white'
 };
 $(document).ready(function(){
		
		$('#mobile').mask("999-999?9-9999");
		
		$('#addressInput').bind("keydown", function(e) {
			  //입력 허용 키
			  if( e.keyCode == 13){
				  json_getJsonAddressList2(1);
			  }
		});
		
		
					  
});
 </script>

	
	
<div id="wrapper">
	<header>
		<div class="field">			
			<h1>
				회원가입
			</h1>        		
        			
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<form:form  action="newAccount" commandName="user" method="post">
				<div id="userForm"> 
					
					<!--<form:errors path="*" cssClass="errorblock" element="div" />-->
					
					<div class="field">
						<form:input id="email" path="email"  placeholder="이메일" />
						<form:errors path="email" cssClass="error" />
					</div>
					<div class="field">
						<form:input id="name" path="name" placeholder="이름"  />
						<form:errors path="name" cssClass="error" />
					</div>	
					<div class="field">
						<form:input id="password" path="password" placeholder="비밀번호"  />
						<form:errors path="password" cssClass="error" />
					</div>	
					<div class="field">
						<form:input id="mobile" path="mobile" placeholder="휴대폰"  />
						<form:errors path="mobile" cssClass="error" />
					</div>	
					
				</div>
		
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
				
				
				<div class="agreements_box">
			        <div class="input_panel">
			          <div class="label">서비스 이용약관</div>
			          <div class="field">
			            <div class="agreement">
			            	<p class="title">제 1 조 (목적) </p>
						</div>
			            <div style="font:11px Dotum;color:#000;text-align:left;padding:5px 10px;"><input id="terms_agreement" name="terms_agreement" type="checkbox" value="1"> 서비스 이용약관에 동의합니다.</div>
			            <div class="error" attr="terms_agreement"></div>
			          </div>
			        </div>
			        <div class="input_panel" style="padding-top:20px;">
			          <div class="label">개인정보의 수집 및 이용</div>
			          <div class="field">
			            <div class="agreement">
			            	<p class="title">수집하는 개인정보의 항목 및 수집방법</p> 
						</div>
			            <div style="font:11px Dotum;color:#000;text-align:left;padding:5px 10px;"><input id="privacy_agreement" name="privacy_agreement" type="checkbox" value="1"> 개인정보 수집 및 이용에 동의합니다.</div>
			            <div class="error" attr="privacy_agreement"></div>
			          </div>
			        </div>        
			    </div>
	
			<div id="buttons">
				<button type="submit" class="large orange awesome">회원가입 &raquo;</button>
				<button type="submit" class="large orange awesome">로그인 &raquo;</button>
			</div>
			</form:form>
		</div><!-- content end -->
	</div>
	<footer>Footer</footer>
</div>

<!-- 
<p>
	<button type="button" id="btn_ajaxTest" > AjaxTest</button>
	<div id="testAjax"></div>
</p> 
<p>
	<button type="button" id="btn_JsonTest" > JsonTest</button>
	<div id="testJson"></div>
</p> 
<p>
	<button type="button" id="btn_JsonObject" > JsonObject</button>
	<div id="testObject"></div>
</p>
 -->
	   
	   
