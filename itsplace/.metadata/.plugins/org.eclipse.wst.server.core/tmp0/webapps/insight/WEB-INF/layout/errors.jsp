<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div class="page-module" id="errors" style="display: none;">
	<div class="page-module-header">
		<h2 id="noscript-title">Insight Server Error</h2>
		<ul class="controls">
			<spring:url value="/static-{versionNumber}/dojo/dijit/themes/tundra/images/tabClose.png" var="url">
				<spring:param name="versionNumber" value="${versionNumber}" />
			</spring:url>
			<li class="control"><a href="javascript:dijit.byId('errors').hide()" style="margin: 2px" title="close"><img src="${fn:escapeXml(url)}" alt="close" style="margin-top: 2px;" /></a></li>
		</ul>
	</div>
	<div class="page-module-body">
		<p>
			Unable to request data from Insight server.
			This does not indicate an issue with the monitored application, however, some of its data may be temporarily unavailable.
			If the problem persists after refreshing the page, please check that the Insight server is operating normally.
		</p>
		<div class="errorList">
			<table style="width: 100%">
			</table>
		</div>
	</div>
</div>

<script>
	dojo.addOnLoad(function(){
		dojo.require("insight.components.ErrorDialog");
		new insight.components.ErrorDialog({}, "errors");
	});
</script>