
<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>

<script type="text/javascript">
$(document).ready(function() {
	 $('.bar2').mosaic({animation	:	'slide'	});
	//
	 $('.fancy').fancybox({
			'autoDimensions':false,
			'scrolling':'auto',
			'autoScale':false,
			'height':400,
	});
	 $('#signup').click(function(){
		 if ($('#signinForm').is(":hidden")){
			 
			 $('#signinForm').slideDown("slow"); 
		 }else{
			 $('#signinForm').slideUp("fast");
		 }
	 });
	 $('#close').click(function(){
		 $('#signinForm').slideUp();
	 });
});
</script>
<style>


#m-container {
position: relative;
overflow: hidden;
padding: 0 0 50px 0;
background: url(/media/images/gray_noise-a57d294e4fac444d96aeee2acc60ed9617735a60.jpg) #202020 repeat top left;
height: auto;
}
.inner{
border:1px solid red;
	position: relative;
	width: 100%;
	margin: 0 auto;
	padding: 35px 15px;
	max-width: 960px;
	overflow: hidden;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}
#header {
padding: 0;
width: 100%;
height: 49px;
}
.Sketchetik-Light { 
	font-family: Sketchetik-Light;
	font-weight: normal;
	font-style: normal;
}
#header .logo {
float: left;
font-size:40px;color:#EF8600
}
#header #signin, #header #signup {
position: relative;
float: right;
z-index: 2;
}
#signin:hover, #signup:hover {
color: #EF8600;
cursor: pointer;
}
#header a {
display: block;
height: 49px;
line-height: 49px;

text-decoration: none;
color: white;
font-weight: 700;
font-size: 18px;
}
#m-container h1, #m-container h2 {
padding: 0;
font-weight: 400;
color: white;
}
#infiniteCarousel {
width: 960px;
height: 240px;
position: relative;
}
#info-container {
position: relative;
overflow: hidden;
background: url(/media/images/light_gray_noise-994fecd3fbd995349a86ef01bbe5efadc91939a9.jpg) #2D2D2D repeat 0 0;
}
.headline{
font-size: 28px;
font-weight: 400;
color: white;
}
.info-headline {
margin: 0 auto;
padding: 30px 0;
max-width: 688px;
width: 100%;
font-size: 26px;
font-weight: 400;
line-height: 1.25em;
text-align: center;
color: gray;
}
footer {
background: black;
min-height: 150px;
}
footer ul {
position: relative;
float: left;
}
footer li {
position: relative;
float: left;
display: block;
}
footer li a {
font-size: 12px;
color: #3A3A3A;
text-decoration: none;
text-transform: uppercase;
margin: 0 30px 0 0;
font-weight: 600;
}
.copyright {
float: right;
font-size: 12px;
color: #3A3A3A;
}
nav ul, nav ol {
list-style: none;
list-style-image: none;
}
ul, ol {
list-style-type: none;
margin: 0;
padding: 0;
}


.gray-button{
background: #404040;
padding: 8px 14px;
border-radius: 3px;
line-height: 40px;
height: 40px;
}
.blackBack{
	background-color: #111;
}
#auth-container{
	background-color: #111;
	border-bottom: 8px solid #151515;
	position: absolute;
padding: 0;
height: auto;
width: 100%;
z-index: 10;
}
.details{ margin:18px 20px;  height:50px;}	
		h4{ font:300 11px 'Helvetica Neue', Helvetica, Arial, sans-serif; color:#fff; text-shadow:1px 1px 0 rgb(0,0,0); }
		p{ margin-top:15px; font:300 12px 'Lucida Grande', Tahoma, Verdana, sans-serif; color:#aaa; text-shadow:1px 1px 0 rgb(0,0,0);}
		a{ text-decoration:none; } 
.place{
			float: left;
			position: relative;
			overflow: hidden;
			width: 280px;
			height: 230px;
			margin: 10px;
			background: #111 url(../img/progress.gif) no-repeat center center;
			border: 1px solid white;
			-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
		}
		
		.place img{
			width:280px;
			height:230px;
		}
		
		
#signinForm input[type="email"], #signinForm input[type="text"] , #signinForm input[type="password"] {
	margin: 0 10px 0 0;
	padding: 12px 18px;
	background: #292929;
	font-family: "proxima-nova","Helvetica-neue",helvetica,sans-serif;
	font-weight: 200;
	font-size: 16px;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.5);
	color: #777;
	border: 1px solid #444;
	outline: 0;
}
#signinForm fieldset{
float: left;
padding: 0 0 10px 0;
width: 100%;
}		
#signinForm #right{
float: right;
width: 50%;
padding: 20px 0 0 0;
} 
#rightContainer{
	margin: 0 auto;
width: 300px;

}

#signinForm input{
width: 100%;
}
#signinForm button{
width: 100%;
}
#signinForm #left{
float: left;
width: 50%;
padding: 40px 0 0 0;
} 
#leftContainer{
		margin: 0 auto;
