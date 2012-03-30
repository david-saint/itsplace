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
	<div class="page-module-body table-body">
		<table class="zebra" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="sort-field sort-field-name">Name</th>
					<th class="sort-field sort-field-version numeric">Version</th>
					<th class="sort-field sort-field-publisher">Publisher</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plugins}" var="plugin">
					<tr>
						<td>
							<c:if test="${not empty plugin.href}">
								<a href="${fn:escapeXml(plugin.href)}" target="_blank">${fn:escapeXml(plugin.name)}</a>
							</c:if>
							<c:if test="${empty plugin.href}">
								${fn:escapeXml(plugin.name)}
							</c:if>
						</td>
						<td class="numeric">${fn:escapeXml(plugin.version)}</td>
						<td>${fn:escapeXml(plugin.publisher)}</td>
					</tr>
				</c:forEach>
			</tbody>
			<c:if test="${empty plugins}">
				<tr>
					<td colspan="3">no plug-ins installed</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>
