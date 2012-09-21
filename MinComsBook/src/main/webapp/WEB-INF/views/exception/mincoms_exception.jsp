<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Exception</title>
    <script type="text/javascript">
 	$(document).ready(function(){
 		
    });
	</script>      
  </head>
  <body>    
    <h2><div id="error">심플${exception.message}</div></h2>
    <error>${ajaxExceptionMessage}</error>
    <div>${ajaxExceptionMessage}</div>
  </body>
</html>
      