width: 300px;

}
.v-divider {
position: absolute;
height: 86%;
border-left: 1px solid #333;
left: 50%;
top: 7%;
}
.v-divider .label-container {
text-transform: uppercase;
text-align: center;
font-weight: 900;
line-height: 46px;
color: #999;
position: absolute;
margin: -23px 0 0 -23px;
top: 50%;
width: 46px;
height: 46px;
background: #111;
}
.blueButton {
margin: 0;
padding: 13px 20px;
max-height: 40px;
background: #EF8600;
font-weight: 700;
color: white;
text-transform: uppercase;
text-decoration: none;
border: 0;
-webkit-box-shadow: 0 4px 0 0 #854F15;
-moz-box-shadow: 0 4px 0 0 #854F15;
box-shadow: 0 4px 0 0 #854F15;

}
.border{
border:1px solid blue;
}
#closeButton {
position: absolute;
top: 20px;
right: 20px;
}
.border-box {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}
fieldset{
	border:none;
}
</style>
<section id="signinForm" class="blackBack" style="display:block;height:300px"> 
	<div id="auth-container">
		<div class="inner">
		<hgroup>
        	<h2 id="create" class="headline">Sign In to Pulse</h2>
        </hgroup>
        <button id="closeButton">close</button>
			
			<div id="left" class="">
				<div id="leftContainer">
					<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
			        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,email" />
			        <input type="hidden" id="on_off" name="_spring_security_remember_me" value="1" /> 
					<button type="submit" class="blueButton">페이스북으로 로그인 </button>
				</form>
				</div>
				
			</div>
			<div class="v-divider ">
                    <div class="label-container">
                        <span>or</span>
                    </div>
            </div>
			<div id="right" >
				<div id="rightContainer">
                       <form id="signin-form" action="/login" method="post" novalidate="">
                            <input type="hidden" name="mode" value="direct-login">
                            <input type="hidden" name="form" value="form_auth">
                            
                            
                            <fieldset>
                                <label for="username" style="display: none; ">Email or Username</label>
                                <input id="username" class="border-box" type="email" name="username" placeholder="Email or Username" tabindex="1">
                            </fieldset>
                            
                            <fieldset>
                                <label for="password" style="display: none; ">Password</label>
                                <input id="password" class="border-box" type="password" name="password" placeholder="Password" tabindex="2"></fieldset>
                            <fieldset>
	                            <button class="blueButton">로그인</button>
                            </fieldset>
                            
                        
                        </form>
                        <a id="forgot-pass" href="/password-reset" target="_blank">비밀번호가 기억 안나세요?</a>

                        <div id="login-error-output"><p>Your login and password didn't match.</p></div>
                    </div>
			</div>
		</div>	
	</div>
</section>
	
<section id="m">
	<div id="m-container">
		<div class="inner">
			<div id="header" class="clearfix">
				<h1 class="Sketchetik-Light logo"  title="Pulse">place</h1>
				<nav>
					<a id="signin" class="fancy">로그인</a>
					
					<a id="signup" ><span class="gray-button">회원가입</span></a>
					   <%-- <a href="<c:url value="${context}/logout" />" > Logout</a> --%>
				</nav>
			</div>
			<div class="headline" style="margin:30px">
				
			</div>
			
						<c:forEach var="place" items="${recentPlaceList}">
						<div class="place bar2">
							<a class="mosaic-overlay">
								<div class="details">
									<h4>${place.fname }</h4>
									<%-- <p>${place.category} <br /> ${place.fullAddress }</p> --%>
								</div>
							</a>
							<div class="mosaic-backdrop">
								<img src="${place.imageHost}${place.fileName }"  />
							</div>
							
						</div>
						</c:forEach>
					
			
			
			 
		</div>
	</div>
</section>

<section id="info">
	<div id="info-container">
		<div class="inner">
			<h2 class="info-headline">
				Pulse is a <strong>fast</strong>, <strong>fun</strong> and <strong>beautiful</strong>
				way to read your favorite blogs, magazines and newspapers
			</h2>
		</div>
		<div id="content-boxes">
			
				
		</div>
	</div>
</section>
<div style="border:1px solid red;color:white">ssssssss
 <sec:authorize ifAnyGranted="ROLE_USER">
 	이미지: <sec:authentication property="principal.username" /> 
 </sec:authorize>
 <sec:authorize access="isAuthenticated()">
  인증됨   <sec:authentication property="principal" />
   <sec:authentication property="principal.User.profileImageUrl" />"
 </sec:authorize>
 </div>

<footer>
	<div class="inner">
		<nav>
			<ul>
				<li><a href="/about/">About</a></li>
				<li><a href="/jobs">Jobs</a></li>
				<li><a href="http://blog.pulse.me">Blog</a></li>
				<li><a href="http://blog.pulse.me/faq">FAQ</a></li>
				<li><a href="/about/#/contact">Contact</a></li>
			</ul>
		</nav>
		<small class="copyright">All content Copyright 2012 by
			Alphonso Labs</small>
	</div>
</footer>

<!--Bar 2-->
		

					
	
						<%-- <c:forEach var="event" items="${recentEventList}">
							<li>
								<a href="#" class="alignleft">
									<figure>
										<img src="/resources/oakland/images/img/news_1.jpg" alt="" />
									</figure>
								</a>
								<abbr title=""  class="published"><fmt:formatDate value="${event.startDate}" pattern="yyyy-MM-dd"/> - <fmt:formatDate value="${event.endDate}" pattern="yyyy-MM-dd"/></abbr>
								<a href="#">${event.title}</a>
								<p>${event.place.fullAddress}</p>
								<p>${event.content}</p>
							</li>
						</c:forEach>
						</ul>
 --%>