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

		
html {
font-size: 100%;
}
body {
margin: 0;
padding: 0;
font-family: "proxima-nova","Helvetica-neue",helvetica,sans-serif;
}
article, aside, details, figcaption, figure, footer, header, hgroup, nav, section {
display: block;
}
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
}
#header #signin, #header #signup {
position: relative;
float: right;
z-index: 2;
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
</style>
<section id="signinForm" style="display:none;height:300px">
 <form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,email" />
        <input type="checkbox" id="on_off" name="_spring_security_remember_me" value="1" /> 
		<button type="submit">사인인 위드 페이스북</button>
		<button id="close">클로</button>
	</form>
	
	<input type="text" name="email"/>
	<input type="password" name="password"/>
	<button>로그</button>
</section>
	
<section id="m">
	<div id="m-container">
		<div class="inner">
			<div id="header" class="clearfix">
				<h1 class="Sketchetik-Light logo" style="font-size:60px;color:green;" title="Pulse">place</h1>
				<nav>
					<a href="#signinForm" id="signin" class="fancy ">로그인</a>
					<a  id="signup" ><span class="gray-button">회원가입</span></a>
					   <%-- <a href="<c:url value="${context}/logout" />" > Logout</a> --%>
				</nav>
			</div>
			<div class="headline" >
				<h1 class="Sketchetik-Light">
					What do you want to read today? <span class="grey-text">Pick
						a category to start.</span>
				</h1>
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
<div>ssssssss
 <sec:authorize ifAnyGranted="ROLE_USER">
 	이미지: <sec:authentication property="principal.username" /> 
 </sec:authorize>
 <sec:authorize access="isAuthenticated()">
  인증됨   <sec:authentication property="principal" />
   <sec:authentication property="principal.User.profileImageUrl" />"
 </sec:authorize>
 </div>

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