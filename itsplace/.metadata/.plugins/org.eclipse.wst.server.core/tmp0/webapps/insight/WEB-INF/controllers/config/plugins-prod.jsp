<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="insight" uri="http://static.springsource.com/tags/insight" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div class="page-module">
	<div class="page-module-header">
		<h2>Collection Plug-ins</h2>
	</div>
	<div class="page-module-body table-body" style="overflow: auto;">
		<table class="zebra" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th>&nbsp;</th>
					<c:forEach items="${pluginNames}" var="name">
						<th>
							<c:if test="${not empty dashboardPluginSet[name] and not empty dashboardPluginSet[name].href}">
								<a href="${fn:escapeXml(dashboardPluginSet[name].href)}" target="_blank">${fn:escapeXml(name)}</a>
							</c:if>
							<c:if test="${empty dashboardPluginSet[name] or empty dashboardPluginSet[name].href}">
								${fn:escapeXml(name)}
							</c:if>
							<c:if test="${not empty outOfSyncPlugins[name]}">
								<strong style="color: red;">Out of Sync</strong>
							</c:if>
						</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${servers}" var="server">
					<c:set var="pluginSet" value="${plugins[server]}" />
					<tr>
						<td>
							<c:choose>
								<c:when test="${server.label ne 'dashboard'}">
									<spring:url value="/resources${'#'}resource={resourceKey}" var="url">
										<spring:param name="resourceKey" value="${server.key}" />
									</spring:url>
									<a href="${fn:escapeXml(url)}">${fn:escapeXml(server.label)}</a>
								</c:when>
								<c:otherwise>
									${fn:escapeXml(server.label)}
								</c:otherwise>
							</c:choose>
						</td>
						<c:forEach items="${pluginNames}" var="name">
							<td title="Published by ${fn:escapeXml(pluginSet[name].publisher)}">
								<c:if test="${not empty pluginSet[name]}">
									${fn:escapeXml(pluginSet[name].version)}
								</c:if>
								<c:if test="${empty pluginSet[name]}">
									<em class="example">--</em>
								</c:if>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
