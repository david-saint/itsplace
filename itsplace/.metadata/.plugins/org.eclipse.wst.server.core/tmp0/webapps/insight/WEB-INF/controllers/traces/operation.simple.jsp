<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="insight" uri="http://static.springsource.com/tags/insight" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<h2>${fn:escapeXml(insight:fmtUnderscoreLabel(operation.type.name))} Operation</h2>

<table class="dl" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>Label</td>
		<td>${fn:escapeXml(operation.label)}</td>
	</tr>
	<c:forEach items="${insight:operationProperties(operation)}" var="property">
		<tr>
			<td>${fn:escapeXml(insight:fmtBeanPropertyName(property))}</td>
			<td>${fn:escapeXml(insight:fmtBeanPropertyValue(operation[property]))}</td>
		</tr>
	</c:forEach>
</table>
