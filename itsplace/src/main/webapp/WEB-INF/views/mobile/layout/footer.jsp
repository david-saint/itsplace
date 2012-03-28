<%@ page session="false" %>	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
	
	

	 <div id="console" style="color: white;background: black"></div>	
	 <div id="mFooter" data-role="footer"  data-position="fixed">
	 	<p>
	 		<small data-role="none">© 2011 Its place - new media agency </small>
	 	</p>
	 	<p>
	 		<small>
	 			<a href="${currentUrl}?site_preference=normal" data-role="none" rel="external">view original web siet </a>
	 		</small>
	 	</p>
	 
		<!-- <a href="${currentUrl}?site_preference=normal" rel="external">Normal</a><
 		<a href="${currentUrl}?site_preference=mobile" rel="external">Mobile</a>
 		<a data-rel="back" data-role="button" data-icon="back">Back</a>
		<a href="<c:url value="/" />" rel="external" data-role="button" data-icon="home">HOME</a>
		 -->	
	<!-- 하단 네비게이션 메뉴 
		<div id="footer" data-role="navbar">		
			<ul>
				<li><a id="footer_home"  href="<c:url value="/" />"    rel="external" data-icon="home" data-iconpos="top" >home</a></li>				
				<li><a id="footer_place" href="<c:url value="/place/search" />"   rel="external" data-icon="grid"  data-iconpos="top">Place</a></li>
				<li><a id="footer_map" href="<c:url value="/partner/franchiserList" />?searchWord="  rel="external" data-icon="star"  data-iconpos="top">Map</a></li>
				<li><a id="footer_myStamp" href="<c:url value="/stamp/myStamp" />"  data-icon="gear"  rel="external" data-iconpos="top">myStamp</a></li>
				<li><a href=""  data-icon="gear" data-rel="back" data-icon="back" data-iconpos="top">뒤로</a></li>
			</ul>
		</div>
	 -->	
	</div>
	
	
	


	
	
	
