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

<c:if test="${not empty resource.properties}">
	<spring:eval var="propertyEntries" expression="resource.properties.getProperties(true)" />
	<c:if test="${not empty propertyEntries}">
		<h2>Properties</h2>
		<table class="dl" border="0" cellpadding="0" cellspacing="0">
			<c:forEach items="${propertyEntries}" var="property">
				<tr>
					<td>${fn:escapeXml(property.key)}</td>
					<td>${fn:escapeXml(property.value.value)}</td>
				</tr>
			</c:forEach> 
		</table>
	</c:if>
	<spring:eval var="propertyEntries" expression="resource.properties.getProperties(false)" />
	<c:if test="${not empty propertyEntries}">
		<div class="blind-closed" style="display: none;">
			<h2>All Properties</h2>
			<table class="dl" border="0" cellpadding="0" cellspacing="0">
				<c:forEach items="${propertyEntries}" var="property">
					<tr>
						<td>${fn:escapeXml(property.key)}</td>
						<td>${fn:escapeXml(property.value.value)}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dojo.query(".blind-closed", self.domNode).forEach(function(node) {
				dojo.require("dijit.TitlePane");
				var title = dojo.query("h2", node)[0];
				var pane = new dijit.TitlePane({
					title: title.innerHTML,
					open: false
				}, node);
				title.parentNode.removeChild(title);
				dojo.addClass(pane.domNode, "all-properties");
			}, this);
		});
	</script>
</c:if>
