<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<!DOCTYPE HTML PUBLIC>

<html>
<head>
	<title><tiles:insertAttribute name="title" /></title>
	<c:choose>
		<c:when test="${fn:contains(header['User-Agent'], 'chromeframe')}">
			<meta http-equiv="X-UA-Compatible" content="chrome=1">
			<script type="text/javascript">
				if (!window.ChromeFrame) {
					window.ChromeFrame = {};
				}
			</script>
		</c:when>
		<c:when test="${fn:contains(header['User-Agent'], 'MSIE')}">
			<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		</c:when>
	</c:choose>
	<spring:url value="/static-{versionNumber}/images/favicon.ico" var="url">
		<spring:param name="versionNumber" value="${versionNumber}" />
	</spring:url>
	<link rel="shortcut icon" href="${fn:escapeXml(url)}" type="image/x-icon" />
	<spring:url value="/static-{versionNumber}/dojo/dijit/themes/tundra/tundra.css" var="url">
		<spring:param name="versionNumber" value="${versionNumber}" />
	</spring:url>
	<link type="text/css" rel="stylesheet" href="${fn:escapeXml(url)}" />
	<spring:url value="/static-{versionNumber}/styles/style.css" var="url">
		<spring:param name="versionNumber" value="${versionNumber}" />
	</spring:url>
	<link type="text/css" rel="stylesheet" href="${fn:escapeXml(url)}" />
	<spring:url value="/static-{versionNumber}/dojo/dojo/dojo.js" var="url">
		<spring:param name="versionNumber" value="${versionNumber}" />
	</spring:url>
	<script type="text/javascript" src="${fn:escapeXml(url)}"></script>
	<spring:url value="/static-{versionNumber}/dojo/dijit/dijit.js" var="url">
		<spring:param name="versionNumber" value="${versionNumber}" />
	</spring:url>
	<script type="text/javascript" src="${fn:escapeXml(url)}"></script>
	<script type="text/javascript">
		dojo.require("insight.Insight");
		Insight = new insight.Insight('<spring:url value="/" javaScriptEscape="true" />');
		dojo.addOnLoad(function() { Insight.checkBrowserAbilities() });
		<c:if test="${not empty pageContext.request.userPrincipal}">
			<spring:url value="/logout" var="url" />
			Insight.scheduleLogout('${fn:escapeXml(url)}');
		</c:if>
	</script>
</head>
<body id="page" class="tundra">
		<tiles:insertAttribute name="header" />
		<noscript>
			<tiles:insertDefinition name="noscript" />
		</noscript>
		<div id="noscript" style="display: none;">
			<tiles:insertDefinition name="noscript" />
		</div>
		<tiles:insertDefinition name="errors" />
		<tiles:insertAttribute name="content" />
		<tiles:insertAttribute name="footer" />
</body>
</html>
