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

<div id="resource-vitals" class="submodule">
	<h2>Vitals</h2>
	<table class="dl" border="0" cellpadding="0" cellspacing="0">
		<c:if test="${empty resource.health.zones}">
			<tr>
				<td>Health</td>
				<td colspan="2">
					<spring:url value="/static/images/health/healthlamp-{lamp}.png" var="url">
						<spring:param name="lamp" value="${resource.responseTimeLamp}" />
					</spring:url>
					<img src="${fn:escapeXml(url)}" style="padding: 0.25em;" />
					<span style="text-transform: capitalize;">${fn:escapeXml(resource.responseTimeLamp)}</span>
				</td>
			</tr>
		</c:if>
		<tr>
			<td>Throughput</td>
			<td class="numeric labeled"><fmt:formatNumber value="${resource.invocationRate}" minFractionDigits="1" maxFractionDigits="1" /></td>
			<td class="label">tpm</td>
		</tr>
		<tr>
			<td>Invocations</td>
			<td class="numeric labeled"><fmt:formatNumber value="${resource.invocationCount}" maxFractionDigits="0" /></td>
			<td class="label">&nbsp;</td>
		</tr>
		<tr>
			<td>Errors</td>
			<td class="numeric labeled"><fmt:formatNumber value="${resource.errorCount}" maxFractionDigits="0" /></td>
			<td class="label">(<fmt:formatNumber value="${resource.errorRate}" type="percent" minFractionDigits="1" maxFractionDigits="1" />)</td>
		</tr>
	</table>
</div>
