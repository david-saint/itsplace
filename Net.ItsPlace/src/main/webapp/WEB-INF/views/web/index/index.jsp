<%@page import="java.util.Locale"%>
<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="메인페이지 "/>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/boot.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/js/camera.css" />" />
  </head>
  <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">ITS PLACE <spring:message  code="signup"/></a>
          <div class="nav-collapse in collapse" style="height: auto;">
            <ul class="nav" >
              <li><a href="#camea_slider" class="nav-button">PLACES</span></a></li>
              <li><a href="#howtouse" class="nav-button">How to Use</span></a></li>
              <li><a href="#about" class="nav-button">ABOUT</span></a></li>
              <li><a href="#credit" class="nav-button">도움주신</span></a></li>
               <li><a href="#" class="nav-login" ><%=request.getLocale().getDisplayLanguage(request.getLocale())%> </a>
               <ul>
                        <li><a href="?locale=en"><%=Locale.ENGLISH.getDisplayLanguage()%></a></li>
                        <li><a href="?locale=ko"><%=Locale.KOREAN.getDisplayLanguage(Locale.KOREAN)%></a></li>
                        <li><a href="?locale=ko"><%=Locale.JAPANESE.getDisplayLanguage(Locale.JAPANESE)%></a></li>
                       
                      </ul> <!-- /Sub menu --> 
                  </li>
              <li><a href="#myModal" class="nav-login" >시작하기</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

      <div class="container-fluid clearfix " id="camea_slider" style="padding-right:0px">
         <div class="row" >
            <div id="camer_container">
              <div class="camera_wrap camera_azure_skin" id="camera_wrap_1">
                <div data-thumb="${context}/resources/web/js/slides/thumbs/bridge.jpg" data-src="${context}/resources/web/js/slides/bridge.jpg">
                    <div class="camera_caption fadeFromLeft" style="position:absolute;top:100px">The text of your html element</div>
                </div>
                <div data-thumb="${context}/resources/web/js/slides/thumbs/leaf.jpg" data-src="${context}/resources/web/js/slides/leaf.jpg">
                    <div class="camera_caption fadeFromTop" style="position:absolute;top:100px;left:200px;background-color:transparent;width:300px;">
                        It uses a light version of jQuery mobile, <em>아하하하하하</em>
                    </div>
                </div>
                <div data-thumb="${context}/resources/web/js/slides/thumbs/road.jpg" data-src="${context}/resources/web/js/slides/road.jpg">
                    <div class="camera_caption fadeFromBottom">
                        <em>It's completely free</em> (even if a donation is appreciated)
                    </div>
                </div>
                <div data-thumb="${context}/resources/web/js/slides/thumbs/sea.jpg" data-src="${context}/resources/web/js/slides/sea.jpg">
                    <div class="camera_caption fadeFromRight">
                        Camera slideshow provides many options <em>to customize your project</em> as more as possible
                    </div>
                </div>
               
                <div data-thumb="${context}/resources/web/js/slides/thumbs/tree.jpg" data-src="${context}/resources/web/js/slides/tree.jpg">
                    <div class="camera_caption moveFromTop">
                        Different color skins and layouts available, <em>fullscreen ready too</em>
                    </div>
                </div>
              </div><!-- #camera_wrap_1 -->  
            </div>     
         </div>
      </div>


    <!-- 소개-->
    
     <div class="container-fluid" id="howtouse" >

      <div class="row"> 
        <div class="span4">
          <p class="">11111111111111</p>
        </div>
        <div class="span4">
          <p class="">222222</p>
        </div>
        <div class="span4">
          <p class="">222222</p>
        </div>
     </div>
   </div>
 </div>


   <div class="container-fluid" id="about" >
      <div class="row">
        <!-- <div class="span6">
          <div class="test2">
             <p class="animate-in" data-animation="fly-in-right">This paragraph will fly in from the left.</p>
          </div>
        </div>
        <div class="span6">
          <div class="test2">
             <p class="animate-in" data-animation="fly-in-right">This paragraph will fly in from the left.</p>
          </div>
        </div> -->
      </div>
   </div>
   <div class="container-fluid" id="login">
     <div class="row-fluid " >
      <div class="span5" style="border:1px solid red;">
            <h1>Login</h1>
            <form action="" method="post">
                <input type="text" name="username" class="username" placeholder="Username">
                <input type="password" name="password" class="password" placeholder="Password">
                <button type="submit" class="btn btn-large ">Sign me in</button>
                <div class="error"><span>+</span></div>
            </form>

          

            <div class="connect">
                <p class="animate-in" data-animation="fly-in-left">ssshis paragraph will fly in from the left.</p>
                <p>dddddddddddd
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>   
     </div>
     <div class="span7">
        <div class="login-form">
            <div class="control-group">
              <input type="text" class="" value="" placeholder="Enter your name" id="login-name">
              <label class="login-field-icon fui-man-16" for="login-name"></label>
            </div>

            <div class="control-group">
              <input type="password" class="login-field" value="" placeholder="Password" id="login-pass">
              <label class="login-field-icon fui-lock-16" for="login-pass"></label>
            </div>

            <a class="btn btn-primary btn-large btn-block" href="#">Login</a>
            <a class="login-link" href="#">Lost your password?</a>
          </div>
     </div>
     </div>
