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
							<h3>이벤트</h3>
						</div>
						<div class="cont_nav">
							<a href="index.html">Home</a>&nbsp; /&nbsp;<a href="portfolio_sort_four.html">Portfolio</a>&nbsp; /&nbsp;<a href="portfolio_four.html">Portfolio Without Sorting</a>&nbsp; /&nbsp;One Column
						</div>

<!-- __________________________________________________ Start Content -->
						<section id="middle_content">
						<c:forEach var="event" items="${placeEventList}">
							
						
							<div class="entry">
								<section class="portfolio_container one_block">
									<div class="fl">
										<article class="portfolio">
											<figure class="fl fullwidth">
												<a href="album_four.html" ><img src="/resources/images/img/fproject_1_1.jpg" alt="" class="fullwidth" /></a>
											</figure>
											<div class="port_text">
												<header class="entry-header">
													<h3 class="entry-title"><a href="album_four.html">${event.title}</a></h3>
												</header>
												<footer class="post_category entry-meta"><a href="#">Advertising</a></footer>
												<div class="entry-content">
													<p>Duis semper nunc id metuque risus leo lacinia sapien, sed cursus majjj
													jjjj
													j
													jssa risuskkkkkkkkkkkkkkkkkkkkkkkkkkkkk</p>
												</div>
												<a href="album_four.html" class="button"><span>View project</span></a>
											</div>
										</article>
									</div>
									<div class="fl"></div>
								</section>
							</div>
							</c:forEach>
						</section>
						<ul class="pagination">
            	<li class="page">Page 2 of 10</li>
                <li><a href="#">«</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#" class="active">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">»</a></li>
                <li><a href="#">last</a></li>
            </ul>
<!-- __________________________________________________ Finish Content -->	


					</div>
				</section>
<!-- __________________________________________________ Finish Middle -->


			</div>
<div id="paging"></div>