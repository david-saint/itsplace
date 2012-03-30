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
		<h2>General Statistics</h2>
		<insight:admin>
			<ul class="controls">
				<li class="control"><span class="statsUpdateButton">[update]</span></li>
			</ul>
		</insight:admin>
	</div>
	<div class="page-module-body table-body" style="overflow-x: scroll">
		<table class="zebra" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="sort-field sort-field-name">Name</th>
					<th class="sort-field sort-field-count numeric">Count</th>
					<th class="sort-field sort-field-notes">Notes</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${stats}" var="stat">
					<tr>
						<td>${fn:escapeXml(stat.name)}</td>
						<td class="numeric"><fmt:formatNumber value="${stat.count}" /></td>
						<td>${fn:escapeXml(stat.notes)}</td>
					</tr>
				</c:forEach>
			</tbody>
			<c:if test="${empty stats}">
				<tr>
					<td colspan="3">no stats available</td>
				</tr>
			</c:if>
		</table>
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.query(".statsUpdateButton", this.domNode).forEach(function(buttonNode) {
					new dijit.form.Button({
								label: "Update",
								onClick: function() {
									var table = dijit.byId("config-body");
									table.load({force: "true"})
								}
							}, buttonNode);
				}, this);
			});
		</script>
	</div>
</div>
