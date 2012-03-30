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
		<h2>Trace Detail</h2>
		<h3>
			<fmt:formatDate value="${trace.date}" type="time" dateStyle="medium" />
			(<fmt:formatNumber value="${trace.range.durationMillis}" maxFractionDigits="0" /> ms)
		</h3>
		<c:if test="${not empty traceAnalysis.endPointAnalysis}">
			<ul class="controls">
				<li class="control"><div id="advanced-trace-button"></div></li>
				<li class="control"><div id="related-trace-resources"></div></li>
				<li class="control"><div id="filter-trace-button"></div></li>
			</ul>
		</c:if>
	</div>
	<div class="page-module-body">
		<c:if test="${filtered}">
			<div class="info">
				<spring:url value="/static/dojo/dijit/themes/tundra/images/tabClose.png" var="url" />
				<a href="javascript:Insight.clearOperationFilter()"><img alt="clear filter" src="${fn:escapeXml(url)}" /></a>
				Filter applied to trace:
				<c:forEach items="${filterIds}" var="filterId" varStatus="status">
					${fn:escapeXml(fn:replace(filterId, ',', ''))}<c:if test="${not status.last}">, </c:if>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${not empty traceAnalysis.errors}">
			<table class="dl" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>Error</td>
					<td>
						<ul>
							<c:forEach items="${traceAnalysis.errors}" var="error">
								<li>${fn:escapeXml(error.message)}</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
			</table>
		</c:if>
		<div id="traceFrameStack"><span class="example">Loading...</span></div>
	</div>
</div>

<script type="text/javascript">
	dojo.addOnLoad(function() {
		dojo.require("insight.traces.FrameStack");
		new insight.traces.FrameStack({
			traceId: '${insight:escapeJs(trace.id)}',
			traceUri: Insight.buildUri("/services/traces/{traceId}?type=json", { traceId: '${insight:escapeJs(trace.id)}' }),
			operationUri: Insight.buildUri("/services/traces/{traceId}/frames/{frameId}/operation")
		}, "traceFrameStack");
	});

	dojo.addOnLoad(function() {
		dojo.require("dijit.Menu");
		dojo.require("dijit.MenuItem");
		dojo.require("dijit.form.Button");
		dojo.require("dijit.form.DropDownButton");
		dojo.require("insight.components.MenuItemLink");
		dojo.require("insight.components.ToggleMenuItem");
		dojo.require("insight.traces.FilterMenu");

		// filter button
		var filterButtonMenu = new dijit.Menu({ executeOnClick: false });
		<c:forEach items="${filters}" var="filter">
			filterButtonMenu.addChild(new insight.components.ToggleMenuItem({
				label: "${fn:escapeXml(insight:escapeJs(filter.label))}",
				filter: "${fn:escapeXml(insight:escapeJs(filter.id))}",
				value: Insight.hasOperationFilter("${fn:escapeXml(insight:escapeJs(filter.id))}"),
				toggleStates: [
				    "",
				    "+"
				    <c:if test="${filter.inversable}">
				    , "-"
				    </c:if>
				],
				onChange: function(value) {
					if (value) {
						Insight.addOperationFilter(this.filter, value);
					} else {
						Insight.removeOperationFilter(this.filter);
					}
				}
			}));
		</c:forEach>
		var filterButton = new dijit.form.DropDownButton({dropDown: filterButtonMenu, label: "Filter"}, "filter-trace-button");
		filterButtonMenu.connect(filterButtonMenu, "onFocus", function() {
			var filter = Insight.getOperationFilter();
			var blurConnect = filterButtonMenu.connect(filterButtonMenu, "onBlur", function() {
				filterButtonMenu.disconnect(blurConnect);
				blurConnect = null;
				if (filter != Insight.getOperationFilter()) {
					self.load();
				}
			});
		});
		filterButton.startup();

		// advanced button
		var advancedButtonMenu = new dijit.Menu();
		advancedButtonMenu.addChild(new insight.components.MenuItemLink({label: "Export Trace", href: Insight.buildUri("/services/traces/{traceId}?type=bin", { traceId: '${insight:escapeJs(trace.id)}' }) }));
		advancedButtonMenu.addChild(new insight.components.MenuItemLink({label: "Bookmarkable Trace URL", href: Insight.buildUri("/traces#trace={traceId}", { traceId: '${insight:escapeJs(trace.id)}' }) }));
		var advancedButton = new dijit.form.DropDownButton({dropDown: advancedButtonMenu, iconClass: "sprocketIcon", showLabel: false, label: "Advanced Options"}, "advanced-trace-button");
		advancedButton.startup();
	});

	dojo.addOnLoad(function(){
		dojo.require("dijit.Menu");
		dojo.require("insight.components.MenuItemLink");
		dojo.require("dijit.form.DropDownButton");

		var menu = new dijit.Menu();
		var resourceUrl = Insight.buildUri("/resources${'#'}resource={resourceKey}");
		var currentResource = Insight.currentResource();
		<c:forEach items="${resources}" var="resourceEntry">
			menu.addChild(new insight.components.MenuItemLink({
				label: "<spring:message code='repo.resources.types.${resourceEntry.value.type}.name' javaScriptEscape='true' htmlEscape='true' /> ${insight:escapeJs(resourceEntry.value.label)}",
				href: resourceUrl.build({ resourceKey: '${insight:escapeJs(resourceEntry.value.key)}' }),
				disabled: !!(currentResource && currentResource == '${insight:escapeJs(resourceEntry.value.key)}')
			}));
		</c:forEach>
		new dijit.form.DropDownButton({ label: "Related to", dropDown: menu }, "related-trace-resources");
	});
</script>
