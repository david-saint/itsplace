<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>
<script type="text/javascript">
menuSelected("나의스탬프");
</script>
<div class="container">
	<section id="middle">
		<div class="middle_inner">
			<div class="headline">
				<h3>나의스탬프 - ${fname }</h3>
			</div>
			<div class="content_wrap">
				<section id="content">
					<div class="entry">
						<!-- <h2>Experience is our power</h2>
						<h5>25 years on market</h5>
						<figure class="alignleft">
							<img src="images/img/about_1.jpg" alt="">
						</figure>
						<p>Worem ipsum dolor sit amet, consec tetuer adipis cing elitraesent vestibulum molestie</p>
						<p>Aum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculusmus. Nulla dui. Fusce feugiat malesuada odio. Morbi nunc odio, gravida at, cursus nec luctus lorem aecenas tristique orci ac semuis ultricies pharetra</p>
						<a href="#" class="with_arrow fr">Read more about us</a>
						<div class="cl"></div>
						<br> -->
						
						<c:forEach var="stamppedList" items="${stamppedListAll}">
						<div 
							class="${stamppedList.placeStamp.theme}"
							stampid="${stamppedList.placeStamp.stampid}">
			
							<h5 class="stampTitle">${stamppedList.placeStamp.stampTitle}</h5>
							<span class="ableDate">
								유효기간:
							</span>
							<span>
								<fmt:formatDate value="${stamppedList.placeStamp.startDate}" pattern="yyyy-MM-dd" />
								~
								<fmt:formatDate value="${stamppedList.placeStamp.endDate}" pattern="yyyy-MM-dd" />
							</span>
			
							<ul style="display: block">
								<c:forEach var="stamp" items="${ stamppedList.stampList}" varStatus="status">
									<li class="${stamp.attribute}" pid="${stamp.pid}"
										saveDate="<fmt:formatDate value="${stamp.saveDate}" pattern="yyyy-MM-dd" />">
										${status.index+1}
										${stamp.status}
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="divider"></div>
					</c:forEach>
					</div>
				</section>
<!-- __________________________________________________ Finish Content -->


<!-- __________________________________________________ Start Sidebar -->
				<section id="sidebar">
					<div class="one_first">
						<aside class="widget widget_links">
							<h3 class="widgettitle">places list</h3>
							<ul>
								<c:forEach var="placeStamp" items="${placeStampedList}">
								<li><a href="/stamp/list/${placeStamp.fid}">${placeStamp.fname }</a></li>
								</c:forEach>
							</ul>
						</aside>
					</div>
				</section>
				<div class="cl"></div>
			</div>
		</div>
	</section>
</div>