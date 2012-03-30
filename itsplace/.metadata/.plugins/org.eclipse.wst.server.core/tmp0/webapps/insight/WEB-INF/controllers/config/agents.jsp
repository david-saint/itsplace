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
		<h2>Agents</h2>
	</div>

    <div class="page-module-body">
        <div id="agent-summary" class="submodule">
            <span>
                Active: <fmt:formatNumber value="${numAgents}" maxFractionDigits="0" />
            </span>
            <span>
                Licensed: <fmt:formatNumber value="${numAgentsLicensed}" maxFractionDigits="0" />
            </span>

            <c:if test="${numAgentsUnlicensed > 0}">
            <span>
                 Unlicensed:
                     <span style="color: red;">
                        <fmt:formatNumber value="${numAgentsUnlicensed}" maxFractionDigits="0" />
                     </span>
            </span>
            </c:if>
        </div>

        <div class="table-body">
            <table class="zebra" border="0" cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <th></th>
                    <th class="sort-field sort-field-label">Name</th>
                    <th class="sort-field sort-field-version">Version</th>
                    <th class="sort-field sort-field-licensed">License</th>
                    <th class="sort-field sort-field-throughput numeric">Trace Throughput</th>
                    <th class="sort-field sort-field-heartbeat numeric">Heart Beat</th>
                    <th class="sort-field sort-field-timestamp numeric">Last</th>
                    <th class="sort-field sort-field-skew numeric">Clock Skew</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${agents}" var="agent" varStatus="status">
                    <tr>
                        <td><c:out value="${status.count}"/></td>
                        <td>
                            <spring:url value="/resources${'#'}resource={resourceKey}" var="url">
                                <spring:param name="resourceKey" value="${agent.resourceKey}" />
                            </spring:url>
                            <a href="${fn:escapeXml(url)}">${fn:escapeXml(agent.label)}</a>
                        </td>

                        <c:choose>
                            <c:when test="${agent.versionSameAsDashboard}">
                                <td><span title="${fn:escapeXml(agent.build)}">
                                        ${fn:escapeXml(agent.version)}
                                </span></td>
                            </c:when>
                            <c:otherwise>
                                <td><span style="color: red;" title="${fn:escapeXml(agent.build)}">
                                    ${fn:escapeXml(agent.version)}
                                </span></td>
                            </c:otherwise>
                        </c:choose>

                        <td>
                            <c:choose>
                                <c:when test="${agent.licensed}">yes</c:when>
                                <c:otherwise>no</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="numeric"><fmt:formatNumber value="${agent.throughput}"/> tpm</td>
                        <td class="numeric"><fmt:formatNumber value="${agent.heartbeat}"/> s</td>
                        <td class="numeric"><fmt:formatDate value="${agent.lastSeenDate}" type="time" dateStyle="medium"/></td>
                        <td class="numeric">
                            <c:choose>
                                <c:when test="${agent.skew < 0}">-</c:when>
                                <c:otherwise>+</c:otherwise>
                            </c:choose>
                            <fmt:formatDate value="${agent.skewDate}" timeZone="Etc/GMT" type="time" pattern="HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <c:if test="${empty agents}">
                    <tr>
                        <td colspan="3">no agents connected</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>