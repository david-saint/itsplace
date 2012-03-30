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
	<div id="resources-tree-module" style="display: none;"></div>
</div>
<div>
	<div id="resource" style="display: none;"></div>
	<div id="resources" style="display: none;"></div>
	<div id="resources_alt" style="display: none;"></div>
	<div id="traces-window" style="display: none;"></div>
	<div id="trace" style="display: none;"></div>
</div>

<script type="text/javascript">
	dojo.addOnLoad(function() {
		dojo.require("insight.components.PageModule");
		dojo.require("insight.components.SortableTable");
		if (Insight.isBrowserSupported()) {
			Insight.hashMode("resource");
			new insight.components.PageModule({
				url: Insight.buildUri("/services/resources/tree")
			}, "resources-tree-module");
			var resource = Insight.currentResource();
			Insight.loadResource(resource ? resource : "${insight:escapeJs(resource)}");
		}
	});
</script>
