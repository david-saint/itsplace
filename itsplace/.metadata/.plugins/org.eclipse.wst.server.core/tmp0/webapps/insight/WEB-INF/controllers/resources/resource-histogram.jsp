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
	<div id="responseTimeHistogram" style="height: 200px;"></div>
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			dojo.require("insight.charting.ResponseTimeHistogram");
			var timeRange = Insight.getTimeRange();
			var chart = new insight.charting.ResponseTimeHistogram({
				timeRange: timeRange,
				dataPoints: 15,
				gridHLines: false,
				url: Insight.buildUri("/services/resources/{resource}/{start}/{end}/histogram", {
					resource: '${insight:escapeJs(resource.resource.key)}',
					start: timeRange.getStart(),
					end: timeRange.getEnd()
				})
			}, "responseTimeHistogram");
			chart.connect(chart, "onChartElementClick", function(event) {
				if (event.element == "bar") {
					var s = this.stores.main, 
						i = s._items[event.index],
						min = s.getValue(i, "start"),
						max = s.getValue(i, "end"),
						timeRange = Insight.getTimeRange(),
						start = timeRange.getStart(),
						end = timeRange.getEnd();
					if (min == Number.NEGATIVE_INFINITY) { min = "-infinity" }
					if (max == Number.POSITIVE_INFINITY) { max = "infinity" }
					Insight.loadWindow(start, end, '${insight:escapeJs(resource.resource.key)}', min, max);
				}
			});
			chart.subscribe("Insight.timeRangeMajorPulse", function(timeRange) {
				this.updateTimeRange({ timeRange: timeRange });
			});
			chart.startup();
		});
	</script>
</c:if>
