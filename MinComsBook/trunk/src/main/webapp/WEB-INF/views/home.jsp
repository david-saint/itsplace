<%@ page  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglib.jsp" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Books API Example</title>
    <script type="text/javascript">
 	$(document).ready(function(){
 		
    });
	</script>      
  </head>
  <body>    <a href="<c:url value="${context}/logout" />" > Logout</a>
  <c:if test="${applicationScope.AppMode=='Development'}">ddddddd</c:if>
 ${applicationScope.AppMode}
    <%-- <div><spring:message  code="save"/></div> --%>
  </body>
</html>
      