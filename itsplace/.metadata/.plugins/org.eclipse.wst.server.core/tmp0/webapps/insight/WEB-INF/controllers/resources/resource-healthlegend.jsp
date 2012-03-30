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

<div id="resource-healthlegend">
	<c:if test="${not empty resource.health.zones && resource.responseTimeThreshold gt 0}">
		<insight:admin>
			<spring:url value="/config/threshold" var="url">
				<spring:param name="endpoint" value="${resourceKey.endPointName}" />
			</spring:url>
			<a href="${fn:escapeXml(url)}" style="float: right;">Edit</a>
		</insight:admin>
		<h3 style="font-weight: bold; margin: 0.5em 0;">Health Legend:</h3>
		<table class="healthZones" border="0" cellpadding="0" cellspacing="0">
			<c:forEach items="${resource.health.zones}" var="healthZone">
				<tr class="healthZone ${fn:escapeXml(healthZone.tag)}" onclick="Insight.loadTaggedWindow('<fmt:formatDate value="${timeRange.startDate}" pattern="yyyy-MM-dd'T'HH:mm:ssZ" />', '<fmt:formatDate value="${timeRange.endDate}" pattern="yyyy-MM-dd'T'HH:mm:ssZ" />', '${insight:escapeJs(fn:escapeXml(resource.resource.key))}', '${insight:escapeJs(fn:escapeXml(healthZone.tag))}')">
					<td style="width: 1.2em"><div class="barContainer"><div class="bar" style="width: 1.2em">&nbsp;</div></div></td>
					<td class="label numeric" style="font-weight: bold;"><fmt:formatNumber value="${healthZone.invocationCount}" /></td>
					<td class="label zone">${fn:escapeXml(healthZone.label)}</a>
					</td>
					<td class="label threshold">
						<c:if test="${not empty healthZone.tag}">
							<c:choose>
								<c:when test="${healthZone.tag eq 'satisfied'}">
									&lt;${fn:escapeXml(insight:fmtMillis(resource.responseTimeThreshold, pageContext.request.locale))}
								</c:when>
								<c:when test="${healthZone.tag eq 'tolerated'}">
									${fn:escapeXml(insight:fmtMillis(resource.responseTimeThreshold, pageContext.request.locale))}
									- ${fn:escapeXml(insight:fmtMillis(resource.responseTimeThreshold * 4, pageContext.request.locale))}
								</c:when>
								<c:when test="${healthZone.tag eq 'frustrated'}">
									&gt;${fn:escapeXml(insight:fmtMillis(resource.responseTimeThreshold * 4, pageContext.request.locale))}, error
								</c:when>
							</c:choose>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
