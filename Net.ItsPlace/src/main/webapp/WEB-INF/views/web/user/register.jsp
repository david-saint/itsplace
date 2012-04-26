<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>
<style type="text/css">
input[type="text"], input[type="password"], textarea{
	border:1px solid #c8c8c8;
	background-color:#e4e4e4;
	margin-bottom:5px;
	height:20px;
	padding:5px 4px;
	margin:0 0 3px;
	border-radius:4px;
	-moz-border-radius:4px;
	-webkit-border-radius:4px;
}
.box{
	border:1px solid #c8c8c8;
	background-color:#e4e4e4;
	margin-bottom:5px;
	padding:5px 4px;
	margin:0 0 3px;
}
label.left{display: inline;}
span.label{font-weight: bold;}
</style>
<div class="container">
	<section id="middle">
		<div class="middle_inner">
			<div class="headline">
				<h3>회원가입</h3>
			</div>
			<div class="cont_nav">
				<a href="/">Home</a>&nbsp; /&nbsp;<a href="/user/register">나의스템프</a>&nbsp; /&nbsp;회원가입
			</div>
			<div style="padding:5px;"> 
				<form action="#" method="post" id="form_contact_form_widget_001">
					<div class="form_info cmsms_input">
						<label for="field_003">
							<span class="label">Email(아이디로 사용됩니다 정확히 기재 해주세요!!)</span>
							<span class="color_3"> *</span>
						</label>
						<input type="text" name="email" id="email" style="width:500px;" class="validate[required,custom[email]]" />
					</div>
					<div class="form_info cmsms_input">
						<label for="field_003">
							<span class="label">이름(별명)</span>
							<span class="color_3"> *</span>
						</label>
						<input type="text" name="email" id="email" style="width:500px;" class="validate[required]" />
					</div>
					<div class="form_info cmsms_input">
						<label for="field_003">
							<span class="label">비밀번호</span>
							<span class="color_3"> *</span>
						</label>
						<input type="password" name="password" id="password" style="width:500px;" class="validate[required]" />
					</div> 
					<div class="form_info cmsms_input">
						<label for="field_003">
							<span class="label">비밀번호확인</span>
							<span class="color_3"> *</span>
						</label>
						<input type="password" name="re-password" id="re-password" style="width:500px;" class="validate[required]" />
					</div>
					<div class="">
						<label>
							<span class="label">성별</span>
							<span class="color_3" style="font-weight:bold;"> *</span>
						</label>
						<div class="box" style="width:500px;">
							<input type="radio" name="sex" id="man" class="validate[required]" /><label class="left" for="man">남자</label>
							<input type="radio" name="sex" id="woman" class="validate[required]" /><label class="left" for="woman">여자</label>
						</div>
					</div>  
					<div class="form_info cmsms_textarea">
						<label for="field_004">
							<span class="label">이용약관</span>
							<span class="color_3" style="font-weight:bold;"> *</span>
						</label>
						<textarea name="wmessage" id="field_004" style="width:500px;height:150px;" readonly="readonly">* 이용약관
1. itsplace 이래저래해서이렇게 저렇게 한다.
2. 이하동문
3. 이하동문
4. 이하동문
5. 이하동문
6. 이하동문
7. 이하동문
8. 이하동문
9. 이하동문
10. 이하동문
11. 이하동문
12. 이하동문
13. 이하동문
14. 이하동문
15. 이하동문
16. 이하동문
17. 이하동문
18. 이하동문
19. 이하동문
20. 이하동문
						</textarea>
						<div>
						<input type="checkbox" name="yes" id="yes" class="validate[required]" /><label class="left" for="yes">이용약관 동의</label>
						</div>
					</div>
					<div>
						<a href="#" class="button">
							<span>가입하기</span>
						</a>
					</div>
					<!-- <div class="form_info cmsms_textarea">
						<label for="field_004">Message<span class="color_3"> *</span></label>
						<textarea name="wmessage" id="field_004" cols="28" rows="6" tabindex="13" class="validate[required,minSize[3]]"></textarea>
					</div>
					<div class="loading"></div>
					<div><input type="hidden" name="contact_form_widget_001_wurl" id="contact_form_widget_001_wurl" value="http://oakland-html.cmsmasters.net/php/sendmail.php" /></div>Here you need to set the path to the sendmail file
					<div><a href="#" id="contact_form_widget_001_wformsend" class="button" tabindex="14"><span>Send Message</span></a></div> -->
				</form>
			</div>
		</div>
	</section>
</div>