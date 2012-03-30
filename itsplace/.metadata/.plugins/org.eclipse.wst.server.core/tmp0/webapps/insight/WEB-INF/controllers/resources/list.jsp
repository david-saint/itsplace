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
		<h2>
			<c:choose>
				<c:when test="${not empty context}">
					<spring:message code="repo.resources.types.${type}.context.${context}.name.pural" var="label" />
				</c:when>
				<c:otherwise>
					<spring:message code="repo.resources.types.${type}.name.pural" var="label" />
				</c:otherwise>
			</c:choose>
			${fn:escapeXml(label)}
		</h2>
		<c:if test="${type.relatedToServer and not empty server}">
			<h3>
				on ${fn:escapeXml(server)}
			</h3>
		</c:if>
	</div>
	<div class="page-module-body table-body">
		<table class="zebra-body" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="sort-field sort-field-responseTimeScore sort-numeric">Health</th>
					<th class="sort-field sort-field-resource.label">
						<c:choose>
							<c:when test="${not empty context}">
								<spring:message code="repo.resources.types.${type}.context.${context}.name" var="label" />
							</c:when>
							<c:otherwise>
								<spring:message code="repo.resources.types.${type}.name" var="label" />
							</c:otherwise>
						</c:choose>
						${fn:escapeXml(label)}
					</th>
					<th>Health&nbsp;Trend</th>
					<!--th class="numeric sort-field sort-field-invocationCount">Invocations</th-->
					<th class="numeric sort-field sort-field-invocationRate" title="Traces per Minute">Throughput</th>
					<th class="numeric sort-field sort-field-errorRate" title="Errors as percent of Invocations">Errors</th>
					<c:if test="${type.responseTimeAware}">
						<th class="numeric sort-field sort-field-ninetyFifthPercentile" title="95th Percentile" colspan="2">Response Time</th>
					</c:if>
				</tr>
			</thead>
			<c:if test="${empty resources and nosampleCount eq 0}">
				<tbody>
					<tr>
						<td colspan="8">
							<c:choose>
								<c:when test="${not empty context}">
									<em><spring:message code="repo.resources.types.${type}.context.${context}.list.empty" htmlEscape="true" /></em>
								</c:when>
								<c:otherwise>
									<em><spring:message code="repo.resources.types.${type}.list.empty" htmlEscape="true" /></em>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</c:if>
			<c:forEach items="${resources}" var="resource" varStatus="status">
				<tbody>
					<tr>
						<td>
							<spring:url value="/static/images/health/healthlamp-{lamp}.png" var="url">
								<spring:param name="lamp" value="${resource.responseTimeLamp}" />
							</spring:url>
							<img title="${fn:escapeXml(resource.responseTimeRating)}. ${fn:escapeXml(resource.health.note)}" src="${fn:escapeXml(url)}" />
						</td>
						<td class="collapse"><a href="javascript:Insight.loadResource('${fn:escapeXml(insight:escapeJs(resource.resource.key.key))}')" title="${fn:escapeXml(resource.resource.label)}">${fn:escapeXml(resource.resource.label)}</a></td>					 
						<spring:url value="/services/resources/{resource}/{start}/{end}/health?type=png" var="url">
							<spring:param name="resource" value="${resource.resource.key}" />
							<spring:param name="start"><fmt:formatDate value="${timeRange.startDate}" pattern="yyyy-MM-dd'T'HH:mm:ssZ" /></spring:param>
							<spring:param name="end"><fmt:formatDate value="${timeRange.endDate}" pattern="yyyy-MM-dd'T'HH:mm:ssZ" /></spring:param>
						</spring:url>
						<td>
							<img src="${fn:escapeXml(url)}"
								style="cursor: pointer;"
								width="100"
								height="15"
								onmouseover="dojo.publish('Insight.highlightResource', ['${fn:escapeXml(insight:escapeJs(resource.resource.key.key))}'])"
								onmouseout="dojo.publish('Insight.blurResource', ['${fn:escapeXml(insight:escapeJs(resource.resource.key.key))}'])"
								onclick="dojo.publish('Insight.restackResource', ['${fn:escapeXml(insight:escapeJs(resource.resource.key.key))}'])"
								/>
						</td>
						<td class="numeric"><fmt:formatNumber value="${resource.invocationRate}" minFractionDigits="1" maxFractionDigits="1" />&nbsp;tpm</td>
						<td class="numeric"><fmt:formatNumber value="${resource.errorRate}" type="percent" minFractionDigits="1" maxFractionDigits="1" /></td>
						<c:if test="${type.responseTimeAware}">
							<c:choose>
								<c:when test="${resource.invocationCount ne 0}">
									<c:set var="n" value="${fn:split(insight:fmtMillis(resource.ninetyFifthPercentile, pageContext.request.locale),' ')}" />
									<td class="numeric labeled">${fn:escapeXml(n[0])}</td>
									<td class="label">${fn:escapeXml(n[1])}</td>
								</c:when>
								<c:otherwise>
									<td colspan="2" class="numeric">n/a</td>
								</c:otherwise>
							</c:choose>
						</c:if>
					</tr>
					<c:if test="${type.exampleAware}">
						<tr>
							<td>&nbsp;</td>
							<td class="collapse"><span class="example" title="${fn:escapeXml(resource.lastRequest)}">${fn:escapeXml(resource.lastRequest)}</span></td>
							<td colspan="6">&nbsp;</td>
						</tr>
					</c:if>
				</tbody>
			</c:forEach>
			<c:if test="${not nosample and nosampleCount gt 0}">
				<tbody>
					<tr>
						<c:choose>
							<c:when test="${not empty context}">
								<spring:message code="repo.resources.types.${type}.context.${context}.name.pural" var="label" />
							</c:when>
							<c:otherwise>
								<spring:message code="repo.resources.types.${type}.name.pural" var="label" />
							</c:otherwise>
						</c:choose>
						<td colspan="8">
							<em>${fn:escapeXml(nosampleCount)} ${fn:escapeXml(fn:toLowerCase(label))} hidden; no throughput in time range.</em>
						</td>
					</tr>
				</tbody>
			</c:if>
		</table>
	</div>
</div>
