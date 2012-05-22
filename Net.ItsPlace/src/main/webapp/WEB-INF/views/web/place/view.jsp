<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3cc715fbd2c405578092bdae6c2a3a6867790d9f" charset="utf-8"></script>
<script type="text/javascript">
menuSelected("가맹점검색", "주변검색");

</script>
<style type="text/css">
#text_box li{list-style: none;clear: both;}
#text_box li span{display: block; float: left;}
#text_box span.title{width:80px;padding-left:3px;}

#comment li{
	list-style: none outside none;	
	display:inline;
}
#comment a{
	display:inline-block;
}
.social img{
	width:23px;
	height:23px;
}
.commentList{
	border:1px solid black;
	clear: both;
}
.commentList img{
	float: left;
	border: 1px solid #CCC;
	background: white;
	margin: 0 10px;
	padding: 3px;
}
.commentBody{
	border:1px solid #CCC;
}

</style>
<div class="container">
	<section id="middle">
		<div class="middle_inner">
			<div class="headline">
				<h3>${place.fname}${ DurationFromNow.test2}</h3>
				
			</div>
			<section id="middle_content">
				<div class="entry">
					<div style="width:35%;border:1px solid red;float: left;">
						<div><img src="/resources/images/view/view.jpg" width="400" height="300" /></div>
						<div style="height:30px;">이미지 슬라이드 넣을까?</div>
						<div style="height:50px;padding-top:10px;">
							음 여기는 평가 넣기 별표 점수 등등등
						</div>
						<div id="text_box">
							상세정보
							<ul>
								<li>
									<span class='title'>전화번호</span>
									<span>053-555-5555</span>
								</li>
								<li>
									<span class='title'>주소</span>
									<span>대구시 서구 비산동 2312 번지</span>
								</li>
								<li>
									<span class='title'>이용시간</span>
									<span>상시(월~일) 10:00 ~ 00:00 </span>
								</li>
								<li>
									<span class='title'>휴무일</span>
									<span>연중무휴</span>
								</li>
								<li>
									<span class='title'>결제정보</span>
									<span>카드가능</span>
								</li>
								<li>
									<span class='title'>영업정보</span>
									<span>예약 가능, 배달 불가능, 포장 가능</span>
								</li>
								<li>
									<span class='title'>시설정보</span>
									<span>지상 1개층 / 수용인원 300명 이하</span>
								</li>
							</ul>
						</div>
						<div style="height:50px;padding-top:10px;">
							음 여기 지도가 들어가야하나??
						</div>
					</div>
					<div style="width:64%;border:1px solid red;float: left;">
						<div style="height:100px;">
							여기에 가게 소개등이 들어가면 되겠죠??
						</div>
						<div style="height:150px;">
							여기에 별표 한번 넣어 봅시다. 댓글
						</div>
						<div style="padding-top:5px;padding-bottom:5px;">
							댓글 리스트
						</div>
					</div>
				</div>
			</section>
			<section>
				
				
				
				
				<div id="comment" style="border:1px solid blue">
					<ul style="margin:0px">
						<li id="facebook" class="social">
							<c:choose>
								<c:when test="${not empty facebook}">
									<img status="connected" src="<c:url value="/resources/images/social/32/facebook.png" />" />
								</c:when>
								<c:otherwise>
									<img status="connect" src="<c:url value="/resources/images/social/32/facebook2.png" />" />
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
					<form action="" method="POST">
						<ul style="border:1px solid blue">
							<li style="border:1px solid blue">
								<img id="imageUrl" style="width:50px;height:50px" src=""/>
							</li>
							<li >
								<textarea name="comment" style="width:300px;height:50px"></textarea>
							</li>
							<li>
								<button>코멘트 </button>
							</li>
						</ul>
					</form>
					<c:if test="${!empty placeCommentList}">
						<div id="comments">
							<h3>리뷰(${placeCommentCount} )</h3>
							<ol>
							<c:forEach var="placeComment" items="${placeCommentList}" >
			  					 <li class="commentList">
			  						<div class="commentBody">
			  							<img src="${placeComment.profileImageUrl}" width="50" height="50">
			  							<span>${placeComment.name}</span>

			  							<p>${placeComment.comment}</p>
			  							<spring:eval expression="T(net.itsplace.util.DurationFromNow).getTimeDiffLabel(placeComment.saveDate)" var="saveDate" />
 										<p>${saveDate}</p>
			  							<sec:authentication property="name" var="currentUserName"/>
										<c:if test="${currentUserName == placeComment.email}">
										 <p>삭제버튼</p>
										</c:if>
			  						</div>
			  					</li>
			  				</c:forEach>	
							</ol>
						</div><!--  comments -->
					</c:if>		
				</div><!-- comment div end -->
			</section>
		</div>
	</section>
</div>

<script type="text/javascript">
$(document).ready(function(){

	$('.dsocial').fancybox({//autodimensions false 후 width , height 가느
			'autoDimensions':false,
			'scrolling':'auto',
			'autoScale':false,
			'height':500,
			//'centerOnScroll':true
			//'title':'사용자 정보 수정'
			//fancybox,주소 dataraletable
	});
	
	 $('.social').live('click',function() {
		var status = $(this).find('img').attr('status');
		var id = $(this).attr("id");
		var url = "/connect/" + id;
		console.log(status + id);
		if(status=="connected"){
			console.log("logout");
			$.ajax({
                 url: "/connect/facebook",
                 type:"POST",
                 data:"_method=delete",
                 success: function(response){
                  console.log("s");
                 },
                 error: function(jqXHR, textStatus, errorThrown){
                	alert(textStatus+jqXHR+errorThrown); 
                 }
           });//ajax
			//window.open(url, 'ADpop','resizable=yes,status=no,toolbar=no,menubar=no,width=840,height=800,scrollbars=yes');
		}else{

			//c.newWindow(url);
			window.open(url, 'ADpop','resizable=yes,status=no,toolbar=no,menubar=no,width=840,height=800,scrollbars=yes');
		}
	}); 
	
});	
function chageSocialStatus(provider,status){
	var img = $('#'+provider).find('img');
	$(img).attr('status',status);
	if(provider=="facebook" && status=="connect"){
		$(img).attr('src',"/resources/images/social/32/facebook2.png");
	}else{
		//connected
		
		
		$(img).attr('src',"/resources/images/social/32/facebook.png");
		$.ajax({
            url: "/social/fbProfile", 
            type:"GET",
            success: function(response){
             console.log("s:"+response.status);
             console.log("s:"+response.imageUrl);
             console.log("s:"+response.displayName);
             $('#displayName').text(response.displayName);
             $('#imageUrl').attr('src',response.imageUrl);
            },
            error: function(jqXHR, textStatus, errorThrown){
          	 	alert(textStatus+jqXHR+errorThrown); 
            }
    	});
	}
}
</script>
			