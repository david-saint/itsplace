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
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
				</div>
				<div><a href="javascript:view('open')" id="main_open">더보기</a><a href="javascript:view('close')" id="main_close" style="display:none">닫기</a></div>
				<div id="main_display_none" style="display:none;">
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
				</div>
				<div><a href="javascript:main_event('open')" id="event_open">더보기</a><a href="javascript:main_event('close')" id="event_close" style="display:none">닫기</a></div>
				<div id="main_event_none" style="display:none;">
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
					<div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
						<div class="one_fourth">
							<figure class="preloader">
								<img src="/resources/images/main/main_list_1.jpg" alt="" class="fullwidth" />
							</figure>
							<br />
							<h6>가맹점 1</h6>
							<p>커피 전문점 <br /> 대구광역시 달서구 여기동 1번지</p> 
						</div>
					</div>
				</div>
			</section>
			<div class="top_sidebar_divider"></div>
<!--  Finish Top Sidebar -->


<!-- Start Content -->
<%-- 			<section id="middle_content">
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
							<li>
								<a href="#" class="alignleft">
									<figure>
										<img src="/resources/oakland/images/img/news_1.jpg" alt="" />
									</figure>
								</a>
								<abbr title="01-04-2012" class="published">01-04-2012</abbr>
								<a href="#">Duis semper nunc metus</a>
								<p>sagittis nec ullamcorper justo consequat urabitur pharetra</p>
							</li>
							<li>
								<a href="#" class="alignleft">
									<figure>
										<img src="/resources/oakland/images/img/news_2.jpg" alt="" />
									</figure>
								</a>
								<abbr title="01-04-2012" class="published">01-04-2012</abbr>
								<a href="#">Duis semper nunc metus</a>
								<p>sagittis nec ullamcorper justo consequat urabitur pharetra</p>
							</li>
							<li>
								<a href="#" class="alignleft">
									<figure>
										<img src="/resources/oakland/images/img/news_3.jpg" alt="" />
									</figure>
								</a>
								<abbr title="01-04-2012" class="published">01-04-2012</abbr>
								<a href="#">Duis semper nunc metus</a>
								<p>sagittis nec ullamcorper justo consequat urabitur pharetra</p>
							</li>
						</ul>
					</div>
				</div>
			</section>
		</div>
	</section> --%>
<!--Finish Content -->	
<!-- Finish Middle -->


