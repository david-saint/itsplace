<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="도서목록"/>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	//window.AndroidCall.show("hi");
	enquire.register("screen and (max-width:468px)", {

	    match : function() {
	    	
	    		console.log("match 468px");
		  		
	        
	    },
	    unmatch : function() {
	  
	        console.log("unmatch 468px");
	      
	    } 
    }).register("screen and (max-width:900px)", {
	    match : function() {
	    	
		        console.log("match 900px");
		     
	    },
	}).register("screen and (max-width:1100px)", {

		  match : function() {
			  
			
				  console.log("match 1100px");
				
		        
		    },
		    unmatch : function() {
		   
					  console.log("unmatch 1100px");
				
		    }
	}).listen();
    
	 $('.bar2').mosaic({animation	:	'slide'	});
	//
	/*  $('.fancy').fancybox({
			'autoDimensions':false,
			'scrolling':'auto',
			'autoScale':false,
			'height':400,
	}); */
	 $('#signup').click(function(){
		 if ($('#signinForm').is(":hidden")){
			 
			 $('#signinForm').slideDown("slow"); 
		 }else{
			 $('#signinForm').slideUp("slow");
		 }
	 });
	 $('#btnClose').click(function(){
		 $('#signinForm').slideUp();
	 });
	 
	 $('#register-form').submit(function(){
			var url = "${context}/user/register";
			$.ajax({
              url: url,
              type:"POST",                                
              data:$("#register-form").serialize(),                   
              success: function(response){
                if(response.status=="SUCCESS"){
             	   document.location.href='${context}/places';
                }else{                 
             	 //  console.log("결과:"+response.result);
             	   
             	   for(var i =0 ; i < response.result.length ; i++){
                        var field = "#"+response.result[i].field;
                        
                        if($(field).length <= 0 ){
                     	  field =  $('select[name='+response.result[i].field+']').next()//label;
                     	  
                        }
                        console.log("필드:"+field);
                      //  $(field).attr('original-title',response.result[i].message);
                        $('input[name='+response.result[i].field+']').next().text(response.result[i].message);
                    }
             	   
                }
              },
              complete:function(){
             	 //setTimeout("c.unloading()",1500); 
              }
            });//ajax
            return false;
		});
	 $("#register-form").find('input').live('click',function() {
		 $(this).next().text("");
	 });
	 
	 $('#signin-form').submit(function() {
			//securityLogin();
	 });
	 
	 $('#password').keyup(function (e) {
			var keyCode = (event.which) ? event.which : event.keyCode;
 			if(keyCode==13){
 				securityLogin();
 			}
	 });
});

</script>
</head>
<body>
<section id="signinForm" class="blackBack" style="display:none;height:300px"> 
	<div id="auth-container">
		<div class="inner">
		<hgroup>
        	<h2 id="create" class="headline">THE PLACE ${applicationScope.ImgaeHost} </h2>
        </hgroup>
        <button id="btnClose"><img src="${context}/resources/images/icon/keyamoon/32px/cancel.png" /></button>
			
			<div id="left" class="">
				<div id="leftContainer">
					<form id="register-form">
			           <fieldset>
			                <div>
			                 	<input type="text" name="name" class="border-box"/><span></span>
			                </div>
			           </fieldset>
			           <fieldset>
			                <div> 
			                	<input type="text" name="email" class="border-box"/><span></span>
			                </div>                                  
			           </fieldset>
				       <fieldset>
			                <div> 
			                	<input type="password" name="password" class="border-box"/><span></span>
			                </div>                                  
				       </fieldset>
				     
				       <fieldset>
				       	 <button id="btnRegister" class="greenButton"><spring:message  code="signup"/></button>
				       </fieldset>
					</form>  
				  	  
				       
				</div>
				
			</div>
			<div class="v-divider ">
                    
            </div>
			<div id="right" >
				<div id="rightContainer">
                       <form id="signin-form" method="post" action="signin/authenticate">
                            <input type="hidden" id="on_off" name="_spring_security_remember_me"  value="1"   />
                            <fieldset>
                                <label for="username" style="display: none; ">Email or Username</label>
                                <input id="username" class="border-box" type="email" name="j_username" placeholder="Email or Username" tabindex="1">
                            </fieldset>
                            
                            <fieldset>
                                <label for="password" style="display: none; ">Password</label>
                                <input id="password" class="border-box" type="password" name="j_password" placeholder="Password" tabindex="2"></fieldset>
                            <fieldset>
	                            <button  id="btnSignIn" class="grayButton">로그인</button>
                            </fieldset>
                        </form>
                        
                        <form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
							
							        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,email" />
							        <input type="hidden" id="on_off" name="_spring_security_remember_me" value="1" />
							        <fieldset> 
									<button  type="submit" class="grayButton facebook"><i class="icon-facebook"></i><span>페이스북 아이디로 로그인</span></button>
                            </fieldset>
					    </form>
                        <a id="forgot-pass" href="/passwordreset" target="_blank">비밀번호가 기억 안나세요?</a>

                        <div id="login-error-output"><p>${error}</p></div>
                    </div>
			</div>
		</div>	
	</div>
</section>
	
<section id="m">
	<div id="m-container">
		<div class="inner">
			<div id="header" class="clearfix">
				<h1 class="gota logo">THE PLACE <div style="font-size: 24px;">
<!--   <i class="icon-camera-retro"></i> --></div>
				</h1>
				<nav>
				<!-- 	<a id="signin" >로그인</a> -->
					
					<a id="signup" ><span class="gray-button">시작하기</span></a>
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
 	이미지: <sec:authentication property="principal.user.email" /> 
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
				<li><a href="/?site_preference=Mobile">Mobile</a></li>
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
 </body>
 </html>