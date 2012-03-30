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

<c:if test="${resource.resource.type.responseTimeAware}">
	<div id="comboChart" style="height: 150px; clear: both"></div>
	<div id="comboHealthChart" style="height: 65px; clear: both"></div>
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			dojo.require("dojox.charting.plot2d.Areas");
			dojo.require("dojox.charting.plot2d.Lines");
			dojo.require("insight.charting.MultiMetricResourceChart");
			dojo.require("insight.charting.MultiResourceChart");
			dojo.require("insight.charting._HealthBands");
			dojo.require("insight.charting.action2d.StrokeHighlight");
			dojo.require("insight.charting.axis2d.Fixed");
			dojo.require("insight.charting.axis2d.Legend");
			dojo.require("insight.charting.plot2d.HeatColumns");
			dojo.require("insight.charting.plot2d.HorizontalBands");
			var timeRange = Insight.getTimeRange()
			var dataPoints = dojo.isIE ? 30 : 60;
			var chart = new insight.charting.MultiMetricResourceChart({
				clickable: true,
				timeRange: timeRange,
				dataPoints: dataPoints,
				url: Insight.buildUri("/services/resources/{resource}/{start}/{end}/metricData", {
					resource: '${insight:escapeJs(resource.resource.key)}',
					start: timeRange.getStart(),
					end: timeRange.getEnd()
				}),
				hideXAxis: true,
				margins: {l: 10, t: 10, r: 10, b: 0}
			}, "comboChart");
			chart.addResourceMetricPlot("main", "throughput", "Throughput Trend",
				{ maxLabelSize: 57, includeZero: true, fixed: false, minorTicks: false, labelFunc: function(t, n, p) { return chart._throughputLabelFunc(t, n, p, chart._names("throughput").axis) } },
				{ type: dojox.charting.plot2d.Areas, markers: true, tension: "S" },
				{ fill: new dojo.Color([0xFF, 0xD1, 0x19, 0.5]), stroke: { color: "#FFFFFF", width: 2 }, markerFill: new dojo.Color([0xFF, 0xFF, 0xFF, 0]), markerStroke: { color: new dojo.Color([0xFF, 0xFF, 0xFF, 0]) } }
			);
			chart.addResourceMetricPlotAction("throughput", insight.charting.action2d.StrokeHighlight, { highlight: "#2354A4" })
			chart.addResourceMetricPlot("main", "responseTime", "Response Time Trend",
				{ maxLabelSize: 57, includeZero: true, fixed: false, minorTicks: false, labelFunc: function(t, n, p) { return chart._responseTimeLabelFunc(t, n, p, chart._names("responseTime").axis) } },
				{ type: dojox.charting.plot2d.Lines, markers: true, tension: "S" },
				{ stroke: { color:"#005AFF", width: 1.5 }, markerStroke: { color: new dojo.Color([0x00, 0x5A, 0xFF, 0]) } }
			);
			chart.addResourceMetricPlotAction("responseTime", insight.charting.action2d.StrokeHighlight, { highlight: "#2354A4" })
			chart.addResourceMetricPlot("main", "errorRate", "Error Rate",
				{ maxLabelSize: 57, includeZero: true, max: 1, fixed: false, minorTicks: false, labelFunc: function(t, n, p) { return chart._errorRateLabelFunc(t, n, p, chart._names("errorRate").axis) } },
				{ type: insight.charting.plot2d.HeatColumns },
				{ fill: new dojo.Color([0xCC, 0x33, 0x33, 0.5]) }
			);
			chart.chart.addAxis("legend", { type: insight.charting.axis2d.Legend, series: [chart._names("throughput").series, chart._names("errorRate").series, chart._names("responseTime").series ], leftBottom: false });
			chart.chart.movePlotToBack(chart._names("errorRate").plot);
			chart.chart.movePlotToBack(chart._names("throughput").plot);
			chart.chart.movePlotToBack(chart._names("grid").plot);
			chart.connect(chart, "onChartElementClick", function(e){
				if (e.plot.name == "foregroundColumnsPlot") {
					var range = e.run.data[e.index];
					Insight.loadWindow(range.start, range.end, '${insight:escapeJs(resource.resource.key)}');
				}
			});
			chart.subscribe("Insight.timeRangePulse", function(timeRange) {
				this.updateTimeRange({ timeRange: timeRange });
			});
			var healthBands = insight.charting._HealthBands.healthBands;
			var healthChart = new insight.charting.MultiMetricResourceChart({
				timeRange: timeRange,
				dataPoints: dataPoints,
				type: dojox.charting.plot2d.Lines,
				theme: insight.charting.themes.SpringTransparent,
				url: Insight.buildUri("/services/resources/{resource}/{start}/{end}/metricData", {
					resource: '${insight:escapeJs(resource.resource.key)}',
					start: timeRange.getStart(),
					end: timeRange.getEnd()
				}),
				gridHLines: false,
				scaleOnRestack: false,
				margins: {l: 70, t: 0, r: 70, b: 10}
			}, "comboHealthChart");
			healthChart.addResourceMetricPlot("main", "health", "Health",
				{ type: dojox.charting.axis2d.Invisible, vertical: true, min: -0.1, max: 1.1, labelFunc: healthChart._healthLabelFunc },
				{ type: dojox.charting.plot2d.Lines, markers: true, tension: "S" },
				{ stroke: { color: "#339933", width: 1.5 }, markerFill: new dojo.Color([0x33, 0x99, 0x33, 0]), markerStroke: { color: new dojo.Color([0x33, 0x99, 0x33, 0]) } }
			);
			healthChart.addResourceMetricPlotAction("health", insight.charting.action2d.StrokeHighlight, { highlight: "#2354A4" })
			healthChart.chart.addPlot("healthBands", { type: insight.charting.plot2d.HorizontalBands, bands: healthBands, vAxis: healthChart._names("health").axis });
			healthChart.subscribe("Insight.timeRangePulse", function(timeRange) {
				this.updateTimeRange({ timeRange: timeRange });
			});
			chart.startup();
			healthChart.startup();
			chart.chart.axes["legend"].dirty = true;
			healthChart.chart.movePlotToBack("healthBands");
		});
	</script>
</c:if>
