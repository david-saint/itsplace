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
		<h2>Purge Data</h2>
	</div>
	<div class="page-module-body">
		<c:choose>
			<c:when test="${empty purge}">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="right"><span class="purgeAllButton">[Purge All]</span></td>
						<td>Delete all data stored by Insight</td>
					</tr>
					<c:if test="${lastFull != null}">
						<tr>
							<td></td>
							<td>Last purge of all data: <fmt:formatDate value="${lastFull}" type="both" dateStyle="medium"/></td>
						</tr>
					</c:if>
					<c:if test="${lastIncremental != null}">
						<tr>
							<td></td>
							<td>Last purge of expired data: <fmt:formatDate value="${lastIncremental}" type="both" dateStyle="medium"/></td>
						</tr>
					</c:if>
				</table>
				<script type="text/javascript">
					dojo.addOnLoad(function(){
						dojo.query(".purgeAllButton", this.domNode).forEach(function(buttonNode) {
							new dijit.form.Button({
								label: "Purge Data",
								onClick: function() {
									if (confirm("Delete all data collected by Spring Insight?")) {
										self.load({ scope: "ALL_DATA", "_method": "delete" }, "post");
									}
								}
							}, buttonNode);
						}, this);
					});
				</script>
			</c:when>
			<c:otherwise>
				Purging: ${purge}
				<script type="text/javascript">
					dojo.addOnLoad(function(){
						self.delayedLoad(1000);
					});                                                                                                        
				</script>
			</c:otherwise>
		</c:choose>
	</div>
</div>
