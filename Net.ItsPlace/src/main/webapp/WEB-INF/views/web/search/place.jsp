<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
menuSelected("가맹점검색", "주변검색");
</script>
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
.box span.s{
	display: block;
	float: left;
}
.box span.s.select{
	padding-top:5px;
}
.box span.s.pl5{
	padding-left:5px;
}
.box span.s p{
	overflow: hidden;	
}
label.left{display: inline;}
span.label{font-weight: bold;}
</style>
<div class="container">
	<section id="middle">
		<div class="middle_inner">
			<div class="headline">
				<h3>가맹점검색</h3>
			</div>
			<div class="cont_nav">
				<a href="index.html">Home</a>&nbsp; /&nbsp;<a href="shortcodes.html">가맹점검색</a>&nbsp; /&nbsp;주변검색
			</div>
			<section id="middle_content">
				<div class="entry">
					<div class="form_info cmsms_input">
						<input type="text" title="example@gmail.com" name="email" id="email" style="width:90%;" class="validate[required,custom[email]]" />
						<a href="#" class="button" style="background-color:#696969;"><span>검색</span></a>
					</div>
				</div>
				<div>
					<div style="width:25%;float: left;">
						<div class="box">
							<span class="s select">
								<select name="" id="">
									<option value="">선택</option>
									<option value="">선택</option>
									<option value="">선택</option>
								</select>
							</span>
							<span class="s">
								<input type="text" title="example@gmail.com" name="email" id="email" style="width:140px;" class="validate[required,custom[email]]" />
								<a href="#" class="button" style="background-color:#696969;"><span>검색</span></a>
							</span>
						</div>
						<div class="box">
							<span class="s pl5">
								<a href="#" class="button_medium" style="width:65px;background-color:#3c7bc9;"><span>커피숍</span></a>
							</span>
							<span class="s pl5">
								<a href="#" class="button_medium" style="width:55px;background-color:#3c7bc9;"><span>한식</span></a>
							</span>
							<span class="s pl5">
								<a href="#" class="button_medium" style="width:55px;background-color:#3c7bc9;"><span>중식</span></a>
							</span>
						</div>
						<div class="box">
							<span class="s pl5">
								<a href="#" class="button_medium" style="width:65px;background-color:#3c7bc9;"><span>커피숍</span></a>
							</span>
							<span class="s pl5">
								<a href="#" class="button_medium" style="width:55px;background-color:#3c7bc9;"><span>한식</span></a>
							</span>
							<span class="s pl5">
								<a href="#" class="button_medium" style="width:55px;background-color:#3c7bc9;"><span>중식</span></a>
							</span>
						</div>
						<div class="box">
							<span class="s" style="border-bottom:2px solid #bbb;width:100%;"></span>
						</div>
						<div class="box">
							<span class="s" style="padding-top:5px;">
								<img src="/resources/images/search/iPhone_23.jpg" width="100" height="100" />
							</span>
							<span class="s">
								
								<div class="ovh">
								여기가 어딜까?<br />
								전화번호 : 053) 666-6666<br />
								대구 달서구 송현동 111번지 <br />
									<p>이래저래 여기는 이런게 맛있고 이런거를 추천 합니다.....</p>
								</div>
							</span>
						</div>
					</div>
					<div style="width:75%;float: left;">
						미암ㄴ
					</div>
				</div>
			</section>
		</div>
	</section>
</div>
			