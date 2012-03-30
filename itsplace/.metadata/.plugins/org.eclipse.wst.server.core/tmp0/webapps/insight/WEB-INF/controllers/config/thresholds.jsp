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
		<h2>End Point Response Time Thresholds</h2>
		<insight:admin>
			<ul class="controls">
				<li class="control"><span class="newRuleButton">[new]</span></li>
				<li class="control"><span class="cancelButton">[cancel]</span></li>
			</ul>
		</insight:admin>
	</div>
	<div class="page-module-body table-body">
		<c:if test="${rulesLocal}">
			<div class="info">
				<div style="float: right;"><span class="revertRulesButton">[revert]</span></div>
				<span class="persistRulesButton">[persist]</span>
				Rules contain intermediate changes that have not been applied
			</div>
		</c:if>
		<table class="thresholdRuleList zebra-body" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th>Rule</th>
					<th>Threshold</th>
					<th>Matching Endpoints</th>
					<insight:admin>
						<th colspan="4">&nbsp;</th>
					</insight:admin>
				</tr>
			</thead>
			<c:if test="${empty rules}">
				<tbody>
					<tr>
						<td colspan="7">
							No rules found
						</td>
					</tr>
				</tbody>
			</c:if>
			<c:forEach items="${rules}" var="rule" varStatus="status">
				<tbody class="thresholdRule<c:if test="${not empty endpointRule and rule eq endpointRule}"> selected</c:if>">
					<fmt:formatNumber value="${rule.threshold}" var="threshold" />
					<spring:eval expression="partitions.getResources(rule)" var="ruleResources" />
					<tr class="precedence precedence-${fn:escapeXml(rule.precedence)}">
						<td class="rulePattern">${fn:escapeXml(rule.requiredEndPoint.pattern)}</td>
						<td class="ruleThreshold">${fn:escapeXml(threshold)} ms</td>
						<td class="thresholdRuleResourceCount">${fn:escapeXml(fn:length(ruleResources))}</td>
						<insight:admin>
							<td class="buttonHolder">
								<span class="editRuleButton">[edit]</span>
							</td>
							<td class="buttonHolder">
								<c:if test="${rule.precedence ne 0 and not status.first}">
									<span class="promoteRuleButton">[promote]</span>
								</c:if>
							</td>
							<td class="buttonHolder">
								<c:if test="${rule.precedence ne 0 and status.count + 1 lt fn:length(rules)}">
									<span class="demoteRuleButton">[demote]</span>
								</c:if>
							</td>
							<td class="buttonHolder">
								<c:if test="${rule.precedence ne 0}">
									<span class="deleteRuleButton">[delete]</span>
								</c:if>
							</td>
						</insight:admin>
					</tr>
					<tr class="thresholdRuleResourceList" style="display: none;">
						<td colspan="7">
							<table style="margin-left: 15%" border="0" cellpadding="0" cellspacing="0">
								<c:forEach items="${ruleResources}" var="resource">
									<spring:url value="/resources${'#'}resource={resourceKey}" var="applicationUrl">
										<spring:param name="resourceKey" value="${resource.applicationKey}" />
									</spring:url>
									<spring:url value="/resources${'#'}resource={resourceKey}" var="endpointUrl">
										<spring:param name="resourceKey" value="${resource.key}" />
									</spring:url>
									<tr>
										<td><a href="${fn:escapeXml(applicationUrl)}">${fn:escapeXml(resource.applicationName.formatted)}</a></td>
										<td class="collapse"><a href="${fn:escapeXml(endpointUrl)}" title="${fn:escapeXml(resource.label)}">${fn:escapeXml(resource.key.name)}</a></td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>

<script type="text/javascript">
	dojo.addOnLoad(function(){
		Insight.resetLogout();
		dojo.query("table.thresholdRuleList tbody.thresholdRule", self.domNode).forEach(function(thresholdRule){
			dojo.query(".thresholdRuleResourceCount", thresholdRule).forEach(function(thresholdRuleResourceCount){
				var count = thresholdRuleResourceCount.innerHTML;
				var expando = dojo.create("a", { href: "#" }, thresholdRuleResourceCount, "only");
				expando.innerHTML = count;
				dojo.connect(expando, "onclick", expando, function(){
					dojo.query(".thresholdRuleResourceList", thresholdRule).forEach(function(list){
						dojo.style(list, "display", dojo.style(list, "display") === "none" ? "" : "none");
					});
				});
			});
		});
	});
</script>
