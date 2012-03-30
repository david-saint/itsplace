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

<div id="header">
	<!-- someday we'll have webfonts...
	<h1><span class="spring">spring</span><span class="insight">insight</span></h1>
	<h2>vFabric Technology</h2>
	-->
	<spring:url value="/static-{versionNumber}/images/logo.png" var="url">
		<spring:param name="versionNumber" value="${versionNumber}" />
	</spring:url>
	<img alt="Spring Insight, vFabric Technology" src="${fn:escapeXml(url)}">
	<ul>
		<li><a target="_blank" href="http://www.springsource.org/insight">Home</a></li>
		<li><a target="_blank" href="http://forum.springsource.org/forumdisplay.php?f=71">Forum</a></li>
		<li><a target="_blank" href="http://blog.springsource.com/category/tc-server/">Blog</a></li>
		<li><a target="_blank" href="http://static.springsource.com/projects/tc-server/2.1/index.html">Help</a></li>
	</ul>
</div>
<c:if test="${empty devEdition or devEdition or (not devEdition and not empty pageContext.request.userPrincipal)}">
	<div id="navigation">
		<ul class="links">
			<spring:url value="/resources#resource=insight%3Atype%3DApplication" var="url" />
			<c:choose>
				<c:when test="${not empty tab and tab eq'resources'}">
					<li class="selected"><a href="${fn:escapeXml(url)}">Browse Resources</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${fn:escapeXml(url)}">Browse Resources</a></li>
				</c:otherwise>
			</c:choose>
			<spring:url value="/traces" var="url" />
			<c:choose>
				<c:when test="${not empty tab and tab eq'traces'}">
					<li class="selected"><a href="${fn:escapeXml(url)}">Recent Activity</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${fn:escapeXml(url)}">Recent Activity</a></li>
				</c:otherwise>
			</c:choose>
			<spring:url value="/config" var="url" />
			<c:choose>
				<c:when test="${not empty tab and tab eq'config'}">
					<li class="selected"><a href="${fn:escapeXml(url)}">Administration</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${fn:escapeXml(url)}">Administration</a></li>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty pageContext.request.userPrincipal}">
					<spring:url value="/logout" var="url" />
					<li class="logout"><a href="${fn:escapeXml(url)}">Logout</a></li>
			</c:if>
		</ul>
		<ul class="controls">
			<li class="control"><div id="error-button"></div></li>
			<li class="control"><div id="time-period"></div><div id="time-period-decrement"></div><div id="time-period-increment"></div></li>
		</ul>
	</div>
</c:if>

<c:if test="${empty devEdition or devEdition or (not devEdition and not empty pageContext.request.userPrincipal)}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			dojo.require("dijit.Menu");
			dojo.require("dijit.MenuItem");
			dojo.require("dijit.MenuSeparator");
			dojo.require("dijit.form.Button");
			dojo.require("dijit.form.DropDownButton");
			dojo.require("insight.components.ErrorButton");
			dojo.require("insight.components.MenuItemLink");
			dojo.require("insight.time");
			dojo.require("insight.time.TimeRangeDropDownButton");
	
			var button, decButton, incButton;
	
			button = new insight.time.TimeRangeDropDownButton({selected: Insight.getTimeRange().getDuration()}, "time-period");
			button.setLabelTimeRange(Insight.getTimeRange());
			button.startup();
			button.connect(button, "onChange", function(duration) {
				var range = Insight.getTimeRange();
				if (duration <= 0) {
					if (range.isAnchored()){
						Insight.shiftTimeRangeToNow();
					}
					else {
						Insight.pause();
					}
				}
				else {
					Insight.setTimeRange(range.changeDuration(duration));
				}
			});
			button.subscribe("Insight.timeRangePulse", button.setLabelTimeRange);
			button.subscribe("Insight.play", button.setLabelTimeRange);
			button.subscribe("Insight.pause", button.setLabelTimeRange);
	
			decButton = new dijit.form.Button({ iconClass: "decrementIcon", showLabel: false, label: "Decrement time range" }, "time-period-decrement");
			decButton.startup();
			decButton.connect(decButton, "onClick", function() {
				Insight.decrementTimeRange();
			});
	
			incButton = new dijit.form.Button({ iconClass: "incrementIcon", showLabel: false, label: "Increment time range" }, "time-period-increment");
			incButton.startup();
			incButton.connect(incButton, "onClick", function() {
				Insight.incrementTimeRange();
			});
			
			var errorButton = new insight.components.ErrorButton({
				label: "Error details",
				iconClass: "errorIcon",
				showLabel: false
			}, "error-button");
			errorButton.connect(errorButton, "onClick", function() {
				var dialog = dijit.byId("errors");
				if (dialog) {
					dialog.show();
				}
			});
			errorButton.subscribe("insight/error", function(message, code, uid, minor) {
				if (!minor) {
					this.attr("hidden", false);
				}
			});
			errorButton.subscribe("insight/error/pause", function() {
				this.attr("iconClass", "errorPausedIcon");
			});
			errorButton.subscribe("insight/error/play", function() {
				this.attr("iconClass", "errorIcon");
			});
			errorButton.subscribe("insight/error/close", function() {
				this.attr("hidden", true);
			});
			errorButton.startup();
		});
	</script>
</c:if>
