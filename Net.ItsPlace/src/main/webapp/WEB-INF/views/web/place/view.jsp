<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3cc715fbd2c405578092bdae6c2a3a6867790d9f" charset="utf-8"></script>
<script type="text/javascript">
var socket = io.connect('http://localhost:8070');
$(document).ready(function() {
	socket.on('connect', function () {
	    console.log("connected socket:"+'${place.fname}' + $('#userName').val());
	    socket.emit('PlaceOn', { room: '${place.fname}',name:$('#userName').val()});
	});
	socket.on("PlaceOn", function (data) {
		console.log("입장:"+data.room); 
	});
	socket.on('SetUserList', function (data) {
	  	
	  	console.log("유저목록:"+data);
	    $('#onlineUsers').empty();
	  	
	  	 for(var i=0;i<data.length;i++){
	  		 console.log("user name:"+data[i]);	       		
	  		$('#onlineUsers').append('<div>'+data[i]+'</div>');
	  	 }
	  	
	});
	socket.on('message', function(data) {
		console.log("message receive");
        if (data.comment) {
        	$('.message').last().after('<div class="message"><span>'+data.name + ":</span><span>" + data.comment+'</span></div>');
        	$('#chatContainer').animate({scrollTop:999999}, 'slow');
        }
    });
	socket.on('error', function(data) {
		console.log("ddd");
        if (data.error) {
        	alert(data.error);
        }
    });
	
	
	
	 $('#btnMessage').live('click',function(){	
		 //socket.emit('sendMessage', { room: $('#room').val(),userId: $('#userId').val(), message: $('#message').val()});
		 socket.json.send({ room: '${place.fname}', name:$('#userName').val(), data: $('#message').val() });
		 
		// $('#content').append('<p style="color:blue;">'+$('#userId').val() +": " +$('#message').val()+'<p>');
	 });
	 $('#message').keyup(function (e) {
			var keyCode = (event.which) ? event.which : event.keyCode;
 			//console.log(keyCode);
 			if(keyCode==13){
 				socket.json.send({ room: '${place.fname}', name:$('#userName').val(), data: $('#message').val() });
 				
 			}
 	 });
	 
	$("body").fadeIn("slow");
});


</script>
<style type="text/css">
#placeOn{
	border:0px solid red;
}
#chat{
	border:0px solid gray;
	width:80%;
	height:300px;
	float:left;
}
#chatContainer{
 width:100%;
 height:250px;
 border:1px solid gray;
 float:left;
 overflow: auto;
}
#messageContainer{
	width:100%;
	height:50px;
	border:1px solid gray; float:left;
}
.message{
	margin:5px;
}
#message{
	margin:5px;
	padding:5px;
	width:70%
}
#onlineUsers{
	border:1px solid blue;
	height:300px;
	width:19%;
	float:right;
}
</style>
<input id="userName" type="hidden"  value="<sec:authentication property="principal.user.name" />"/>
 
<div id="placeOn">
	<div id="chat">
		<div id="chatContainer">
			<div class="message"><span></span><span></span></div>
		</div>
		<div id="messageContainer">
			<input type="text" id="message"><button id="btnMessage">보내기</button>
		</div>
	</div>
	<div id="onlineUsers">
		<h1>online</h1>
	</div>
</div>
<div style="display:none">

<div class="title">
	<h3>${place.fname}</h3>
</div>
  

<section id="placeInfo" class="placeContent">
	<div id="placeImage">
		<img src="${place.imageHost}${place.fileName}" height="300" />
	</div>	
	<div id="map" >
	map
	</div>
	<div id="text_box">
			상세정보
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

</section>
<section class="placeContent">
	
					<div >
						<div style="height:100px;">
							${place.info }
						</div>
						<div style="height:150px;">
							여기에 별표 한번 넣어 봅시다. 댓글
						</div>
						<section>
							이벤트
						</section>
						<section>
							가맹점 기타 사진
						</section>
						<div id="Comment" style="border:1px solid blue">
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
							
							<div id="displayName" style="border:1px solid red;">
							  kimdonghonn
							</div>
							<form id="placeComment" >
								<ul style="border:1px solid blue">
									<li style="border:1px solid blue">
										<img id="imageUrl" style="width:70px;height:70px" src=""/>
									</li>
									<li>
										<input type="hidden" name="fid" value="${place.fid}" />
										<textarea name="comment" style="width:500px;height:70px"></textarea>
										<a id="btnComment" class="button" style="height:70px;float:right;"><span>Add comment</span></a>
									</li>
									
								</ul>						
							</form>
						</div><!-- comment div end -->
						<section>			
							<aside id="comments">								
								<ol id="commentList" class="commentlist">				
								</ol>
							</aside>			
							<div id="paging" class="wp-pagenavi"></div>
							<div class="cl"></div>
						</section>
						<section>
							리뷰 
						</section>
</section>



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
	</div>		