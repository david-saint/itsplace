<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div class="page-module">
	<div class="page-module-header">
		<h2 id="noscript-title">D'OH</h2>
		<ul class="controls">
			<spring:url value="/static-{versionNumber}/dojo/dijit/themes/tundra/images/tabClose.png" var="url">
				<spring:param name="versionNumber" value="${versionNumber}" />
			</spring:url>
			<li class="control"><a href="javascript:Insight.suppressWarning()" id="noscript-ignore" style="display: none; margin: 2px"><img src="${fn:escapeXml(url)}" alt="close" /></a></li>
		</ul>
	</div>
	<div class="page-module-body">
		<p id="noscript-warning">JavaScript support is required to use Spring Insight. Please enable JavaScript or upgrade your browser.</p>
		<div id="noscript-upgrade" style="margin-top: 1em;">
			<h2>Recommended Browsers</h2>
			<table width="80%" align="center" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" valign="top">
						<spring:url value="/static-{versionNumber}/images/browser_logo_chrome.png" var="url">
							<spring:param name="versionNumber" value="${versionNumber}" />
						</spring:url>
						<a href="http://www.google.com/chrome"><img src="${fn:escapeXml(url)}" alt="Chrome logo" /></a>
						<br /><a href="http://www.google.com/chrome">Google Chrome</a>
					</td>
					<td align="center" valign="top">
						<spring:url value="/static-{versionNumber}/images/browser_logo_safari.png" var="url">
							<spring:param name="versionNumber" value="${versionNumber}" />
						</spring:url>
						<a href="http://www.apple.com/safari/"><img src="${fn:escapeXml(url)}" alt="Safari logo" /></a>
						<br /><a href="http://www.apple.com/safari/">Safari 5</a>
						<br /><span class="example">...or later</span>
					</td>
					<td align="center" valign="top">
						<spring:url value="/static-{versionNumber}/images/browser_logo_firefox.png" var="url">
							<spring:param name="versionNumber" value="${versionNumber}" />
						</spring:url>
						<a href="http://www.getfirefox.com/"><img src="${fn:escapeXml(url)}" alt="Firefox logo" /></a>
						<br /><a href="http://www.getfirefox.com/">Mozilla Firefox 3.6</a>
						<br /><span class="example">...or later</span>
					</td>
					<td align="center" valign="top">
						<spring:url value="/static-{versionNumber}/images/browser_logo_sts.png" var="url">
							<spring:param name="versionNumber" value="${versionNumber}" />
						</spring:url>
						<a href="http://www.springsource.com/products/sts"><img src="${fn:escapeXml(url)}" alt="STS logo" /></a>
						<br /><a href="http://www.springsource.com/products/sts">SpringSource Tool Suite</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
