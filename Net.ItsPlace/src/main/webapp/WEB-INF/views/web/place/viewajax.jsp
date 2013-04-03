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
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/view.css" />" media="screen" />
  </head>
<body>

<div id="view" class="container-fluid" >
      <div class="row-fluid" > 
        <div class="span8">
          <h3 id="placeName" class="dynamicContent"></h3>
        </div>
        <div class="span4">
          	네이게이션메뉴
        </div>
     </div>
     <!-- 가맹점사진  medialist -->
     <div class="row-fluid">
     	<div class="span8">
     		 <!-- #camera_wrap_1 -->
         	 <div class="camera_wrap camera_azure_skin dynamicContent" id="camera_wrap_1">
             </div>
        </div>
        <div class="span4 sidebar">
        <h5 class="short_headline"><span>공유하기</span></h5>
        	<ul>
				<li>좋아요 조회수 공유버튼
				</li>
				<li>리뷰횟수</li>
			</ul>
        <h5 class="short_headline"><span>상세정보</span></h5>
			<ul id="placeDetail" class="dynamicContent">
				
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

			<div class="tab-content ">
				<div class="tab-pane container-fluid active" id="review">
					<ul class="nav nav-pills nav-justified">
						<li class="active"><a id="btnFilter" href="#">필터1</a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="#">Messages</a></li>
					</ul>
					<div id="isotope">
						<ul id="userMedias" class="thumbnails">
								

						</ul>
					</div>
				</div>
				<!-- review end -->
				<div class="tab-pane" id="profile">.pppp..</div>
				<div class="tab-pane" id="messages">.mmmm..</div>
				<div class="tab-pane" id="events">
					<div class="accordion" id="accordionEvents"></div>
				</div>
			</div>
		</div>
     
     
     <div class="row-fluid">
			<form id="placeComment" >
             
              <div class=" controls controls-row" style="">
                <img id="imageUrl" class="span1 img-rounded" style="width:70px;height:61px" src="<sec:authentication property='principal.user.profileImageUrl' />"/>
                <input type="hidden" name="fid" value="xxx" />
				<textarea name="comment" class="span10" rows="2"  style="margin-left: 25px;"></textarea>
			  <button id="btnComment" class="span1 btn " style="height:61px; float:right;"  class="blueButton">남기기</button>	
				
              </div>
				
			</form>
     </div>
</div>

	 
	 <!-- camera slider -->
	 <script type="text/javascript" src="${context}/resources/web/js/camera.min.js"></script>
	 <script type="text/javascript" src="${context}/resources/web/js/jquery.easing.1.3.js" ></script>
	 <script type="text/javascript" src="${context}/resources/web/js/jquery.isotope.min.js" ></script>	 
	 <!-- camera slider -->
	 <script type="text/javascript" src="${context}/resources/web/js/fancybox/jquery.fancybox.pack.js" /></script>
	
	 <script type="text/javascript">
		$(document).ready(function() {
			//make view ajax
			$.ajax({
				url : "/place/46",
				type : "GET",				
				success : function(response) {
					util.log(response);
					util.log(response.place.fullAddress);
					/* place Detail*/
					$('#placeName').text(response.place.fname); 
					$('input[name="fid"]').val(response.place.fid);
					var placeDetail = "";
					placeDetail += response.place.phone1 !=""? '<li><span class="detail">'+response.place.phone1+'</span></li>' : "" + 
								   response.place.fullAddress !=""? '<li><span class="detail">'+response.place.fullAddress+'</span></li>' : "" +
								   response.place.openDay !=""? '<li><span class="detail">'+response.place.openDay+'</span></li>' : "" +
								   response.place.parkInfo !=""? '<li><span class="detail">'+response.place.parkInfo+'</span></li>' : "" +
								   response.place.payInfo !=""? '<li><span class="detail">'+response.place.payInfo+'</span></li>' : "" ;
					
					$('#placeDetail').append(placeDetail);															     
					
					
					//response.place
					/* camera slider */
					var placeMedias = "";
					$.each(response.placeMedias, function(i){
						//util.log('${applicationScope.ImageHost}' +this.mUrl);
						placeMedias +='<div data-thumb="" data-src="${applicationScope.ImageHost}'+this.mUrl+'">'+
				                      '<div class="camera_caption fadeFromLeft" style="position:absolute;top:100px">'+this.mTitle+'</div>'+
		   		                      '</div>';
					});
					$('#camera_wrap_1').append(placeMedias);
					jQuery('#camera_wrap_1').camera({
				        	fx: 'random', time: 2000, loader: 'none', playPause: false,
				            pagination: true,
				            thumbnails: false,
				            hover: false,
				            opacityOnGrid: true
				          
				    });
					/* UserMedia Review */
					var placeUserMedias = "";
					$.each(response.placeUserMedias, function(i){
						util.log("review:"+this.url);
						
						if(this.url==null || this.url==""){
							placeUserMedias +=  '<li class="span3 userMedias rental ">' +
												'<div class="thumbnail review-text">' +
												'	<p class="text">'+this.content+ '</p>' +
												'	<div class="caption-text">' +
												'		<p>' +
												'			<span>'+ this.createDate+'</span>' +
												'		</p>' +
												'	</div>' +
												'</div>' +
												'</li>	' ;
						}else{
							placeUserMedias +=  '<li class="span3 userMedias">' +
							'<div class="thumbnail">'+
							'<a class="fancybox" href="${applicationScope.ImageHost}'+this.url+'" data-fancybox-group="gallery" title="">' +
							'<figure class="thumbnail-figure">'+
							'<img style="width: 260px; height: 180px;" src="${applicationScope.ImageHost}'+this.url+'">'+
							'<figcaption class="thumbnail-title">'+
							'<p><span>'+this.createDate+'</span></p>'+
							'</figcaption>'+
							'</figure>'+
							'</a>'+
							'<p>'+this.content+'</p>'+
							'</div>'+
							'</li>';
							
						}
					});
					$('#userMedias').append(placeUserMedias);
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
					$('.fancybox').fancybox({
						openEffect  : 'elastic',
						closeEffect : 'elastic'
					});
				},
				error : function(jqXHR,textStatus,errorThrown) {
					console.log(textStatus
							+ jqXHR
							+ errorThrown);
				}
			});//ajax
			
			
			
			
			//add review
			$('#btnComment').live('click',function(e) {
				e.preventDefault();
								$.ajax({
									url : "/place/addComment",
									type : "POST",
									data : $('#placeComment').serialize(),
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

			    
				
			/* //camera slider
	        jQuery('#camera_wrap_1').camera({
	        	fx: 'random', time: 2000, loader: 'none', playPause: false,
	            pagination: true,
	            thumbnails: false,
	            hover: false,
	            opacityOnGrid: true
	          
	         });//camera slider */
			   
	       /* $('#isotope').isotope({
   	 			itemSelector:'.item',
   	 			layoutMode:'fitRows'
   	 		});
	       */
	       
	      
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