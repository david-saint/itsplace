<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="insight" uri="http://static.springsource.com/tags/insight" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div class="sidebar">
	<div class="page-module">
		<div class="page-module-header">&nbsp;</div>
		<div id="alt_navigation" class="page-module-body">
			<h2>Affect Change</h2>
			<ul>
				<spring:url value="/config/threshold" var="url" />
				<c:choose>
					<c:when test="${not empty module and module eq 'threshold'}">
						<li class="active"><a href="${fn:escapeXml(url)}">End Point Thresholds</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${fn:escapeXml(url)}">End Point Thresholds</a></li>
					</c:otherwise>
				</c:choose>
				<insight:admin>
					<spring:url value="/config/import" var="url" />
					<c:choose>
						<c:when test="${not empty module and module eq 'import'}">
							<li class="active"><a href="${fn:escapeXml(url)}">Import Trace</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${fn:escapeXml(url)}">Import Trace</a></li>
						</c:otherwise>
					</c:choose>
					<spring:url value="/config/maintenance" var="url" />
					<c:choose>
						<c:when test="${not empty module and module eq 'maintenance'}">
							<li class="active"><a href="${fn:escapeXml(url)}">Purge Data</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${fn:escapeXml(url)}">Purge Data</a></li>
						</c:otherwise>
					</c:choose>
				</insight:admin>
			</ul>
			<h2>Reports and Statistics</h2>
			<ul>
				<spring:url value="/config/plugins" var="url" />
				<c:choose>
					<c:when test="${not empty module and module eq 'plugins'}">
						<li class="active"><a href="${fn:escapeXml(url)}">Collection Plug-ins</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${fn:escapeXml(url)}">Collection Plug-ins</a></li>
					</c:otherwise>
				</c:choose>
				<c:if test="${not devEdition}">
					<spring:url value="/config/agents" var="url" />
					<c:choose>
						<c:when test="${not empty module and module eq 'agents'}">
							<li class="active"><a href="${fn:escapeXml(url)}">Agents</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${fn:escapeXml(url)}">Agents</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
				<spring:url value="/config/stats" var="url" />
				<c:choose>
					<c:when test="${not empty module and module eq 'stats'}">
						<li class="active"><a href="${fn:escapeXml(url)}">General Statistics</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${fn:escapeXml(url)}">General Statistics</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<insight:admin development="false">
				<h2>Support Tools</h2>
				<ul>
					<spring:url value="/config/groovy-console" var="url" />
					<c:choose>
						<c:when test="${not empty module and module eq 'groovy-console'}">
							<li class="active"><a href="${fn:escapeXml(url)}">Groovy Console</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${fn:escapeXml(url)}">Groovy Console</a></li>
						</c:otherwise>
					</c:choose>
					<spring:url value="/config/sql-console" var="url" />
					<c:choose>
						<c:when test="${not empty module and module eq 'sql-console'}">
							<li class="active"><a href="${fn:escapeXml(url)}">SQL Console</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${fn:escapeXml(url)}">SQL Console</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</insight:admin>
		</div>
	</div>
</div>
<div>
	<div id="config-body" style="display: none;"></div>
</div>

<c:choose>
	<c:when test="${module eq 'plugins' and devEdition}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.SortableTable");
				new insight.components.SortableTable({
					url: Insight.buildUri("/services/config/plugins"),
					defaultSortSettings: {
						field: "name",
						desc: false
					}
				}, "config-body");
			});
		</script>
	</c:when>
	<c:when test="${module eq 'plugins' and not devEdition}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.PageModule");
				new insight.components.PageModule({
					url: Insight.buildUri("/services/config/plugins")
				}, "config-body");
			});
		</script>
	</c:when>
	<c:when test="${module eq 'import'}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.PageModule");
				new insight.components.PageModule({
					url: Insight.buildUri("/services/config/import", {
						<c:if test="${not empty param.error}">error: "true"</c:if>
					})
				}, "config-body");
			});
		</script>
	</c:when>
	<c:when test="${module eq 'stats'}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.SortableTable");
				new insight.components.SortableTable({
					url: Insight.buildUri("/services/config/stats"),
					defaultSortSettings: {
						field: "entity",
						desc: false
					}
				}, "config-body");
			});
		</script>
	</c:when>
	<c:when test="${module eq 'agents'}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.SortableTable");
				new insight.components.SortableTable({
					url: Insight.buildUri("/services/config/agents"),
					defaultSortSettings: {
						field: "name",
						desc: false
					}
				}, "config-body");
			});
		</script>
	</c:when>
	<c:when test="${module eq 'maintenance'}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.PageModule");
				new insight.components.PageModule({
					url: Insight.buildUri("/services/config/maintenance")
				}, "config-body");
			});
		</script>
	</c:when>
	<c:when test="${module eq 'groovy-console'}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.PageModule");
				new insight.components.PageModule({
					url: Insight.buildUri("/services/config/groovy-console")
				}, "config-body");
			});
		</script>
	</c:when>
	<c:when test="${module eq 'sql-console'}">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.components.PageModule");
				new insight.components.PageModule({
					url: Insight.buildUri("/services/config/sql-console")
				}, "config-body");
			});
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.config.ResponseTimeThresholdList");
				new insight.config.ResponseTimeThresholdList({
					<c:if test="${not empty param.endpoint}">endpoint: "${fn:escapeXml(insight:escapeJs(param.endpoint))}",</c:if>
					url: Insight.buildUri("/services/config/thresholds")
				}, "config-body");
			});
		</script>
	</c:otherwise>
</c:choose>
