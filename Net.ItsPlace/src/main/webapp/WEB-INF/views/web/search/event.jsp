<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
menuSelected("이벤트");
$(document).ready(function() {
	 $.ajax({
	        type: 'GET'
	        , async: false
	        , url: "/search/event/list"
	       // , dataType: 'json'		  
	        , data: ({currentPage : "1",pageSize : "10",pageGroupSize : "10"}) 
	        , success: function(data) {
	        	console.log(data);
	        	
	        	$.each(data.result, function(i){
	        		console.log(this.title);
	        		console.log(this.editDate);
	        		console.log(this.content);
	        	});  
	        	
	        	$('#paging').append(data.paging);
	        }
	 		, error: function(data, status, err) {
	 			alert(data+err+status);
	 		}
	 		, complete: function() {
	 		
	 		}
	 });
});
</script>
<!-- __________________________________________________ Start Middle -->
			<div class="container">
				<section id="middle">
					<div class="middle_inner">
						<div class="headline">
							<h3>One Column</h3>
						</div>
						<div class="cont_nav">
							<a href="index.html">Home</a>&nbsp; /&nbsp;<a href="portfolio_sort_four.html">Portfolio</a>&nbsp; /&nbsp;<a href="portfolio_four.html">Portfolio Without Sorting</a>&nbsp; /&nbsp;One Column
						</div>

<!-- __________________________________________________ Start Content -->
						<section id="middle_content">
							<div class="entry">
								<section class="portfolio_container one_block">
									<div class="fl">
										<article class="portfolio">
											<figure class="fl fullwidth">
												<a href="album_four.html" class="preloader"><img src="/resources/images/img/fproject_1_1.jpg" alt="" class="fullwidth" /></a>
											</figure>
											<div class="port_text">
												<header class="entry-header">
													<h3 class="entry-title"><a href="album_four.html">Gamer</a></h3>
												</header>
												<footer class="post_category entry-meta"><a href="#">Advertising</a></footer>
												<div class="entry-content">
													<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque risus leo lacinia sapien, sed cursus massa risus</p>
												</div>
												<a href="album_four.html" class="button"><span>View project</span></a>
											</div>
										</article>
									</div>
									<div class="fl">
										<article class="portfolio">
											<figure class="fl fullwidth">
												<a href="album_four.html" class="preloader"><img src="/resources/images/img/fproject_1_2.jpg" alt="" class="fullwidth" /></a>
											</figure>
											<div class="port_text">
												<header class="entry-header">
													<h3 class="entry-title"><a href="album_four.html">Funny creatures</a></h3>
												</header>
												<footer class="post_category entry-meta"><a href="#">Artworks</a></footer>
												<div class="entry-content">
													<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque risus leo lacinia sapien, sed cursus massa risus</p>
												</div>
											</div>
										</article>
									</div>
									<div class="fl">
										<article class="portfolio">
											<figure class="fl fullwidth">
												<a href="album_four.html" class="preloader"><img src="/resources/images/img/fproject_1_3.jpg" alt="" class="fullwidth" /></a>
											</figure>
											<div class="port_text">
												<header class="entry-header">
													<h3 class="entry-title"><a href="album_four.html">These eyes</a></h3>
												</header>
												<footer class="post_category entry-meta"><a href="#">Web design</a></footer>
												<div class="entry-content">
													<p>Duis semper nunc id metus sagittis nec ullamcorper justo consequat. Curabitur pharetra condimentum mattis. Cras venenatis, eros id congue pellentesque risus leo lacinia sapien, sed cursus massa risus</p>
												</div>
											</div>
										</article>
									</div>
									<div class="cl"></div>
								</section>
							</div>
						</section>
<!-- __________________________________________________ Finish Content -->	


					</div>
				</section>
<!-- __________________________________________________ Finish Middle -->


			</div>
<div id="paging"></div>