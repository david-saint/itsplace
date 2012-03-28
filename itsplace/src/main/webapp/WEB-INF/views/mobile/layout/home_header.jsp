
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<style type="text/css">

</style>
<div id="mHeader" data-role="header"  >
	<!--
	<div id="top_header">
		
				
		 <a href="<c:url value="/logout" />" data-role="button">LogOut</a> 	
	</div>
	-->
	<a data-role="button" data-icon="back" data-rel="back"  data-iconpos="notext" title="back"></a>	
	<h1>
			<a href="<c:url value="/" />" data-role="none;" data-ajax="false" rel="external">Its Place</a>
	</h1>
	<div id="profile" class="ui-btn-right">
		<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">		
			<img src="${USERSESSION.profileImageUrl}" width=20 height=20 />
			<!-- <span>${USERSESSION.name} </span> -->
		</sec:authorize>
	</div>
</div>