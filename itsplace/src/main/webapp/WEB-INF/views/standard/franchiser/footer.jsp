<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer>
	
			<span>view: 
				<c:if test="${currentSitePreference.mobile}">
					<a href="${currentUrl}?q=&site_preference=normal">Original</a> | Mobile
				</c:if>
			<c:if test="${!currentSitePreference.mobile}">
				 Original | <a href="${currentUrl}?q=&site_preference=mobile">Mobile</a>
			</c:if>
			</span>
			<span style="float:right;margin-right:0px;">
				<a href="http://ITSPLACE.COM/">
					<img src="<c:url value="/resources/images/BlogIcon.png" />" width="20" height="20" alt="Wordpress">
				</a>	
				<a href="http://itsplace.net/">
					<img src=<c:url value="/resources/images/FacebookIcon.png" /> width="20" height="20" alt="Facebook">
				</a>	
				<a href="http://itsplace.net">
					<img src="<c:url value="/resources/images/TwitterIcon.png" />" width="20" height="20" alt="Twitter">
				</a>
			</span>
			<span style="float:right;margin-right:50px;">
				<small data-role="none">© 2011 Its place - new media agency </small>
			</span>
			
		
</footer>