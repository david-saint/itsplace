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

<div id="stackChart" style="height: 150px; clear: both"></div>
<script type="text/javascript">
	dojo.addOnLoad(function() {
		dojo.require("insight.charting.MultiResourceChart");
		dojo.require("insight.charting._HealthBands");
		dojo.require("insight.charting.axis2d.Fixed");
		dojo.require("insight.charting.plot2d.HeatColumns");
		dojo.require("insight.charting.plot2d.HorizontalBands");
		dojo.require("insight.charting.themes.Spring");
		var timeRange = Insight.getTimeRange();
		var dataPoints = dojo.isIE ? 30 : 60;
		var healthBands = insight.charting._HealthBands.healthBands;
		var chart = new insight.charting.MultiResourceChart({
			timeRange: timeRange,
			dataPoints: dataPoints,
			type: dojox.charting.plot2d.Lines,
			theme: insight.charting.themes.SpringTransparent,
			url: Insight.buildUri("/services/resources/{resource}/{start}/{end}/health", {
				resource: '${insight:escapeJs(resourceKey)}',
				start: timeRange.getStart(),
				end: timeRange.getEnd()
			}),
			yAxisArgs: { type: insight.charting.axis2d.Fixed, vertical: true, min: -0.1, max: 1.1, labels: healthBands, labelFunc: "_healthLabelFunc" },
			gridHLines: false,
			scaleOnRestack: false
		}, "stackChart");
		chart.chart.addPlot("healthBands", { type: insight.charting.plot2d.HorizontalBands, bands: healthBands });
		chart.addStore("errorRate", Insight.buildUri("/services/resources/{resource}/{start}/{end}/errorRate", {
			resource: '${insight:escapeJs(resourceKey)}',
			start: timeRange.getStart(),
			end: timeRange.getEnd(),
			flatten: true
		}));
		chart.addResourceMetricPlot("errorRate", "errorRate", "Error Rate",
			{ hidden: true, includeZero: true, max: 1, fixed: false, minorTicks: false, labelFunc: function(t, n, p) { return chart._errorRateLabelFunc(t, n, p, chart._names("errorRate").axis) } },
			{ type: insight.charting.plot2d.HeatColumns },
			{ fill: new dojo.Color([0xCC, 0x33, 0x33, 0.5]) }
		);
		chart.subscribe("Insight.highlightResource", chart.highlightResource);
		chart.subscribe("Insight.blurResource", chart.blurResource);
		chart.subscribe("Insight.restackResource", function(resource) { chart.restackResource(resource, "main"); });
		chart.subscribe("Insight.timeRangePulse", function(timeRange) {
			this.updateTimeRange({ timeRange: timeRange });
		});
		chart.startup();
		chart.chart.movePlotToBack("healthBands");
	});
</script>
