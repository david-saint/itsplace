<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<!-- Start Header -->
			<header id="header">
				<div class="header">
					<a class="logo" href="index.html"><img src="images/logo.png" alt="" /></a>
					<nav>
						<ul id="navigation">
							<li class="current_page_item"><a href="index.html">Home</a></li>
							<li><a href="shortcodes.html">Features</a>
								<ul>
									<li><a href="shortcodes.html">Shortcodes</a>
										<ul>
											<li><a href="buttons.html">Buttons</a></li>
											<li><a href="boxes.html">Information Boxes</a></li>
											<li><a href="columns.html">Columns</a></li>
											<li><a href="tooltips.html">Tooltips</a></li>
											<li><a href="tabs.html">Tabs &amp; Toggle</a></li>
											<li><a href="shortcodes.html">Post Type Shortcode</a></li>
											<li><a href="media.html">Media</a></li>
											<li><a href="player.html">Audio &amp; Video Players</a></li>
											<li><a href="lightboxes.html">Lightboxes</a></li>
											<li><a href="tour.html">Site Tour</a></li>
											<li><a href="googlemaps.html">Google Maps</a></li>
										</ul>
									</li>
									<li><a href="typography.html">Typography</a></li>
									<li><a href="about.html">Layouts</a>
										<ul>
											<li><a href="about.html">About</a></li>
											<li><a href="services.html">Services</a></li>
											<li><a href="error.html">404 Error Page</a></li>
											<li><a href="sitemap.html">Sitemap</a></li>
											<li><a href="lsidebar.html">Left Sidebar</a></li>
											<li><a href="rsidebar.html">Right Sidebar</a></li>
										</ul>
									</li>
									<li><a href="heading.html">Heading Templates</a>
										<ul>
											<li><a href="heading.html">Standard Heading</a></li>
											<li><a href="heading_icon.html">With Icon</a></li>
											<li><a href="heading_text.html">With Text</a></li>
											<li><a href="heading_icon_text.html">Heading With Icon &amp; Text</a></li>
										</ul>
									</li>
								</ul>
							</li>
							<li><a href="portfolio_sort_four.html">Portfolio</a>
								<ul>
									<li><a href="portfolio_sort_four.html">Sortable Portfolio</a>
										<ul>
											<li><a href="portfolio_sort_one.html">One Column</a></li>
											<li><a href="portfolio_sort_two.html">Two Columns</a></li>
											<li><a href="portfolio_sort_three.html">Three Columns</a></li>
											<li><a href="portfolio_sort_four.html">Four Columns</a></li>
										</ul>
									</li>
									<li><a href="portfolio_four.html">Portfolio Without Sorting</a>
										<ul>
											<li><a href="portfolio_one.html">One Column</a></li>
											<li><a href="portfolio_two.html">Two Columns</a></li>
											<li><a href="portfolio_three.html">Three Columns</a></li>
											<li><a href="portfolio_four.html">Four Columns</a></li>
										</ul>
									</li>
									<li><a href="project_slider.html">Project Layouts</a>
										<ul>
											<li><a href="project_slider.html">Image Slider</a></li>
											<li><a href="project_video.html">Video</a></li>
											<li><a href="album_four.html">Album Four Columns</a></li>
											<li><a href="album_three.html">Album Three Columns</a></li>
											<li><a href="album_two.html">Album Two Columns</a></li>
											<li><a href="album_one.html">Album One Column</a></li>
										</ul>
									</li>
								</ul>
							</li>
							<li><a href="blog_sidebar.html">Blog</a>
								<ul>
									<li><a href="blog_sidebar.html">Blog With Sidebar</a></li>
									<li><a href="blog_fwidth.html">Full Width Blog</a></li>
									<li><a href="openpost.html">Blog Post</a></li>
								</ul>
							</li>
							<li><a href="contacts.html">Contacts</a></li>
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