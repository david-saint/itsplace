<%@page import="java.util.Locale"%>
<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<c:set var="title" value="메인페이지 "/>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/js/camera.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/js/isotope.css" />" />
		<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3cc715fbd2c405578092bdae6c2a3a6867790d9f" charset="utf-8"></script>
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/js/fancybox/jquery.fancybox.css?v=2.1.4" />" media="screen" />
			
		<style>
		#placeTab a{
			text-decoration: none;
		}
		.accordion-heading  a {
			text-decoration: none;
		}
		.accordion-group {
			border: 0px solid #e5e5e5 !important;
		}
		.short_headline {
			border-bottom: 1px solid #d8dfe5;
			margin: 0 0 1em 0;
			text-transform: lowercase;
			padding-bottom: 9px;
			line-height: normal;
			}
		.short_headline span {
			border-bottom: 5px solid #d8dfe5;
			padding-bottom: 10px;
			color: #758694;
			}
			.sidebar{
			background-color: #fff;
			height: 385px;
				background-image: url(http://bombdiggitydesign.com/crisp/Crisp-cool/assets/images/rule.png);
				/* background-position: 54.5% 0; */
				background-repeat: repeat-y;
				padding:10px;
							text-shadow: 0px 1px 0px #fff;
				-webkit-border-radius: 4px;
				-moz-border-radius: 4px;
				border-radius: 4px;
				-webkit-box-shadow: 0px 1px 1px rgba(0,0,0,.15), 0px 2px 1px rgba(0,0,0,.10), 0px 3px 1px rgba(0,0,0,.05);
				-moz-box-shadow: 0px 1px 1px rgba(0,0,0,.15), 0px 2px 1px rgba(0,0,0,.10), 0px 3px 1px rgba(0,0,0,.05);
				box-shadow: 0px 1px 1px rgba(0,0,0,.15), 0px 2px 1px rgba(0,0,0,.10), 0px 3px 1px rgba(0,0,0,.05);
			}
			
			.userMedias{
				margin-left:5px !important;
			}
			.thumbnail img:hover {
				opacity: .2;
			}
					.thumbnail-figure {
						margin: 0 0 24px;
						position: relative;
						background: #333333;
					}
		.thumbnail-title {
			position: absolute;
			top: 80%;
			margin-top: -30px;
			-webkit-transition: all 0.2s;
			-moz-transition: all 0.2s;
			-o-transition: all 0.2s;
			transition: all 0.2s;
		}
		.thumbnail-title span {
			display: inline-block;
			font-size:10px;
			background: white;
			background: rgba(255, 255, 255, 0.8);
			color: black;
			padding: 5px 10px;
			/* text-shadow: 1px 1px 1px white; */
			margin-top: 1px;
		}

			.review-text > .text{
				padding-bottom:45px;	
			}
		.caption-text{
			position: absolute;
			top: 95%;
			margin-top: -30px;
			margin-left:-4px;
			-webkit-transition: all 0.7s;
			-moz-transition: all 0.7s;
			-o-transition: all 0.2s;
			transition: all 0.2s;
		}
		.caption-text span {
			display: inline-block;
			background: white;
			background: rgba(88, 78, 78, 0.8);
			color: rgb(248, 248, 248);
			padding: 5px 10px;
			/* text-shadow: 1px 1px 1px white; */
			margin-top: 1px;
		}
		
.thumbnail-figure img {
-webkit-transition: all 0.2s;
-moz-transition: all 0.2s;
-o-transition: all 0.2s;
transition: all 0.2s;
}

body{
background: url("http://wbpreview.com/previews/WB0C44S9M/img/bg-wrapper.jpg")
}

		</style>		
</head>
<body>

<div class="container" >
      <div class="row-fluid" > 
        <div class="span8">
          <h3>${place.fname} ${place.info }</h3>
        
        </div>
        <div class="span4">
          	네이게이션메뉴
        </div>
     </div>
     <!-- 가맹점사진  medialist -->
     <div class="row-fluid" style="">
     	<div class="span8">
     		
         	<%--  <img src="${applicationScope.ImageHost}${place.fileName}" height="300" /> --%>
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
        <div class="span4 sidebar">
        <h5 class="short_headline"><span>공유하기</span></h5>
        	<ul>
				<li>좋아요 조회수 공유버튼
				</li>
				<li>리뷰횟수</li>
		
			</ul>
        
        <h5 class="short_headline"><span>상세정보</span></h5>
			<ul>
				<li><span class='title'>전화번호</span> <span>${place.phone1}</span>
				</li>
				<li><span class='title'>주소</span> <span>${place.fullAddress}</span>
				</li>
				<li><span class='title'>이용시간</span> <span>${place.openDay}</span>
				</li>
				<li><span class='title'>휴무일</span> <span>${place.closeDay}</span>
				</li>
				<li><span class='title'>결제정보</span> <span>${place.payInfo}</span>
				</li>
				<li><span class='title'>주차정보</span> <span>${place.parkInfo}</span>
				</li>
				<li><span class='title'>시설정보</span> <span>${place.bldInfo}</span>
				</li>
			</ul>
        </div>
       
     	
     </div>
     <!-- 사용자 미디어 -->
     <div class="row-fluid" style="border: 0px solid #ff5821;background-color: #fff;">
      <ul class="nav nav-tabs" id="placeTab">
			  <li class="active"><a href="#review" data-toggle="tab" state="0">리뷰</a></li>
			  <li><a href="#profile" data-toggle="tab" state="0"> 지도</a></li>
			  <li><a href="#messages" data-toggle="tab" state="0">스탬프</a></li>
			  <li><a href="#events" data-toggle="tab" state="0">이벤트</a></li>
			</ul>
			 
			<div class="tab-content">
			  <div class="tab-pane active" id="review">
			  
			  
			  			<ul class="nav nav-pills nav-justified">
						  <li class="active"><a id="btnFilter" href="#">필터1</a></li>
						  <li><a href="#">Profile</a></li>
						  <li><a href="#">Messages</a></li>
						</ul>
						     <div id="isotope">
						      <ul class="thumbnails" >
						      	<c:forEach var="media" items="${placeMediaList}">
					             <li class="span3 userMedias">
								     <div class="thumbnail">
								    <a class="fancybox" href="${applicationScope.ImageHost}${media.mUrl}" data-fancybox-group="gallery" title="Etiam quis mi eu elit temp">
									    <figure class="thumbnail-figure">
							            <img  style="width: 260px; height: 180px;" src="${applicationScope.ImageHost}${media.mUrl}">
							            
						            
						            
						            <figcaption class="thumbnail-title">
						             
						              <p>
						                <span>오늘. 홍길동</span>
						              </p>
						            </figcaption>
						            </figure>
								    </a>  
								      <p>Thumbnail caption..ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ.</p>
								    </div>
								  </li>    	
				               			
								</c:forEach>
								
						      	 <li class="span3 userMedias rental " >
						      	    <div class="thumbnail review-text">
								    <p  class="text">안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요
								    </p>
								    <div class="caption-text">
						              <p>
						                <span>Managing director</span>
						              </p>
						            </div>
						            </div>
								  </li> 
								  
								   <li class="span3 userMedias rental review-text" style="border:0px solid blue;">
								   		<div class="thumbnail review-text">
									    <p  class="text">dfxfxfxdfxdf요
									    </p>
									    <div class="caption-text">
							              <p>
							                <span>Managing director</span>
							              </p>
							            </div>
							            </div>
								  </li> 
								  
								   <li class="span3 userMedias renta review-text" style="border:0px solid red;">
								    <div class="thumbnail review-text">
									    <p  class="text">하하하하하하하하하하하하하하하하하하하하
									    </p>
									    <div class="caption-text">
							              <p>
							                <span>Managing director</span>
							              </p>
							            </div>
							            </div>
								  </li> 
								  
								  
								   <li class="span3 userMedias">
								     <div class="thumbnail">
									    <a href="#">
									    <div class="thumbnail-figure">
							            <img style="width: 260px; height: 180px;" src="${context}/resources/web/js/slides/thumbs/tree.jpg">
							            
							            <div class="thumbnail-title">
							              
							              <p>
							                <span>Managing director</span>
							              </p>
							            </div>
							            </div>
									    </a>  
									      <h3>Thumbnail label</h3>
									      <p>Thumbnail 안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요..ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ.</p>
									    </div>
								  </li>    	
								  <li class="span3 userMedias rental review-text" style="border:0px solid blue;">
								   		<div class="thumbnail review-text">
									    <p  class="text">xxxxxxxxxxxxxxxxxxxxxxxxxxx
									    </p>
									    <div class="caption-text">
							              <p>
							                <span>Managing director</span>
							              </p>
							            </div>
							            </div>
								  </li> 
								  
								  <li class="span3 userMedias">
								     <div class="thumbnail">
								    <a href="#">
								    <figure class="thumbnail-figure">
						            <img style="width: 260px; height: 180px;" src="${context}/resources/web/js/slides/thumbs/tree.jpg">
						            
						            <figcaption class="thumbnail-title">
						              <h3>
						                <span>Betty Doe</span>
						              </h3>
						              <p>
						                <span>Managing director</span>
						              </p>
						            </figcaption>
						            </figure>
								    </a>  
								      <h3>Thuail label</h3>
								      <p>Thumbna..ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ.</p>
								    </div>
								  </li>    	
								  
						      </ul>
						     </div>
			  </div><!-- review end -->
			  <div class="tab-pane" id="profile">.pppp..</div>
			  <div class="tab-pane" id="messages">.mmmm..</div>
			  
			  <div class="tab-pane" id="events">
			  	
			  	<div class="accordion" id="accordionEvents">
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="accordionEvents" href="#collapseOne">
                    	     
                       		무료 이벤트 진행중입니다 2013-01-01 ~ 2013-02-03
                    </a>
                  </div>
                  <div id="collapseOne" class="accordion-body collapse ">
                    <div class="accordion-inner">
                     	<div class="span8">
						      <div class="clearfix content-heading">
						          <img class="pull-left"  style="width: 260px; height: 180px;" src="${context}/resources/web/js/slides/thumbs/tree.jpg"/>
						          <div style="margin-left:280px;">
						           <h3>Experience &nbsp </h3> 
						          <p>Donec id elit non mi porta gravida at eget metus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
						          </div>
						          
						      </div>
						      
						</div>
                    </div>
                  </div>
                </div>
               
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="accordionEvents" href="#collapseThree">
                  				    무료 이벤트 진행중입니다 2013-01-01 ~ 2013-02-03
                    </a>
                  </div>
                  <div id="collapseThree" class="accordion-body collapse in">
                    <div class="accordion-inner">
                      3333333333333333333333333333333333333333333333333333333
                    </div>
                  </div>
                </div>
              </div>
			  	
			  	<c:forEach var="event" items="${placeEventList}">
			  	 	${event.title }
			  	</c:forEach>
			  	<c:forEach var="review" items="${placeReviewList}">
			  		${review.title }
			  	</c:forEach>
			  	<c:forEach var="comment" items="${placeComments}">
			  		${comment.comment }
			  	</c:forEach>
			  </div>
			</div>
			
			
			
     
     </div>
    <!--  <div class="row">
     <div class="accordion" id="accordion2">
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                      Collapsible Group Item #1
                    </a>
                  </div>
                  <div id="collapseOne" class="accordion-body collapse in">
                    <div class="accordion-inner">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                      Collapsible Group Item #2
                    </a>
                  </div>
                  <div id="collapseTwo" class="accordion-body collapse">
                    <div class="accordion-inner">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
                      Collapsible Group Item #3
                    </a>
                  </div>
                  <div id="collapseThree" class="accordion-body collapse">
                    <div class="accordion-inner">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div>
              </div>
     </div> -->
     <%-- <div class="row-fluid" style="border: 1px solid #ff5821;">
     	<div class="span8">
     	 <ul class="thumbnails">
		      	<c:forEach var="media" items="${placeMediaList}">
	             <li class="span3 userMedias">
				    <div class="thumbnail">
				    <a href="#">
				    <figure class="thumbnail-figure">
		            <img data-src="${applicationScope.ImageHost}${media.mUrl}" alt="260x180" style="width: 260px; height: 180px;" src="${applicationScope.ImageHost}${media.mUrl}">
		            
		            <figcaption class="thumbnail-title">
		              <h3>
		                <span>Betty Doe</span>
		              </h3>
		              <p>
		                <span>Managing director</span>
		              </p>
		            </figcaption>
		            </figure>
				    </a>  
				      <h3>Thumbnail label</h3>
				      <p>Thumbnail caption..ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ.</p>
				    </div>
				  </li>    	
               			
				</c:forEach>
				
		      	 <li class="span3 userMedias rental">
				    <div class="thumbnail">
				    	<div style="width: 260px; height: 180px;">
				    	<p>dddddddddddddddddddddddddddddddddddddkssu안녕하세요aaaaaaaaaaaaaaaaa</p>
				    	</div>
				     
				      <h3>Thumbnail ggg</h3>
				      <p>Thumbnail caption...ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ</p>
				    </div>
				  </li> 
		      </ul>
	    </div>
	    <div class="span4">
	    sss
	    </div>
                    
     </div>  --%>
     <div class="row-fluid">
     	<div class="media">
              <a class="fancybox pull-left" href="#">
                <img class="media-object" data-src="holder.js/64x64" alt="64x64" style="width: 64px; height: 64px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAABM0lEQVR4Xu2YQQ4CIQxFZ+7GsTkTV9C4IKlYoATQRt6sJhls6ee3L3KnlB7Xwc+NADiAFmAGHDwDL4YgFIACUAAKQIGDFQCDYBAMgkEweDAE+DMEBsEgGASDYBAMTigQY3z7dQjhI1peo32rpd4Vt8w3hcGyMK1QWYhVgF1xNbGXCqAlWCHAqrhfF6Bm494J91rG0h7Wrl7iAJlM2nxWgNG41qLluiUC5KLlydXec/LWKbYc0os7KsLPBHhttGb1vxKgPJHSLfm71jotZ9XiunGA3Mjo0OutL9vIitflFJA21k6ytlFLgZZJ36OFxQ1TM8CSwPsaBOBChAsRLkS4EPE+qXfuDwpAASgABaDAzinrPTYUgAJQAApAAe+Teuf+oAAUgAJQAArsnLLeY0MBKHA4BZ4dA7WQLRJORAAAAABJRU5ErkJggg==">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </div>
        </div>     	
     	<div class="media">
              <a class="fancybox pull-left" href="#">
                <img class="media-object" data-src="holder.js/64x64" alt="64x64" style="width: 64px; height: 64px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAABM0lEQVR4Xu2YQQ4CIQxFZ+7GsTkTV9C4IKlYoATQRt6sJhls6ee3L3KnlB7Xwc+NADiAFmAGHDwDL4YgFIACUAAKQIGDFQCDYBAMgkEweDAE+DMEBsEgGASDYBAMTigQY3z7dQjhI1peo32rpd4Vt8w3hcGyMK1QWYhVgF1xNbGXCqAlWCHAqrhfF6Bm494J91rG0h7Wrl7iAJlM2nxWgNG41qLluiUC5KLlydXec/LWKbYc0os7KsLPBHhttGb1vxKgPJHSLfm71jotZ9XiunGA3Mjo0OutL9vIitflFJA21k6ytlFLgZZJ36OFxQ1TM8CSwPsaBOBChAsRLkS4EPE+qXfuDwpAASgABaDAzinrPTYUgAJQAApAAe+Teuf+oAAUgAJQAArsnLLeY0MBKHA4BZ4dA7WQLRJORAAAAABJRU5ErkJggg==">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </div>
        </div>     	
     </div>
     
     
     <div class="row-fluid">
			<form id="placeComment" >
             
              <div class=" controls controls-row" style="">
                <img id="imageUrl" class="span1 img-rounded" style="width:70px;height:61px" src="<sec:authentication property='principal.user.profileImageUrl' />"/>
                <input type="hidden" name="fid" value="${place.fid}" />
				<textarea name="comment" class="span10" rows="2"  style="margin-left: 25px;"></textarea>
			  <button id="btnComment" class="span1 btn " style="height:61px; float:right;"  class="blueButton">남기기</button>	
				
              </div>
				
			</form>
     </div>
</div>


<!-- 
<ul style="margin:0px;margin-right:50px;">
				<li id="commentCount">
					리뷰(0) 
				</li>
				<li id="facebook" class="social">
					<c:choose>
						<c:when test="${not empty facebook}">
							<img status="connected" src="<c:url value="/resources/images/social/32/facebook.png" />" />
						</c:when>
						<c:otherwise>
						<a href="<c:url value="/connect/facebook" />">
							<img status="connect" src="<c:url value="/resources/images/social/32/facebook2.png" />" />
						</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li id="twitter" class="social">
					<c:choose>
						<c:when test="${not empty twitter}">
						<a href="<c:url value="/connect/twitter" />">
							<img class="connected" src="<c:url value="/resources/images/social/32/twitter.png" />" />
						</a>	
						</c:when>
						<c:otherwise>
						<a href="<c:url value="/connect/twitter" />">
							<img status="connect" src="<c:url value="/resources/images/social/32/twitter2.png" />" />
						</a>	
						</c:otherwise>
					</c:choose>
				</li>
				
			</ul>
			
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						getCommentList(1);
						$('#btnComment')
								.live(
										'click',
										function() {
											$
													.ajax({
														url : "/place/addComment",
														type : "POST",
														data : $(
																'#placeComment')
																.serialize(),
														success : function(
																response) {
															console
																	.log("s:"
																			+ response.result);
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															alert(textStatus
																	+ jqXHR
																	+ errorThrown);
														}
													});
										});
						$('.dsocial').fancybox({//autodimensions false 후 width , height 가느
							'autoDimensions' : false,
							'scrolling' : 'auto',
							'autoScale' : false,
							'height' : 500,
						//'centerOnScroll':true
						//'title':'사용자 정보 수정'
						//fancybox,주소 dataraletable
						});

						$('.social')
								.live(
										'click',
										function() {
											var status = $(this).find('img')
													.attr('status');
											var id = $(this).attr("id");
											var url = "/connect/" + id;
											console.log(status + id);
											if (status == "connected") {
												console.log("logout");
												$
														.ajax({
															url : "/connect/facebook",
															type : "POST",
															data : "_method=delete",
															success : function(
																	response) {
																console
																		.log("s");
															},
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																alert(textStatus
																		+ jqXHR
																		+ errorThrown);
															}
														});//ajax
												//window.open(url, 'ADpop','resizable=yes,status=no,toolbar=no,menubar=no,width=840,height=800,scrollbars=yes');
											} else {

												//c.newWindow(url);
												window
														.open(
																url,
																'ADpop',
																'resizable=yes,status=no,toolbar=no,menubar=no,width=840,height=800,scrollbars=yes');
											}
										});

					}); //ready
	function chageSocialStatus(provider, status) {
		var img = $('#' + provider).find('img');
		$(img).attr('status', status);
		if (provider == "facebook" && status == "connect") {
			$(img).attr('src', "/resources/images/social/32/facebook2.png");
		} else {
			//connected

			$(img).attr('src', "/resources/images/social/32/facebook.png");
			$.ajax({
				url : "/social/fbProfile",
				type : "GET",
				success : function(response) {
					console.log("s:" + response.status);
					console.log("s:" + response.imageUrl);
					console.log("s:" + response.displayName);
					$('#displayName').text(response.displayName);
					$('#imageUrl').attr('src', response.imageUrl);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(textStatus + jqXHR + errorThrown);
				}
			});
		}
	}
	function getCommentList(currentpage) {
		var fid = '${place.fid}';
		console.log("fid:" + fid);

		$.ajax({
			url : "/place/getPlaceCommentListJson",
			type : "GET",
			data : ({
				currentPage : currentpage,
				pageSize : "10",
				pageGroupSize : "10",
				fid : fid
			}),
			success : function(data) {
				console.log(data);
				console.log(data.result.length);
				$('#commentList').empty();
				$.each(data.result, function(i) {
					var str = "";
					str += '<li>';
					str += '<figure class="alignleft">';
					str += '<img src="'+this.profileImageUrl+'">';
					str += '</figure>';
					str += '<div class="comment-body">';
					str += '<div class="com_box">';
					str += '<div class="com_info">';
					str += '<span class="fl"><strong>' + this.name
							+ '</strong> said</span>';
					str += '<a href="#" class="fr">Edit</a>';
					str += '<div class="cl"></div>';
					str += '</div>';
					str += '<p>' + this.comment + '</p>';
					str += '<span class="fl">' + this.prettyDate + '</span>';
					str += '<a href="#" class="fr">Delete</a>';
					str += '<div class="cl"></div>';
					str += '</div>';
					str += '</div>';
					str += "</li>";
					$('#commentList').append(str);
				});
				$('#paging').empty();
				$('#paging').append(data.paging);
				$('#commentCount').text("리뷰(" + data.totalCount + ")");
			},
			error : function(data, status, err) {
				alert(data + err + status);
			},
			complete : function() {
				$('#paging').find('a').each(function(i) {
					var page = $(this).attr('page');
					console.log(page);
					$(this).live('click', function() {
						getCommentList(page);
					});
				});
			}
		});
	}
