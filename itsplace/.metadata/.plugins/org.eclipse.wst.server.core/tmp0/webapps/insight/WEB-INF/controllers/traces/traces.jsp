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
		<h2>Trace History</h2>
		<h3 id="trace-end-label"></h3>
		<c:if test="${not empty applications}">
			<ul class="controls">
				<li class="control">
					<select id="applications" style="display: none">
						<option value="*">All Applications</option>
						<c:set var="currentApplicationFound" value="${false}" />
						<c:forEach items="${applications}" var="a">
							<c:if test="${a.applicationName eq application}">
								<c:set var="currentApplicationFound" value="${true}" />
							</c:if>
							<option value="${fn:escapeXml(a.applicationName)}" <c:if test="${application eq a.applicationName}">selected="selected"</c:if>>${fn:escapeXml(a.label)}</option>
						</c:forEach>
						<c:if test="${not empty application and not currentApplicationFound}">
							<option value="${fn:escapeXml(application)}" selected="selected">Unknown Application</option>
						</c:if>
					</select>
				</li>
			</ul>
		</c:if>
	</div>
	<div class="page-module-body">
		<div id="trace-windows-chart" class="chart" style="width: 100%; height: 150px;"></div>
	</div>
</div>

<script type="text/javascript">
	dojo.addOnLoad(function() {
		dojo.require("dijit.form.ToggleButton");
		dojo.require("insight.charting.RealTimePerformanceChart");
		dojo.require("insight.time.TimeRange");

		var timeRange = Insight.getTimeRange();
		var chart = new insight.charting.RealTimePerformanceChart({
			timeRange: timeRange,
			dataPoints: 60,
			url: Insight.buildUri("/services/traces/windows/{start}/{end}?type=json", {
				<c:if test="${not empty application}">
					application: "${fn:escapeXml(insight:escapeJs(application))}",
				</c:if>
				start: timeRange.getStart(),
				end: timeRange.getEnd()
			})
		}, "trace-windows-chart");
		chart.connect(chart, "onLoadWindow", function(window) {
			if (dijit.byId("traces-window")) {
				dijit.byId("traces-window").loadWindow({
					<c:if test="${not empty application}">
						application: "${fn:escapeXml(insight:escapeJs(application))}",
					</c:if> 
					start: window.start,
					end: window.end
				});
			}
			else {
				Insight.loadWindow(window.start, window.end, "${fn:escapeXml(insight:escapeJs(application))}");
			}
		});
		chart.subscribe("Insight.timeRangePulse", function(timeRange) {
			this.updateTimeRange({ timeRange: timeRange });
		});
		chart.startup();
	});

	dojo.addOnLoad(function() {
		// application selector
		dojo.require("insight.components.SelectDropDownButton");
		var select = new insight.components.SelectDropDownButton({}, "applications");
		select.startup();
		select.connect(select, "onChange", function(value) {
			Insight.switchToApplication(value);
		});
	});
</script>