</div>


  <div class="container-fluid " id="credit">
    <div class="row-fluid">
            <ul class="thumbnails">
              <li class="span4">
                <div class="thumbnail">
                  <img src="https://www.pulse.me/media/images/new_site/brands_hero_device-717a37a6e2bda681302dd6339ea965c0459a5833.jpg " alt="300x200">
                  <div class="caption">
                    <h3>Thumbnail label</h3>
                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                  </div>
                </div>  
              </li>
              <li class="span4">
                <div class="thumbnail">
                  <img  alt="300x200" data-src="holder.js/300x200">
                  <div class="caption">
                    <h3>Thumbnail label</h3>
                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                  </div>
                </div>
              </li>
              <li class="span4">
                <div class="thumbnail">
                  <a href="#" >
                  <img src="http://www.springsource.org/files/homepage/home-video-big_WhatIsSpring_496.png" alt="300x200">
                </a>
                  <div class="caption">
                    <h3>Thumbnail label</h3>
                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <p><a href="#" class="btn btn-primary">Action</a> <a href="#" class="btn">Action</a></p>
                  </div>
                </div>
              </li>
            </ul>
          </div>   
  </div> 




  <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">Place 시작하기 </h3>
    </div>
    <div class="modal-body">
        <div class="login-form">
            <form method="post" action="signin/authenticate">
            <div class="control-group">
              <input type="email" name="j_username" class="login-field" value="" placeholder="Enter your name" id="login-name">
              <label class="login-field-icon fui-man-16" for="login-name"></label>
            </div>

            <div class="control-group">
              <input type="password" name="j_password" class="login-field" value="" placeholder="Password" id="login-pass">
              <label class="login-field-icon fui-lock-16" for="login-pass"></label>
            </div>

            <button class="btn btn-primary btn-large btn-block">Login</button>
            </form>
            
            <form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
							
							        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,email" />
							        <input type="hidden" id="on_off" name="_spring_security_remember_me" value="1" />
							        <fieldset> 
									<button  type="submit" class="btn btn-large facebook"><i class="icon-facebook"></i><span>페이스북 아이디로 로그인</span></button>
                            </fieldset>
			</form>
					${error}    
            <a id="forgot-pass" href="/passwordreset" target="_blank" class="login-link" href="#">Lost your password?</a>
            <a id="forgot-pass" href="/passwordreset" target="_blank" class="login-link" href="#">Email로 가입하기 </a>
            
          </div>
          
       
    </div>
   <!--  <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
      <button class="btn btn-primary">Save changes</button>
    </div> -->
  </div>


   <a  class="scrollup">Scroll</a>

   <script type="text/javascript" src="${context}/resources/web/js/jquery.easing.1.3.js" ></script>
		<script type="text/javascript" src="${context}/resources/web/js/jquery.mobile.customized.min.js"></script>
		<script type="text/javascript" src="${context}/resources/web/js/camera.min.js"></script>
		<script type="text/javascript" src="${context}/resources/web/js/jquery.scrollTo-1.4.3.1.min.js"></script>
		<script type="text/javascript" src="${context}/resources/web/js/jquery.parallax-1.1.3.min.js"></script>
		<script type="text/javascript" src="${context}/resources/web/js/jquery.localscroll-1.2.7-min.js"></script>
	
	
    
 
    <script type="text/javascript">
      $(document).ready(function() {
    	  //alert('${error}');
        $('.nav').localScroll(800);
        
        //$('#camea_slider').parallax("50%", 0.4);
        
        //$('#howtouse').parallax("50%", 0.4);

        $('.nav-button').click(function(){
         // $('.nav-button').removeClass("active");
         // $(this).addClass("active")

        });
		if(!util.empty('${errorcode}')){
			$('#myModal').modal({
	              keyboard: false
	            });
		}
        $('.nav-login').click(function(){
            $('#myModal').modal({
              keyboard: false
            });
        }); 
        // $('.btn-navbar').toggle(function(){
        //     $('.nav-buttons').slideDown();
        // },function(){
        //     $('.nav-buttons').slideUp();
        // });
        
         //camera
        jQuery('#camera_wrap_1').camera({
          //  height: '50%',
            time:3000,
            loader: 'bar',//'bar'
            pagination: false,
            thumbnails: false,
            hover: false,
            opacityOnGrid: true,
            imagePath: '../images/'
         });

        
        //scroll to top
        $(window).scroll(function(){
                if ($(this).scrollTop() > 100) {
                    $('.scrollup').fadeIn();
                } else {
                    $('.scrollup').fadeOut();
                }
            });

        $('.scrollup').click(function(){
            $("html, body").animate({ scrollTop: 0 }, 600);
            return false;
        });  

        var buttons = $('.nav-button');
        var scrollPoints = [];
        for (i=0; i<buttons.length;i++){
          var p = $($(buttons[i]).attr('href')).offset().top;
          scrollPoints.push(p);
          console.log(p + $(buttons[i]).attr('href'));
        }


        $(window).scroll(function( ){  //스크롤이 움직일때마다 이벤트 발생 
              var position = $(this).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.

              if($('#howtouse').offset().top>position){
                //console.log("ss:"+position);
              }
              

              for(i=0;i<scrollPoints.length;i++){
                if(i == 0 && position <= scrollPoints[i]){
                  $(buttons).removeClass('active');
                    $(buttons[i]).addClass('active');
                    console.log("처음 ");
                    break;
                }else{
                  if( position >= scrollPoints[i-1]  && position <= scrollPoints[i] ){
                    console.log("위치:"+scrollPoints[i] );
                    $(buttons).removeClass('active');
                    $(buttons[i-1]).addClass('active');
                    break;
                  }
                }
                if(i== scrollPoints.length-1){

                    $(buttons).removeClass('active');
                    $(buttons[i]).addClass('active');
                   console.log("마지막");
                    break;
                }
              }
         });
      });

    </script>
  </body>
</html>

