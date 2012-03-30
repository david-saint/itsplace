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

<c:choose>
	<c:when test="${invalidResource}">
		<%-- Invalid resource key --%>
		<div class="page-module">
			<div class="page-module-header">
				<h2>Unknown Resource</h2>
			</div>
			<div class="page-module-body">
				This resource does not exist.
				<c:if test="${not devEdition}">
					It's likely this resources just doesn't exist on this server.
					Try selecting 'All Servers' in the tree.
				</c:if>
			</div>
		</div>
	</c:when>
	<c:when test="${empty resourceKey.name}">
		<%-- Resource group --%>
		<div class="page-module">
			<div class="page-module-header">
				<spring:message code="repo.resources.types.${resourceType}.name.pural" var="label" />
				<h2>${fn:escapeXml(label)} Health Trend</h2>
				<c:if test="${not resourceType.exampleAware and not empty resource}">
					<h3>${fn:escapeXml(resource.resource.label)}</h3>
				</c:if>
				<c:if test="${not devEdition}">
					<ul class="controls">
						<li class="control"><div id="related-resources"></div></li>
					</ul>
				</c:if>
			</div>
			<div class="page-module-body">
				<tiles:insertAttribute name="stackchart" />
			</div>
		</div>
		<script type="text/javascript">
			dojo.addOnLoad(function(){
				Insight.loadResourceList("resources", "${insight:escapeJs(resourceKey)}");
				<c:if test="${not devEdition and resourceKey eq 'insight:type=Application'}">
					Insight.loadResourceList("resources_alt", "insight:type=Server");
				</c:if>
			});
		</script>
	</c:when>
	<c:when test="${not resource.resource.type.responseTimeAware}">
		<%-- Response time ignorant resource --%>
		<div class="page-module">
			<div class="page-module-header">
				<spring:message code="repo.resources.types.${resourceType}.name" var="label" />
				<h2>${fn:escapeXml(label)}</h2>
				<c:if test="${not resourceType.exampleAware and not empty resource}">
					<h3>${fn:escapeXml(resource.resource.label)}</h3>
				</c:if>
				<c:if test="${not devEdition}">
					<ul class="controls">
						<li class="control"><div id="related-resources"></div></li>
					</ul>
				</c:if>
			</div>
			<div class="page-module-body">
				<c:if test="${resource.resource.type.exampleAware}">
					<h2>${fn:escapeXml(resource.resource.label)}</h2>
					<div class="example">${fn:escapeXml(resource.lastRequest)}</div>
				</c:if>
				<tiles:insertAttribute name="combochart" />
				<table class="layout" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="35%">
							<tiles:insertAttribute name="vitals" />
						</td>
						<td width="65%">
							<tiles:insertAttribute name="healthtrend" />
						</td>
					</tr>
				</table>
				<tiles:insertAttribute name="properties" />
			</div>
		</div>
		<script type="text/javascript">
			dojo.addOnLoad(function(){
				dojo.require("spring.HtmlFragmentResponseHandler");
				<c:if test="${resourceType.endPointAware}">
					Insight.loadResourceList("resources", "${insight:escapeJs(resourceKey)}", "${insight:escapeJs(resourceType.endPointType)}");
				</c:if>
				<c:if test="${not devEdition and resourceType.applicationServerAware and not empty resourceKey.name}">
					Insight.loadResourceList("resources_alt", "${insight:escapeJs(resourceKey)}", "APPLICATION_SERVER");
				</c:if>
				var refresh = self.subscribe("Insight.timeRangeMajorPulse", function(timeRange) {
					dojo.xhrGet({
						url: this.url.build({ fragments: "vitals", start: timeRange.getStart(), end: timeRange.getEnd() }),
						timeout: insight.runtime.getXhrTimeout("ResourceVitals"),
						headers: { Accept: "text/html;type=ajax" },
						handleAs: "html",
						handle: spring.HtmlFragmentResponseHandler.handle
					});
				});
				var load = self.connect(self, "load", function() {
					this.disconnect(load);
					this.unsubscribe(refresh);
				});
			});
		</script>
	</c:when>
	<c:when test="${resource.resource.type.responseTimeAware}">
		<%-- Response time aware resource --%>
		<div class="page-module">
			<div class="page-module-header">
				<spring:message code="repo.resources.types.${resourceType}.name" var="label" />
				<h2>${fn:escapeXml(label)}</h2>
				<c:if test="${not resourceType.exampleAware and not empty resource}">
					<h3>${fn:escapeXml(resource.resource.label)}</h3>
				</c:if>
				<c:if test="${not devEdition}">
					<ul class="controls">
						<li class="control"><div id="related-resources"></div></li>
					</ul>
				</c:if>
			</div>
			<div class="page-module-body submodules">
				<c:if test="${resource.resource.type.exampleAware}">
					<h2>${fn:escapeXml(resource.resource.label)}</h2>
					<div class="example">${fn:escapeXml(resource.lastRequest)}</div>
				</c:if>
				<tiles:insertAttribute name="combochart" />
				<table class="layout" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="50%">
							<tiles:insertAttribute name="vitals" />
						</td>
						<td width="50%">
							<tiles:insertAttribute name="responsetime" />
						</td>
					</tr>
				</table>
				<div class="submodule">
					<h2>Response Time Histogram</h2>
					<table class="layout" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="65%">
								<tiles:insertAttribute name="histogram" />
							</td>
							<td width="35%">
								<tiles:insertAttribute name="healthlegend" />
							</td>
						</tr>
					</table>
				</div>
				<tiles:insertAttribute name="properties" />
			</div>
		</div>
		<script type="text/javascript">
			dojo.addOnLoad(function(){
				dojo.require("spring.HtmlFragmentResponseHandler");
				<c:if test="${resourceType.endPointAware}">
					Insight.loadResourceList("resources", "${insight:escapeJs(resourceKey)}", "${insight:escapeJs(resourceType.endPointType)}");
				</c:if>
				<c:if test="${not devEdition and resourceType.applicationServerAware and not empty resourceKey.name}">
					Insight.loadResourceList("resources_alt", "${insight:escapeJs(resourceKey)}", "APPLICATION_SERVER");
				</c:if>
				<c:if test="${not devEdition and resourceKey eq 'insight:type=Application'}">
					Insight.loadResourceList("resources_alt", "insight:type=Server");
				</c:if>
				var refresh = self.subscribe("Insight.timeRangeMajorPulse", function(timeRange) {
					dojo.xhrGet({
						url: this.url.build({ fragments: "vitals,responsetime,healthlegend", start: timeRange.getStart(), end: timeRange.getEnd() }),
						timeout: insight.runtime.getXhrTimeout("ResourceVitalsAll"),
						headers: { Accept: "text/html;type=ajax" },
						handleAs: "html",
						handle: spring.HtmlFragmentResponseHandler.handle
					});
				});
				var load = self.connect(self, "load", function() {
					this.disconnect(load);
					this.unsubscribe(refresh);
				});
			});
		</script>
	</c:when>
