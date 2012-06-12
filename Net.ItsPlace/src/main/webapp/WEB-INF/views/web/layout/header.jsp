<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
a{font-family: "돋옴, 돋움"; font-size: 12px; font-weight:bold;}
</style>
<script type="text/javascript">
function menuSelected(name, sub)
{
	var intCnt = 0;
	$(".current_page_item").removeClass("current_page_item");
	$("#navigation").find("a").each(function(){
		if($(this).text() == name || $(this).text() == sub)
		{
			$(this).parent().addClass("current_page_item");
			intCnt++;
			if(intCnt == 2) return false;
			//return false;
		}
	});
}
</script>
<!-- Start Header -->
			<header id="header">
				<div class="header">
					<h1><a id="ci" class="logo" href="/">Its Place</a></h1>
					<nav>
						<ul id="navigation">
							<li class="current_page_item"><a href="/">홈</a></li>
							<li><a href="/feature">서비스소개</a></li>
							<li><a href="/user/register">나의스탬프</a>
								<ul>
									<li><a href="/user/register">회원가입</a></li>
									<li><a href="/stamp/list">스템프확인</a></li>
									<li><a href="typography.html">즐겨찾기(북마크)</a></li>
								</ul>
							</li>
							<li><a href="/search/place">가맹점검색</a>
								<ul>
									<li><a href="/search/place">주변검색</a></li>
									<li><a href="/search/map">지도검색</a></li>
									<li><a href="/search/event">이벤트</a></li>
								</ul>
							</li>
							<li><a href="/support/help">도움말</a>
								<ul>
									<li><a href="/support/help">FAQ</a></li>
									<li><a href="/support/write">1:1 문의</a></li>
									<li><a href="/support/list">나의 문의 현황</a></li>
								</ul>
							</li>
						</ul>						
					</nav>
				</div>
			</header>
<!--Finish Header -->