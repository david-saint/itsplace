<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<h2>Method Call</h2>

<table class="dl" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>Label</td>
		<td>${fn:escapeXml(operation.label)}</td>
	</tr>
	<c:if test="${not empty operation.arguments}">
		<tr>
			<td>Arguments</td>
			<td>
				<ol>
					<c:forEach items="${operation.arguments}" var="argument">
						<li>${fn:escapeXml(argument)}</li>
					</c:forEach>
				</ol>
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty operation.returnValue}">
		<tr>
			<td>Return Value</td>
			<td>${fn:escapeXml(operation.returnValue)}</td>
		</tr>
	</c:if>
	<c:if test="${not empty operation.exception}">
		<tr>
			<td>Exception</td>
			<td>${fn:escapeXml(operation.exception)}</td>
		</tr>
	</c:if>
</table>

<c:if test="${not empty operation.sourceCodeLocation}">
	<h2>Source Code Location</h2>
	<table class="dl" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>Class Name</td>
			<td>${fn:escapeXml(operation.sourceCodeLocation.className)}</td>
		</tr>
		<tr>
			<td>Method Name</td>
			<td>${fn:escapeXml(operation.sourceCodeLocation.methodName)}</td>
		</tr>
	</table>
</c:if>
