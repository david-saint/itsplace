<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>



<style type="text/css">

.placeComment-date{
	float:right;
	margin-right:10em; 
	margin-top:3px;
}
</style>

<script type="text/javascript">
var map;
$(document).ready(function(){
	if($('#gallery').has('li').length>0){
		var myPhotoSwipe = $("#gallery a").photoSwipe({ enableMouseWheel: false , enableKeyboard: false });
	}
	var lat = '${franchiserMember.latitude}';
	var lng = '${franchiserMember.longitude}';
	
	 map = new daum.maps.Map(document.getElementById('map'), {

			center: new daum.maps.LatLng(lat, lng)

		});

		var marker = new daum.maps.Marker({

			position: new daum.maps.LatLng(lat, lng)

		});

		marker.setMap(map);
	
});

function mapLarge(){
	alert("");
}
</script>

	
<div id="wrapper">
	<header>
		<div class="field">	
		<ul>
			<li style="display: inline-block; width: 900px;margin:0px">		<h1>${franchiserMember.fname}</h1></li>
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<li style="display: inline-block;"><a  href="<c:url value="/partner/franchiserDetail/${franchiserMember.fid}" />" class="large blue awesome">정보수정 </a></li>
			</sec:authorize>
			<sec:authentication property="name" var="currentUserName"/>
			<c:if test="${currentUserName == franchiserMember.email}">
			<li style="display: inline-block;;margin:0px"><a  href="<c:url value="/partner/franchiserDetail/${franchiserMember.fid}" />" class="large blue awesome">정보수정 </a></li>
			</c:if>
			
		</ul>
		</div>	
	</header>
	<div id="main">
		<div id="content">
			<article>
				<!-- <h2 class="entry-title"><a href="#">${franchiserMember.fname}</a></h2> -->
				
				<div id="map">지도뷰</div>
				<button style="position:absolute;right: 215px;z-index: 99999;top: 370px;" class="small blue awesome" onclick="mapLarge()">크게보기</button> 
				<figure>
                	<a href="#">
                		<img src="<c:url value="/resources/images/" />${franchiserMember.fileName}" width="200" height="200" alt="프런트 이미지" class="thumbnail alignleft">
                	</a>
            	</figure>
            	
            	
            	<ul>
            		<li id="address">
	            		<dl>
							<dt>주소</dt>
							<dd>${franchiserMember.address.sido} ${franchiserMember.address.gugun} ${franchiserMember.address.doroname} ${franchiserMember.address.bldmain}-${franchiserMember.address.bldsubmain}</dd>
						</dl>
            		</li>
            		<li id="phone">
            			<dl>
							<dt>전화번호</dt>
							<dd>${franchiserMember.phone1}</dd>
						</dl>
					</li>
            		<li id="homepage">
            			<dl>
							<dt>홈패이지</dt>
							<dd>http://</dd>
						</dl>
            		</li>
            	</ul>
            	
            	<p style="border:1px solid black;">
            		drtdtdr
            	</p>
            	
			</article>
			
			
		<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
			<div class="stamp_box">			
				<c:forEach var="stamppedList" items="${stamppedListAll}"  >
				<h3>스탬프</h3>
					<ul style="border:1px solid blue;">
						<c:forEach var="stamp" items="${stamppedList}" varStatus ="status">
							<li id="${stamp.pid}" class="stamp_column ${stamp.eventday}"  title="<fmt:formatDate value="${stamp.inpdate}" pattern="yyyy-MM-dd hh:mm:ss"/>"  pid="${stamp.pid}" date="">
								${status.index+1}
							</li>
						</c:forEach>
					</ul>
					<div>
						<pre>${stamptypeRegister.remark}</pre>
					</div>	
				</c:forEach>	
			</div><!-- stamp_box -->
		</sec:authorize>
		<c:if test="${!empty franchiserImageList}">
			<ul id="gallery">
				<h3>사 진</h3>
				<c:forEach var="franchiserImage" items="${franchiserImageList}"  >
					<li><a href="<c:url value="/resources/images/${franchiserImage.fileName}" />"><img src="<c:url value="/resources/images/${franchiserImage.fileName}" />" alt="" /></a></li>
				</c:forEach>
			</ul><!-- gallery -->
		</c:if>	
				
		
		<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
			<div id="commentWriteBox">
			<h3>리 뷰</h3>
				<form action="<c:url value="/place/newPlaceReview" />" method="post">
					<input type="hidden" name="fid" value="${franchiserMember.fid}"/>
					<fieldset>
					<legend></legend>
					<dl>
						<dt>
							<textarea name="comment"></textarea>
						</dt>						
						<dd>
							<button type="submit" class="large blue awesome"  >등록</button>
						</dd>							
					</dl>
					</fieldset>
				</form>
			</div><!-- commentWriteBox -->	
		</sec:authorize>
		
		<c:if test="${!empty placeCommentList}">
			<div id="comments">
				<h3>리뷰(${franchiserMember.commentCount} )</h3>
				<ol>
				<c:forEach var="placeComment" items="${placeCommentList}" >
  					
  					 <li class="commentList">
  						<div class="commentBody">
  							<img src="${placeComment.profileImageUrl}" width="50" height="50">
  							<span>${placeComment.name}</span>
  							<span>${placeComment.writeDate}</span>
  							<p>${placeComment.comment}</p>
  							<p>${placeComment.email}</p>
  							<sec:authentication property="name" var="currentUserName"/>
							<c:if test="${currentUserName == placeComment.email}">
							 삭제버튼
							</c:if>
  						</div>
  					</li>
  				</c:forEach>	
				</ol>
			</div><!--  comments -->
		</c:if>		
		</div><!-- contents -->			
	</div>
	<footer></footer>
	<img src="<c:url value="/resources/images/" />${franchiserMember.qrcode}" width="100" height="100" alt="qr코드" >
</div>
