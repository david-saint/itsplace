<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div class="page-module">
	<div class="page-module-header">
		<h2>Traces</h2>
		<h3>
			<fmt:formatDate value="${timeRange.startDate}" type="both" timeStyle="medium" />
			- <fmt:formatDate value="${timeRange.endDate}" type="time" timeStyle="medium" />
		</h3>
	</div>
	<div class="page-module-body table-body">
		<table class="zebra-body" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="sort-field sort-field-range.duration numeric">Duration</th>
					<th class="sort-field sort-field-endPointLabel">End Point</th>
					<c:if test="${empty application}">
						<th class="sort-field sort-field-applicationName">Application</th>
					</c:if>
					<c:if test="${not devEdition}">
						<th class="sort-field sort-field-serverLabel">Server</th>
					</c:if>
					<th class="sort-field sort-field-range.start numeric">Start</th>
					<th class="sort-field sort-field-errorCount sort-numeric">Error</th>
				</tr>
			</thead>
			<c:forEach items="${traceSummaries}" var="traceSummary" end="200" varStatus="status">
				<tbody id="trace_list_${fn:escapeXml(traceSummary.id)}" class="trace_list_item">
					<tr>
						<fmt:formatNumber value="${traceSummary.range.durationMillis}" var="duration" />
						<td class="numeric"><a href="javascript:Insight.loadTrace(${fn:escapeXml(traceSummary.id)}, true);">${fn:escapeXml(duration)} ms</a></td>
						<td class="collapse">${fn:escapeXml(traceSummary.endPointLabel)}</td>
						<c:if test="${empty application}">
							<td class="collapse">${fn:escapeXml(traceSummary.applicationName.formatted)}</td>
						</c:if>
						<c:if test="${not devEdition}">
							<td class="collapse">${fn:escapeXml(traceSummary.serverLabel)}</td>
						</c:if>
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
					<tr>
						<td><span class="example">&nbsp;</span></td>
						<td class="collapse" colspan="5"><span class="example">${fn:escapeXml(traceSummary.label)}</span></td>
					</tr>
				</tbody>
				<c:if test="${not empty status.end and status.last and status.count eq status.end+1}">
					<tr>
						<td colspan="6"><em>list truncated</em></td>
					</tr>
				</c:if>
			</c:forEach>
			<c:if test="${empty traceSummaries}">
				<tr>
					<td colspan="6">no traces available</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>

<%-- blank out the existing trace frame stack because the list can be empty --%>
<div id="trace" style="display: none;"></div>
