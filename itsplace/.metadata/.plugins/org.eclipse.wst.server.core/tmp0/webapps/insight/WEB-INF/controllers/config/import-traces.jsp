<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div class="page-module">
	<div class="page-module-header">
		<h2>Import Trace</h2>
	</div>
	<div class="page-module-body">
		<c:if test="${not empty param.error}">
			<div class="error">Unable to import trace. See log for errors.</div>
		</c:if>
		<c:url value="/services/config/import" var="url" />
		<form action="${fn:escapeXml(url)}" method="post" enctype="multipart/form-data">
			<table class="dl" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>Trace File</td>
					<td>
						<input type="file" name="traceFile" />
					</td>
				</tr>
				<tr class="nolabel">
					<td>&nbsp;</td>
					<td>
						<input type="submit" value="Import" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
