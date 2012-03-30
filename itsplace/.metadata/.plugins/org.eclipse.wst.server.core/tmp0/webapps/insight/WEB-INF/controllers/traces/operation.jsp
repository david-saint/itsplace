<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="insight" uri="http://static.springsource.com/tags/insight" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<c:catch>
	<c:if test="${not empty operation.sourceCodeLocation and not empty cookie['com.springsource.sts.run.embedded']}">
		<spring:url value="sts://openJavaElement(/{contextPath},{className}/{methodName}/{lineNumber})" var="url">
			<spring:param name="contextPath" value="${application}" />
			<spring:param name="className" value="${operation.sourceCodeLocation.className}" />
			<spring:param name="methodName" value="${operation.sourceCodeLocation.methodName}" />
			<spring:param name="lineNumber" value="${operation.sourceCodeLocation.lineNumber}" />
		</spring:url>
		<a href="${fn:escapeXml(url)}" class="sts-link" title="${fn:escapeXml(operation.sourceCodeLocation.className)}#${fn:escapeXml(operation.sourceCodeLocation.methodName)}">Go to Source</a>
	</c:if>
</c:catch>

<insight:render view="operation.${operation.type.name}">
	<%-- fall back to the simple view --%>
	<tiles:insertDefinition name="operation.simple" />
</insight:render>