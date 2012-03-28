<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>

 

<style type="text/css">
.ui-li .ui-btn-inner a.ui-link-inherit, .ui-li-static.ui-li {
	
	padding: 0px 0px 0px 70px;
	padding-top:1px;
}
.placeComment-date{
	float:right;
	margin-right:10em; 
	margin-top:3px;
}
</style>

<script type="text/javascript">

$(document).ready(function(){
	if($('#gallery').has('li').length>0){
		//var myPhotoSwipe = $("#gallery a").photoSwipe({ enableMouseWheel: false , enableKeyboard: false });
	}
	
	 
/* 안드로이드에서 작동 안됨, 웹은 작동(jquery.touch-gallery-1.0.0.js)*/
	//$('#gallery a').touchGallery();
 
	//$('.stamp_column').each(function(i){
		//this.bind
		//var url =  common_getHostUrl()+"/partner/flocation/lat/"+lat+"/lot/"+lot+"/?";
	//});
	//$.mobile.changePage( common_getHostUrl()+"/place/placeView/2", { transition: "slideup"} );
	//$('#test').trigger('click');
	///stamp/stampByUser
	
	
	
});

function stamp_popup(obj){
	var fid = '${franchiserMember.fid}';
	var stampType ='${stamptypeRegister.stamptype}';
	var fname = '${franchiserMember.fname}';
	var event ="";
	if($(obj).hasClass('stampped') || $(obj).hasClass('stamppedEvent') ){
		return false;
		
	}
	if($(obj).hasClass('eventday')){
		event = $.trim($(obj).text());	
		//이벤트와 등록을 동시에 가능함
		log.info("이벤트:"+event);
	}else{
		event = "none"; 
	}
	
	var url =  common_getHostUrl()+"/stamp/stampByUser/"+fid+"/"+stampType+"/"+event+"/"+fname;
	log.info(url);
	//$.mobile.changePage(url);
	$('#test').attr("href",url);
	$('#test').trigger('click');
}
</script>

<div id="content" >

	<a id="test" style="display:none;" href="<c:url value="/stamp/stampByUser" />${franchiserMember.fid}" data-rel="dialog" data-transition="pop"></a> 
	
	<article>
			<a class="placeName" href="<c:url value="/place/placeView/${franchiserMember.fid}" />">
				${franchiserMember.fname} 
			</a>
			<span style="font-size: 12px">${franchiserMember.commentCount}리뷰</span>
		
			
		<figure>
            <a href="#">
            	<c:if test=""></c:if>
            	<img class="placeImage" src="<c:url value="/resources/images/" />${franchiserMember.fileName}"" alt="Post thumbnail">
            </a>
        </figure>
		<ul>
          	<li id="address">
           		<dl>
					<dt></dt>
					<dd>${franchiserMember.address.sido} ${franchiserMember.address.gugun} ${franchiserMember.address.doroname} ${franchiserMember.address.bldmain}-${franchiserMember.address.bldsubmain}</dd>
				</dl>
          	</li>
          	<li id="phone">
          		<dl>
					<dt></dt>
					<dd>${franchiserMember.phone1}</dd>
				</dl>
			</li>
          	<li id="homepage">
          		<dl>
					<dt></dt>
					<dd>http://</dd>
				</dl>
          	</li>
          	</ul>
	</article>
	
	<div id="social">
		<h3>SNS</h3>
		<img class="kakaotalk" onclick="common_kakao('Its Place !!' ,'<c:url value="/place/placeView/${franchiserMember.fid}" />')" src="<c:url value="/resources/openapi/kakaotalk/kakaotalkicon_3535.png" />" alt="카카오톡 보내기">
	</div>
	
	<h3>스탬프</h3>
	<div class="stamp_box">
		<c:forEach var="stamppedList" items="${stamppedListAll}"  >
			<ul>
				<c:forEach var="stamp" items="${stamppedList}" varStatus ="status">
					<li id="${stamp.pid}" onclick="stamp_popup(this)" class="stamp_column ${stamp.eventday}"  title="<fmt:formatDate value="${stamp.inpdate}" pattern="yyyy-MM-dd hh:mm:ss"/>">
							<p>${status.index+1}
							
							</p>
					</li>
				</c:forEach>
			</ul>
		</c:forEach>	
		<div class="remark">
			<pre>${stamptypeRegister.remark}</pre>
		</div>	
	</div><!-- stamp_box -->
		<!--
	<div id="gallery">
		<c:forEach var="franchiserImage" items="${franchiserImageList}"  >
		<a href="<c:url value="/resources/images/${franchiserImage.fileName}" />"  >
			<img src="<c:url value="/resources/images/${franchiserImage.fileName}" />"  data-large="<c:url value="/resources/images/${franchiserImage.fileName}" />" />
		</a>	
		</c:forEach>
	</div>	
	-->
	<h3>사  진</h3>
	<ul id="gallery">
	<c:forEach var="franchiserImage" items="${franchiserImageList}"  >
		<li><a href="<c:url value="/resources/images/${franchiserImage.fileName}" />" rel="external"><img src="<c:url value="/resources/images/${franchiserImage.fileName}" />" alt="Image 01" /></a></li>
	</c:forEach>
	</ul>
</div><!-- content -->		
	<div id="review" >
		<h3 style="padding:10px;">리 뷰
		<c:if test="${franchiserMember.commentCount != '0'}">
			${franchiserMember.commentCount}  	
		</c:if>
		</h3>
			<ul data-role="listview" data-split-icon="delete" data-split-theme="d">
				<c:forEach var="placeComment" items="${placeCommentList}" >
  					 <li class="commentList">
  					 <a>
				 	 	<img src="${placeComment.profileImageUrl}" width="50" height="50">
  						
  							<p class="placeComment-date">${placeComment.writeDate}</p>
  							<p style="font-size:13px;margin-top:3px">${placeComment.name}</p>
  							<p><pre>${placeComment.comment}</pre></p>
  							<p>${placeComment.email}</p>
  							<sec:authentication property="name" var="currentUserName"/>
							
  					</a>
  					<c:if test="${currentUserName == placeComment.email}">
						<a href="<c:url value="/place/placeView/${franchiserMember.fid}" />"  data-transition="slideup" title="삭제"></a>
					</c:if>
  						
  					</li>
  				</c:forEach>	
				</ul>
	</div>
	<a href="<c:url value="/partner/location/${franchiserMember.fid}" />" rel="external" data-role="button" >위치정보 등록</a>
