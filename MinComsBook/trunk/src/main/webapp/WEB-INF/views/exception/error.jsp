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
  <body>    
    <h2>error:${exception.message}</h2>
    ${ajaxExceptionMessage}
  </body>
</html>
      