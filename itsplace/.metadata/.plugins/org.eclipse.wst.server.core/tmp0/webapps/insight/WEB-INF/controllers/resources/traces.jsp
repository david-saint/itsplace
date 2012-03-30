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

<div id="traces-window" class="page-module">
	<div class="page-module-header">
		<h2>Representative Traces</h2>
		<h3>
			<c:choose>
				<c:when test="${not empty titleLabel and titleLabel eq 'timeRange'}">
					<fmt:formatDate value="${timeRange.startDate}" type="both" timeStyle="medium" />
					- <fmt:formatDate value="${timeRange.endDate}" type="time" timeStyle="medium" />
				</c:when>
				<c:when test="${not empty titleLabel and titleLabel eq 'tag'}">
					${fn:escapeXml(tag)}
				</c:when>
				<c:otherwise>
					${fn:escapeXml(insight:fmtMillis(min, pageContext.request.locale))}
					- ${fn:escapeXml(insight:fmtMillis(max, pageContext.request.locale))}
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="page-module-body table-body">
		<table class="zebra" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="sort-field sort-field-range.duration numeric">Duration</th>
					<th class="sort-field sort-field-label">Label</th>
					<th class="sort-field sort-field-range.start numeric">Start</th>
					<th class="sort-field sort-field-errorCount sort-numeric">Error</th>
				</tr>
			</thead>
			<tbody id="trace_list_body">
				<c:forEach items="${traceSummaries}" var="traceSummary" end="200" varStatus="status">
					<tr id="trace_list_${fn:escapeXml(traceSummary.id)}" class="trace_list_item" trace-date="<fmt:formatDate value="${traceSummary.range.startDate}" pattern="yyyy-MM-dd'T'HH:mm:ssZ" />">
						<fmt:formatNumber value="${traceSummary.range.durationMillis}" var="duration" />
						<td align="right"><a href="javascript:Insight.loadTrace(${insight:escapeJs(traceSummary.id)}, true);">${fn:escapeXml(duration)} ms</a></td>
						<td class="collapse">${fn:escapeXml(traceSummary.label)}</td>
						<td class="numeric"><fmt:formatDate value="${traceSummary.range.startDate}" type="time" timeStyle="medium"/></td>
						<c:choose>
							<c:when test="${traceSummary.errorCount eq 1}">
								<td class="collapse">${fn:escapeXml(traceSummary.errorText)}</td>
							</c:when>
							<c:when test="${traceSummary.errorCount gt 1}">
								<fmt:formatNumber value="${traceSummary.errorCount}" var="errorCount" />
								<td>${fn:escapeXml(errorCount)} errors</td>
							</c:when>
							<c:otherwise>
								<td>ok</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<c:if test="${not empty status.end and status.last and status.count eq status.end+1}">
						<tr>
							<td colspan="4"><em>list truncated</em></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
			<c:if test="${empty traceSummaries}">
				<tr>
					<td colspan="4">no traces available</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>

<%-- blank out the existing trace frame stack because the list can be empty --%>
<div id="trace" style="display: none;"></div>
