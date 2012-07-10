<%@ page language="java"   contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"  %>

<!--Start Middle -->
<div class="container">
	<section id="middle">
		<div class="middle_inner">
<!-- Start Top -->
			<section id="top">
				<ul id="slider" class="responsiveSlider">
					<li>
						<img src="/resources/images/franchise/main_1.jpg" alt="" class="slide-image" />
						<div class="slideCaption">
							<div class="slideCaptionInner">
								<h1>Be Smart!</h1>
								<h2 class="fl">with&nbsp;</h2>
								<h5 style="font-size:36px; line-height:36px;">Oakland</h5>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet mauris est. Ut scelerisque, massa et imperdiet ornare, enim ipsum molestie libero</p>
								<a href="#" class="button"><span>Read more</span></a>
							</div>
						</div>
					</li>
					<li>
						<img src="/resources/images/franchise/main_2.jpg" alt="" class="slide-image" />
						<div class="slideCaption">
							<div class="slideCaptionInner">
								<h1>Be Happy!</h1>
								<h2 class="fl">with&nbsp;</h2>
								<h5 style="font-size:36px; line-height:36px;">Oakland</h5>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet mauris est. Ut scelerisque, massa et imperdiet ornare, enim ipsum molestie libero</p>
								<a href="#" class="button"><span>Read more</span></a>
							</div>
						</div>
					</li>
					<li>
						<img src="/resources/images/franchise/main_3.jpg" alt="" class="slide-image" />
						<div class="slideCaption">
							<div class="slideCaptionInner">
								<h1>Be Wise!</h1>
								<h2 class="fl">with&nbsp;</h2>
								<h5 style="font-size:36px; line-height:36px;">Oakland</h5>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet mauris est. Ut scelerisque, massa et imperdiet ornare, enim ipsum molestie libero</p>
								<a href="#" class="button"><span>Read more</span></a>
							</div>
						</div>
					</li>
					<li>
						<img src="/resources/images/franchise/main_4.jpg" alt="" class="slide-image" />
						<div class="slideCaption">
							<div class="slideCaptionInner">
								<h1>Be adfasdfadfasd!</h1>
								<h2 class="fl">with&nbsp;</h2>
								<h5 style="font-size:36px; line-height:36px;">Oakland</h5>
								<p>asdfasdfasdfasdfasdfsafd</p>
								<a href="#" class="button"><span>Read more</span></a>
							</div>
						</div>
					</li>
				</ul>
			</section>
<!-- Finish Top -->

<script type="text/javascript">
function view(d)
{
	if(d == "open")
	{
		$("#main_open").hide();
		$("#main_close").show();
		$("#main_display_none").slideDown("slow");	
	}else{
		$("#main_open").show();
		$("#main_close").hide();
		$("#main_display_none").slideUp("slow");
	}
	
}
function main_event(d)
{
	if(d == "open")
	{
		$("#event_open").hide();
		$("#event_close").show();
		$("#main_event_none").slideDown("slow");	
	}else{
		$("#event_open").show();
		$("#event_close").hide();
		$("#main_event_none").slideUp("slow");
	}
	
}
</script>
<!--Start Top Sidebar -->
			<section id="top_sidebar">
				<div class="one_first">
					<h1 class="main_text">가맹점</h1>
				</div>
				<div id="main_display">
					<div>
					<c:forEach var="place" items="${recentPlaceList}">
						<div class="one_fourth">
							<figure class="preloader">
								<img src="${place.imageHost}${place.fileName }" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>${place.fname }</h6>
							<p>${place.category} <br /> ${place.fullAddress }</p> 
						</div>
					</c:forEach>	
					</div>										
				</div>
				
			</section>
			<!--  Finish Top Sidebar -->
			<div class="top_sidebar_divider"></div>
			
			
<!-- Start Content -->
 			<section id="middle_content">
				<div class="entry">
					<div class="two_third">
						<h3>Fantastic features</h3>
						<div class="tab">
							<ul class="tabs">
								<li><a href="#">Feature One</a></li>
								<li><a href="#">Feature Two</a></li>
								<li><a href="#">Feature Three</a></li>
							</ul>
							<div class="tab_content">
								<div class="tabs_tab">
									<figure class="alignleft">
										<img src="/resources/oakland/images/img/tab_1.jpg" alt="" />
									</figure>
									<h6>Unlimited possibilities</h6>
									<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque, risus leo lacinia sapien, sed cursus massa risus eget arcu. Sed ac porta felis. Vivamus dignissim varius augue ut tempor. </p>
									<a href="#" class="button"><span>Read More</span></a>
								</div>
								<div class="tabs_tab" style="display:none;">
									<figure class="alignright">
										<img src="/resources/oakland/images/img/tab_1.jpg" alt="" />
									</figure>
									<h6>Unlimited possibilities</h6>
									<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque, risus leo lacinia sapien, sed cursus massa risus eget arcu. Sed ac porta felis. Vivamus dignissim varius augue ut tempor. </p>
									<a href="#" class="button"><span>Read More</span></a>
								</div>
								<div class="tabs_tab" style="display:none;">
									<figure class="alignleft">
										<img src="/resources/oakland/images/img/tab_1.jpg" alt="" />
									</figure>
									<h6>Unlimited possibilities</h6>
									<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque, risus leo lacinia sapien, sed cursus massa risus eget arcu. Sed ac porta felis. Vivamus dignissim varius augue ut tempor. </p>
									<a href="#" class="button"><span>Read More</span></a>
								</div>
							</div>
						</div>
					</div>
					<div class="one_third last">
						<h3>Latest news</h3>
						<ul class="latest_news">
						<c:forEach var="event" items="${recentEventList}">
							<li>
								<a href="#" class="alignleft">
									<figure>
										<img src="/resources/oakland/images/img/news_1.jpg" alt="" />
									</figure>
								</a>
								<abbr title=""  class="published"><fmt:formatDate value="${event.startDate}" pattern="yyyy-MM-dd"/> - <fmt:formatDate value="${event.endDate}" pattern="yyyy-MM-dd"/></abbr>
								<a href="#">${event.title}</a>
								<p>${event.place.fullAddress}</p>
								<p>${event.content}</p>
							</li>
						</c:forEach>
						</ul>
					</div>
				</div>
			</section>
		</div>
	</section> 
<!--Finish Content -->	
		</div><!--  middle inner -->





<!-- Finish Middle -->


<!--  Start Bottom -->
 
<!-- Finish Bottom -->
<script type="text/javascript">
 
	
	
</script>
