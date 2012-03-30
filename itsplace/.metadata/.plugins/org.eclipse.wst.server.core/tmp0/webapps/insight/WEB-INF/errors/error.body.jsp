<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div class="page-module">
	<div class="page-module-header">
		<h2>
			${fn:escapeXml(requestScope['javax.servlet.error.status_code'])}
			<c:if test="${not empty requestScope['javax.servlet.error.message']}">
				- ${fn:escapeXml(requestScope['javax.servlet.error.message'])}
			</c:if>
		</h2>
	</div>
	<div class="page-module-body">
		<c:choose>
			<c:when test="${requestScope['javax.servlet.error.status_code'] eq '403' and empty devEdition}">
				Access to the Insight Dashboard is restricted to localhost
			</c:when>
			<c:otherwise>
				Sorry, this error page is boring.  Did you expect a whale or rainbow?
			</c:otherwise>
		</c:choose>
	</div>
</div>
