<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
a{font-family: "돋옴, 돋움"; font-size: 12px; font-weight:bold;}
</style>
<script>
</script>
<!-- Start Header -->
			<header id="header">
				<div class="header">
					<a class="logo" href="index.html"><img src="/resources/oakland/images/logo.png" alt="" /></a>
					<nav>
						<ul id="navigation">
							<li class="current_page_item"><a href="index.html">Home</a></li>
							<li><a href="/feature">서비스소개</a></li>
							<li><a href="portfolio_sort_four.html">나의스템프</a>
								<ul>
									<li><a href="/user/register">회원가입</a></li>
									<li><a href="shortcodes.html">스템프확인</a></li>
									<li><a href="typography.html">즐겨찾기(북마크)</a></li>
								</ul>
							</li>
							<li><a href="blog_sidebar.html">가맹점검색</a>
								<ul>
									<li><a href="blog_sidebar.html">주변검색</a></li>
									<li><a href="blog_fwidth.html">지도검색</a></li>
									<li><a href="openpost.html">이벤트</a></li>
								</ul>
							</li>
							<li><a href="blog_sidebar.html">도움말</a>
								<ul>
									<li><a href="blog_sidebar.html">FAQ</a></li>
									<li><a href="blog_fwidth.html">1:1 문의</a></li>
									<li><a href="openpost.html">나의 문의 현황</a></li>
								</ul>
							</li>
						</ul>
						<select class="resp_navigation">
							<option value="" selected="selected"> - - Navigate to... - - </option>
							<option value="index.html" class="main_item">Home</option>
							<option value="shortcodes.html" class="main_item">Features</option>
							<option value="shortcodes.html"> - - Shortcodes</option>
							<option value="buttons.html"> - - - - Buttons</option>
							<option value="boxes.html"> - - - - Information Boxes</option>
							<option value="columns.html"> - - - - Columns</option>
							<option value="tooltips.html"> - - - - Tooltips</option>
							<option value="tabs.html"> - - - - Tabs &amp; Toggle</option>
							<option value="shortcodes.html"> - - - - Post Type Shortcoder</option>
							<option value="media.html"> - - - - Media</option>
							<option value="player.html"> - - - - Audio &amp; Video Players</option>
							<option value="lightboxes.html"> - - - - Lightboxes</option>
							<option value="tour.html"> - - - - Site Tour</option>
							<option value="googlemaps.html"> - - - - Google Maps</option>
							<option value="typography.html"> - - Typography</option>
							<option value="about.html"> - - Layouts</option>
							<option value="about.html"> - - - - About</option>
							<option value="services.html"> - - - - Services</option>
							<option value="error.html"> - - - - 404 Error Page</option>
							<option value="sitemap.html"> - - - - Sitemap</option>
							<option value="lsidebar.html"> - - - - Left Sidebar</option>
							<option value="rsidebar.html"> - - - - Right Sidebar</option>
							<option value="heading.html"> - - Heading Templates</option>
							<option value="heading.html"> - - - - Standard Heading</option>
							<option value="heading_icon.html"> - - - - With Icon</option>
							<option value="heading_text.html"> - - - - With Text</option>
							<option value="heading_icon_text.html"> - - - - Heading With Icon &amp; Text</option>
							<option value="portfolio_one.html" class="main_item">Portfolio</option>
							<option value="portfolio_sort_four.html"> - - Sortable Portfolio</option>
							<option value="portfolio_sort_one.html"> - - - - One Column</option>
							<option value="portfolio_sort_two.html"> - - - - Two Columns</option>
							<option value="portfolio_sort_three.html"> - - - - Three Columns</option>
							<option value="portfolio_sort_four.html"> - - - - Four Columns</option>
							<option value="portfolio_four.html"> - - Portfolio Without Sorting</option>
							<option value="portfolio_one.html"> - - - - One Column</option>
							<option value="portfolio_two.html"> - - - - Two Columns</option>
							<option value="portfolio_three.html"> - - - - Three Columns</option>
							<option value="portfolio_four.html"> - - - - Four Columns</option>
							<option value="project_slider.html"> - - Project Layouts</option>
							<option value="project_slider.html"> - - - - Image Slider</option>
							<option value="project_video.html"> - - - - Video</option>
							<option value="album_four.html"> - - - - Album Four Columns</option>
							<option value="album_three.html"> - - - - Album Three Columns</option>
							<option value="album_two.html"> - - - - Album Two Columns</option>
							<option value="album_one.html"> - - - - Album One Column</option>
							<option value="blog_sidebar.html" class="main_item">Blog</option>
							<option value="blog_sidebar.html"> - - Blog With Sidebar</option>
							<option value="blog_fwidth.html"> - - Full Width Blog</option>
							<option value="openpost.html"> - - Blog Post</option>
							<option value="contacts.html" class="main_item">Contacts</option>
						</select>
					</nav>
				</div>
			</header>
<!--Finish Header -->