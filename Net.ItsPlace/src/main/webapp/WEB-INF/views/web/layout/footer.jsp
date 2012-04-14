<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<footer id="footer">
	<div class="footer_inner">
		<div class="fr">
			<p class="fl">Find us elsewhere</p>
			<ul class="social_list fr">
				<li><a href="#" class="link_tooltip" title="Facebook"><img src="<c:url value="/resources/oakland/images/socicons/facebook.png" />" alt="" /></a></li>
				<li><a href="#" class="link_tooltip" title="Flickr"><img src="<c:url value="/resources/oakland/images/socicons/flickr.png" />" alt="" /></a></li>
				<li><a href="#" class="link_tooltip" title="Twitter"><img src="<c:url value="/resources/oakland/images/socicons/twitter.png" />" alt="" /></a></li>
				<li><a href="#" class="link_tooltip" title="Last fm"><img src="<c:url value="/resources/oakland/images/socicons/lastfm.png" />" alt="" /></a></li>
				<li><a href="#" class="link_tooltip" title="Vimeo"><img src="<c:url value="/resources/oakland/images/socicons/vimeo.png" />" alt="" /></a></li>
				<li><a href="#" class="link_tooltip" title="Google+"><img src="<c:url value="/resources/oakland/images/socicons/googleplus.png" />" alt="" /></a></li>
			</ul>
		</div>
		<span>ItsPlace &copy; 2012 All rights reserved</span>
		<div class="cl"></div>
	</div>
</footer>
<script type="text/javascript">
jQuery(document).ready(function () { 
	var w = 1220, 
		h = 500;
	
	if (jQuery(window).width() >= 1006 && jQuery(window).width() < 1348) { 
		w = 980;
		h = 400;
	} else if (jQuery(window).width() >= 740 && jQuery(window).width() < 1006) { 
		w = 740;
		h = 300;
	} else if (jQuery(window).width() >= 462 && jQuery(window).width() < 740) { 
		w = 460;
		h = 185;
	} else if (jQuery(window).width() < 462) { 
		w = 300;
		h = 120;
	}
	
	jQuery('#slider').cmsmsResponsiveSlider( { 
		sliderWidth : w, 
		sliderHeight : h, 
		animationSpeed : 500, 
		animationEffect : 'fade', 
		animationEasing : 'easeInOutExpo', 
		pauseTime : 5000, 
		activeSlide : 1, 
		buttonControls : true, 
		touchControls : true, 
		pauseOnHover : false, 
		showCaptions : true, 
		arrowNavigation : false, 
		arrowNavigationHover : false, 
		slidesNavigation : true, 
		slidesNavigationHover : false, 
		showTimer : true, 
		timerHover : false 
	} );
} );
</script>
<!--  <footer class="footer">
	<div  style="float:left">
		<p>Site: 
		<c:if test="${currentSitePreference.mobile}">
			<a href="?site_preference=normal">Normal</a> | Mobile
		</c:if>
		<c:if test="${!currentSitePreference.mobile}">
			 Normal | <a href="?site_preference=mobile">Mobile</a>
		</c:if>
		</p>
		<p>
			<small data-role="none">© 2011 Its place - new media agency </small>
		</p>
	</div>		
</footer>
-->