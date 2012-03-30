<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty timeZone}">
	<fmt:setTimeZone value="${timeZone}" />
</c:if>

<div id="footer">
	<hr />
	<p>
		Spring Insight <span id="version-number">${fn:escapeXml(versionNumber)}<c:if test="${buildTimeStamp ne '${buildTimeStamp}'}"> (${fn:escapeXml(buildTimeStamp)})</c:if></span>
		| Copyright &copy; 2009<c:if test="${buildTimeStamp ne '${buildTimeStamp}'}">-${fn:escapeXml(fn:substring(buildTimeStamp, 0, 4))}</c:if> SpringSource, a division of <a href="http://www.vmware.com/" target="_blank">VMware, Inc.</a>
		| All Rights Reserved.
	</p>
</div>
