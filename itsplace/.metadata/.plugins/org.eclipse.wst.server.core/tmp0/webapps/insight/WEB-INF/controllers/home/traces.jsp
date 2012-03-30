<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="insight" uri="http://static.springsource.com/tags/insight" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div id="traces" style="display: none;"></div>
<div id="traces-window" style="display: none;"></div>
<div id="trace" style="display: none;"></div>

<script type="text/javascript">
	dojo.addOnLoad(function() {
		if (Insight.isBrowserSupported()) {
			Insight.hashMode("trace");
			<c:choose>
				<c:when test="${not empty application}">
					Insight.loadTraces("${fn:escapeXml(insight:escapeJs(application))}");
				</c:when>
				<c:otherwise>
					Insight.loadTraces();
				</c:otherwise>
			</c:choose>
			var trace = Insight.currentTrace();
			if (trace) {
				Insight.loadTrace(trace);
			}
		}
		Insight.setSwitchUri("/traces");
		Insight.setSwitchApplicationUri("/traces/{application}");
	});
</script>
