<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer class="footer">
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
	
	<div style="float:right">
		<a href="http://ITSPLACE.COM/">
			<img src="<c:url value="/resources/images/BlogIcon.png" />" width="32" height="32" alt="Blog">
		</a>	
		<a href="http://itsplace.net/">
			<img src=<c:url value="/resources/images/FacebookIcon.png" /> width="32" height="32" alt="Blog">
		</a>	
		<a href="http://itsplace.net">
			<img src="<c:url value="/resources/images/TwitterIcon.png" />" width="32" height="32" alt="Blog">
		</a>	
	</div>
</footer>