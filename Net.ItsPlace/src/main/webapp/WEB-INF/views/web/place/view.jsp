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
</style>
<div class="container">
	<section id="middle">
		<div class="middle_inner">
			<div class="headline">
				<h3>가맹점검색</h3>
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
		</div>
	</section>
</div>
			