</script>
	 -->
	 <h1>푸헤헤헤 마지막</h1>
	 
	 <!-- camera slider -->
	 <script type="text/javascript" src="${context}/resources/web/js/camera.min.js"></script>
	 <script type="text/javascript" src="${context}/resources/web/js/jquery.easing.1.3.js" ></script>
	 <script type="text/javascript" src="${context}/resources/web/js/jquery.isotope.min.js" ></script>	 
	 <!-- camera slider -->
	 <script type="text/javascript" src="${context}/resources/web/js/fancybox/jquery.fancybox.pack.js" /></script>
	
	 <script type="text/javascript">
		$(document).ready(function() {
			
			
			
			$('.fancybox').fancybox({
				openEffect  : 'elastic',
				closeEffect : 'elastic'
			});
			
			//add review
			$('#btnComment').live('click',function(e) {
				e.preventDefault();
								$.ajax({
									url : "/place/addComment",
									type : "POST",
									data : $(
											'#placeComment')
											.serialize(),
									success : function(response) {
										if(util.status(response)){
											console.log("success");
										}else{
											console.log("s:"+ response.result);
										}
										
									
									},
									error : function(jqXHR,textStatus,errorThrown) {
										console.log(textStatus
												+ jqXHR
												+ errorThrown);
									}
								});
					});
			
			//탭
			/* $('#placeTab a').click(function (e) {
			  e.preventDefault();
			  $(this).tab('show');
			}); */
			
			 $('a[data-toggle="tab"]').on('shown', function (e) {
					util.log("tab id:" + $(this).prop('hash'));
					
					if($(this).prop('hash') == "#events"){
						//이벤트 콜
						$.ajax({
							url : "/place/events/" + '${place.fid}',
							type : "GET",							
							success : function(response) {
								
									util.log("이벤트 정보들 ");
									util.log(response);
									var html = "";
									$.each(response, function(i){
										console.log(this.title);
										console.log(this.eid);
										html +=	'<div class="accordion-group">' +
		                  		        '<div class="accordion-heading">' +
		                    			'<a class="accordion-toggle" data-toggle="collapse" data-parent="accordionEvents" href="#event_'+this.eid+'">' +
			                       		this.title + 
		                    		    '</a>' +
		                  			    '</div>'+
					                    '<div id="event_'+this.eid+'" class="accordion-body collapse ">'+
					                    ' <div class="accordion-inner">'+
					                    ' 	<div class="span8">'+
										'	      <div class="clearfix content-heading">'+
										'	          <img class="pull-left"  style="width: 260px; height: 180px;" src="${context}/resources/web/js/slides/thumbs/tree.jpg"/>'+
										'	          <div style="margin-left:280px;">'+
										'	           <h3>Experience &nbsp </h3> '+
										'	          <p>'+this.content+'</p>'+
										'	          </div>'+
										'	      </div>'+
										'	</div>'+
					                    '</div>'+
					                    ' </div>'+
					                    ' </div>';
					                    
									});
								 
									
				                $('#accordionEvents').append(html);
							
							},
							error : function(jqXHR,textStatus,errorThrown) {
								console.log(textStatus
										+ jqXHR
										+ errorThrown);
							},
							complete : function(){
								//alert();
								$(".collapse").collapse();
							}
						});
		
						
                
						//$(".collapse").collapse();
					}
			        /* var myState = $(this).attr('state'),
			            state = $('.expandcollapse').attr('state');

			        if(myState != state) {
			          toggleTab($(this).prop('hash'));
			          $(this).attr('state',state);
			        }
 */
			    }); // te

			    
				
			//$(".collapse").collapse();
			   //camera
	        jQuery('#camera_wrap_1').camera({
	        	fx: 'random', time: 2000, loader: 'none', playPause: false,
	            pagination: true,
	            thumbnails: false,
	            hover: false,
	            opacityOnGrid: true
	          
	         });
			   
	       /* $('#isotope').isotope({
   	 			itemSelector:'.item',
   	 			layoutMode:'fitRows'
   	 		});
	       */
	       
	       $('#isotope').isotope({
				//filter: "*",
				itemSelector:'.userMedias',
   	 			//layoutMode:'fitRows',
				animationOptions: {
					duration: 750,
					easing: 'linear',
					queue: false,
				},
				masonry: { columnWidth: 1  }
			});
	        $('#btnFilter').click(function(){  
	 			 $('#isotope').isotope({ filter: '.rental' });
	 		});
	        
			 //.camera({ fx: 'random', time: 2000, loader: 'none', playPause: false, height: '65%', pagination: true });
			$("body").fadeIn("3000");
			
		/* 
			jQuery('#bannerscollection_kenburns_generous').bannerscollection_kenburns({
				skin: 'generous',
				responsive:true,
				width: 934,
				height: 414,
				width100Proc:false,
				numberOfThumbsPerScreen:7,
				thumbsOnMarginTop:50,
				thumbsWrapperMarginTop: -110,
				autoHideBottomNav:false,
				autoPlay:2
			});		 */
			
		});
		
		
		</script>
</body>
</html>	 