</c:choose>

<c:if test="${not devEdition}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			if (!dojo.byId("related-resources")) {
				return;
			}
			
			dojo.require("dijit.Menu");
			dojo.require("dijit.MenuItem");
			dojo.require("dijit.form.DropDownButton");
			
			var menu = new dijit.Menu();
			
			<c:if test="${resource.resource.type.relatedToApplication}">
				menu.addChild(new dijit.MenuItem({
					label: "Application ${insight:escapeJs(resource.resource.applicationName.formatted)}",
					onClick: function(){
						Insight.loadResource('${insight:escapeJs(resource.resource.applicationKey)}');
					}
				}));
			</c:if>
			<c:if test="${resource.resource.type.relatedToServer}">
				menu.addChild(new dijit.MenuItem({
					label: "Server ${insight:escapeJs(resource.resource.serverName)}",
					onClick: function(){
						Insight.loadResource('${insight:escapeJs(resource.resource.serverKey)}');
					}
				}));
			</c:if>
			<c:if test="${resource.resource.type.relatedToApplicationServer}">
				menu.addChild(new dijit.MenuItem({
					label: "Application ${insight:escapeJs(resource.resource.applicationName.formatted)} on Server ${insight:escapeJs(resource.resource.serverName)}",
					onClick: function(){
						Insight.loadResource('${insight:escapeJs(resource.resource.applicationServerKey)}');
					}
				}));
			</c:if>
			
			if (menu.getChildren().length > 0) {
				new dijit.form.DropDownButton({ label: "Related to", dropDown: menu }, "related-resources");
			}
		});
	</script>
</c:if>