<!--  Start Bottom -->
<%-- 	<section id="bottom">
		<div class="bottom_inner">
			<div class="one_third">
				<aside class="widget widget_custom_portfolio_entries">
					<div class="widget_custom_portfolio_entries_container">
						<h3 class="widgettitle">Sliding article</h3>
						<ul class="widget_custom_portfolio_entries_slides responsiveContentSlider">
							<li>
								<figure>
									<img src="/resources/oakland/images/img/sliding_1.jpg" alt="" class="fullwidth" />
								</figure>
								<h6 class="project_title">Successful team</h6>
								<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque risus leo lacinia sapien, sed cursus massa risus eget</p>
							</li>
							<li>
								<figure>
									<img src="/resources/oakland/images/img/sliding_2.jpg" alt="" class="fullwidth" />
								</figure>
								<h6 class="project_title">Successful team</h6>
								<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque risus leo lacinia sapien, sed cursus massa risus eget</p>
							</li>
							<li>
								<figure>
									<img src="/resources/oakland/images/img/sliding_3.jpg" alt="" class="fullwidth" />
								</figure>
								<h6 class="project_title">Successful team</h6>
								<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque risus leo lacinia sapien, sed cursus massa risus eget</p>
							</li>
						</ul>
					</div>
				</aside>
			</div>
			<div class="one_third">
				<aside class="widget widget_custom_tweets_entries">
					<h3 class="widgettitle">Latest tweets</h3>
					<ul>
						<li>
							<abbr class="published">about 6 hours ago</abbr>
							<div class="tweet_content"><p>Duis semper nunc id metus sagittis nec ullamcorper furabitur pharetra condimentum mattis. </p></div>
						</li>
						<li>
							<abbr class="published">about 15 hours ago</abbr>
							<div class="tweet_content"><p>Duis semper nunc id metus sagittis nec ullamcorper <a href="#">toconsequat</a> curabitur pharetra condimentum mattis. </p></div>
						</li>
						<li>
							<abbr class="published">about 15 hours ago</abbr>
							<div class="tweet_content"><p>Duis <a href="#">onsequat</a> semper nunc id metus sagittis nec ullamcorper curabitur pharetra condimentum mattis.</p></div>
						</li>
					</ul>
				</aside>
			</div>
			<div class="one_third">
				<aside class="widget widget_custom_contact_form_entries">
					<h3 class="widgettitle">Quick form</h3>
					<div class="cmsms-form-builder">
						<div class="widgetinfo">Thank you! <br />Your message has been sent successfully.</div>
						<form action="#" method="post" id="form_contact_form_widget_001">
							<div class="form_info cmsms_input">
								<label for="field_002">Your Name<span class="color_3"> *</span></label>
								<input type="text" name="wname" id="field_002" size="22" tabindex="11" class="validate[required,minSize[3],maxSize[100],custom[onlyLetterSp]]" />
							</div>
							<div class="form_info cmsms_input">
								<label for="field_003">Email<span class="color_3"> *</span></label>
								<input type="text" name="wemail" id="field_003" size="22" tabindex="12" class="validate[required,custom[email]]" />
							</div>
							<div class="form_info cmsms_textarea">
								<label for="field_004">Message<span class="color_3"> *</span></label>
								<textarea name="wmessage" id="field_004" cols="28" rows="6" tabindex="13" class="validate[required,minSize[3]]"></textarea>
							</div>
							<div class="loading"></div>
							<div><input type="hidden" name="contact_form_widget_001_wurl" id="contact_form_widget_001_wurl" value="http://oakland-html.cmsmasters.net/php/sendmail.php" /></div><!-- Here you need to set the path to the sendmail file -->
							<div><a href="#" id="contact_form_widget_001_wformsend" class="button" tabindex="14"><span>Send Message</span></a></div>
						</form>
					</div>
				</aside>
			</div>
		</div>
	</section> --%>
<!-- Finish Bottom -->
<script type="text/javascript">
/* 	jQuery(document).ready(function () { 
		jQuery('.widget_custom_portfolio_entries_slides').cmsmsResponsiveContentSlider( { 
			sliderWidth : '100%', 
			sliderHeight : 'auto', 
			animationSpeed : 500, 
			animationEffect : 'slide', 
			animationEasing : 'easeInOutExpo', 
			pauseTime : 5000, 
			activeSlide : 1, 
			touchControls : true, 
			pauseOnHover : false, 
			arrowNavigation : true, 
			slidesNavigation : false 
		} );
	} );
	
	jQuery(document).ready(function () { 
		jQuery('#form_contact_form_widget_001').validationEngine('init');
		
		jQuery('#form_contact_form_widget_001 a#contact_form_widget_001_wformsend').click(function () { 
			var form_builder_url = jQuery('#contact_form_widget_001_wurl').val();
			
			jQuery('#form_contact_form_widget_001 .loading').animate( {
				opacity : 1
			} , 250);
			
			if (jQuery('#form_contact_form_widget_001').validationEngine('validate')) { 
				jQuery.post(form_builder_url, { 
					field_002 : jQuery('#field_002').val(), 
					field_003 : jQuery('#field_003').val(), 
					field_004 : jQuery('#field_004').val(), 
					formname : 'contact_form_widget_001', 
					formtype : 'widget' 
				}, function () { 
					jQuery('#form_contact_form_widget_001 .loading').animate( { opacity : 0 }, 250);
					document.getElementById('form_contact_form_widget_001').reset();
					jQuery('#form_contact_form_widget_001').parent().find('.widgetinfo').hide();
					jQuery('#form_contact_form_widget_001').parent().find('.widgetinfo').fadeIn('fast');
					jQuery('html, body').animate( { scrollTop : (jQuery('#form_contact_form_widget_001').offset().top - 100) }, 'slow');
					jQuery('#form_contact_form_widget_001').parent().find('.widgetinfo').delay(5000).fadeOut(1000);
				} );
				
				return false;
			} else { 
				jQuery('#form_contact_form_widget_001 .loading').animate( { opacity : 0 }, 250);
				
				return false;
			}
		} ); */
	} );	
</script>
</div>