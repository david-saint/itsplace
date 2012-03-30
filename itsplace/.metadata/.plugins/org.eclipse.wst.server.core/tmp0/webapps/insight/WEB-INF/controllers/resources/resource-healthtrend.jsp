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

<c:if test="${not resource.resource.type.responseTimeAware}">
	<div class="submodule">
		<h2 style="float: left;">Health Trend</h2>
		<div id="healthTrend" style="height: 150px; clear: both"></div>
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.require("insight.charting.MultiResourceChart");
				dojo.require("insight.charting._HealthBands");
				dojo.require("insight.charting.axis2d.Fixed");
				dojo.require("insight.charting.plot2d.HeatColumns");
				dojo.require("insight.charting.plot2d.HorizontalBands");
				dojo.require("insight.charting.themes.Spring");
				dojo.require("insight.resources.ResourceKey");
				dojo.require("dijit.form.Button");
				var timeRange = Insight.getTimeRange();
				var requstedResource = new insight.resources.ResourceKey('${insight:escapeJs(resourceKey)}'), resource;
				if ("Server" === requstedResource.getType()) {
					resource = requstedResource.makeQueryKey("Application.Server");
				}
				else if (requstedResource.getAttribute("Server")) {
					resource = requstedResource.makeQueryKey("Application.Server.EndPoint");
				}
				else {
					resource = requstedResource.makeQueryKey("Application.EndPoint");
				}
				var healthBands = insight.charting._HealthBands.healthBands;
				var chart = new insight.charting.MultiResourceChart({
					timeRange: timeRange,
					dataPoints: 30,
					type: dojox.charting.plot2d.Lines,
					theme: insight.charting.themes.SpringTransparent,
					url: Insight.buildUri("/services/resources/{resource}/{start}/{end}/health", {
						resource: resource.toString(),
						start: timeRange.getStart(),
						end: timeRange.getEnd()
					}),
					yAxisArgs: { type: insight.charting.axis2d.Fixed, vertical: true, min: -0.1, max: 1.1, labels: healthBands, labelFunc: "_healthLabelFunc" },
					gridHLines: false,
					scaleOnRestack: false
				}, "healthTrend");
				chart.addStore("metrics", Insight.buildUri("/services/resources/{resource}/{start}/{end}/metricData", {
					resource: '${insight:escapeJs(resourceKey)}',
					start: timeRange.getStart(),
					end: timeRange.getEnd()
				}));
				chart.addResourceMetricPlot("metrics", "errorRate", "Error Rate",
					{ hidden: true, includeZero: true, max: 1, fixed: false, minorTicks: false, labelFunc: function(t, n, p) { return chart._errorRateLabelFunc(t, n, p, chart._names("errorRate").axis) } },
					{ type: insight.charting.plot2d.HeatColumns },
					{ fill: new dojo.Color([0xCC, 0x33, 0x33, 0.5]) }
				);
				chart.chart.addPlot("healthBands", { type: insight.charting.plot2d.HorizontalBands, bands: healthBands });
				chart.subscribe("Insight.highlightResource", chart.highlightResource);
				chart.subscribe("Insight.blurResource", chart.blurResource);
				chart.subscribe("Insight.restackResource", function(resource) { chart.restackResource(resource, "main"); });
				chart.subscribe("Insight.timeRangePulse", function(timeRange) {
					this.updateTimeRange({ timeRange: timeRange });
				});
				chart.startup();
				chart.chart.movePlotToBack("healthBands");
				<c:if test="${not devEdition and resourceType.typeName eq 'Application'}">
					var controls, control, serversToggle, endPointsToggle;
					controls = dojo.create("ul", { className: "controls" }, chart.domNode, "before");
					control = dojo.create("li", { className: "control" }, controls);
					<spring:message code="repo.resources.types.APPLICATION_END_POINT.context.APPLICATION.name.pural" var="label" />
					endPointsToggle = new dijit.form.Button({ label: "${fn:escapeXml(insight:escapeJs(label))}" });
					<spring:message code="repo.resources.types.APPLICATION_SERVER.context.APPLICATION.name.pural" var="label" />
					serversToggle = new dijit.form.Button({ label: "${fn:escapeXml(insight:escapeJs(label))}" });
					endPointsToggle.connect(endPointsToggle, "onClick", function() {
						chart.urlParam('resource', requstedResource.makeQueryKey('Application.EndPoint'), 'main');
						chart.reset();
						dojo.addClass(endPointsToggle.domNode, "dijitHighlight");
						dojo.removeClass(serversToggle.domNode, "dijitHighlight");
					});
					serversToggle.connect(serversToggle, "onClick", function() {
						chart.urlParam('resource', requstedResource.makeQueryKey('Application.Server'), 'main');
						chart.reset();
						dojo.addClass(serversToggle.domNode, "dijitHighlight");
						dojo.removeClass(endPointsToggle.domNode, "dijitHighlight");
					});
					dojo.addClass(endPointsToggle.domNode, "dijitHighlight");
					dojo.place(endPointsToggle.domNode, control);
					dojo.place(serversToggle.domNode, control);
					endPointsToggle.startup();
					serversToggle.startup();
				</c:if>
			});
		</script>
	</div>
</c:if>
