<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3cc715fbd2c405578092bdae6c2a3a6867790d9f" charset="utf-8"></script>
<script type="text/javascript">;
$(document).ready(function() {
	
	 
	$("body").fadeIn("slow");
});


</script>

<div class="title">
	<h3>${place.fname}</h3>
</div>
  

<section id="placeInfo" class="placeContent">
	<div id="placeImage">
		<img src="${place.imageHost}${place.fileName}" height="300" />
	</div>	
	<c:forEach var="media" items="${placeMediaList}">
		${media.mUrl}
	</c:forEach>
	
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
	<div>
		<div style="height:100px;">
			${place.info }
		</div>
		<div style="height:150px;">
			여기에 별표 한번 넣어 봅시다. 댓글
		</div>
		<div>
			진행중인 이벤트
		</div>
		<div>
			가맹점 기타 사진
		</div>
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
						<button id="btnComment"  style="height:70px;float:right;"  class="blueButton">남기기</button>
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
	