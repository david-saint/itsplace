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

<div id="resource-responsetime" class="submodule">
	<c:if test="${resource.resource.type.responseTimeAware}">
		<h2>Response Time</h2>
		<table class="dl" border="0" cellpadding="0" cellspacing="0">
			<col>
			<colgroup span="2">
			<tr>
				<td>95th Percentile</td>
				<c:set var="n" value="${fn:split(insight:fmtMillis(resource.ninetyFifthPercentile, pageContext.request.locale),' ')}" />
				<td class="numeric labeled">${fn:escapeXml(n[0])}</td>
				<td class="label">${fn:escapeXml(n[1])}</td>
			</tr>
			<tr>
				<td>Mean</td>
				<c:set var="n" value="${fn:split(insight:fmtMillis(resource.responseTimeSummary.mean, pageContext.request.locale),' ')}" />
				<td class="numeric labeled">${fn:escapeXml(n[0])}</td>
				<td class="label">${fn:escapeXml(n[1])}</td>
			</tr>
			<tr>
				<td>Standard Deviation</td>
				<c:set var="n" value="${fn:split(insight:fmtMillis(resource.responseTimeSummary.stdDev, pageContext.request.locale),' ')}" />
				<td class="numeric labeled">${fn:escapeXml(n[0])}</td>
				<td class="label">${fn:escapeXml(n[1])}</td>
			</tr>
		</table>
	</c:if>
</div>
